package controllerAndView;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public abstract class Alerts {

    public static void exitAlert(Stage stage) {
        Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("EXIT");
        exitAlert.setHeaderText("You're about to exit the FOOD SERVICE SYSTEM");
        exitAlert.setContentText("Are sure you want to continue?! ");
        if (exitAlert.showAndWait().get() == ButtonType.OK)
            stage.close();
    }

    public static void emptyFieldAlert() {
        Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
        invalidAlert.setTitle("Validate Fields");
        invalidAlert.setHeaderText("OOPS!");
        invalidAlert.setContentText("Fields Shouldn't Be Empty!");
        invalidAlert.showAndWait();
    }

    public static void addUserAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("User Adding");
        done.setHeaderText("DONE!");
        done.setContentText("User is added:)");
        done.show();
    }

    public static void addFoodAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Food Adding");
        done.setHeaderText("DONE!");
        done.setContentText("Food is added:)");
        done.show();
    }

    public static void giveFoodAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Food Giving");
        done.setHeaderText("DONE!");
        done.setContentText("Food is given:)");
        done.show();
    }

    public static void sellFoodAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Food Giving");
        done.setHeaderText("DONE!");
        done.setContentText("Food is given:)");
        done.show();
    }

    public static void reserveAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Reserving");
        done.setHeaderText("DONE!");
        done.setContentText("Food is reserved:)");
        done.show();
    }

    public static void cantReserveAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Reserving");
        done.setHeaderText("OOPS!");
        done.setContentText("You are not allowed to reserve food for the chosen time!");
        done.show();
    }

    public static void notEnoughCreditAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Reserving");
        done.setHeaderText("OOPS!");
        done.setContentText("You don't have enough money in your account:(");
        done.show();
    }

    public static void transferAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Transferring");
        done.setHeaderText("DONE!");
        done.setContentText("Your reservation is transferred");
        done.show();
    }

    public static void hasMenuAlert() {
        Alert hasMenuAlert = new Alert(Alert.AlertType.WARNING);
        hasMenuAlert.setTitle("Menu Setting");
        hasMenuAlert.setHeaderText("OOPS!");
        hasMenuAlert.setContentText("Menu is already set for day!");
        hasMenuAlert.showAndWait();
    }

    public static void sameFoodAlert() {
        Alert hasMenuAlert = new Alert(Alert.AlertType.WARNING);
        hasMenuAlert.setTitle("Menu Setting");
        hasMenuAlert.setHeaderText("OOPS!");
        hasMenuAlert.setContentText("You should choose 2 different food!");
        hasMenuAlert.showAndWait();
    }

    public static void studentNotFoundAlert() {
        Alert hasMenuAlert = new Alert(Alert.AlertType.WARNING);
        hasMenuAlert.setTitle("Transferring");
        hasMenuAlert.setHeaderText("OOPS!");
        hasMenuAlert.setContentText("Student not Found");
        hasMenuAlert.showAndWait();
    }

    public static void retakeAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Retaking");
        done.setHeaderText("DONE!");
        done.setContentText("You've got your money back:)");
        done.show();
    }

    public static void reserveTimesUpAlert() {
        Alert noTime = new Alert(Alert.AlertType.WARNING);
        noTime.setTitle("Reserving");
        noTime.setHeaderText("OOPS!");
        noTime.setContentText("You can only reserve food for 2 or more days later\nSo you can buy food from the WAIT LIST");
        noTime.showAndWait();
    }

    public static void retakeTimesUpAlert() {
        Alert noTime = new Alert(Alert.AlertType.WARNING);
        noTime.setTitle("Retaking");
        noTime.setHeaderText("OOPS!");
        noTime.setContentText("You can only retake reservations for 2 or more days later\nSo you can put it into the WAIT LIST");
        noTime.showAndWait();
    }

    public static void addToWaitListAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Adding to Wait List");
        done.setHeaderText("DONE!");
        done.setContentText("Your reservation was added to wait list\nYou'll get your money if someone buy it:)");
        done.show();
    }

    public static void numberDepositAlert() {
        Alert number = new Alert(Alert.AlertType.WARNING);
        number.setTitle("Deposit");
        number.setHeaderText("OOPS!");
        number.setContentText("Numbers Only for deposit");
        number.showAndWait();
    }

    public static void depositAlert() {
        Alert done = new Alert(Alert.AlertType.INFORMATION);
        done.setTitle("Deposit");
        done.setHeaderText("DONE!");
        done.setContentText("Your credit is increased:)");
        done.show();
    }

    public static void invalidAmountAlert() {
        Alert number = new Alert(Alert.AlertType.WARNING);
        number.setTitle("Deposit");
        number.setHeaderText("OOPS!");
        number.setContentText("The value must be greater than zero!");
        number.showAndWait();
    }
}

