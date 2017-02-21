package com.springapp.mvc.utility;

import java.util.ArrayList;
import java.util.Iterator;

import Jama.*;
import com.springapp.mvc.dto.SuspensionTestResults;
import flanagan.complex.Complex;
import flanagan.complex.ComplexMatrix;
import org.ejml.data.Complex64F;
import org.ejml.data.DenseMatrix64F;
import org.ejml.factory.DecompositionFactory;
import org.ejml.interfaces.decomposition.EigenDecomposition;

import static java.lang.Math.abs;
import static java.lang.Math.cos;

/**
 * Created by Sehan Rathnayake on 17/02/19.
 */
public class CurveFitting {
    public static void main(String[] args) {
        //getDampedSineCurve();

    }

    public static SuspensionTestResults getDampedSineCurve(ArrayList<double[]> sigList,int range, double sampleRate) {
        Iterator<double[]> iterator = sigList.iterator();
        double[] signal = new double[range];
        int pointer = 0;
        while (pointer<range) {
            signal[pointer++] = iterator.next()[1];
        }

        double[] f = fourierTransform(signal);
        double max = f[0];
        int index = 0;
        for (int i = 0; i < f.length; i++) {
            if (f[i] > max) {
                max = f[i];
                index = i;
            }
        }
        double nn = signal.length / index;
        int dd = (int) nn / 4;

        int n = signal.length;

        double[] sn0 = new double[n - 2 * dd];
        double[] sn1 = new double[n - 2 * dd];
        double[] sn2 = new double[n - 2 * dd];

        System.arraycopy(signal, 2 * dd, sn0, 0, sn0.length);
        System.arraycopy(signal, dd, sn1, 0, sn1.length);
        System.arraycopy(signal, 0, sn2, 0, sn2.length);

        double[][] m = new double[2][sn1.length];
        for (int i = 0; i < sn1.length; i++) {
            m[0][i] = -1 * sn1[i];
        }
        for (int i = 0; i < sn1.length; i++) {
            m[1][i] = -1 * sn2[i];
        }

        Matrix M = new Matrix(m).transpose();
        Matrix transM = M.transpose();
        Matrix coff = (transM.times(M)).inverse().times(transM).times(getMatrix(sn0).transpose());

        double[] temp = coff.getColumnPackedCopy();
        double[] temp2 = new double[temp.length + 1];
        temp2[temp2.length - 1] = 1;
        for (int i = 0; i < temp.length; i++) {
            temp2[i] = temp[temp.length - 1 - i];
        }

        ComplexLo[] rest = findRoots(temp2);
        ComplexLo[] rest1 = new ComplexLo[rest.length];

        for (int i = 0; i < rest1.length; i++) {
            rest1[i] = ComplexLo.pow(rest[i], 1.0 / dd);
        }

        double[] far = Polynomial.getPolynomial(rest1);
/////////////////////////////
        double[][] temp3 = new double[1][n];
        for (int i = 0; i < n; i++) {
            temp3[0][i] = i;
        }


        ComplexLo[][] temp4 = new ComplexLo[1][rest1.length];
        temp4[0] = rest1;


        double[][] temp5 = repmat(temp3, 2, 1);
        temp4 = repmat(trasposeMatrix(temp4), 1, n);

        ComplexLo[][] MM = new ComplexLo[temp4.length][temp4[0].length];

        for (int i = 0; i < temp4.length; i++) {
            for (int j = 0; j < temp4[0].length; j++) {
                MM[i][j] = ComplexLo.pow(temp4[i][j], temp5[i][j]);
            }
        }
        ComplexLo[][] MM1 = multiplyMatrices(MM, trasposeMatrix(MM));


        ComplexLo[][] temp6 = findInverse(MM1);

        temp6 = multiplyMatrices(temp6, MM);


        ComplexLo[][] complexSig = new ComplexLo[1][signal.length];
        for (int i = 0; i < signal.length; i++) {
            complexSig[0][i] = new ComplexLo(signal[i]);
        }

        ComplexLo[][] coff1 = multiplyMatrices(temp6, trasposeMatrix(complexSig));
        ComplexLo[][] temp7 = multiplyMatrices(trasposeMatrix(MM), coff1);
        double[] sig3r = new double[temp7.length];
        for (int i = 0; i < temp7.length; i++) {
            sig3r[i] = temp7[i][0].real();
        }
        System.out.println();

        double[] sig1r = new double[n];
        sig1r[0] = sig3r[0];
        sig1r[1] = sig3r[1];

        for (int i = 2; i < sig1r.length; i++) {
            sig1r[i] = -far[1] * sig1r[i - 1] - far[2] * sig1r[i - 2];
        }

        double br = far[2];
        double alphar = Math.log(Math.sqrt(br));
        double wr = Math.acos(-far[1] / 2 / Math.sqrt(br));


        // coff=inv(M'*M)*M'*sig;

        double[][] M2 = new double[n][2];
        for (int i = 0; i < n; i++) {
            M2[i][0] = Math.exp(alphar * i) * Math.sin(i * wr);
            M2[i][1] = Math.exp(alphar * i) * cos(i * wr);
        }

        Matrix temp8 = new Matrix(M2);
        Matrix coff2 = (temp8.transpose().times(temp8)).inverse().times(temp8.transpose()).times(getMatrix(signal).transpose());

        ComplexLo cc = new ComplexLo(coff2.get(1, 0), coff2.get(0, 0));
        double Ar = ComplexLo.abs(cc);
        double phr = ComplexLo.arg(cc.conjugate());


        double[] sig2r = new double[n];
        for (int i = 0; i < n; i++) {
            sig2r[i] = Ar * Math.exp(alphar * i) * cos(i * wr + phr);
        }

        double ind1a = -1;
        double ind2a = -1;
        double kk = 1;

        while (true) {
            nn = 1000;
            double[] ee = new double[(int) nn];
            double[] vv = new double[(int) nn];
            for (int i = 0; i < vv.length; i++) {
                vv[i] = 0.2 + 5 * (double) i / (double) nn;
            }
            double y = Double.MAX_VALUE;
            int ind1 = 0;
            for (int i = 0; i < nn; i++) {
                double[] sigsig = new double[n];
                for (int j = 0; j < n; j++) {
                    sigsig[j] = Ar * Math.exp(vv[i] * alphar * (j)) * Math.cos(wr * (j) + phr);

                }

                double sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += Math.abs(signal[j] - sigsig[j]);
                }

                ee[i] = sum;
                if (sum < y) {
                    y = sum;
                    ind1 = i;
                }
            }
            alphar = vv[ind1] * alphar;

            y = Double.MAX_VALUE;
            int ind2 = 0;

            for (int i = 0; i < vv.length; i++) {
                vv[i] = 0.5 + 2 * (i) / nn;
            }


            for (int i = 0; i < nn; i++) {
                double[] sigsig = new double[n];
                for (int j = 0; j < n; j++) {
                    sigsig[j] = vv[i] * Ar * Math.exp(alphar * (j)) * Math.cos(wr * (j) + phr);

                }

                double sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += Math.abs(signal[j] - sigsig[j]);
                }

                ee[i] = sum;
                if (sum < y) {
                    y = sum;
                    ind2 = i;
                }
            }
            Ar = vv[ind2] * Ar;
            if (ind1 == ind1a) {
                if (ind2 == ind2a)
                    break;
            }
            ind1a = ind1;
            ind2a = ind2;
            kk = kk + 1;
            if (kk > 100) break;

        }

        sig2r=new double[sigList.size()];
        for (int i = 0; i < sigList.size(); i++) {
            sig2r[i] = Ar * Math.exp(alphar * (i)) * Math.cos(wr * (i) + phr);
        }

        ArrayList<double[]> sineWave = new ArrayList<double[]>();
        iterator = sigList.iterator();

        pointer = 0;
        while (iterator.hasNext()) {
            double[] value = iterator.next();
            sineWave.add(new double[]{value[0], value[1], sig2r[pointer++]});
        }
        double pi=Math.PI;
        double c = Math.abs(alphar) / (Math.sqrt(wr * wr + alphar * alphar));
        double fd = wr * sampleRate/(2*pi);
        double fn = fd / Math.sqrt(1 - c * c);

        SuspensionTestResults suspensionTestResults = new SuspensionTestResults();
        suspensionTestResults.setDampedFrequency(fd);
        suspensionTestResults.setDampingFactor(c);
        suspensionTestResults.setNaturalFrequency(fn);
        suspensionTestResults.setSinewave(sineWave);

        return suspensionTestResults;
    }

    public static double[] fourierTransform(double[] signal) {

        int i = 0;
        int n = signal.length;


        double f[] = new double[n / 2];
        ArrayList<Double> frequencySpectrum = new ArrayList<Double>();

        for (int j = 0; j < n / 2; j++) {

            double firstSummation = 0;
            double secondSummation = 0;

            for (int k = 0; k < n; k++) {
                double twoPInjk = ((2 * Math.PI) / n) * (j * k);
                firstSummation += signal[k] * cos(twoPInjk);
                secondSummation += signal[k] * Math.sin(twoPInjk);
            }

            f[j] = abs(Math.sqrt(Math.pow(firstSummation, 2) +
                    Math.pow(secondSummation, 2)));

            double amplitude = f[j];
            frequencySpectrum.add(amplitude);
        }
        return f;

    }

    public static ComplexLo[][] trasposeMatrix(ComplexLo[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        ComplexLo[][] trasposedMatrix = new ComplexLo[n][m];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                trasposedMatrix[x][y] = matrix[y][x];
            }
        }

        return trasposedMatrix;
    }

    static double[][] multiplyMatrices(double[][] a, double[][] b) {

        int m = a.length;
        int n = a[0].length;

        int p = b.length;
        int q = b[0].length;

        double sum = 0;
        double multiply[][] = new double[m][q];
        if (n != p) {
            System.out.println("Matrices with entered orders can't be multiplied with each other.");
            return null;
        } else {
            for (int c = 0; c < m; c++) {
                for (int d = 0; d < q; d++) {
                    for (int k = 0; k < p; k++) {
                        sum = sum + a[c][k] * b[k][d];
                    }

                    multiply[c][d] = sum;
                    sum = 0;
                }
            }
            return multiply;
        }
    }

    static ComplexLo[][] multiplyMatrices(ComplexLo[][] a, ComplexLo[][] b) {

        int m = a.length;
        int n = a[0].length;

        int p = b.length;
        int q = b[0].length;

        ComplexLo sum = new ComplexLo(0.0, 0.0);
        ComplexLo[][] multiply = new ComplexLo[m][q];
        if (n != p) {
            System.out.println("Matrices with entered orders can't be multiplied with each other.");
            return null;
        } else {
            for (int c = 0; c < m; c++) {
                for (int d = 0; d < q; d++) {
                    for (int k = 0; k < p; k++) {
                        sum = sum.add(a[c][k].multiply(b[k][d]));
                    }

                    multiply[c][d] = sum;
                    sum = new ComplexLo(0.0, 0.0);

                }
            }
            return multiply;
        }
    }

    static double[][] multiplyMatrices(double[][] a, double[] b) {
        double[][] c = new double[1][b.length];


        return multiplyMatrices(a, c);
    }


    static Matrix getMatrix(double[] a) {
        double[][] temp = new double[1][a.length];
        temp[0] = a;
        return new Matrix(temp);
    }

    public static ComplexLo[] findRoots(double... coefficients) {
        int N = coefficients.length - 1;

        // Construct the companion matrix
        DenseMatrix64F c = new DenseMatrix64F(N, N);

        double a = coefficients[N];
        for (int i = 0; i < N; i++) {
            c.set(i, N - 1, -coefficients[i] / a);
        }
        for (int i = 1; i < N; i++) {
            c.set(i, i - 1, 1);
        }

        // use generalized eigenvalue decomposition to find the roots
        EigenDecomposition<DenseMatrix64F> evd = DecompositionFactory.eig(N, false);

        evd.decompose(c);

        ComplexLo[] roots = new ComplexLo[N];
        Complex64F temp;
        for (int i = 0; i < N; i++) {
            temp = evd.getEigenvalue(i);
            roots[i] = new ComplexLo(temp.getReal(), temp.getImaginary());

        }

        return roots;
    }

    static ComplexLo[][] repmat(ComplexLo[][] a, int m, int n) {
        ComplexLo[][] b = new ComplexLo[a.length * m][a[0].length * n];
        for (int i = 0; i < a.length * m; i++) {
            for (int j = 0; j < a[0].length * n; j++) {
                b[i][j] = a[i / m][j / n];
            }
        }
        return b;
    }

    static double[][] repmat(double[][] a, int m, int n) {
        double[][] b = new double[a.length * m][a[0].length * n];
        for (int i = 0; i < a.length * m; i++) {
            for (int j = 0; j < a[0].length * n; j++) {
                b[i][j] = a[i / m][j / n];
            }
        }
        return b;
    }

    static ComplexLo[][] findInverse(ComplexLo[][] a) {
        Complex[][] b = new Complex[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                b[i][j] = new Complex(a[i][j].real(), a[i][j].imag());
            }
        }

        ComplexMatrix complexMatrix = new ComplexMatrix(b);
        complexMatrix = complexMatrix.inverse();
        b = complexMatrix.getArrayReference();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                a[i][j] = new ComplexLo(b[i][j].getReal(), b[i][j].getImag());
            }
        }
        return a;

    }
}
