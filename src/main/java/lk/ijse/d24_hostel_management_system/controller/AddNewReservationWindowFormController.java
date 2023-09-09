package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.ReservationBO;
import lk.ijse.d24_hostel_management_system.bo.custom.RoomBO;
import lk.ijse.d24_hostel_management_system.bo.custom.StudentBO;
import lk.ijse.d24_hostel_management_system.dto.ReservationDTO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.dto.StudentDTO;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewReservationWindowFormController implements Initializable {
    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtAvailableQty;

    @FXML
    private JFXTextField txtPricePerRoom;

    @FXML
    private ComboBox<String> cmbStudentId;

    @FXML
    private ComboBox<String> cmbRoomTypeId;

    @FXML
    private JFXButton payRoomBtn;

    @FXML
    private JFXButton bookBtn;

    @FXML
    private DatePicker date;

    @FXML
    private AnchorPane root;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    @FXML
    void bookBtnOnAction(ActionEvent event) {
        saveReservation("un-paid");
    }

    private void saveReservation(String status) {
        try {
            /*if (validateData()) {*/
                ReservationDTO reservationDto = new ReservationDTO();
                reservationDto.setRes_id(txtId.getText());
                reservationDto.setStatus(status);
                reservationDto.setDate(Date.valueOf(date.getValue()));

                StudentDTO studentDto = studentBO.searchbyStudentId(cmbStudentId.getValue());
                reservationDto.setStudentDto(studentDto);

                RoomDTO dto = roomBO.searchByRoomTypeId(cmbRoomTypeId.getValue());
                dto.setQty(dto.getQty() - 1);
                reservationDto.setRoomDto(dto);

                reservationBO.saveReservation(reservationDto);

                Stage stage = (Stage) root.getScene().getWindow();
                stage.setAlwaysOnTop(false);
                new Alert(Alert.AlertType.INFORMATION, "Added!").showAndWait();
                stage.setAlwaysOnTop(true);
                stage.close();
           /* }*/
        } catch (RuntimeException exception) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(false);
        }
    }

    @FXML
    void payRoomBtnOnAction(ActionEvent event) {
        saveReservation("paid");
    }

    @FXML
    void txtIdOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadStudentID();
        loadRoomTypeId();
        generateNextID();
    }

    private void loadRoomTypeId() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> roomBOCodes = roomBO.getCodes();
        for (String reservationId : roomBOCodes) {
            observableList.add(reservationId);
        }
        cmbRoomTypeId.setItems(observableList);
    }

    private void loadStudentID() {
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<String> reservation = studentBO.getCodes();
        for (String reservationId : reservation) {
            observableList.add(reservationId);
        }
        cmbStudentId.setItems(observableList);
    }

    private void generateNextID() {
        String nextId = reservationBO.generatenextReservationId();
        txtId.setText(nextId);
    }

    @FXML
    void cmbRoomTypeIdOnAction(ActionEvent event) {
        try {
            String selectedItem = cmbRoomTypeId.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                RoomDTO room = roomBO.searchByRoomTypeId(selectedItem);
                txtPricePerRoom.setText(String.valueOf(room.getKey_money()));
                txtAvailableQty.setText(String.valueOf(room.getQty()));

                if (room.getQty() != 0) {
                    payRoomBtn.setDisable(false);
                    bookBtn.setDisable(false);
                } else {
                    payRoomBtn.setDisable(true);
                    bookBtn.setDisable(true);
                    throw new RuntimeException("Room not available at the moment!");
                }
            } else {
                txtPricePerRoom.setText("0");
                txtAvailableQty.setText("0");
            }
        } catch (RuntimeException exception) {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(false);
        }
    }
}
