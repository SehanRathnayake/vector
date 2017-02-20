package com.springapp.mvc.utility;

/**
 * Created by Sehan Rathnayake on 17/02/19.
 */
public class Polynomial {
    public static void main(String[] args) {
        double[] d = getPolynomial(new ComplexLo[]{new ComplexLo(11.6219, 0.0000), new ComplexLo(-0.3110, 2.6704), new ComplexLo(-0.3110, -2.6704)});
    //  d=getPolynomial(new ComplexLo[]{new ComplexLo(12.1229),new ComplexLo(-5.7345),new ComplexLo(-0.3884)});
       System.out.println();
    }

    public static ComplexLo[] multiplyByX(ComplexLo[] a) {
        ComplexLo[] b = new ComplexLo[a.length + 1];
        b[0] = new ComplexLo(0.0);
        for (int i = 1; i < b.length; i++) {
            b[i] = a[i - 1];
        }
        return b;
    }

    public static ComplexLo[] multiply(ComplexLo[] a, ComplexLo r) {


        for (int i = 0; i < a.length; i++) {
            a[i] = a[i].multiply(r);
        }
        return a;
    }

    public static ComplexLo[] substract(ComplexLo[] a, ComplexLo[] b) {


        for (int i = 0; i < b.length; i++) {
            a[i] = a[i].subtract(b[i]);
        }
        return a;
    }

    public static double[] getPolynomial(ComplexLo[] roots) {
        ComplexLo[] polynomial = new ComplexLo[1];
        ComplexLo[] polynomial2;
        polynomial[0] = new ComplexLo(1.0);
        for (ComplexLo root : roots) {
            polynomial2 = polynomial.clone();
            polynomial = multiplyByX(polynomial);
            polynomial2 = multiply(polynomial2, root);
            polynomial = substract(polynomial, polynomial2);
        }

        double[] d=new double[polynomial.length];
        for(int i = 0; i < polynomial.length; i++)
        {
           d[i]=polynomial[polynomial.length-1-i].real();
        }
        return d;
    }

}
