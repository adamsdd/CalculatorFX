package adamsdd.calculator.controller;

import adamsdd.calculator.model.CalculationOperationType;
import adamsdd.calculator.model.Calculations;
import adamsdd.calculator.model.FunctionOperationType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.math.BigDecimal;


public class CalculatorController {

    private Calculations calculations;
    private BigDecimal x;
    private BigDecimal y;
    private String operation;
    private StringBuilder resultBuilder;

    @FXML
    private TextField textView;

    public CalculatorController() {
        calculations = new Calculations();
    }

    public void initialize() {
        resultBuilder = new StringBuilder();
    }

    public void mathematicalOperationButtonClick(ActionEvent actionEvent) {
        String buttonText = getButtonTextFromEvent(actionEvent);
        resultBuilder.append(buttonText);
        appendToTextView(buttonText);
        System.out.println(resultBuilder.toString());
    }

    public void functionalButtonClick(ActionEvent actionEvent) {
        resultBuilder = calculations.doFunctionOperation(FunctionOperationType.valueOf(getButtonTextFromEvent(actionEvent)), resultBuilder);
        textView.setText(resultBuilder.toString());
    }

    private String getButtonTextFromEvent(ActionEvent actionEvent) {
        return ((Button)actionEvent.getSource()).getText();
    }

    public void buttonClick(ActionEvent actionEvent) {
        String calculationResult = calculations.calculateExpression(resultBuilder.toString()).toString();
        textView.setText(calculationResult);
        resultBuilder.setLength(0);
        resultBuilder.append(calculationResult);
    }

/*    public void buttonClick(ActionEvent actionEvent) {
        Button pressedButton = (Button) actionEvent.getSource();
        textView.setText(pressedButton.getText());

        if (y == null && calculations.isOperation(pressedButton.getText())) {
            operation = pressedButton.getText();
        } else if (x == null) {
            x = new BigDecimal(pressedButton.getText());
        } else if (x != null && y == null && operation != null) {
            y = new BigDecimal(pressedButton.getText());
        }
        if (x != null && y != null && operation != null) {
            textView.setText(calculations.calculate(x, y, operation).toString());
            clearValues();
            resultBuilder.setLength(0);
        }

        resultBuilder.append(pressedButton.getText());
        setTextViewText(resultBuilder.toString());
    }*/

    private void clearValues() {
        x = null;
        y = null;
        operation = null;
    }

    private void appendToTextView(String text) {
        if (text.startsWith("sqrt")) {
            text = "\u221A";
        }
        if (text.startsWith("pow")) {
            text = "\u00B3";
        }

        textView.setText(textView.getText() + text);
    }
}
