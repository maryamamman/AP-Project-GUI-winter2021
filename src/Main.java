import controllerAndView.Alerts;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Database;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        Database.read("database.json");
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("controllerAndView/LoginMenu.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("Food Service");
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            exiting(stage);
        });

    }

    private void exiting(Stage stage) {
        Alerts.exitAlert(stage);
    }
}
