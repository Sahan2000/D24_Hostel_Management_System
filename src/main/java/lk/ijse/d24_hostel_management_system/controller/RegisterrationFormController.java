package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.UserBO;
import lk.ijse.d24_hostel_management_system.dto.UserDTO;
import lk.ijse.d24_hostel_management_system.entity.User;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterrationFormController implements Initializable {
    @FXML
    private JFXTextField txtUserName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtRepeatPassword;

    @FXML
    private JFXTextField txtPasswordHint;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton regiterationBtn;

    @FXML
    private JFXTextField txtuserID;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    @FXML
    void regiterationBtnOnAction(ActionEvent event) {
        String id = txtuserID.getText();
        String name = txtUserName.getText();
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        String passwordHint = txtPasswordHint.getText();

        UserDTO userDTO = new UserDTO(id,name,email,password,passwordHint);
        boolean isSaved = userBO.saveUser(userDTO);

        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Registeration Successful!...").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"User not Add!...").show();
        }
    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        regiterationBtn.fire();
    }

    @FXML
    void txtPasswordHintOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtRepeatPasswordOnAction(ActionEvent event) {
        txtPasswordHint.requestFocus();
    }

    @FXML
    void txtUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtuserIDOnAction(ActionEvent event) {
        txtUserName.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtRepeatPassword.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateNextId();
    }

    private void generateNextId() {
        String nextId = userBO.generatenextUserId();
        txtuserID.setText(nextId);
    }
}
