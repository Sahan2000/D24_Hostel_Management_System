package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ForgotPasswordFormController {
    @FXML
    private JFXButton continueBtn;

    @FXML
    private AnchorPane root;
    @FXML
    void continueBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/resetPassword_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
