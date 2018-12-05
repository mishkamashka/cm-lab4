package se.ifmo.ru;

public enum Equation {

    FIRST("y'= 3sin2y + x"), SECOND("y' = -2y"), THIRD("y = cos(x)");

    private String function;

    Equation(String function) {
        this.function = function;
    }

    public double calculateFunction(double x, double y) {
        switch (this) {
            case FIRST:
                return (3 * Math.sin(2 * y) + x);
            case SECOND:
                return (-2 * y); // y(0) = 2; y = 2e^(-2x)
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
