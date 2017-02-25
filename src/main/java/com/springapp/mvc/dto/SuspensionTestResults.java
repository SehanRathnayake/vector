package com.springapp.mvc.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sehan Rathnayake on 17/02/21.
 */
public class SuspensionTestResults implements Serializable{

    private double chassiPeakValue;
    private double axcelPeakValue;
    private double disturbanceTime;
    private double chassiRMSvalue;
    private double axcelRMSvalue;


    private double dampingFactor;
    private double dampedFrequency;
    private double naturalFrequency;



    private ArrayList<double[]> sinewave;
    private  ArrayList<double[]> axcelSignalClipped;
    private  ArrayList<double[]> chassiSignalClipped;
    private  ArrayList<double[]> differenceSignal;
    private ArrayList<double[]> chassiSignalFiltered;
    private ArrayList<double[]> chassiFrequencySpectrum;

    public double getDampingFactor() {
        return dampingFactor;
    }

    public void setDampingFactor(double dampingFactor) {
        this.dampingFactor = dampingFactor;
    }

    public double getDampedFrequency() {
        return dampedFrequency;
    }

    public void setDampedFrequency(double dampedFrequency) {
        this.dampedFrequency = dampedFrequency;
    }

    public double getNaturalFrequency() {
        return naturalFrequency;
    }

    public void setNaturalFrequency(double naturalFrequency) {
        this.naturalFrequency = naturalFrequency;
    }

    public ArrayList<double[]> getSinewave() {
        return sinewave;
    }

    public void setSinewave(ArrayList<double[]> sinewave) {
        this.sinewave = sinewave;
    }

    public ArrayList<double[]> getAxcelSignalClipped() {
        return axcelSignalClipped;
    }

    public  void setAxcelSignalClipped(ArrayList<double[]> axcelSignalClipped) {
        this.axcelSignalClipped = axcelSignalClipped;
    }

    public  ArrayList<double[]> getChassiSignalClipped() {
        return chassiSignalClipped;
    }

    public  void setChassiSignalClipped(ArrayList<double[]> chassiSignalClipped) {
     this.chassiSignalClipped = chassiSignalClipped;
    }

    public ArrayList<double[]> getChassiSignalFiltered() {
        return chassiSignalFiltered;
    }

    public  void setChassiSignalFiltered(ArrayList<double[]> chassiSignalFiltered) {
       this.chassiSignalFiltered = chassiSignalFiltered;
    }

    public double getChassiPeakValue() {
        return chassiPeakValue;
    }

    public void setChassiPeakValue(double chassiPeakValue) {
        this.chassiPeakValue = chassiPeakValue;
    }

    public double getAxcelPeakValue() {
        return axcelPeakValue;
    }

    public void setAxcelPeakValue(double axcelPeakValue) {
        this.axcelPeakValue = axcelPeakValue;
    }

    public double getDisturbanceTime() {
        return disturbanceTime;
    }

    public void setDisturbanceTime(double disturbanceTime) {
        this.disturbanceTime = disturbanceTime;
    }

    public ArrayList<double[]> getChassiFrequencySpectrum() {
        return chassiFrequencySpectrum;
    }

    public void setChassiFrequencySpectrum(ArrayList<double[]> chassiFrequencySpectrum) {
        this.chassiFrequencySpectrum = chassiFrequencySpectrum;
    }

    public ArrayList<double[]> getDifferenceSignal() {
        return differenceSignal;
    }

    public void setDifferenceSignal(ArrayList<double[]> differenceSignal) {
        this.differenceSignal = differenceSignal;
    }

    public double getChassiRMSvalue() {
        return chassiRMSvalue;
    }

    public void setChassiRMSvalue(double chassiRMSvalue) {
        this.chassiRMSvalue = chassiRMSvalue;
    }

    public double getAxcelRMSvalue() {
        return axcelRMSvalue;
    }

    public void setAxcelRMSvalue(double axcelRMSvalue) {
        this.axcelRMSvalue = axcelRMSvalue;
    }
}
