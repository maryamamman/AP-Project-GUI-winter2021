package gui.admin;

import gui.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AdminMainController {

    @FXML
    private Button back, exit;

    @FXML
    private AnchorPane adminMenuScene;

    public void exiting(ActionEvent event) {
        Alerts.exitAlert((Stage) adminMenuScene.getScene().getWindow());
    }
}
