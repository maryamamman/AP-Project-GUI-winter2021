import gui.Alerts;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Database;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) throws Exception {
        //Database database = Database.read("database.json");
        launch();
        /*Database database = Database.read("database.json");
        Controller controller = new LoginController();
        while (controller != null) {
            controller = controller.run();
            Database.write("database.json", database);
        }*/
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("gui/LoginMenu.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(windowEvent -> {
            windowEvent.consume();
            exiting(stage);
        });

        Database database = Database.read("database.json");

    }

    private void exiting(Stage stage) {
        Alerts.exitAlert(stage);
    }
}
