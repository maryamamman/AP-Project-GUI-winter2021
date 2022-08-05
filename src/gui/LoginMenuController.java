package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

public class LoginMenuController {
    @FXML
    private Button logIn;

    @FXML
    private Label loginError;

    @FXML
    private TextField getUsername;

    @FXML
    private TextField getPassword;

    private Parent root;
    private Stage stage;
    private Scene scene;

    public void logIn(ActionEvent event) throws IOException {
        String username = getUsername.getText();
        String password = getPassword.getText();
        User matchedUser = User.search(username, password);
        if (matchedUser == null) {
            loginError.setText("OOPS! Wrong username or password!");
            getUsername.setText(null);
            getPassword.setText(null);
        } else {
            if (matchedUser instanceof Student) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("student/StudentMain.fxml")));
            } else if (matchedUser instanceof Distributor) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("distributor/DistributorMain.fxml")));
            } else if (matchedUser instanceof Admin) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("admin/AdminMain.fxml")));
            }
            SceneController.switchToScene(event,root,stage,scene);

        }
    }
}

