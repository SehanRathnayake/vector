package com.springapp.mvc.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.springapp.mvc.dto.SuspensionTestResults;
import com.springapp.mvc.utility.CurveFitting;
import com.springapp.mvc.utility.ExcelManipulations;

import static com.springapp.mvc.utility.ExcelManipulations.*;
import static com.springapp.mvc.utility.SignalProcessing.*;

/**
 * Created by Sehan Rathnayake on 17/02/21.
 */
public class TestResultServiceImpl {

    private static String baseUrl = "D:\\Vector Data\\";

    private ArrayList<double[]> chassiSignalFull;
    private ArrayList<double[]> chassiSignalVertical;

    private ArrayList<double[]> axcelSignalFull;
    private ArrayList<double[]> axcelSignalVertical;

    private ArrayList<double[]> axcelSignalClipped;
    private ArrayList<double[]> differenceSignalClipped;
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
    private double cutOffFrequency = 10;

    private static double minAccShockStartingPoint = -1.0;

    private boolean writeToexcel = false;

    private ArrayList<double[]> axcelFrequencySpectrum;
    private ArrayList<double[]> chassiFrequencySpectrum;

    public static void main(String[] args) {
        // SuspensionTestResults s = new TestResultServiceImpl().getResults("sehan", "corolla", "job1", "Rear Left");
        String customer = "sehan";
        String vehicle = "corolla";
        String job = "job1";
        String wheel = "Rear Left";


        String url = baseUrl + customer + "\\" + vehicle + "\\" + job + "\\" + wheel + "\\";
        String excelFileName = customer + "-" + vehicle + "-" + job + "-" + wheel + ".xlsx";
        String excelUrl = url + excelFileName;

        insertToSpreadsheet(url, excelFileName);
        ArrayList<double[]> chassiSignalFull = getSignal(excelUrl, "chassi");
        ArrayList<double[]> axcelSignalFull = getSignal(excelUrl, "axcel");

        String chassiData = url + "\\2-datalog.txt";
        String axcelData = url + "\\1-datalog.txt";
        chassiSignalFull = getSignalFromText(chassiData);
        axcelSignalFull = getSignalFromText(axcelData);

        ArrayList<double[]> axcelSignalVertical = calibrate(axcelSignalFull);
        ArrayList<double[]> chassiSignalVertical = calibrate(chassiSignalFull);

        writeToExcel(excelUrl, "without resample", axcelSignalVertical);
        writeToExcel(excelUrl, "resample", getResampledSignal(axcelSignalVertical));
    }

    public SuspensionTestResults getResults(String customer, String vehicle, String job, String wheel) {
        String url = baseUrl + customer + "\\" + vehicle + "\\" + job + "\\" + wheel + "\\";
        String excelFileName = customer + "-" + vehicle + "-" + job + "-" + wheel + ".xlsx";
        String excelUrl = url + excelFileName;
        if (writeToexcel) {
            insertToSpreadsheet(url, excelFileName);
            chassiSignalFull = getSignal(excelUrl, "chassi");
            axcelSignalFull = getSignal(excelUrl, "axcel");
        } else {
            String chassiData = url + "\\2-datalog.txt";
            String axcelData = url + "\\1-datalog.txt";
            chassiSignalFull = getSignalFromText(chassiData);
            axcelSignalFull = getSignalFromText(axcelData);
        }

        axcelSignalVertical = calibrate(axcelSignalFull);
        chassiSignalVertical = calibrate(chassiSignalFull);

        chassiSampleRate = getSampleRate(chassiSignalVertical);
        axcelSignalVertical = getResampledSignal(axcelSignalVertical, chassiSignalVertical);

        axcelSignalVertical = getResampledSignal(axcelSignalVertical);
        chassiSignalVertical = getResampledSignal(chassiSignalVertical);

        int chassiShockStartPoint = getShockStartPoint(chassiSignalVertical, minAccShockStartingPoint, stabilityWindow, 2 * maxAccStability);
        int axcelShockStartPoint = getShockStartPoint(axcelSignalVertical, minAccShockStartingPoint, stabilityWindow, 2 * maxAccStability);

//TODO set range
        if (chassiShockStartPoint > axcelShockStartPoint) {
            int gap = chassiShockStartPoint - axcelShockStartPoint;
            chassiSignalVertical = new ArrayList<double[]>(chassiSignalVertical.subList(gap, chassiSignalVertical.size()));
            double startTime = chassiSignalVertical.get(0)[0];
            chassiSignalVertical = changeStartingTime(chassiSignalVertical, startTime);
        } else if (chassiShockStartPoint < axcelShockStartPoint) {
            int gap = axcelShockStartPoint - chassiShockStartPoint;
            axcelSignalVertical = new ArrayList<double[]>(axcelSignalVertical.subList(gap, axcelSignalVertical.size()));
            double startTime = axcelSignalVertical.get(0)[0];
            axcelSignalVertical = changeStartingTime(axcelSignalVertical, startTime);
        }


        double[] peakPoint = findPeakPoint(axcelSignalVertical);
        axcelPeakPoint = (int) peakPoint[0];
        axcelPeakValue = peakPoint[1];

        peakPoint = findPeakPoint(chassiSignalVertical);
        chassiPeakPoint = (int) peakPoint[0];
        chassiPeakValue = peakPoint[1];


        axcelSignalClipped = new ArrayList<double[]>(axcelSignalVertical.subList(axcelPeakPoint - clipingWindowLeft, axcelPeakPoint + clipingWindowRight));
        chassiSignalClipped = new ArrayList<double[]>(chassiSignalVertical.subList(axcelPeakPoint - clipingWindowLeft, axcelPeakPoint + clipingWindowRight));

        differenceSignalClipped = getDifferenceSignal(axcelSignalClipped, chassiSignalClipped);

        chassiSignalFiltered = lowPassFilter(chassiSignalClipped, cutOffFrequency, chassiSampleRate);

        peakPoint = findPositivePeakPoint(chassiSignalFiltered);
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

        if (writeToexcel) {
            saveResultsToExcel(suspensionTestResults, excelUrl);
        }

        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream(url + "results.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(suspensionTestResults);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return suspensionTestResults;
    }

    public static void saveResultsToExcel(SuspensionTestResults suspensionTestResults, String url) {

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

    public SuspensionTestResults getPastResults(String customer, String vehicle, String job, String wheel) {
        String url = baseUrl + customer + "\\" + vehicle + "\\" + job + "\\" + wheel + "\\" + "results.ser";
        FileInputStream f_in = null;
        SuspensionTestResults suspensionTestResults = null;
        try {
            f_in = new FileInputStream(url);
            ObjectInputStream obj_in =
                    new ObjectInputStream(f_in);

            Object obj = obj_in.readObject();
            suspensionTestResults = (SuspensionTestResults) obj;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return suspensionTestResults;
    }


}
