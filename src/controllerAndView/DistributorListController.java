package controllerAndView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.user.Distributor;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DistributorListController implements Initializable {

    @FXML
    private Label name, selfName;

    @FXML
    private AnchorPane distributorListScene;

    private Parent root;
    private Stage stage;
    private Scene scene;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Distributor distributor : Distributor.distributors) {
            name.setText(name.getText() + "\n" + distributor.name);
            selfName.setText(selfName.getText() + "\n" + distributor.selfName);
        }
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) distributorListScene.getScene().getWindow());
    }
}
