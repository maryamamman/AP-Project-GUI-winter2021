package gui.admin.commands;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    @FXML
    private ChoiceBox<String> typeChooser;
    private final String[] types = {"Admin", "Distributor", "Student"};

    @FXML
    private AnchorPane addAdmin;

    @FXML
    private AnchorPane addDistributor;

    @FXML
    private AnchorPane addStudent;

    @FXML
    private Button addButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChooser.getItems().addAll(types);
        typeChooser.setOnAction(this::setInformation);
    }

    public void setInformation(ActionEvent event){
        addButton.setVisible(false);
        addAdmin.setVisible(false);
        addDistributor.setVisible(false);
        addStudent.setVisible(false);
        switch (typeChooser.getValue()){
            case "Admin" -> addAdmin.setVisible(true);
            case "Distributor" -> addDistributor.setVisible(true);
            case "Student" -> addStudent.setVisible(true);
        }
        addButton.setVisible(true);
    }


}
