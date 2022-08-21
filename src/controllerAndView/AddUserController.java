package controllerAndView;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import model.user.Admin;
import model.user.Distributor;
import model.user.Student;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    private Stage stage;
    private Parent root;
    private Scene scene;

    @FXML
    private ChoiceBox<String> typeChooser;
    private final String[] types = {"Admin", "Distributor", "Student"};

    @FXML
    private AnchorPane addAdmin, addDistributor, addStudent, addUserScene;

    @FXML
    private Button addButton;

    @FXML
    private TextField adminUsername, adminPass, adminName;

    @FXML
    private TextField distributorUsername, distributorPass, distributorName, distributorSelfName;

    @FXML
    private TextField studentUsername, studentPass, studentName, studentID;

    @FXML
    private CheckBox inDorm;

    @FXML
    private Label numError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChooser.getItems().addAll(types);
        typeChooser.setOnAction(this::setInformation);
    }

    public void setInformation(ActionEvent event) {
        addButton.setVisible(false);
        addAdmin.setVisible(false);
        addDistributor.setVisible(false);
        addStudent.setVisible(false);
        switch (typeChooser.getValue()) {
            case "Admin" -> addAdmin.setVisible(true);
            case "Distributor" -> addDistributor.setVisible(true);
            case "Student" -> addStudent.setVisible(true);
        }
        addButton.setVisible(true);
    }

    public void add(ActionEvent event) {

        switch (typeChooser.getValue()) {
            case "Admin" -> {
                if (validateAddAdmin()) {
                    Admin admin = new Admin(adminUsername.getText(), adminPass.getText(), adminName.getText());
                    Alerts.addUserAlert();
                    emptyAdmin();
                } else Alerts.emptyFieldAlert();
            }
            case "Distributor" -> {
                if (validateAddDistributor()) {
                    Distributor distributor = new Distributor(distributorUsername.getText(), distributorPass.getText(), distributorName.getText(), distributorSelfName.getText());
                    Alerts.addUserAlert();
                    emptyDistributor();
                } else Alerts.emptyFieldAlert();
            }
            case "Student" -> {
                if (validateAddStudent()) {
                    try {
                        numError.setText("");
                        Student student = new Student(studentUsername.getText(), studentPass.getText(), studentName.getText(), Integer.parseInt(studentID.getText()), inDorm.isSelected());
                        Alerts.addUserAlert();
                        emptyStudent();
                    } catch (NumberFormatException e) {
                        numError.setText("Numbers Only for ID!");
                    }
                } else Alerts.emptyFieldAlert();
            }
        }
    }


    public void exit(ActionEvent event) {
        Alerts.exitAlert((Stage) addUserScene.getScene().getWindow());
    }

    public void back(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMain.fxml")));
        SceneController.switchToScene(event, root, stage, scene);
    }

    private boolean validateAddAdmin() {
        return !adminUsername.getText().isEmpty() && !adminPass.getText().isEmpty() && !adminName.getText().isEmpty();
    }

    private boolean validateAddDistributor() {
        return !distributorUsername.getText().isEmpty() && !distributorPass.getText().isEmpty() && !distributorName.getText().isEmpty() && !distributorSelfName.getText().isEmpty();
    }

    private boolean validateAddStudent() {
        return !studentUsername.getText().isEmpty() && !studentPass.getText().isEmpty() && !studentName.getText().isEmpty() && !studentID.getText().isEmpty();
    }

    private void emptyAdmin(){
        adminUsername.setText(null);
        adminPass.setText(null);
        adminName.setText(null);
    }
    private void emptyDistributor(){
        distributorUsername.setText(null);
        distributorPass.setText(null);
        distributorName.setText(null);
        distributorSelfName.setText(null);
    }
    private void emptyStudent(){
        studentUsername.setText(null);
        studentPass.setText(null);
        studentName.setText(null);
        studentID.setText(null);
        inDorm.setSelected(false);
    }


}
