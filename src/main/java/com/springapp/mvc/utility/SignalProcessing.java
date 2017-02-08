package com.springapp.mvc.utility;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by Sehan Rathnayake on 17/01/13.
 */
public class SignalProcessing {

    private static ArrayList<double[]> chassiSignalFull;
    private static ArrayList<double[]> chassiSignalVertical;

    private static ArrayList<double[]> axcelSignalFull;
    private static ArrayList<double[]> axcelSignalVertical;

    private static ArrayList<double[]> axcelFrequencySpectrum;
    private static ArrayList<double[]> chassiFrequencySpectrum;

    public static void main(String[] args) {
        String url = "C:\\Users\\Sehan Rathnayake\\Desktop\\New folder (2)\\Data\\test.xlsx";
        chassiSignalFull = getSignal(url, "chassi");
        axcelSignalFull = getSignal(url, "axcel");

        chassiSignalVertical = calibrate(chassiSignalFull);
        axcelSignalVertical = calibrate(axcelSignalFull);

        double chassiSampleRate = getSampleRate(chassiSignalVertical);
        double axcelSampleRate = getSampleRate(axcelSignalVertical);

        writeToExcel(url, "axcel Vertical", axcelSignalVertical);
        writeToExcel(url, "chassi Vertical", chassiSignalVertical);


        axcelSignalVertical = lowPassFilter(axcelSignalVertical, 20, axcelSampleRate);
        chassiSignalVertical = lowPassFilter(chassiSignalVertical, 20, chassiSampleRate);

        writeToExcel(url, "axcel filtered", axcelSignalVertical);
        writeToExcel(url, "chassi filtered", chassiSignalVertical);

        axcelFrequencySpectrum = fourierTransform(axcelSignalVertical, 10, axcelSampleRate);
        chassiFrequencySpectrum = fourierTransform(chassiSignalVertical, 10, chassiSampleRate);

        writeToExcel(url, "axcel frequency", axcelFrequencySpectrum);
        writeToExcel(url, "chassi frequency", chassiFrequencySpectrum);


    }

    private static ArrayList<double[]> getSignal(String url, String sheet) {
        File initialFile = new File(url);
        InputStream targetStream = null;
        ArrayList<double[]> signal = new ArrayList<double[]>();
        try {

            targetStream = new FileInputStream(initialFile);

            XSSFWorkbook workbook = new XSSFWorkbook(targetStream);

            XSSFSheet inputSheet = workbook.getSheet(sheet);
            Iterator<Row> rowIteratorRead = inputSheet.iterator();

            Row rowRead;
            double[] temp;
            while (rowIteratorRead.hasNext()) {
                rowRead = rowIteratorRead.next();
                temp = new double[]{rowRead.getCell(3).getNumericCellValue(), rowRead.getCell(0).getNumericCellValue(), rowRead.getCell(1).getNumericCellValue(), rowRead.getCell(2).getNumericCellValue()};
                signal.add(temp);
            }
            workbook.close();
            targetStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return signal;
    }

    private static void writeToExcel(String url, String sheetName, ArrayList<double[]> data) {
        File initialFile = new File(url);
        InputStream targetStream = null;
        ArrayList<double[]> signal = new ArrayList<double[]>();
        try {

            targetStream = new FileInputStream(initialFile);

            XSSFWorkbook workbook = new XSSFWorkbook(targetStream);
            XSSFSheet sheet = workbook.createSheet(sheetName);
            Iterator<double[]> iterator = data.iterator();
            double[] values;
            int i = 0;
            Row row;
            while (iterator.hasNext()) {
                values = iterator.next();
                row = sheet.createRow(i);
                for (int j = 0; j < values.length; j++) {
                    row.createCell(j).setCellValue(values[j]);
                }
                i++;
            }
            targetStream.close();
            FileOutputStream outputStream = new FileOutputStream(new File(url));
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<double[]> calibrate(ArrayList<double[]> signalFull) {
        int distance = 1000;

        double maxVariationX = 0;
        double maxVariationY = 0;
        double maxVariationZ = 0;
        double minVariation = Double.MAX_VALUE;

        double avgX = 0;
        double avgY = 0;
        double avgZ = 0;

        double avgXstill = 0;
        double avgYstill = 0;
        double avgZstill = 0;

        double cosX, cosY, cosZ;

        double valueX = 0, valueY = 0, valueZ = 0;

        double temp;
        Iterator<double[]> iterator = signalFull.iterator();

        boolean flag = true;
        while (iterator.hasNext()) {

            for (int j = 0; j < distance; j++) {
                if (!iterator.hasNext()) {
                    flag = false;
                    break;
                }
                double[] values = iterator.next();
                if (j == 0) {
                    valueX = values[1];
                    valueY = values[2];
                    valueZ = values[3];
                } else {
                    temp = values[1];
                    if (abs(temp - valueX) > maxVariationX) maxVariationX = abs(temp - valueX);
                    valueX = temp;

                    temp = values[2];
                    if (abs(temp - valueY) > maxVariationY) maxVariationY = abs(temp - valueX);
                    valueY = temp;

                    temp = values[3];
                    if (abs(temp - valueZ) > maxVariationZ) maxVariationZ = abs(temp - valueX);
                    valueZ = temp;
                }
                avgX += valueX;
                avgY += valueY;
                avgZ += valueZ;
            }
            avgX = avgX / distance;
            avgY = avgY / distance;
            avgZ = avgZ / distance;

            if (flag && minVariation > maxVariationX + maxVariationY + maxVariationZ) {
                minVariation = maxVariationX + maxVariationY + maxVariationZ;
                avgXstill = avgX;
                avgYstill = avgY;
                avgZstill = avgZ;
            }
        }

        double g = sqrt(avgXstill * avgXstill + avgYstill * avgYstill + avgZstill * avgZstill);
        double error = g - 16384;
        error = error / g;

        cosX = avgXstill / g;
        cosY = avgYstill / g;
        cosZ = avgZstill / g;

        Iterator<double[]> iteratorFullSignal = signalFull.iterator();
        ArrayList<double[]> signal = new ArrayList<double[]>();
        while (iteratorFullSignal.hasNext()) {
            double[] values = iteratorFullSignal.next();
            valueX = values[1];
            valueY = values[2];
            valueZ = values[3];
            double finalValue = valueX * cosX + valueY * cosY + valueZ * cosZ;
            signal.add(new double[]{values[0], finalValue - g});
        }

        return signal;


    }

    public static double getSampleRate(ArrayList<double[]> signal) {
        double startTime = signal.get(0)[0];
        int noOFSamples = signal.size();
        double endTime = signal.get(noOFSamples - 1)[0];
        return (noOFSamples * 1000 / (endTime - startTime));
    }

    public static int findShockStartPoint(ArrayList<double[]> signal) {
        int shockStartPoint = 0;
        Iterator<double[]> iterator = signal.iterator();

        double maxVariation = 0;
        int i = 0;
        double[] values;
        while (iterator.hasNext()) {
            values = iterator.next();
            if (abs(values[1]) > maxVariation) {
                maxVariation = abs(values[1]);
                shockStartPoint = i;
            }
            i++;
        }
        return shockStartPoint;
    }

    public static ArrayList<double[]> lowPassFilter(ArrayList<double[]> signal, double cutOffFrequency, double sampleRate) {
        double rc = 1 / (2 * Math.PI * cutOffFrequency);
        double dt = 1 / sampleRate;
        double alpha = dt / (dt + rc);
        ArrayList<double[]> filteredSignal = new ArrayList<double[]>();
        Iterator<double[]> iterator = signal.iterator();
        double[] current = iterator.next();
        filteredSignal.add(current);

        double[] prev = current;
        double[] newValue;
        while (iterator.hasNext()) {
            current = iterator.next();
            newValue = new double[]{current[0], prev[1] + alpha * (current[1] - prev[1])};
            prev = newValue;
            filteredSignal.add(newValue);
        }

        return filteredSignal;

    }

    public static ArrayList<double[]> fourierTransform(ArrayList<double[]> signal, double time, double sampleRate) {
        double value;
        int i = 0;
        int n = (int) (sampleRate * time);
//   double T = n/sampleRate;
        // float sample_rate = n / T;
        //Calculate the number of equidistant points in time
        //   int n = (int) (T * sample_rate) / 2;
//Calculate the time interval at each equidistant point

        double h = (time / n);
        double x[] = new double[n];

        Iterator<double[]> iterator = signal.iterator();

        while (iterator.hasNext()) {
            value = iterator.next()[1];
            x[i] = value;
            if (i == n - 1) break;
            i++;

        }
        double f[] = new double[n / 2];
        ArrayList<double[]> frequencySpectrum = new ArrayList<double[]>();

        for (int j = 0; j < n / 2; j++) {

            double firstSummation = 0;
            double secondSummation = 0;

            for (int k = 0; k < n; k++) {
                double twoPInjk = ((2 * Math.PI) / n) * (j * k);
                firstSummation += x[k] * Math.cos(twoPInjk);
                secondSummation += x[k] * Math.sin(twoPInjk);
            }

            f[j] = abs(Math.sqrt(Math.pow(firstSummation, 2) +
                    Math.pow(secondSummation, 2)));

            double amplitude = 2 * f[j] / n;
            double frequency = j * h / time * sampleRate;
            //  System.out.println("frequency = " + frequency + ", amp = " + amplitude);
            // System.out.println(frequency + "," + amplitude);
            frequencySpectrum.add(new double[]{frequency, amplitude});
        }
        return frequencySpectrum;

    }

}