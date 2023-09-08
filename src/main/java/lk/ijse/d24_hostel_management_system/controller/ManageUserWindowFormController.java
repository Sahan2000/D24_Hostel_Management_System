package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.UserBO;
import lk.ijse.d24_hostel_management_system.dto.UserDTO;
import lk.ijse.d24_hostel_management_system.tm.StudentTM;
import lk.ijse.d24_hostel_management_system.tm.UserTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageUserWindowFormController implements Initializable {

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
    private TableView<UserTM> userTbl;

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

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        UserTM selectedItem = userTbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = userBO.deleteUser(selectedItem.getUserId());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "User deleted!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "User not deleted   !...").show();
            }
        }
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(txtId.getText());
        userDTO.setUserName(txtName.getText());
        userDTO.setPassword(txtPassword.getText());
        userDTO.setPasswordHint(txtPasswordHint.getText());
        userDTO.setEmail(txtEmail.getText());

        if (saveBtn.getText().equals("Save")){
            boolean isSaved = userBO.saveUser(userDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User saved!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "User not saved!...").show();
            }
        }else if (saveBtn.getText().equals("Update")){
            boolean isUpdated = userBO.updateUser(userDTO);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "User Updated!...").show();
                getAll();
            }else {
                new Alert(Alert.AlertType.ERROR, "User not Updated!...").show();
            }
        }
    }

    private void getAll() {
        ObservableList<UserTM> observableList = FXCollections.observableArrayList();
        List<UserDTO> customerDTOList = userBO.getAllUser();
        for (UserDTO userDTO : customerDTOList) {
            observableList.add(new UserTM(
                            userDTO.getUserId(),
                            userDTO.getUserName(),
                            userDTO.getEmail(),
                            userDTO.getPassword(),
                            userDTO.getPasswordHint()
                    )
            );
        }
        userTbl.setItems(observableList);
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
        UserTM selectedItem = userTbl.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                deleteBtn.setDisable(false);
                txtId.setText(selectedItem.getUserId());
                txtName.setText(selectedItem.getUserName());
                txtEmail.setText(selectedItem.getEmail());
                txtPassword.setText(selectedItem.getPassword());
                txtPasswordHint.setText(selectedItem.getPassword_hint());
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPasswordHint.setCellValueFactory(new PropertyValueFactory<>("password_hint"));
    }
}
