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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.d24_hostel_management_system.bo.BOFactory;
import lk.ijse.d24_hostel_management_system.bo.custom.RoomBO;
import lk.ijse.d24_hostel_management_system.dto.RoomDTO;
import lk.ijse.d24_hostel_management_system.tm.RoomTM;
import lk.ijse.d24_hostel_management_system.tm.StudentTM;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageRoomsWindowFormController implements Initializable {

    @FXML
    private JFXTextField txtId;
    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private JFXTextField txtKeyMoney;

    @FXML
    private JFXTextField txtQty;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private TableView<RoomTM> roomTble;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableColumn<?, ?> colKeyMoney;

    @FXML
    private TableColumn<?, ?> colQty;

    RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        RoomTM selectedItem = roomTble.getSelectionModel().getSelectedItem();
        if (selectedItem != null){
            boolean isDeleted = roomBO.deleteRoom(selectedItem.getRoom_type_id());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room deleted!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room not deleted   !...").show();
            }
        }
    }

    @FXML
    void roomTbleOnMouseClicked(MouseEvent event) {
        RoomTM selectedItem = roomTble.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                deleteBtn.setDisable(false);
                txtId.setText(selectedItem.getRoom_type_id());
                cmbType.setValue(selectedItem.getType());
                txtKeyMoney.setText(String.valueOf(selectedItem.getKey_money()));
                txtQty.setText(String.valueOf(selectedItem.getQty()));
                saveBtn.setText("Update");
                saveBtn.setStyle("-fx-background-color: #1A5D1A");
            }

        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void saveBtnOnAction(ActionEvent event) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoom_type_id(txtId.getText());
        roomDTO.setType((String) cmbType.getValue());
        roomDTO.setKey_money(Double.valueOf(txtKeyMoney.getText()));
        roomDTO.setQty(Integer.valueOf(txtQty.getText()));
        if (saveBtn.getText().equals("Save")) {
            boolean isSaved = roomBO.saveRooms(roomDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room saved!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room not saved!...").show();
            }
        } else if(saveBtn.getText().equals("Update")) {
            boolean isUpdated = roomBO.updateRooms(roomDTO);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Room updated!...").show();
                getAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Room not updated!...").show();
            }
        }
    }

    private void getAll() {
        ObservableList<RoomTM> observableList = FXCollections.observableArrayList();
        List<RoomDTO> roomDTOList = roomBO.getAllRooms();
        for (RoomDTO roomDTO : roomDTOList) {
            observableList.add(new RoomTM(
                            roomDTO.getRoom_type_id(),
                            roomDTO.getType(),
                            roomDTO.getKey_money(),
                            roomDTO.getQty()
                    )
            );
        }
        roomTble.setItems(observableList);
    }

    @FXML
    void txtKeyMoneyOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

    @FXML
    void txtTypeOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadRoomType();
        getAll();
        setCellValueFactory();
        generateNextRoomId();
    }

    private void generateNextRoomId() {
        String nextId = roomBO.generatenextRoomId();
        txtId.setText(nextId);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }
    private void loadRoomType() {
        ObservableList<String> options = FXCollections.observableArrayList(
                "AC",
                "Non-AC",
                "AC / Food",
                "Non-Ac/ Food"
        );
        cmbType.setItems(options);
    }
}
