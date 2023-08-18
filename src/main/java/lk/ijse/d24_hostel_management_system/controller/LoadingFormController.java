package lk.ijse.d24_hostel_management_system.controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingFormController implements Initializable {

    @FXML
    private ProgressBar prgBar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    updateProgress(i, 55);
                    Thread.sleep(55);
                }
                return null;
            }
        };

        prgBar.progressProperty().bind(task.progressProperty());
        task.setOnSucceeded(event -> {

            Parent loginParent = null;
            try {
                loginParent = FXMLLoader.load(getClass().getResource("/view/login_page_form.fxml"));

                Scene loginScene = new Scene(loginParent);
                Stage loginStage = new Stage();
                loginStage.setResizable(false);
                Image icon = new Image(getClass().getResourceAsStream("/images/Screenshot_from_2023-08-17_09-02-38-removebg-preview.png"));
                loginStage.getIcons().add(icon);
                loginStage.setTitle("D24 Hostel Management System - Login Page");
                loginStage.setScene(loginScene);
                loginStage.show();
                /*new Thread(() -> {
                    playSound("media/loading.mp3");
                }).start();*/

                // Close the welcome stage
                ((Stage) prgBar.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        new Thread(task).start();
    }
}
