package adamsdd.calculator.view;

import adamsdd.calculator.model.Calculations;
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
        x = BigDecimal.valueOf(0);
        textView.setText(x.toString());
        resultBuilder = new StringBuilder();
    }

    public void buttonClick(ActionEvent actionEvent) {
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
    }

    private void clearValues() {
        x = null;
        y = null;
        operation = null;
    }

    private void setTextViewText(String text) {
        textView.setText(text);
    }
}
