package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ManageUserWindowFormController {

    @FXML
    private JFXButton searchBtn;

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtPassword;

    @FXML
    private JFXTextField txtPasswordHint;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private TableView<?> userTbl;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colPasswordHint;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    void deleteBtnOnAction(ActionEvent event) {

    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {

    }

    @FXML
    void searchBtnOnAction(ActionEvent event) {

    }

    @FXML
    void txtEmailOnAction(ActionEvent event) {
        saveBtn.fire();
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {
        txtName.requestFocus();
    }

    @FXML
    void txtNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    void txtPasswordHintOnAction(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    void txtPasswordOnAction(ActionEvent event) {
        txtPasswordHint.requestFocus();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void userTblOnMouseClicked(MouseEvent event) {

    }

}
