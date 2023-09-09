package lk.ijse.d24_hostel_management_system.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardWindowFormController implements Initializable {

    @FXML
    private JFXButton dashboardBtn;

    @FXML
    private JFXButton manageStudentBtn;

    @FXML
    private JFXButton manageRoomsBtn;

    @FXML
    private JFXButton manageUsersBtn;

    @FXML
    private AnchorPane root1;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton reservationBtn;

    @FXML
    void reservationBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/reservation_window_form.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDshboardIcon();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDshboardIcon() throws IOException {
        setForms("/view/dashboard_icon_form.fxml");
    }

    @FXML
    void manageStudentOnAction(ActionEvent event) throws IOException {
        setForms("/view/manage_student_window_form.fxml");
    }

    @FXML
    void dashboardBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/dashboard_icon_form.fxml");
    }

    @FXML
    void manageRoomsBtnOnAtion(ActionEvent event) throws IOException {
        setForms("/view/manage_rooms_window_form.fxml");
    }

    @FXML
    void manageUsersBtnOnAction(ActionEvent event) throws IOException {
        setForms("/view/manage_user_window_form.fxml");
    }


    private void setForms(String forms) throws IOException {

        String[] formArrays = {"/view/manage_student_window_form.fxml","/view/manage_user_window_form.fxml", "/view/dashboard_icon_form.fxml", "/view/manage_rooms_window_form.fxml", "/view/reservation_window_form.fxml"};

        JFXButton[] btnArray = {manageStudentBtn,manageUsersBtn,dashboardBtn,manageRoomsBtn,reservationBtn};

        AnchorPane load = FXMLLoader.load(getClass().getResource(forms));
        root.getChildren().clear();
        root.getChildren().add(load);

        for (int i = 0; i < formArrays.length; i++) {
            btnArray[i].setStyle("-fx-background-color: #0C829B;");

            if (forms.equals(formArrays[i])){
                btnArray[i].setStyle("-fx-background-color:  #D3D3D3; -fx-text-fill: black");
            }
        }
    }

    @FXML
    void logoutBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/login_page_form.fxml"));
        Stage stage = (Stage) root1.getScene().getWindow();
        stage.close();
        Stage stage1 = new Stage();
        stage1.setScene(new Scene(load));
        stage1.setTitle("D24 Hostel Management System - Login page");
        stage1.centerOnScreen();
        stage1.show();
    }
}
