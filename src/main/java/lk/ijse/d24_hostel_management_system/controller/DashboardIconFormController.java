package lk.ijse.d24_hostel_management_system.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.ReservationBO;
import lk.ijse.d24_hostel_management_system.bo.custom.RoomBO;
import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;
import lk.ijse.d24_hostel_management_system.dto.ReservationDTO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;
import lk.ijse.d24_hostel_management_system.tm.StudentTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardIconFormController implements Initializable {
    @FXML
    private TextField txtRooms;

    @FXML
    private PieChart pieChart;

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
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    private void generateAvailableRoomsCount() {
        String availableRoomsCount = roomBO.availableRoomsCount();
        txtRooms.setText(availableRoomsCount);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateAvailableRoomsCount();
        colId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("contact_no"));

        loadUnPaidStudentTbl();
        loadPieChart();
    }

    private void loadPieChart() {
        List<ReservationDTO> reservations = reservationBO.getAllReservation();
        int pending = 0;
        int done = 0;
        for (ReservationDTO reservationDTO : reservations){
            if (reservationDTO.getStatus().equals("paid")){
                done++;
            }else if (reservationDTO.getStatus().equals("un-paid")){
                pending++;
            }
        }
        ObservableList<PieChart.Data> pieData= FXCollections.observableArrayList(
                new PieChart.Data("Completed Payments", done),
                new PieChart.Data("Pending Payments", pending)
        );

        pieData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " : ", data.pieValueProperty()
                        )
                )
        );
        pieChart.setData(pieData);
    }

    private void loadUnPaidStudentTbl() {
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
}
