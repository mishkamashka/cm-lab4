package se.ifmo.ru.interpolation;
public enum Function {

    FIRST("y = x*x"), SECOND("y = 2(e^(-2x))"), THIRD("y = cos(x)");

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
