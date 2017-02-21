package com.springapp.mvc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.springapp.mvc.dto.SuspensionTestResults;
import com.springapp.mvc.utility.CurveFitting;
import com.springapp.mvc.utility.ExcelManipulations;

import static com.springapp.mvc.utility.ExcelManipulations.getSignal;
import static com.springapp.mvc.utility.ExcelManipulations.insertToSpreadsheet;
import static com.springapp.mvc.utility.ExcelManipulations.writeToExcel;
import static com.springapp.mvc.utility.SignalProcessing.*;

/**
 * Created by Sehan Rathnayake on 17/02/21.
 */
public class TestResultServiceImpl {

    private static String baseUrl = "C:\\Users\\Sehan Rathnayake\\Desktop\\New folder (2)\\Data engine off\\Vector Data\\Data\\";

    private ArrayList<double[]> chassiSignalFull;
    private ArrayList<double[]> chassiSignalVertical;

    private ArrayList<double[]> axcelSignalFull;
    private ArrayList<double[]> axcelSignalVertical;

    private ArrayList<double[]> axcelSignalClipped;
    private ArrayList<double[]> chassiSignalClipped;

    private ArrayList<double[]> chassiSignalFiltered;

    private ArrayList<double[]> chassiSignalForCurveFitting;

    private double chassiSampleRate;
    private double axcelSampleRate;

    private int chassiPeakPoint;
    private int axcelPeakPoint;

    private double chassiPeakValue;
    private double axcelPeakValue;

    private static int clipingWindowLeft = 400;
    private static int clipingWindowRight = 800;

    private static int stabilityWindow = 20;
    private static double maxAccStability = 0.1;
    private static double cutOffFrequency = 2;


    private ArrayList<double[]> axcelFrequencySpectrum;
    private ArrayList<double[]> chassiFrequencySpectrum;

    public static void main(String[] args) {
        new TestResultServiceImpl().getResults("sehan", "corolla", "job1", "Rear Left");
    }

    public void getResults(String customer, String vehicle, String job, String wheel) {
        String url = baseUrl + customer + "\\" + vehicle + "\\" + job + "\\" + wheel + "\\";
        String excelFileName = customer + "-" + vehicle + "-" + job + "-" + wheel + ".xlsx";
        insertToSpreadsheet(url, excelFileName);

        String excelUrl = url + excelFileName;
        chassiSignalFull = getSignal(excelUrl, "chassi");
        axcelSignalFull = getSignal(excelUrl, "axcel");

        axcelSignalVertical = calibrate(axcelSignalFull);
        chassiSignalVertical = calibrate(chassiSignalFull);

        chassiSampleRate = getSampleRate(chassiSignalVertical);
        axcelSignalVertical = getResampledSignal(axcelSignalVertical, chassiSignalVertical);

        double[] peakPoint = findPeakPoint(axcelSignalVertical);
        axcelPeakPoint = (int) peakPoint[0];
        axcelPeakValue = peakPoint[1];

        peakPoint = findPeakPoint(chassiSignalVertical);
        chassiPeakPoint = (int) peakPoint[0];
        chassiPeakValue = peakPoint[1];


        axcelSignalClipped = new ArrayList<double[]>(axcelSignalVertical.subList(axcelPeakPoint - clipingWindowLeft, axcelPeakPoint + clipingWindowRight));
        chassiSignalClipped = new ArrayList<double[]>(chassiSignalVertical.subList(axcelPeakPoint - clipingWindowLeft, axcelPeakPoint + clipingWindowRight));

        chassiSignalFiltered = lowPassFilter(chassiSignalClipped, cutOffFrequency, chassiSampleRate);

        peakPoint = findPeakPoint(chassiSignalFiltered);
        int curveFittingStartPoint = (int) peakPoint[0];

        chassiSignalForCurveFitting = new ArrayList<double[]>(chassiSignalFiltered.subList(curveFittingStartPoint, chassiSignalFiltered.size()));
        double[] stablePoint = getFirstStablePoint(chassiSignalForCurveFitting, stabilityWindow, maxAccStability);
        // chassiSignalForCurveFitting = new ArrayList<double[]>(chassiSignalForCurveFitting.subList(0, (int) stablePoint[0]));


        SuspensionTestResults suspensionTestResults = CurveFitting.getDampedSineCurve(chassiSignalForCurveFitting, (int) stablePoint[0], chassiSampleRate);
        chassiFrequencySpectrum = fourierTransform(chassiSignalClipped, chassiSampleRate);

        suspensionTestResults.setAxcelSignalClipped(axcelSignalClipped);
        suspensionTestResults.setChassiSignalClipped(chassiSignalClipped);
        suspensionTestResults.setChassiSignalFiltered(chassiSignalFiltered);
        suspensionTestResults.setChassiFrequencySpectrum(chassiFrequencySpectrum);

        suspensionTestResults.setAxcelPeakValue(axcelPeakValue);
        suspensionTestResults.setChassiPeakValue(chassiPeakValue);

        saveResults(suspensionTestResults, excelUrl);

    }

    public static void saveResults(SuspensionTestResults suspensionTestResults, String url) {

        writeToExcel(url, "axcel clipped", suspensionTestResults.getAxcelSignalClipped());
        writeToExcel(url, "chassi clipped", suspensionTestResults.getChassiSignalClipped());
        writeToExcel(url, "chassi filtered", suspensionTestResults.getChassiSignalFiltered());
        writeToExcel(url, "sine wave", suspensionTestResults.getSinewave());
        writeToExcel(url, "chassy frequency spectrum", suspensionTestResults.getChassiFrequencySpectrum());

        LinkedHashMap<String, Double> map = new LinkedHashMap<String, Double>();
        map.put("Damped Frequency", suspensionTestResults.getDampedFrequency());
        map.put("Natural Frequency", suspensionTestResults.getNaturalFrequency());
        map.put("Damping Factor", suspensionTestResults.getDampingFactor());
        map.put("Axcel Peak Acceleration", suspensionTestResults.getAxcelPeakValue());
        map.put("Chassi Peak Acceleration", suspensionTestResults.getChassiPeakValue());
        writeToExcel(url, "result", map);

    }

}
