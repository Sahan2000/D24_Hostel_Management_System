package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UnPaidCaseFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton backBtn;

    @FXML
    private TableView<StudentTM> unPaidStudentTbl;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colMobile;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("contact_no"));

        refreshUnpaidStudentTable();
    }

    private void refreshUnpaidStudentTable() {
        try {
            List<StudentDTO> list = studentBO.getUnpaidStudents();
            ObservableList<StudentTM> stdTmList = FXCollections.observableArrayList();
            for (StudentDTO ele : list) {
                StudentTM studentTm = new StudentTM();
                studentTm.setStudent_id(ele.getStudent_id());
                studentTm.setName(ele.getName());
                studentTm.setAddress(ele.getAddress());
                studentTm.setContact_no(ele.getContact_no());
                stdTmList.add(studentTm);
            }
            unPaidStudentTbl.setItems(stdTmList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
        }
    }

    @FXML
    void backBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/reservation_window_form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }
}
