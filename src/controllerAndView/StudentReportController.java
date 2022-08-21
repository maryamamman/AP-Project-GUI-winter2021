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
import model.self.Reserve;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentReportController implements Initializable {

    @FXML
    private AnchorPane reportScene;

    @FXML
    private Label day, type, name, self, eaten;

    private Stage stage;
    private Parent root;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Reserve reserve : LoginController.student.reserveList) {
            day.setText(day.getText() + "\n" + reserve.day);
            type.setText(type.getText() + "\n" + reserve.type);
            name.setText(name.getText() + "\n" + reserve.foodName);
            self.setText(self.getText() + "\n" + reserve.selfName);
            eaten.setText(eaten.getText() + "\n" + reserve.eaten);
        }
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) reportScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

}
