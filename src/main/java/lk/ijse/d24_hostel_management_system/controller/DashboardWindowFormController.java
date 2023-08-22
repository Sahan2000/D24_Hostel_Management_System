package lk.ijse.d24_hostel_management_system.controller;

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
    private AnchorPane root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loadDshboardIcon();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDshboardIcon() throws IOException {
        AnchorPane load = FXMLLoader.load(getClass().getResource("/view/manage_user_window_form.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
