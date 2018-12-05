package se.ifmo.ru.interpolation;

public enum Function {

    FIRST("y = x*x"), SECOND("y = 2(e^(-2x))"), THIRD("y = 2/(2 - x^2)");

    private String function;

    Function(String function) {
        this.function = function;
    }

    public double calculateFunction(double x) {
        switch (this) {
            case FIRST:
                return (x * x);
            case SECOND:
                return (2 * Math.pow(Math.E, (-2 * x)));
            case THIRD:
                if (Math.pow(x, 2) == 2) {
                    return 2 / (2 - Math.pow(x, 1.9)); //TODO: zero-check
                } else
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
