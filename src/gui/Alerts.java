package gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public abstract class Alerts {
    public static void exitAlert(Stage stage){
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("EXIT");
        exitAlert.setHeaderText("You're about to exit the FOOD SERVICE SYSTEM");
        exitAlert.setContentText("Are sure you want to continue?! ");
        if (exitAlert.showAndWait().get() == ButtonType.OK)
            stage.close();
    }
    public static void emptyFieldAlert(){
        Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
        invalidAlert.setTitle("Validate Fields");
        invalidAlert.setHeaderText("OOPS!");
        invalidAlert.setContentText("Fields Shouldn't Be Empty!");
        invalidAlert.showAndWait();
    }

    public static void addUserAlert(){
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("User Adding");
        done.setHeaderText("DONE!");
        done.setContentText("User is added:)");
        done.show();
    }
}
