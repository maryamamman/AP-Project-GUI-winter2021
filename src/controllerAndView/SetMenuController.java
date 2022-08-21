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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class SetMenuController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;
    private int day;
    private String foodType;
    private final ArrayList<String> breakfastFood = new ArrayList<>();
    private final ArrayList<String> lunchFood = new ArrayList<>();
    private final ArrayList<String> dinnerFood = new ArrayList<>();

    @FXML
    private TextField dayGetter;

    @FXML
    private AnchorPane setMenuScene;

    @FXML
    private RadioButton breakfast, lunch, dinner;

    @FXML
    private Label numError;

    @FXML
    private ChoiceBox<String> food1, food2;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        breakfastFood.addAll(FoodHandler.breakfastPrice.keySet());
        lunchFood.addAll(FoodHandler.lunchPrice.keySet());
        dinnerFood.addAll(FoodHandler.dinnerPrice.keySet());
    }

    public void foodType(ActionEvent event) {
        if (breakfast.isSelected()) {
            foodType = "breakfast";
            food1.getItems().clear();
            food2.getItems().clear();
            food1.getItems().addAll(FoodHandler.breakfastPrice.keySet());
            food2.getItems().addAll(FoodHandler.breakfastPrice.keySet());
        } else if (lunch.isSelected()) {
            foodType = "lunch";
            food1.getItems().clear();
            food2.getItems().clear();
            food1.getItems().addAll(FoodHandler.lunchPrice.keySet());
            food2.getItems().addAll(FoodHandler.lunchPrice.keySet());
        } else if (dinner.isSelected()) {
            foodType = "dinner";
            food1.getItems().clear();
            food2.getItems().clear();
            food1.getItems().addAll(FoodHandler.dinnerPrice.keySet());
            food2.getItems().addAll(FoodHandler.dinnerPrice.keySet());
        }
    }

    public void addToMenu(ActionEvent event) {
        try {
            if (validateFields()) {
                if (!Objects.equals(food1.getValue(), food2.getValue())) {
                    day = Integer.parseInt(dayGetter.getText());
                    if (!FoodHandler.hasMenu(day, foodType)) {
                        FoodHandler.setFood(food1.getValue(), food2.getValue(), day, foodType);
                        Alerts.addFoodAlert();
                    } else Alerts.hasMenuAlert();
                    emptyFields();
                } else Alerts.sameFoodAlert();
            } else Alerts.emptyFieldAlert();
        } catch (NumberFormatException e) {
            numError.setText("Numbers Only for day!");
        }

    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) setMenuScene.getScene().getWindow());
    }

    public boolean validateFields() {
        return !dayGetter.getText().isEmpty() && food1.getValue() != null && food2.getValue() != null;
    }

    public void emptyFields() {
        dayGetter.setText(null);
        breakfast.setSelected(true);
        food1.setValue(null);
        food2.setValue(null);
    }
}
