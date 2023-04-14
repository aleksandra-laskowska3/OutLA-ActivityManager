package controller;

import model.*;
import DAO.activityDAO;
import DAO.userDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Activities;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Controller class that controls the login page
 *  @author Alekandra Laskowska
 */
public class loginController implements Initializable {

        Stage stage;
        Parent scene;


        @FXML
        private Label currentLocationLabel;

        @FXML
        private Label locationLabel;

        @FXML
        private AnchorPane loginForm;

        @FXML
        private Label loginLabel;

        @FXML
        private TextField loginPassword;

        @FXML
        private TextField loginUserID;

        @FXML
        private Label passwordLabel;

        @FXML
        private Button signInButton;

        @FXML
        private Label userIDLabel;

        private String alertTitle;
        private String alertContext;

        /**
         * ActionEvent occurs when the sign-in button is pressed
         * Check if the correct username and password is entered
         * If correct login credentials are entered there will be a pop-up to notify the user if there are any activities
         * in the next 15 min, then will take user to the main menu
         * @param event
         * @throws IOException
         */
        @FXML
        void signInHandler(ActionEvent event) throws IOException {
                String userName = loginUserID.getText();
                String passWord = loginPassword.getText();
                int user_ID = userDAO.checkUser(userName, passWord);



                if (user_ID > 0) {
                        ObservableList<Activities> getAllActivities = activityDAO.getAllActivities();
                        LocalDateTime currentTimePlus15Min = LocalDateTime.now().plusMinutes(15);
                        LocalDateTime now = LocalDateTime.now();
                        LocalDateTime startTime;
                        int activity_ID = 0;
                        boolean found = false;

                        for(Activities activities : getAllActivities){
//                                if(activities.getUser_ID()!=user_ID){
//                                       continue;
//                                }
                                startTime = activities.getStart();
                                if(startTime.isEqual(now) || (startTime.isAfter(now) && startTime.isBefore(currentTimePlus15Min)) || startTime.isEqual(currentTimePlus15Min)){
                                        activity_ID = activities.getActivity_ID();
                                        found = true;
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Warning");
                                        alert.setContentText("Activity within 15min, Activity ID: " + activity_ID +" at: " + startTime);
                                        alert.showAndWait();
                                }

                        }
                        if(!found) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Confirmation");
                                alert.setContentText("There are no activities in the next 15 minutes");
                                alert.showAndWait();
                        }
                        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
                        stage.setScene(new Scene(scene));
                        stage.show();


                }
                else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Wrong Username or Password");
                        alert.showAndWait();


                }

        }

        /**
         * Initializes as the application starts and gets the locale information
         * Sets the current location on the screen
         * @param url
         * @param rb
         */
        @Override
        public void initialize(URL url, ResourceBundle rb) {
                Locale locale = Locale.getDefault();
                Locale.setDefault(locale);

                ZoneId zone = ZoneId.systemDefault();

                currentLocationLabel.setText(ZoneId.systemDefault().toString());


                }
        }


