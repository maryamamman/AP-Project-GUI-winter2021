package controllerAndView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.self.FoodHandler;
import model.self.Self;

import java.io.IOException;
import java.util.Objects;


public class ShowDemandsController {

    private int day;
    private String type;
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField dayGetter;

    @FXML
    private RadioButton breakfast, lunch, dinner;

    @FXML
    private Label information, numError;

    @FXML
    private AnchorPane showDemandsScene;

    private String[] food = new String[2];

    public void foodType() {
        try {
            numError.setText("");
            day = Integer.parseInt(dayGetter.getText());
            if (breakfast.isSelected()) {
                type = "breakfast";
                food = FoodHandler.breakfastTable.get(day);
            } else if (lunch.isSelected()) {
                type = "lunch";
                food = FoodHandler.lunchTable.get(day);
            } else if (dinner.isSelected()) {
                type = "dinner";
                food = FoodHandler.dinnerTable.get(day);
            }
        } catch (NumberFormatException e) {
            numError.setText("Numbers only for day!");
        }

    }

    public void distribute(ActionEvent event) {
        information.setText("");
        if (validateFields()) {
            try {
                Self.selves.forEach((name, self) -> {
                    int food1 = self.demands(day, type, food[0]);
                    int food2 = self.demands(day, type, food[1]);
                    information.setText(information.getText() + "\n" + name + ": " + "\t" + food[0] + " -> " + food1 + "\t" + food[1] + " -> " + food2);
                });
            } catch (NullPointerException e) {
                information.setText("No Demands!");
            }
            emptyFields();
        } else Alerts.emptyFieldAlert();
    }

    public void foodDemands(ActionEvent event) {
        information.setText("");
        if (validateFields()) {
            try {
                int food1 = 0;
                int food2 = 0;
                for (Self self : Self.selves.values()) {
                    food1 += self.demands(day, type, food[0]);
                    food2 += self.demands(day, type, food[1]);
                }
                information.setText("Food 1: " + food[0] + " -> Demands: " + food1 + "\nFood 2: " + food[1] + " -> Demands: " + food2);
            } catch (NullPointerException e) {
                information.setText("No Demands!");
            }
            emptyFields();
        } else Alerts.emptyFieldAlert();
    }


    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) showDemandsScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }


    private boolean validateFields() {
        return !dayGetter.getText().isEmpty() && (breakfast.isSelected() || lunch.isSelected() || dinner.isSelected());
    }

    private void emptyFields() {
        dayGetter.setText("");
        breakfast.setSelected(false);
        lunch.setSelected(false);
        dinner.setSelected(false);
    }
}
