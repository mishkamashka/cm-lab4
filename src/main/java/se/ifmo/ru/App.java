package se.ifmo.ru;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import se.ifmo.ru.interpolation.*;

public class App extends Application {

    final private static int HEIGHT = 500;
    final private static int WIDTH = 1000;
    final private static NumberAxis xAxis = new NumberAxis();
    final private static NumberAxis yAxis = new NumberAxis();
    final private static LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
    private static ToggleGroup equationsGroup = new ToggleGroup();
    private static TextField aTextField = new TextField("0");
    private static TextField bTextField = new TextField("1");
    private static TextField y0TextField = new TextField("1");
    private static TextField accuracyTextField = new TextField("0.1");

    private static Double[] xArray;
    private static Double[] yArray;
    private static int a, b;
    private static double y0, accuracy;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setHgap(0);
        pane.setPrefSize(WIDTH, HEIGHT);

        GridPane gridPane1 = new GridPane();
        gridPane1.setHgap(0);
        gridPane1.setVgap(5);
        gridPane1.setAlignment(Pos.TOP_LEFT);
        gridPane1.setPadding(new Insets(10, 0, 10, 10));

        GridPane gridPane2 = new GridPane();
        gridPane2.setHgap(10);
        gridPane2.setVgap(5);
        gridPane2.setAlignment(Pos.TOP_RIGHT);
        gridPane2.setPadding(new Insets(10, 10, 10, 0));

        lineChart.setLegendVisible(false);

        addFunctionsChoice(gridPane1);
        addTextFields(gridPane1);

        addButtons(gridPane1, gridPane2);

        pane.add(gridPane1, 0, 1);
        pane.add(gridPane2, 1, 1);

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Euler method app");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static void addFunctionsChoice(GridPane gridPane) {

        Label label = new Label("Choose an equation:");
        label.setFont(new Font(15));

        RadioButton equation1 = new RadioButton(Equation.FIRST.toString());
        RadioButton equation2 = new RadioButton(Equation.SECOND.toString());
        RadioButton equation3 = new RadioButton(Equation.THIRD.toString());
        equation1.setFont(new Font(15));
        equation2.setFont(new Font(15));
        equation3.setFont(new Font(15));
        equation1.setUserData(Equation.FIRST);
        equation2.setUserData(Equation.SECOND);
        equation3.setUserData(Equation.THIRD);
        equation1.setToggleGroup(equationsGroup);
        equation2.setToggleGroup(equationsGroup);
        equation3.setToggleGroup(equationsGroup);

        gridPane.add(label, 0, 0);
        gridPane.add(equation1, 0, 1);
        gridPane.add(equation2, 0, 2);
        gridPane.add(equation3, 0, 3);
    }

    private static void addTextFields(GridPane gridPane) {
        Label label = new Label();
        label.setText("Enter a:");
        label.setFont(new Font(15));
        gridPane.add(label, 0, 5);

        label = new Label();
        label.setText("Enter b:");
        label.setFont(new Font(15));
        gridPane.add(label, 0, 7);

        label = new Label();
        label.setText("Enter y0:");
        label.setFont(new Font(15));
        gridPane.add(label, 0, 9);

        label = new Label();
        label.setText("Enter accuracy:");
        label.setFont(new Font(15));
        gridPane.add(label, 0, 11);

        Pattern pattern = Pattern.compile("-\\d*|\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        aTextField.setTextFormatter(formatter);

        formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        bTextField.setTextFormatter(formatter);

        Pattern pattern1 = Pattern.compile("-\\d*|-\\d*+\\.\\d*|\\d*|\\d+\\.\\d*");
        formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern1.matcher(change.getControlNewText()).matches() ? change : null;
        });
        y0TextField.setTextFormatter(formatter);

        Pattern pattern2 = Pattern.compile("\\d*|\\d+\\.\\d*");
        formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern2.matcher(change.getControlNewText()).matches() ? change : null;
        });
        accuracyTextField.setTextFormatter(formatter);

        gridPane.add(aTextField, 0, 6);
        gridPane.add(bTextField, 0, 8);
        gridPane.add(y0TextField, 0, 10);
        gridPane.add(accuracyTextField, 0, 12);

    }

    private static void addModelChart(Function function) {

        XYChart.Series series = new XYChart.Series();
        series.setName("Model chart");

        for (double i = a; i <= b; i += 0.1) {
            XYChart.Data data = new XYChart.Data(i, function.calculateFunction(i));
            Rectangle rectangle = new Rectangle(0, 0);
            rectangle.setVisible(false);
            data.setNode(rectangle);
            series.getData().add(data);
        }

        lineChart.getData().add(series);
    }

    private static void addInterpolatedChart() {

        XYChart.Series series = new XYChart.Series();
        series.setName("Interpolated chart");

        for (double i = a; i <= b; i += 0.1) {
            double x = i;
            double y = CubicSplineInterpolation.getInterpolatedFunctionY(i);

            XYChart.Data data = new XYChart.Data(x, y);
            if (!isInArray(x, xArray)) {
                Rectangle rectangle = new Rectangle(0, 0);
                rectangle.setVisible(false);
                data.setNode(rectangle);
            }
            series.getData().add(data);
        }

        lineChart.getData().add(series);
    }

    private static void addButtons(GridPane gridPane, final GridPane functionsGridPane) {
        Button okButton = new Button("Ok");

        okButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                Equation equation;
                Toggle toggle1 = equationsGroup.getSelectedToggle();
                try {
                    equation = (Equation) toggle1.getUserData();
                } catch (NullPointerException e) {
                    return;
                }

                try {
                    a = Integer.parseInt(aTextField.textProperty().getValue());
                    b = Integer.parseInt(bTextField.textProperty().getValue());
                    y0 = Double.parseDouble(y0TextField.textProperty().getValue());
                    accuracy = Double.parseDouble(accuracyTextField.textProperty().getValue());
                } catch (NumberFormatException e) {
                    return;
                }

                EulerSolver eulerSolver = new EulerSolver(a, b, y0, accuracy, equation);
                xArray = eulerSolver.getXArray();
                yArray = eulerSolver.getYArray();

                CubicSplineInterpolation.createSplines(xArray, yArray, xArray.length);

                if (functionsGridPane.getChildren().size() != 0) {
                    LineChart<Number, Number> lineChart = (LineChart<Number, Number>) functionsGridPane.getChildren().get(0);
                    while (lineChart.getData().size() > 0)
                        lineChart.getData().remove(0);
                } else
                    functionsGridPane.add(lineChart, 0, 0);
                addModelChart(equation.getFunction());
                addInterpolatedChart();
            }
        });
        gridPane.add(okButton, 1, 14);
    }

    private static boolean isInArray(double x, Double[] xArray) {
        int i = 0;
        while (i < xArray.length) {
            if (Math.abs(xArray[i] - x) < 0.09)
                return true;
            i++;
        }
        return false;
    }

}
