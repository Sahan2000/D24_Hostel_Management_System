package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.UserBO;
import lk.ijse.d24_hostel_management_system.entity.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class LoginPageFormController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton loginBtn;
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

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

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
        loginBtn.fire();
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
        loginBtn.fire();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        passwordField.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showPassword.setVisible(false);
        txtPassword.setVisible(false);
    }

    @FXML
    void loginBtnOnAction(ActionEvent event) throws IOException {
        String userName = txtUserName.getText();
        String password = passwordField.getText();
        String password1 = txtPassword.getText();

        boolean isValid = userBO.checkUser(userName,password,password1);
        if (isValid){
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_window_form.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(anchorPane));
            Stage stage1 = (Stage) root.getScene().getWindow();
            stage1.close();
            stage.setTitle("D24 Hostel Management System - Dashboard");
            stage.centerOnScreen();
            stage.show();
        }else{
            new Alert(Alert.AlertType.ERROR, "Username and Password incorrect!...").show();
        }
    }

    @FXML
    void signUpOnMouseClicked(MouseEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/registerration_form.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("Sign up page");
        stage.centerOnScreen();
        stage.show();
    }
}
