package com.example.calculatorapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Label displayLabel;

    @FXML
    public void onUtilityClick(ActionEvent actionEvent) {
    }
    @FXML
    public void onOperatorClick(ActionEvent actionEvent) {
    }
    @FXML
    public void onNumberClick(ActionEvent actionEvent) {
        String value = ((Button)actionEvent.getSource()).getText();
        if (displayLabel.getText().equals("0")){
            displayLabel.setText(value);
        }
        else {
            displayLabel.setText(displayLabel.getText() + value);
        }

    }

    @FXML
    public void onEqualsClick(ActionEvent actionEvent) {
    }

    public void initialize(){
        displayLabel.setText("0");
    }


    public void onACClick(ActionEvent actionEvent) {
        displayLabel.setText("0");
    }

    public void onPlusMinusClick(ActionEvent actionEvent) {

    }
}