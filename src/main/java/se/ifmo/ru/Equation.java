package se.ifmo.ru;

public enum Equation {

    FIRST("y'= 3sin2y + x"), SECOND("y = sin(x)"), THIRD("y = cos(x)");

    private String function;

    Equation(String function) {
        this.function = function;
    }

    public double calculateFunction(double x, double y) {
        switch (this) {
            case FIRST:
                return (3 * Math.sin(2 * y) + x);
            case SECOND:
                return Math.sin(x);
            case THIRD:
                return Math.cos(x);
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.function;
    }
}
