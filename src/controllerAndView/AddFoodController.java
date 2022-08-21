package controllerAndView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.self.FoodHandler;

import java.io.IOException;
import java.util.Objects;

public class AddFoodController {

    @FXML
    private RadioButton breakfast, lunch, dinner;

    @FXML
    private TextField foodName, foodPrice;

    @FXML
    private Label numError;

    @FXML
    private AnchorPane addFoodScene;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void addFood(ActionEvent event) {
        if (validateFields()) {
            try {
                numError.setText("");
                String foodType = "";
                if (breakfast.isSelected()) foodType = "breakfast";
                else if (lunch.isSelected()) foodType = "lunch";
                else if (dinner.isSelected()) foodType = "dinner";
                FoodHandler.addFood(foodName.getText(), Integer.parseInt(foodPrice.getText()), foodType);
                Alerts.addFoodAlert();
                emptyFields();
            }catch (NumberFormatException e){
                numError.setText("Number only for price!");
            }
        }else Alerts.emptyFieldAlert();
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) addFoodScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    private boolean validateFields() {
        return !foodName.getText().isEmpty() && !foodPrice.getText().isEmpty() && (breakfast.isSelected() || lunch.isSelected() || dinner.isSelected());
    }
    private void emptyFields(){
        foodName.setText(null);
        foodPrice.setText(null);
        breakfast.setSelected(true);
    }


}
