package controllerAndView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.user.Admin;
import model.user.Distributor;
import model.user.Student;
import model.user.User;

import java.io.IOException;
import java.util.Objects;

public class LoginController {
    @FXML
    private Button logIn;

    @FXML
    private Label loginError;

    @FXML
    private TextField getUsername;

    @FXML
    private TextField getPassword;

    @FXML
    private Button btn;

    private Parent root;
    private Stage stage;
    private Scene scene;
    public static Student student;
    public static Distributor distributor;
    public static Admin admin;

    public void logIn(ActionEvent event) throws IOException {
        String username = getUsername.getText();
        String password = getPassword.getText();
        User matchedUser = User.search(username, password);
        if (validateFields()) {
            if (matchedUser == null) {
                loginError.setText("OOPS! Wrong username or password!");
                getUsername.setText(null);
                getPassword.setText(null);
            } else {
                if (matchedUser instanceof Student) {
                    student = (Student) matchedUser;
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentMain.fxml")));
                } else if (matchedUser instanceof Distributor) {
                    distributor = (Distributor) matchedUser;
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DistributorMain.fxml")));
                } else if (matchedUser instanceof Admin) {
                    admin = (Admin) matchedUser;
                    root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminMain.fxml")));
                }
                SceneController.switchToScene(event, root, stage, scene);

            }
        }else Alerts.emptyFieldAlert();
    }

    private boolean validateFields() {
        return getUsername.getText() != null && getPassword.getText() != null;
    }
}

