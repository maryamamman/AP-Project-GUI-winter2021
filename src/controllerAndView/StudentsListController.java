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
import model.user.Student;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentsListController implements Initializable {

    @FXML
    private Label id, name;

    @FXML
    private AnchorPane studentListScene;

    private Parent root;
    private Scene scene;
    private Stage stage;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Student student : Student.students){
            name.setText(name.getText() + "\n" + student.name);
            id.setText(id.getText() + "\n" + student.id);
        }
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) studentListScene.getScene().getWindow());
    }
}
