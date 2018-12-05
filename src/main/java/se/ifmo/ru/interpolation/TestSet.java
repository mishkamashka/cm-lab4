package se.ifmo.ru.interpolation;

import java.util.ArrayList;
import java.util.List;

public enum TestSet {

    FIRST, SECOND, THIRD;
    
    private Function function;

    public List<Double> setXTestSet() {
        List<Double> xList = new ArrayList<Double>();
        switch (this) {
            case FIRST:
                xList.add(0.0);
                xList.add(Math.PI / 2);
                xList.add(Math.PI);
                xList.add(3 * Math.PI / 2);
                xList.add(2 * Math.PI);
                return xList;
            case SECOND:
            case THIRD:
                xList.add(0.0);
                xList.add(Math.PI / 4);
                xList.add(Math.PI / 2);
                xList.add(3 * Math.PI / 4);
                xList.add(Math.PI);
                xList.add(5 * Math.PI / 4);
                xList.add(3 * Math.PI / 2);
                xList.add(7 * Math.PI / 4);
                xList.add(2 * Math.PI);
                return xList;
            default:
                return xList;
        }

    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public List<Double> setYTestSet() {
        List<Double> yList = new ArrayList<Double>();
        switch (this) {
            case FIRST:
                yList.add(function.calculateFunction(0.0));
                yList.add(function.calculateFunction(Math.PI / 2));
                yList.add(function.calculateFunction(Math.PI));
                yList.add(function.calculateFunction(3 * Math.PI / 2));
                yList.add(function.calculateFunction(2 * Math.PI));
                return yList;
            case SECOND:
                yList.add(function.calculateFunction(0.0));
                yList.add(function.calculateFunction(Math.PI / 4));
                yList.add(function.calculateFunction(Math.PI / 2));
                yList.add(function.calculateFunction(3 * Math.PI / 4));
                yList.add(function.calculateFunction(Math.PI));
                yList.add(function.calculateFunction(5 * Math.PI / 4));
                yList.add(function.calculateFunction(3 * Math.PI / 2));
                yList.add(function.calculateFunction(7 * Math.PI / 4));
                yList.add(function.calculateFunction(2 * Math.PI));
                return yList;
            case THIRD:
                yList.add(function.calculateFunction(0.0));
                yList.add(function.calculateFunction(Math.PI / 4));
                yList.add(function.calculateFunction(Math.PI / 2));
                yList.add(-6.0);
                yList.add(function.calculateFunction(Math.PI));
                yList.add(function.calculateFunction(5 * Math.PI / 4));
                yList.add(function.calculateFunction(3 * Math.PI / 2));
                yList.add(function.calculateFunction(7 * Math.PI / 4));
                yList.add(function.calculateFunction(2 * Math.PI));
                return yList;
            default:
                return yList;
        }
        
    }
}
