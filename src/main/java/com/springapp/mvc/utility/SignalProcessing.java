package com.springapp.mvc.utility;

import com.springapp.mvc.dto.SuspensionTestResults;
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


    public static void main(String[] args) {


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
            signal.add(new double[]{values[0], (finalValue - g) * 9.8065 / g});
        }

        return signal;


    }

    public static double getSampleRate(ArrayList<double[]> signal) {
        double startTime = signal.get(0)[0];
        int noOFSamples = signal.size();
        double endTime = signal.get(noOFSamples - 1)[0];
        return (noOFSamples * 1000 / (endTime - startTime));
    }

    public static double[] findPeakPoint(ArrayList<double[]> signal) {
        int peakPoint = 0;
        Iterator<double[]> iterator = signal.iterator();

        double maxVariation = 0;
        int i = 0;
        double[] values;
        while (iterator.hasNext()) {
            values = iterator.next();
            if (abs(values[1]) > maxVariation) {
                maxVariation = abs(values[1]);
                peakPoint = i;
            }
            i++;
        }
        return new double[]{peakPoint, maxVariation};
    }

    public static double[] findPositivePeakPoint(ArrayList<double[]> signal) {
        int peakPoint = 0;
        Iterator<double[]> iterator = signal.iterator();

        double maxVariation = 0;
        int i = 0;
        double[] values;
        while (iterator.hasNext()) {
            values = iterator.next();
            if (values[1] > maxVariation) {
                maxVariation = values[1];
                peakPoint = i;
            }
            i++;
        }
        return new double[]{peakPoint, maxVariation};
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

    public static ArrayList<double[]> fourierTransform(ArrayList<double[]> signal, double sampleRate) {
        double value;
        int i = 0;
        //   int n = (int) (sampleRate * time);
        int n = signal.size();
        double time = (double) n / sampleRate;
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
            if (frequency <= 100.0) frequencySpectrum.add(new double[]{frequency, amplitude});
        }
        return frequencySpectrum;

    }

    private static ArrayList<double[]> getDisplacementSignal(ArrayList<double[]> accelerationSignal, double sampleRate) {
        double t = 1 / sampleRate;
        double displacement;
        ArrayList<double[]> displacementSignal = new ArrayList<double[]>();

        Iterator<double[]> iterator = accelerationSignal.iterator();
        double[] value = iterator.next();

        double veloPrev = 0;
        displacementSignal.add(new double[]{value[0], 0});

        while (iterator.hasNext()) {
            value = iterator.next();
            displacement = veloPrev * t + (value[1] * t * t) / 2;
            veloPrev = veloPrev + value[1] * t;
            displacementSignal.add(new double[]{value[0], displacement});
        }

        return displacementSignal;

    }

    public static ArrayList<double[]> getResampledSignal(ArrayList<double[]> signal) {
        ArrayList<double[]> resampledSignal = new ArrayList<double[]>();
        int timeGap = 3;

        Iterator<double[]> signalIterator = signal.iterator();

        double[] value;
        double[] nextvalue = signalIterator.next();
        value = nextvalue;
        double intrpolatedValue;

        int i = 0;
        int startTime = (int) signal.get(0)[0] + (timeGap - ((int) signal.get(0)[0] % timeGap));

        while (signalIterator.hasNext()) {
            while (nextvalue[0] <= startTime + i * timeGap && signalIterator.hasNext()) {
                value = nextvalue;
                nextvalue = signalIterator.next();

            }
            intrpolatedValue = value[1] + ((startTime + i * timeGap - value[0]) * (nextvalue[1] - value[1]) / (nextvalue[0] - value[0]));

            resampledSignal.add(new double[]{i * timeGap, intrpolatedValue});
            i++;

        }


        return resampledSignal;


    }

    public static ArrayList<double[]> changeStartingTime(ArrayList<double[]> signal, double time) {
        ArrayList<double[]> newSignal = new ArrayList<double[]>();
        double[] values;
        Iterator<double[]> signalIterator = signal.iterator();
        while (signalIterator.hasNext()) {

            values = signalIterator.next();
            newSignal.add(new double[]{values[0] - time, values[1]});
        }
        return newSignal;
    }

    public static ArrayList<double[]> getResampledSignal(ArrayList<double[]> signal, ArrayList<double[]> referenceSignal) {
        ArrayList<double[]> resampledSignal = new ArrayList<double[]>();

        Iterator<double[]> referenceIterator = referenceSignal.iterator();
        double[] refvalue = referenceIterator.next();
        Iterator<double[]> signalIterator = signal.iterator();

        double[] value;
        double[] nextvalue = signalIterator.next();
        value = nextvalue;
        double intrpolatedValue;

        while (referenceIterator.hasNext() && signalIterator.hasNext()) {
            while (nextvalue[0] <= refvalue[0] && signalIterator.hasNext()) {
                value = nextvalue;
                nextvalue = signalIterator.next();

            }
            intrpolatedValue = value[1] + ((refvalue[0] - value[0]) * (nextvalue[1] - value[1]) / (nextvalue[0] - value[0]));

            resampledSignal.add(new double[]{refvalue[0], intrpolatedValue});
            refvalue = referenceIterator.next();

        }


        return resampledSignal;


    }

    public static ArrayList<double[]> getDifferenceSignal(ArrayList<double[]> signal1, ArrayList<double[]> signal2) {
        ArrayList<double[]> difference = new ArrayList<double[]>();

        Iterator<double[]> iterator1 = signal1.iterator();

        Iterator<double[]> iterator2 = signal2.iterator();
        double[] value1, value2;

        while (iterator1.hasNext() && iterator2.hasNext()) {
            value1 = iterator1.next();
            value2 = iterator2.next();
            difference.add(new double[]{value1[0], value1[1] - value2[1]});
        }
        return difference;


    }

    public static int getShockStartPoint(ArrayList<double[]> signal, double minAccShockStartingPoint, int windowSize, double maxAccStability) {
        Iterator<double[]> iterator = signal.iterator();
        double value;
        int i = 0;
        while (iterator.hasNext()) {
            value = iterator.next()[1];
            if (value < minAccShockStartingPoint) {
                break;
            }
            i++;
        }

//
//        for (int j = i; i > 0 + windowSize; ) {
//
//            for (j = 0; j < windowSize; j++) {
//                value = Math.abs(signal.get(i - j)[1]);
//                if (value > maxAccStability) {
//                    i = i - j - 1;
//                    break;
//
//                }
//            }
//            if (j == windowSize) {
//                return i;
//            }
//
//        }
//        return 0;
        int j;
        double minimum = 0;
        int minIndex = i;
        for (j = i; j < signal.size(); j++) {
            value = signal.get(j)[1];
            if (value > 0) break;
            if (value < minimum) {
                minIndex = j;
                minimum = value;
            }

        }
        return minIndex;

    }

    public static double getRMS(ArrayList<double[]> signal) {
        Iterator<double[]> iterator = signal.iterator();
        double rms = 0, value;
        int i = 0;
        while (iterator.hasNext()) {
            value = iterator.next()[1];
            rms += (value * value);
            i++;
        }
        rms = Math.sqrt(rms / i);
        return rms;
    }

    public static double[] getFirstStablePoint(ArrayList<double[]> signal, int windowSize, double maxAccStability) {
        //Iterator<double[]> iterator = signal.iterator();
        double value;
        int length = signal.size();
        int j;
        for (int i = 0; i < length - windowSize; ) {

            for (j = 0; j < windowSize; j++) {
                value = Math.abs(signal.get(i + j)[1]);
                if (value > maxAccStability) {
                    i = i + j + 1;
                    break;

                }
            }
            if (j == windowSize) {
                return new double[]{i, signal.get(i)[0]};
            }

        }
        return new double[]{length - 1, signal.get(length - 1)[0]};

    }

    public void test() {
        String url = "C:\\Users\\Sehan Rathnayake\\Desktop\\New folder (2)\\Data engine off\\Vector Data\\civic\\civic high\\civic3.xlsx";


    }


}
