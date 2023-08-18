package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageFormController implements Initializable {
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private ImageView showPassword;

    @FXML
    private ImageView hidePassword;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    void hidePasswordOnMouseClicked(MouseEvent event) {
        String password = passwordField.getText();
        txtPassword.setText(password);
        showPassword.setVisible(true);
        hidePassword.setVisible(false);
        passwordField.setVisible(false);
        txtPassword.setVisible(true);
    }

    @FXML
    void passwordFieldOnAction(ActionEvent event) {

    }

    @FXML
    void showPasswordOnMouseClicked(MouseEvent event) {
        String password = txtPassword.getText();
        passwordField.setText(password);
        hidePassword.setVisible(true);
        showPassword.setVisible(false);
        passwordField.setVisible(true);
        txtPassword.setVisible(false);
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showPassword.setVisible(false);
        txtPassword.setVisible(false);
    }
}
