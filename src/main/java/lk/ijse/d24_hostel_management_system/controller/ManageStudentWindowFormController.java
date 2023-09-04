package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ManageStudentWindowFormController implements Initializable {

    @FXML
    private JFXTextField txtStudentId;

    @FXML
    private JFXTextField txtStudentName;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXTextField txtContact;

    @FXML
    private DatePicker dob;

    @FXML
    private RadioButton maleBtn;

    @FXML
    private RadioButton femaleBtn;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(txtStudentId.getText());
        studentDTO.setName(txtStudentName.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setContact_no(txtContact.getText());
        studentDTO.setDate(Date.valueOf(dob.getValue()));
        studentDTO.setGender((maleBtn.isSelected()) ? "Male" : "Female");

        boolean isDeleted = studentBO.deleteStudent(studentDTO);

        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!...").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Student not deleted!...").show();
        }
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent_id(txtStudentId.getText());
        studentDTO.setName(txtStudentName.getText());
        studentDTO.setAddress(txtAddress.getText());
        studentDTO.setContact_no(txtContact.getText());
        studentDTO.setDate(Date.valueOf(dob.getValue()));
        studentDTO.setGender((maleBtn.isSelected()) ? "Male" : "Female");

        boolean isSaved = studentBO.saveStudent(studentDTO);
        if (isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Student saved!...").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Student not saved!...").show();
        }
    }

    @FXML
    void txtAddressOnAction(ActionEvent event) {
        txtContact.requestFocus();
    }

    @FXML
    void txtContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtStudentIdOnAction(ActionEvent event) {
        txtStudentName.requestFocus();
    }

    @FXML
    void txtStudentNameOnAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAll();
        generateNextId();
    }

    private void getAll() {

    }

    private void generateNextId() {
        String nextId = studentBO.generatenextStudentId();
        txtStudentId.setText(nextId);
    }
}
