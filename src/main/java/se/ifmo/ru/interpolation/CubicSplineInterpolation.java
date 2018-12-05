package se.ifmo.ru.interpolation;

public class CubicSplineInterpolation {

    private static SplineCoefficients[] splineCoefficients;

    public static void createSplines(Double[] xArray, Double[] yArray, int n) {

        splineCoefficients = new SplineCoefficients[n];

        for (int i = 0; i < n; i++) {
            splineCoefficients[i] = new SplineCoefficients();
            splineCoefficients[i].setX(xArray[i]);
            splineCoefficients[i].setA(yArray[i]);
        }

        splineCoefficients[0].setC(0);
        splineCoefficients[n - 1].setC(0);

        double[] alpha = new double[n - 1];
        double[] beta = new double[n - 1];

        double hi, hi1, a, b, c, f;

        for (int i = 1; i < n - 1; i++) {
            hi = xArray[i] - xArray[i - 1];
            hi1 = xArray[i + 1] - xArray[i];
            a = hi;
            b = hi1;
            c = 2.0 * (hi + hi1);
            f = 6.0 * ((yArray[i + 1] - yArray[i]) / hi1 - (yArray[i] - yArray[i - 1]) / hi);

            alpha[i] = -b / (a * alpha[i - 1] + c);
            beta[i] = (f - a * beta[i - 1] / (a * alpha[i - 1] + c));
        }

        for (int i = n - 2; i > 0; i--) {
            splineCoefficients[i].setC(alpha[i] * splineCoefficients[i + 1].getC() + beta[i]);
        }

        for (int i = n - 1; i > 0; i--) {
            hi = xArray[i] - xArray[i - 1];
            splineCoefficients[i].setD((splineCoefficients[i].getC() - splineCoefficients[i - 1].getC()) / hi);
            splineCoefficients[i].setB(hi * (2.0 * splineCoefficients[i].getC() + splineCoefficients[i - 1].getC()) / 6 + (yArray[i] - yArray[i - 1]) / hi);
        }
    }

    public static double getInterpolatedFunctionY(double x) {

        SplineCoefficients coefficients;
        if (x <= splineCoefficients[0].getX())
            coefficients = splineCoefficients[0];
        else if (x >= splineCoefficients[splineCoefficients.length - 1].getX())
            coefficients = splineCoefficients[splineCoefficients.length - 1];
        else {
            int i = 0;
            int j = splineCoefficients.length - 1;
            while (i + 1 < j) {
                int k = i + (j - i) / 2;
                if (x <= splineCoefficients[k].getX())
                    j = k;
                else
                    i = k;
            }
            coefficients = splineCoefficients[j];
        }
        double dx = x - coefficients.getX();
        return coefficients.getA() + coefficients.getB() * dx + coefficients.getC() * dx * dx / 2 + coefficients.getD() * dx * dx * dx / 6;
    }
}
