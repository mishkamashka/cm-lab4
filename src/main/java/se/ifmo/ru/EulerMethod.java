package se.ifmo.ru;

public class EulerMethod {

    int a, b;
    double[] xArray, yArray;
    int h;
    Equation equation;

    EulerMethod(int a, int b, double y0, double accuracy, Equation equation) {
        if (a > b) {
            this.a = b;
            this.b = a;
        } else {
            this.a = a;
            this.b = b;
        }
        this.h = (int) ((b - a) / accuracy);
        xArray = new double[h];
        yArray = new double[h];
        xArray[0] = a;
        yArray[0] = y0;
        this.equation = equation;
    }

    public void solveEquation(){
        for (int i = 0; i <= h; i++) {
            xArray[a + i] = a + h * i;
            yArray[a + i] = yArray[i - 1] + h * equation.calculateFunction(xArray[i - 1], yArray[i - 1]);
        }
    }

    public double[] getxArray() {
        return xArray;
    }

    public double[] getyArray() {
        return yArray;
    }
}
