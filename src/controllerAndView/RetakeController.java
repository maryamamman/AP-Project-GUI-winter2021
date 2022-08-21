package controllerAndView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.self.Reserve;
import model.self.Self;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class RetakeController implements Initializable {

    @FXML
    private ChoiceBox<String> foodChoiceBox;

    @FXML
    private Button retakeButton, waitButton;

    @FXML
    private AnchorPane retakeScene;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private ArrayList<String> food = new ArrayList<>();
    private Reserve selectedReserve;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (Reserve reserve : LoginController.student.reserveList) {
            if (!reserve.eaten) {
                int day = reserve.day;
                String type = reserve.type;
                String foodName = reserve.foodName;
                int price = reserve.price;
                String selfName = reserve.selfName;
                food.add("Day:" + day + " , Type:" + type + " , Food:" + foodName + " , Price:" + price + " , Self:" + selfName);
            }
        }
        foodChoiceBox.getItems().addAll(food);
    }

    public void retaking(ActionEvent event) {
        for (Reserve reserve : LoginController.student.reserveList) {
            if (Objects.equals(foodChoiceBox.getValue(), "Day:" + reserve.day + " , Type:" + reserve.type + " , Food:" + reserve.foodName + " , Price:" + reserve.price + " , Self:" + reserve.selfName)) {
                selectedReserve = reserve;
                if (Time.day <= reserve.day - 2) {
                    LoginController.student.retake(reserve);
                    Self.selves.get(reserve.selfName).retakeFood(reserve.day, reserve.type, reserve.foodName, LoginController.student.id);
                    Alerts.retakeAlert();
                }else {
                    Alerts.retakeTimesUpAlert();
                    retakeButton.setVisible(false);
                    waitButton.setVisible(true);
                }
                break;
            }
        }
    }

    public void waitList(ActionEvent event){
        Self self = Self.selves.get(selectedReserve.selfName);
        self.addToWaitList(selectedReserve, LoginController.student.id);
        LoginController.student.sendToWaitList(selectedReserve);
        Alerts.addToWaitListAlert();
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) retakeScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }
}