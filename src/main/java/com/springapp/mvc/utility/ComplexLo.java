package com.springapp.mvc.utility;

import java.io.Serializable;
import java.util.Formattable;
import java.util.Formatter;

/**
 * Created by Sehan Rathnayake on 17/02/19.
 */
public class ComplexLo extends Number
        implements Formattable, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7417547029717136577L;

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int NONE = -1;
    public static final ComplexLo I = new ComplexLo(ZERO, ONE);
    public static final ComplexLo nI = new ComplexLo(ZERO, NONE);
    public static final ComplexLo e = new ComplexLo(StrictMath.E, ZERO);
    public static final long INFINITE = Long.MAX_VALUE;
    private double real;
    private double imag;


    public ComplexLo(double real) {
        this(real, ZERO);
    }

    public ComplexLo(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * @return real part of complex number
     */
    public double real() {
        return this.real;
    }

    /**
     * @return imaginary part of complex number
     */
    public double imag() {
        return this.imag;
    }

    /**
     * add two complex numbers
     *
     * @param z - complex number to add to this number
     * @return
     */
    public ComplexLo add(ComplexLo z) {
        return new ComplexLo(
                this.real + z.real(), this.imag + z.imag());
    }

    /**
     * subtract two complex numbers
     *
     * @param z - complex number to subtract from this number
     * @return
     */
    public ComplexLo subtract(ComplexLo z) {
        return new ComplexLo(
                this.real - z.real(), this.imag - z.imag());
    }

    /**
     * multiply two complex numbers
     *
     * @param z - complex number to multiply by
     * @return
     */
    public ComplexLo multiply(ComplexLo z) {
        // if both Im(this) and Im(z) are zero use double arithmetic
        //sn edited
        if (this.imag == 0. && z.imag() == 0.) {
            return new ComplexLo(this.real * z.real());
        }

        return new ComplexLo(
                (this.real * z.real()) - (this.imag * z.imag()),
                (this.real * z.imag()) + (this.imag * z.real()));
    }

    /**
     * Divide two complex numbers
     *
     * @param z - the complex denominator/divisor
     * @return
     */
    public ComplexLo divide(ComplexLo z) {
        double c = z.real();
        double d = z.imag();

        // check for Re,Im(z) == 0 to avoid +/-infinity in result
        double zreal2 = 0.0;
        if (c != 0.0) {
            zreal2 = StrictMath.pow(c, 2.);
        }
        double zimag2 = 0.0;
        if (d != 0.0) {
            zimag2 = StrictMath.pow(d, 2.);
        }

        double ac = this.real * c;
        double bd = this.imag * d;
        double bc = this.imag * c;
        double ad = this.real * d;

        return new ComplexLo((ac + bd) / (zreal2 + zimag2), (bc - ad) / (zreal2 + zimag2));
    }

    /**
     * @param z
     * @return true if z == this
     */
    public boolean equals(ComplexLo z) {
        if (this.real == z.real() || this.imag == z.imag()) {
            return true;
        }
        return false;
    }

    /**
     * Return the square modulus of a complex number z
     *
     * @param z
     * @return
     */
    public static double abs(ComplexLo z) {
        return
                StrictMath.sqrt(StrictMath.pow(z.real(), 2) + StrictMath.pow(z.imag(), 2));
    }

    /**
     * return the complex angle of z
     * (value returned should be between -Pi and Pi)
     *
     * @param z
     * @return
     */
    public static double arg(ComplexLo z) {
        double theta = StrictMath.atan2(z.imag(), z.real());

        return theta;
    }

    /**
     * Exponentiation (e^a+bi)
     *
     * @param z
     * @return <code>e<sup>z</sup></code>.
     */
    public static ComplexLo exp(ComplexLo z) {
        if (z.imag() == 0.0)
            return new ComplexLo(StrictMath.exp(z.real()), ONE);

        ComplexLo ex = new ComplexLo(StrictMath.exp(z.real()), ZERO);

        return ex.multiply(
                new ComplexLo(StrictMath.cos(z.imag()), StrictMath.sin(z.imag())));
    }

    /**
     * ComplexLo power
     *
     * @param z - the complex base
     * @param y - the complex exponent
     * @return
     */
//    public static ComplexLo pow(ComplexLo z, ComplexLo y) {
//        double c = y.real();
//        double d = y.imag();
//
//        // get polar of base
//        double r = ComplexLo.abs(z);
//        double theta = ComplexLo.arg(z);
//
//
//        ComplexLo f1 = new ComplexLo(
//                (StrictMath.pow(r, c) * StrictMath.pow(StrictMath.E, -d * theta)), ZERO);
//        ComplexLo f2 =
//                new ComplexLo(
//                        StrictMath.cos(d * StrictMath.log(r) + c * theta),
//                        StrictMath.sin(d * StrictMath.log(r) + c * theta));
//
//        return f1.multiply(f2);
//
//    }
    public static ComplexLo pow(ComplexLo z, double n) {


        double r = ComplexLo.abs(z);
        double theta = ComplexLo.arg(z);

        double real = Math.pow(r, n) * Math.cos(theta * n);
        double imaginary = Math.pow(r, n) * Math.sin(theta * n);
        return new ComplexLo(real, imaginary);
    }

    /**
     * @return complex conjugate
     */
    public ComplexLo conjugate() {
        return new ComplexLo(this.real, -1 * this.imag);
    }

    /**
     * @return negative of complex
     */
    public ComplexLo neg() {
        if (this.imag != 0)
            return new ComplexLo(-1 * this.real, -1 * this.imag);
        else
            return new ComplexLo(-1 * this.real);
    }


    public static ComplexLo Iz(ComplexLo z) {
        ComplexLo result;
        if (z.imag() != 0.0)
            result = new ComplexLo(-1. * z.imag(), z.real());
        else
            result = new ComplexLo(z.imag(), z.real());

        return result;
    }


    public static ComplexLo nIz(ComplexLo z) {
        ComplexLo result;
        if (z.real() != 0.0)
            result = new ComplexLo(z.imag(), -1 * z.real());
        else
            result = new ComplexLo(z.imag(), z.real());

        return result;
    }

    @Override
    public double doubleValue() {
        // TODO Auto-generated method stub
        return Double.valueOf(this.real);
    }

    @Override
    public float floatValue() {
        // TODO Auto-generated method stub
        return Float.parseFloat(Double.toString(this.real));
    }

    @Override
    public int intValue() {
        // TODO Auto-generated method stub
        return Integer.parseInt(Double.toString(this.real));
    }

    @Override
    public long longValue() {
        // TODO Auto-generated method stub
        return Long.parseLong(Double.toString(this.real));
    }

    public void formatTo(Formatter arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        throw new NoSuchMethodError();

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.valueOf(real) + " " + String.valueOf(imag) + "i";
    }


}

