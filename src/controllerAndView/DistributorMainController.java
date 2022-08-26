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
import model.user.Distributor;
import model.user.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class DistributorMainController implements Initializable {
    @FXML
    private Label thisSelf, day, meal, information;

    @FXML
    private TextField idGetter;

    @FXML
    private ChoiceBox<String> waitListFood;

    @FXML
    private AnchorPane checkDemandScene, waitListScene;

    private int id;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private final Self self = Self.selves.get(LoginController.distributor.selfName);
    private final Distributor distributor = LoginController.distributor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        day.setText("Day: " + Time.day);
        meal.setText("Meal: " + Time.currentMeal());
        thisSelf.setText("This Self: " + distributor.selfName);
    }

    public void given(ActionEvent event) {
        if (validateFields()) {
            try {
                id = Integer.parseInt(idGetter.getText());
                if (distributor.showDemand(id) != null) {
                    distributor.giveFood(id);
                    Alerts.giveFoodAlert();
                    idGetter.setText(null);
                    information.setText("");
                } else information.setText("No Demand!");
            } catch (NumberFormatException e) {
                information.setText("Number only for ID!");
            }
        } else Alerts.emptyFieldAlert();
    }

    public void showInformation(ActionEvent event) {
        if (validateFields()) {
            try {
                id = Integer.parseInt(idGetter.getText());
                if (distributor.showDemand(id) != null) {
                    information.setText("Name: " + Student.getStudent(id).name + "\nFood: " + distributor.showDemand(id));
                } else information.setText("No Demand");
            } catch (NumberFormatException e) {
                information.setText("Number only for ID!");
            }
        } else Alerts.emptyFieldAlert();
    }

    public void waitListShow(ActionEvent event) {
        waitListFood.getItems().clear();
        ArrayList<String> stringReserve = new ArrayList<>();
        for (Reserve reserve : self.waitList) {
            if (reserve.day == Time.day && Objects.equals(reserve.type, Time.currentMeal()))
                stringReserve.add(reserve.foodName + " - " + reserve.price);
        }
        waitListFood.getItems().addAll(stringReserve);
        waitListScene.setVisible(true);
    }

    public void closeWaitList(ActionEvent event) {
        waitListScene.setVisible(false);
    }

    public void sell(ActionEvent event) {
        if (validateWaitListFields()) {
            for (Reserve reserve : self.waitList) {
                if (reserve.day == Time.day && Objects.equals(reserve.type, Time.currentMeal())) {
                    self.selectFromWaitList(reserve);
                    Student.getStudent(reserve.ownerId).selectedFromWaitList(reserve);
                    waitListFood.setValue(null);
                    Alerts.sellFoodAlert();
                    waitListScene.setVisible(false);
                    break;
                }
            }
        } else Alerts.emptyFieldAlert();
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) checkDemandScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginMenu.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    private boolean validateFields() {
        return !idGetter.getText().isEmpty();
    }

    private boolean validateWaitListFields() {
        return waitListFood.getValue() != null;
    }
}
