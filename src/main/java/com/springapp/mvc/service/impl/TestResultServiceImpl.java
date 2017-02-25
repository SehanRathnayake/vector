package com.springapp.mvc.service.impl;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.springapp.mvc.dao.JobDao;
import com.springapp.mvc.dao.SubJobDao;
import com.springapp.mvc.dao.TestResultJdbcDao;
import com.springapp.mvc.dao.UserDao;
import com.springapp.mvc.dto.SuspensionTestResults;
import com.springapp.mvc.service.TestResultService;
import com.springapp.mvc.utility.CurveFitting;
import com.springapp.mvc.utility.ExcelManipulations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.springapp.mvc.utility.ExcelManipulations.*;
import static com.springapp.mvc.utility.SignalProcessing.*;

/**
 * Created by Sehan Rathnayake on 17/02/21.
 */
@Service
public class TestResultServiceImpl implements TestResultService {


    @Autowired
    private JobDao jobDao;


    @Autowired
    private UserDao userDao;

    @Autowired
    private SubJobDao subJobDao;

    @Autowired
    private TestResultJdbcDao testResultJdbcDao;

    private static String baseUrl = "D:\\Vector Data\\";

    private ArrayList<double[]> chassiSignalFull;
    private ArrayList<double[]> chassiSignalVertical;

    private ArrayList<double[]> axcelSignalFull;
    private ArrayList<double[]> axcelSignalVertical;

    private ArrayList<double[]> axcelSignalClipped;
    private ArrayList<double[]> differenceSignalClipped;
    private ArrayList<double[]> chassiSignalClipped;

    private ArrayList<double[]> chassiSignalFiltered;
    private ArrayList<double[]> axcelSignalFiltered;

    private ArrayList<double[]> chassiSignalForCurveFitting;
    private ArrayList<double[]> axcelSignalForRMS;
    private ArrayList<double[]> chassiSignalForRMS;

    private double chassiSampleRate;
    private double axcelSampleRate;

    private int chassiPeakPoint;
    private int axcelPeakPoint;

    private double chassiPeakValue;
    private double axcelPeakValue;

    private double chassiRMSvalue;
    private double axcelRMSvalue;

    private double distarbanceTime;

    private static int clipingWindowLeft = 400;
    private static int clipingWindowRight = 800;

    private static int stabilityWindow = 20;
    private static double maxAccStability = 0.1;
    private double cutOffFrequency = 10;

    private static double minAccShockStartingPoint = -1.0;

    private boolean writeToexcel = true;

    private ArrayList<double[]> axcelFrequencySpectrum;
    private ArrayList<double[]> chassiFrequencySpectrum;

    public static void main(String[] args) {
        SuspensionTestResults s = new TestResultServiceImpl().getResults("sehan", "corolla", "job1", "Rear Left");

    }

    public SuspensionTestResults getResults(long subJobID) {
        String customer = "sehan";
        String vehicle = "corolla";
        String job = "job1";
        String wheel = "Rear Left";

        SuspensionTestResults suspensionTestResults = getResults(customer, vehicle, job, wheel);
        testResultJdbcDao.saveTestResults(suspensionTestResults, subJobID);
        return suspensionTestResults;
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

        // chassiSampleRate = getSampleRate(chassiSignalVertical);
        //axcelSignalVertical = getResampledSignal(axcelSignalVertical, chassiSignalVertical);

        axcelSignalVertical = getResampledSignal(axcelSignalVertical, 3);
        chassiSignalVertical = getResampledSignal(chassiSignalVertical, 3);

        chassiSampleRate = getSampleRate(chassiSignalVertical);

        double[] chassiShockStartPoint = getShockStartPoint(chassiSignalVertical, minAccShockStartingPoint, stabilityWindow, 2 * maxAccStability);
        double[] axcelShockStartPoint = getShockStartPoint(axcelSignalVertical, minAccShockStartingPoint, stabilityWindow, 2 * maxAccStability);

        int chassiShockStartPointIndex = (int) chassiShockStartPoint[0];
        int axcelShockStartPointIndex = (int) axcelShockStartPoint[0];


//TODO set range
        if (chassiShockStartPointIndex > axcelShockStartPointIndex) {
            int gap = chassiShockStartPointIndex - axcelShockStartPointIndex;
            chassiSignalVertical = new ArrayList<double[]>(chassiSignalVertical.subList(gap, chassiSignalVertical.size()));
            double startTime = chassiSignalVertical.get(0)[0];
            chassiSignalVertical = changeStartingTime(chassiSignalVertical, startTime);

        } else if (chassiShockStartPointIndex < axcelShockStartPointIndex) {
            int gap = axcelShockStartPointIndex - chassiShockStartPointIndex;
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


        double[] shockStartPoint = getShockStartPoint(chassiSignalClipped, minAccShockStartingPoint, stabilityWindow, 2 * maxAccStability);
        double shockStartTime = shockStartPoint[1];
        int shockStartIndex = (int) shockStartPoint[0];
        axcelSignalForRMS = new ArrayList<double[]>(axcelSignalClipped.subList(shockStartIndex, (int) stablePoint[0]+curveFittingStartPoint));
        chassiSignalForRMS = new ArrayList<double[]>(chassiSignalClipped.subList(shockStartIndex, (int) stablePoint[0]+curveFittingStartPoint));

        axcelRMSvalue = getRMS(axcelSignalClipped);
        chassiRMSvalue = getRMS(chassiSignalClipped);

        distarbanceTime = stablePoint[1] - shockStartTime;

        SuspensionTestResults suspensionTestResults = new SuspensionTestResults();
        try {
            suspensionTestResults = CurveFitting.getDampedSineCurve(chassiSignalForCurveFitting, (int) stablePoint[0], chassiSampleRate);
        } catch (Exception eaz) {
            System.out.println(eaz);
        }


        chassiFrequencySpectrum = fourierTransform(chassiSignalClipped, chassiSampleRate);

        suspensionTestResults.setAxcelSignalClipped(axcelSignalClipped);
        suspensionTestResults.setChassiSignalClipped(chassiSignalClipped);
        suspensionTestResults.setDifferenceSignal(differenceSignalClipped);
        suspensionTestResults.setChassiSignalFiltered(chassiSignalFiltered);
        suspensionTestResults.setChassiFrequencySpectrum(chassiFrequencySpectrum);
        suspensionTestResults.setDisturbanceTime(distarbanceTime);
        suspensionTestResults.setAxcelRMSvalue(axcelRMSvalue);
        suspensionTestResults.setChassiRMSvalue(chassiRMSvalue);
        suspensionTestResults.setAxcelPeakValue(axcelPeakValue);
        suspensionTestResults.setChassiPeakValue(chassiPeakValue);

        if (writeToexcel) {
            saveResultsToExcel(suspensionTestResults, excelUrl);
            writeToExcel(excelUrl,"chassy rms",chassiSignalForRMS);
            writeToExcel(excelUrl,"axcel rms",axcelSignalForRMS);
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
        map.put("axcel Rms",suspensionTestResults.getAxcelRMSvalue());
        map.put("chassi Rms",suspensionTestResults.getChassiRMSvalue());
        map.put("disturbance time",suspensionTestResults.getDisturbanceTime());


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

    public SuspensionTestResults getPastResults(long subJobId) {


        return testResultJdbcDao.getTestResults(subJobId);
    }

}
