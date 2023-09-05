package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ManageStudentWindowFormController implements Initializable {
    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAdress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;
    @FXML
    private TableView<StudentTM> studentTbl;

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
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    private void getAll() {
        ObservableList<StudentTM> observableList = FXCollections.observableArrayList();
        List<StudentDTO> customerDTOList = studentBO.getAllStudent();
        for (StudentDTO studentDTO : customerDTOList) {
            observableList.add(new StudentTM(
                    studentDTO.getStudent_id(),
                    studentDTO.getName(),
                    studentDTO.getAddress(),
                    studentDTO.getContact_no(),
                    studentDTO.getDate(),
                    studentDTO.getGender()
                    )
            );
        }
        studentTbl.setItems(observableList);
    }

    private void generateNextId() {
        String nextId = studentBO.generatenextStudentId();
        txtStudentId.setText(nextId);
    }
}
