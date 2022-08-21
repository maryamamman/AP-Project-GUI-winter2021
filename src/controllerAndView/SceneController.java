package controllerAndView;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class SceneController {


    protected SceneController() throws IOException {
    }

    public static void switchToScene(ActionEvent event, Parent root, Stage stage, Scene scene){
       stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
       scene = new Scene(root);
       stage.setScene(scene);
       stage.show();
    }

}
