package controllerAndView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class StudentMainController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private Label credit, day, meal;

    @FXML
    private AnchorPane studentMenuScene, depositPane;

    @FXML
    private TextField deposit;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        credit.setText("Credit: " + LoginController.student.wallet);
        day.setText("Day: " + Time.day);
        meal.setText("Meal: " + Time.currentMeal());
    }

    public void reserveScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ReserveScene.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void transfer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Transfer.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void retake(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Retake.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void report(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentReport.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void increaseCredit(ActionEvent event) {
        depositPane.setVisible(true);
    }

    public void increaseCredit2(ActionEvent event) {
        if (validateFields()) {
            try {
                int amount = Integer.parseInt(deposit.getText());
                if (amount > 0){
                    LoginController.student.deposit(amount);
                    depositPane.setVisible(false);
                    credit.setText("Credit: " + LoginController.student.wallet);
                    Alerts.depositAlert();
                }else Alerts.invalidAmountAlert();
            }catch (NumberFormatException e){
                Alerts.numberDepositAlert();
            }
            deposit.setText(null);
        }else Alerts.emptyFieldAlert();
    }

    public void backDeposit(ActionEvent event){
        depositPane.setVisible(false);
    }


    public void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginMenu.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) studentMenuScene.getScene().getWindow());
    }

    private boolean validateFields() {
        return !Objects.equals(deposit.getText(), "");
    }

}
