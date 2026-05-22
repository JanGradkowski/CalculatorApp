package com.example.calculatorapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Label displayLabel;

    private double firstNumber = 0;
    private double secondNumber = 0;
    private String currentOperator = "";
    private boolean isNewNumber = true;

    @FXML
    public void initialize() {
        displayLabel.setText("0");
    }

    @FXML
    public void onNumberClick(ActionEvent actionEvent) {
        String buttonText = ((Button) actionEvent.getSource()).getText();
        String currentText = displayLabel.getText();

        if (isNewNumber) {
            if (buttonText.equals(".")) {
                displayLabel.setText("0.");
            } else {
                displayLabel.setText(buttonText);
            }
            isNewNumber = false;
        } else {
            if (buttonText.equals(".")) {
                if (currentText.contains(".")) {
                    return;
                }
            }

            if (currentText.equals("0") && !buttonText.equals(".")) {
                displayLabel.setText(buttonText);
            } else {
                displayLabel.setText(currentText + buttonText);
            }
        }
    }

    @FXML
    public void onOperatorClick(ActionEvent actionEvent) {
        try {
            firstNumber = Double.parseDouble(displayLabel.getText());
        } catch (NumberFormatException e) {
            return;
        }

        currentOperator = ((Button) actionEvent.getSource()).getText();
        isNewNumber = true;
    }

    @FXML
    public void onEqualsClick(ActionEvent actionEvent) {
        if (currentOperator.isEmpty()) {
            return;
        }

        try {
            secondNumber = Double.parseDouble(displayLabel.getText());
        } catch (NumberFormatException e) {
            return;
        }

        double result = 0;
        switch (currentOperator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber == 0) {
                    displayLabel.setText("Error");
                    isNewNumber = true;
                    currentOperator = "";
                    return;
                }
                result = firstNumber / secondNumber;
                break;
        }

        displayLabel.setText(formatResult(result));

        currentOperator = "";
        isNewNumber = true;
    }

    @FXML
    public void onACClick(ActionEvent actionEvent) {
        displayLabel.setText("0");
        isNewNumber = true;
        currentOperator = "";
        firstNumber = 0;
        secondNumber = 0;
    }

    @FXML
    public void onPlusMinusClick(ActionEvent actionEvent) {
        try {
            double currentNumber = Double.parseDouble(displayLabel.getText());
            if (currentNumber != 0) {
                currentNumber = currentNumber * -1;
                displayLabel.setText(formatResult(currentNumber));
            }
        } catch (NumberFormatException e) {
            System.out.println("There seems to be a problem");
        }
    }

    @FXML
    public void onUtilityClick(ActionEvent actionEvent) {
        try {
            // Calculates percentage by dividing current display by 100
            double currentNumber = Double.parseDouble(displayLabel.getText());
            currentNumber = currentNumber / 100;
            displayLabel.setText(formatResult(currentNumber));
            isNewNumber = true;
        } catch (NumberFormatException e) {
            System.out.println("There seems to be a problem");
        }
    }

    /**
     * Helper method to strip trailing ".0" for whole numbers
     */
    private String formatResult(double number) {
        String text = String.valueOf(number);
        if (text.endsWith(".0")) {
            return text.substring(0, text.length() - 2);
        }
        return text;
    }
}