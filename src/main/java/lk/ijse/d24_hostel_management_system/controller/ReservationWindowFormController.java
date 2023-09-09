package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.ReservationBO;
import lk.ijse.d24_hostel_management_system.bo.custom.RoomBO;
import lk.ijse.d24_hostel_management_system.dto.ReservationDTO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.tm.ReservationTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationWindowFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private JFXButton viewUnPaidBtn;


    @FXML
    private TableView<ReservationTM> reservationTbk;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colRoomType;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableView<RoomDTO> roomTbl;

    @FXML
    private TableColumn<?, ?> colRoomId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton markAsPaid;

    ReservationBO reservationBO = (ReservationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    @FXML
    private JFXButton newReservationBtn;

    @FXML
    void newReservationBtnOnAction(ActionEvent event) throws IOException {
        try {
            pane.setDisable(true);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setAlwaysOnTop(true);
            stage.requestFocus();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/add_new_reservation_window_form.fxml"))));
            stage.setTitle("Booking");
            stage.showAndWait();
            pane.setDisable(false);

            refreshRoomTable();
            getAll();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAll();
        setCellValueFactory();
        setCellValueFactoryRoom();
        refreshRoomTable();
    }

    private void setCellValueFactoryRoom() {
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    private void refreshRoomTable() {
        List<RoomDTO> all = roomBO.getAllRooms();
        ObservableList<RoomDTO> roomObservableList = FXCollections.observableArrayList();
        roomObservableList.addAll(all);
        roomTbl.setItems(roomObservableList);
    }

    private void getAll() {
        ObservableList<ReservationTM> observableList = FXCollections.observableArrayList();
        List<ReservationDTO> reservationDTOList = reservationBO.getAllReservation();
        for (ReservationDTO reservationDTO : reservationDTOList) {
            observableList.add(new ReservationTM(
                            reservationDTO.getRes_id(),
                            reservationDTO.getDate(),
                            reservationDTO.getStatus(),
                            reservationDTO.getStudentDto().getStudent_id(),
                            reservationDTO.getRoomDto().getRoom_type_id()
                    )
            );
        }
        reservationTbk.setItems(observableList);
    }

    private void setCellValueFactory() {
        colReservationId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        try {
            ReservationTM reservationTm = reservationTbk.getSelectionModel().getSelectedItem();
            if (reservationTm != null) {
                deleteBtn.setDisable(false);
                reservationBO.deleteReservation(reservationTm.getRes_id());
                new Alert(Alert.AlertType.ERROR, "Reservation Deleted : " + reservationTm.getRes_id()).show();
                refreshRoomTable();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Select Item First").show();
            }
            deleteBtn.setDisable(true);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    @FXML
    void markAsPaidOnAction(ActionEvent event) {
        try {
            ReservationTM selectedItem = reservationTbk.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                ReservationDTO dto = reservationBO.searchReservationByresId(selectedItem.getRes_id());
                dto.setStatus("paid");
                reservationBO.updateReservation(dto);
                new Alert(Alert.AlertType.INFORMATION, "Payment updated").show();
                getAll();
                refreshRoomTable();
            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    @FXML
    void viewUnPaidBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/unPaid_case_form.fxml"));
        pane.getChildren().clear();
        pane.getChildren().add(load);
    }


}
