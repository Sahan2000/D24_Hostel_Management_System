package lk.ijse.d24_hostel_management_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/loading_form.fxml"));
        primaryStage.setScene(new Scene(load));
        primaryStage.setTitle("D24 hostel Management System");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
