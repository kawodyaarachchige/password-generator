package org.example.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.security.SecureRandom;

public class PasswordGeneratorController {
    @FXML
    private Slider lengthSlider;

    @FXML
    private CheckBox upperCaseCheckBox;

    @FXML
    private CheckBox lowerCaseCheckBox;

    @FXML
    private CheckBox symbolCheckBox;

    @FXML
    private CheckBox numberCheckBox;

    @FXML
    private TextField passwordField;
    @FXML
    private Label lengthLabel;

    @FXML
    private Button generateButton;

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "!@#$%&*()_+-=[]|,./?><";

    private static final String PASSWORD_CHARS = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static final SecureRandom secureRandom = new SecureRandom();


   public void initialize() {
       lengthSlider.setValue(10);
       lengthLabel.setText(String.valueOf((int) lengthSlider.getValue()));
       lengthSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
           lengthLabel.setText(String.valueOf(newValue.intValue()));
           resetFields();
    }
    );}

    private void enableGenerateButton(ActionEvent event) {
        if (upperCaseCheckBox.isSelected() || lowerCaseCheckBox.isSelected() || symbolCheckBox.isSelected() || numberCheckBox.isSelected()) {
            generateButton.setDisable(false);
        } else {
            generateButton.setDisable(true);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void generatePasswordOnAction(ActionEvent actionEvent) {
            int length = (int) lengthSlider.getValue();
            StringBuilder password = new StringBuilder(length);
            String charsToUse = "";

            if (upperCaseCheckBox.isSelected()) {
                charsToUse += CHAR_UPPER;
            }
            if (lowerCaseCheckBox.isSelected()) {
                charsToUse += CHAR_LOWER;
            }
            if (numberCheckBox.isSelected()) {
                charsToUse += NUMBER;
            }
            if (symbolCheckBox.isSelected()) {
                charsToUse += OTHER_CHAR;
            }

            if (charsToUse.isEmpty()) {
                showAlert("Please select at least one option.");
                return;
            }

            for (int i = 0; i < length; i++) {
                int randomIndex = secureRandom.nextInt(charsToUse.length());
                password.append(charsToUse.charAt(randomIndex));
            }

            passwordField.setText(password.toString());
        }
       private void resetFields() {
           passwordField.clear();
           upperCaseCheckBox.setSelected(false);
           lowerCaseCheckBox.setSelected(false);
           symbolCheckBox.setSelected(false);
           numberCheckBox.setSelected(false);
       }

}
