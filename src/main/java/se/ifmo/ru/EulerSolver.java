package se.ifmo.ru;

public class EulerSolver {

    int a, b;
    Double[] xArray, yArray;
    int n;
    double accuracy;
    Equation equation;

    EulerSolver(int a, int b, double y0, double accuracy, Equation equation) {
        if (a > b) {
            this.a = b;
            this.b = a;
        } else {
            this.a = a;
            this.b = b;
        }
        this.accuracy = accuracy;
        this.n = (int) ((b - a) / accuracy);
        xArray = new Double[n];
        yArray = new Double[n];
        xArray[0] = a * 1.0;
        yArray[0] = y0;
        this.equation = equation;
        this.eulerMethod();
    }

    private void eulerMethod(){
        for (int i = 1; i < n; i++) {
            xArray[a + i] = (a + accuracy * i) * 1.0;
            yArray[a + i] = yArray[i - 1] + accuracy * equation.calculateFunction(xArray[i - 1], yArray[i - 1]);
        }
    }

    public Double[] getXArray() {
        return xArray;
    }

    public Double[] getYArray() {
        return yArray;
    }
}
