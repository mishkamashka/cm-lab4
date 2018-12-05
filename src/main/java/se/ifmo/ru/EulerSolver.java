package se.ifmo.ru;

public class EulerSolver {

    int a, b;
    Double[] xArray, yArray;
    int h;
    Equation equation;

    EulerSolver(int a, int b, double y0, double accuracy, Equation equation) {
        if (a > b) {
            this.a = b;
            this.b = a;
        } else {
            this.a = a;
            this.b = b;
        }
        this.h = (int) ((b - a) / accuracy);
        xArray = new Double[h];
        yArray = new Double[h];
        xArray[0] = a * 1.0;
        yArray[0] = y0;
        this.equation = equation;
        this.eulerMethod();
    }

    private void eulerMethod(){
        for (int i = 1; i < h; i++) {
            xArray[a + i] = (a + h * i) * 1.0;
            yArray[a + i] = yArray[i - 1] + h * equation.calculateFunction(xArray[i - 1], yArray[i - 1]);
        }
    }

    public Double[] getXArray() {
        return xArray;
    }

    public Double[] getYArray() {
        return yArray;
    }
}
