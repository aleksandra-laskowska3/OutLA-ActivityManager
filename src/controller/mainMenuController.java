package controller;

import model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that controls the main menu
 * @author Alekandra Laskowska
 */
public class mainMenuController implements Initializable{

    Stage stage;
    Parent scene;

        @FXML
        private Button activityButton;

        @FXML
        private Button friendButton;

        @FXML
        private AnchorPane mainMenuForm;

        @FXML
        private Button reportsButton;

        @FXML
        private Button signOutButton;

    /**
     * ActionEvent that occurs when the activities button is pressed
     * Takes you to the activities page
     * @param event
     * @throws IOException
     */
        @FXML
        void activityHandler(ActionEvent event) throws IOException {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/activities.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    /**
     * ActionEvent that occurs when the friends button is pressed
     * Takes you to the friends page
     * @param event
     * @throws IOException
     */
        @FXML
        void friendHandler(ActionEvent event) throws IOException {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/friends.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    /**
     * ActionEvent that occurs when the reports button is pressed
     * Takes the user to the reports page
     * @param event
     * @throws IOException
     */
        @FXML
        void reportsHandler(ActionEvent event) throws IOException {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/reports.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    /**
     * ActionEvent that occurs when the sign-out button is pressed
     * Takes the user to the login page
     * @param event
     * @throws IOException
     */
        @FXML
        void signOutHandler(ActionEvent event) throws IOException {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

