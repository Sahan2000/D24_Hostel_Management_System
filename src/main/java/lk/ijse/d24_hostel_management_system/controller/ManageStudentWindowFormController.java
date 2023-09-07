package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXTextField;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24_hostel_management_system.tm.StudentTM;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


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

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXTextField txtSearch;

    @FXML
    private JFXButton searchBtn;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        StudentTM selectedItem = studentTbl.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = studentBO.deleteStudent(selectedItem.getStudent_id());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student deleted!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not deleted   !...").show();
            }
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
        if (saveBtn.getText().equals("Save")) {
            boolean isSaved = studentBO.saveStudent(studentDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student saved!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not saved!...").show();
            }
        } else if(saveBtn.getText().equals("Update")) {
            boolean isUpdated = studentBO.updateStudent(studentDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student updated!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Student not updated!...").show();
            }
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
        deleteBtn.setDisable(true);
    }

    private StudentTM toStudentTm(StudentDTO studentDto) {
        StudentTM studentTm = new StudentTM();
        studentTm.setStudent_id(studentDto.getStudent_id());
        studentTm.setName(studentDto.getName());
        studentTm.setAddress(studentDto.getAddress());
        studentTm.setDob(studentDto.getDate());
        studentTm.setGender(studentDto.getContact_no());
        studentTm.setGender(studentDto.getGender());
        return studentTm;
    }

    private void clearAll() {
        txtContact.clear();
        txtAddress.clear();
        txtContact.clear();
        txtStudentName.clear();
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

    @FXML
    void studentTblOnMouseClicked(MouseEvent event) {
        StudentTM selectedItem = studentTbl.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                deleteBtn.setDisable(false);
                txtStudentId.setText(selectedItem.getStudent_id());
                txtStudentName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                if (selectedItem.getGender().equals("Male")) {
                    maleBtn.setSelected(true);
                } else {
                    femaleBtn.setSelected(true);
                }
                txtContact.setText(selectedItem.getContact_no());
                dob.setValue(selectedItem.getDob().toLocalDate());
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        searchBtn.fire();
    }

    @FXML
    void searchBtnOnAction(ActionEvent event) {

    }
}
