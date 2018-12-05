package se.ifmo.ru;

import se.ifmo.ru.interpolation.Function;

public enum Equation {

    FIRST("y'= 3sin2y + x", Function.FIRST), SECOND("y' = -2y", Function.SECOND), THIRD("y' = xy^2", Function.THIRD);

    private String equation;
    private Function function;

    Equation(String equation, Function function) {
        this.equation = equation;
        this.function = function;
    }

    public double calculateFunction(double x, double y) {
        switch (this) {
            case FIRST:
                return (3 * Math.sin(2 * y) + x);
            case SECOND:
                return (-2 * y); // y(0) = 2; y = 2e^(-2x)
            case THIRD:
                return x * Math.pow(y, 2); //y(0) = 1
            default:
                return 0;
        }
    }

    public Function getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return this.equation;
    }
}
