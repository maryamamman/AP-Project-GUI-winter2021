package controllerAndView;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.self.FoodHandler;
import model.self.Reserve;
import model.self.Self;
import model.user.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReserveSceneController implements Initializable {

    @FXML
    private RadioButton breakfast, lunch, dinner;

    @FXML
    private TextField dayGetter;

    @FXML
    private ChoiceBox<String> food, self;

    @FXML
    private Label numError;

    @FXML
    private AnchorPane reserveScene;

    private Parent root;
    private Stage stage;
    private Scene scene;
    private String type;
    private int day;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> selves = new ArrayList<>(Self.selves.keySet());
        self.getItems().addAll(selves);
    }

    public void reserveFood(ActionEvent event) {
        if (validateFields()) {
            if (Time.day <= day - 2){
                Student student = LoginController.student;
                if (!student.hasFood(day, type) && student.canReserve(type)) {
                    String foodName = food.getValue();
                    int price = 0;
                    String selfName = self.getValue();
                    switch (type) {
                        case "breakfast" -> price = FoodHandler.breakfastPrice.get(foodName);
                        case "lunch" -> price = FoodHandler.lunchPrice.get(foodName);
                        case "dinner" -> price = FoodHandler.dinnerPrice.get(foodName);
                    }
                    if (student.wallet >= price) {
                        Reserve reserve = new Reserve(day, type, foodName, price, selfName, student.id);
                        student.reserve(reserve);
                        Self.selves.get(selfName).reserveFood(day, type, foodName, student.id);
                        Alerts.reserveAlert();
                    } else Alerts.notEnoughCreditAlert();
                } else Alerts.cantReserveAlert();
            } else Alerts.reserveTimesUpAlert();
            emptyFields();
        } else Alerts.emptyFieldAlert();
    }

    public void foodType(ActionEvent event) {
        try {
            numError.setText(null);
            day = Integer.parseInt(dayGetter.getText());
            try {
                if (breakfast.isSelected()) {
                    food.getItems().clear();
                    type = "breakfast";
                    food.getItems().addAll(FoodHandler.breakfastTable.get(day));
                } else if (lunch.isSelected()) {
                    food.getItems().clear();
                    type = "lunch";
                    food.getItems().addAll(FoodHandler.lunchTable.get(day));
                } else if (dinner.isSelected()) {
                    food.getItems().clear();
                    type = "dinner";
                    food.getItems().addAll(FoodHandler.dinnerTable.get(day));
                }
            } catch (NullPointerException e) {
                numError.setText("No menu for chosen day!");
            }
        } catch (NumberFormatException e) {
            numError.setText("Number only for day!");
        }
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) reserveScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    private boolean validateFields() {
        return !dayGetter.getText().isEmpty() && (food.getValue() != null) && (self.getValue() != null) && (breakfast.isSelected() || lunch.isSelected() || dinner.isSelected());
    }

    private void emptyFields() {
        dayGetter.setText(null);
        food.setValue(null);
        self.setValue(null);
        breakfast.setSelected(false);
        lunch.setSelected(false);
        dinner.setSelected(false);
    }
}
