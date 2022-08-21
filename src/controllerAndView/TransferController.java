package controllerAndView;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.self.Reserve;
import model.self.Self;
import model.user.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class TransferController implements Initializable {

    private ArrayList<String> food = new ArrayList<>();
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private ChoiceBox<String> foodChoiceBox;

    @FXML
    private TextField transferTo;

    @FXML
    private AnchorPane transferScene;

    @FXML
    private Label numError;

    private Student student = LoginController.student;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Reserve reserve : student.reserveList) {
            if (!reserve.eaten) {
                food.add("Day:" + reserve.day + " , Type:" + reserve.type + " , Food:" + reserve.foodName + " , Price:" + reserve.price + " , Self:" + reserve.selfName);
            }
        }
        foodChoiceBox.getItems().addAll(food);
    }

    public void transferring(ActionEvent event) {
        for (Reserve reserve : student.reserveList) {
            if (Objects.equals(foodChoiceBox.getValue(), "Day:" + reserve.day + " , Type:" + reserve.type + " , Food:" + reserve.foodName + " , Price:" + reserve.price + " , Self:" + reserve.selfName)) {
                try {
                    int toId = Integer.parseInt(transferTo.getText());
                    if (!Student.getStudent(toId).hasFood(reserve.day, reserve.type)) {
                        try {
                            numError.setText("");
                            student.transfer(reserve, toId);
                            Self.selves.get(reserve.selfName).transferFood(reserve.day, reserve.type, reserve.foodName, student.id, toId);
                            Alerts.transferAlert();
                        } catch (NullPointerException e) {
                            Alerts.studentNotFoundAlert();
                        }
                    }else numError.setText("This user have already reserved food!");
                } catch (NumberFormatException e) {
                    numError.setText("Numbers only for ID!");
                }
                break;
            }
        }

    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) transferScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }
}
