package se.ifmo.ru.interpolation;

public enum Function {

    FIRST("y = 2(e^(x^2/2))"), SECOND("y = x(e^x)"), THIRD("y = 2/(2 - x^2)");

    private String function;

    Function(String function) {
        this.function = function;
    }

    public double calculateFunction(double x) {
        switch (this) {
            case FIRST:
                return 2 * Math.pow(Math.E, x*x/2);
            case SECOND:
                return (x + 1) * Math.pow(Math.E, x);
            case THIRD:
                    return (2 / (2 - Math.pow(x, 2)));
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        return this.function;
    }
}
