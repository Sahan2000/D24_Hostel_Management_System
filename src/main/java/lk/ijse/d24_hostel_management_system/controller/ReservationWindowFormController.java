package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ReservationWindowFormController {
    @FXML
    private JFXButton newReservationBtn;

    @FXML
    void newReservationBtnOnAction(ActionEvent event) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("/view/add_new_reservation_window_form.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.setTitle("D24-Hostel-Management-System-Add_New_Reservation");
        stage.centerOnScreen();
        stage.show();
    }
}
