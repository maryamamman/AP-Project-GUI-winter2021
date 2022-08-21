package controllerAndView;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminMainController implements Initializable {
    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private AnchorPane adminMenuScene;

    @FXML
    private Label day, meal;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        day.setText("Day: " + Time.day);
        meal.setText("Meal: " + Time.currentMeal());
    }

    public void addUserButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddUser.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void addFoodButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddFood.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void showDemandsButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ShowDemands.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void setMenuButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SetMenu.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void nextMealButton(ActionEvent event) {
        Time.nextMeal();
        day.setText("Day: " + Time.day);
        meal.setText("Meal: " + Time.currentMeal());
    }

    public void adminsButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminList.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void distributorsButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DistributorList.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void studentsButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentsList.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) adminMenuScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginMenu.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

}
