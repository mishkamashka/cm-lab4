package se.ifmo.ru;

import se.ifmo.ru.interpolation.Function;

public enum Equation {

    FIRST("y'= y + x", Function.FIRST), SECOND("y' = e^x + y", Function.SECOND), THIRD("y' = xy^2", Function.THIRD);

    private String equation;
    private Function function;

    Equation(String equation, Function function) {
        this.equation = equation;
        this.function = function;
    }

    public double calculateFunction(double x, double y) {
        switch (this) {
            case FIRST:
                return (y + x); //y0 = 1
            case SECOND:
                return (Math.pow(Math.E, x) * y); // y(0) = 1
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
