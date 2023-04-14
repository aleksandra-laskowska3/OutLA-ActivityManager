package controller;

import model.*;
import DAO.activityDAO;
import DAO.friendsDAO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

/**
 * Controller class that controls the add activity page
 * @author Alekandra Laskowska
 */
public class addActivityController implements Initializable {
        Stage stage;
        Parent scene;

        @FXML
        private DatePicker addActDatePicker;

        @FXML
        private TextField addActDescription;

        @FXML
        private TextField addActEnd;

        @FXML
        private ComboBox<Friends> addActFriendComboBox;

        @FXML
        private TextField addActID;

        @FXML
        private TextField addActLocation;

        @FXML
        private TextField addActPet;

        @FXML
        private TextField addActPrice;

        @FXML
        private TextField addActStart;

        @FXML
        private TextField addActTitle;

        @FXML
        private Button addAppCancelButton;

        @FXML
        private AnchorPane addAppointmentForm;

        /**
         * Takes user back to the activity page.
         * @param event
         * @throws IOException
         */
        @FXML
        void addActCancelButton(ActionEvent event) throws IOException {
                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/activities.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
        }

        /**
         * Checks the entered data for correctness and no overlap
         * Saves the activity to database and takes user back to activity page.
         * @param event
         * @throws IOException
         */
        @FXML
        void addActSaveButton(ActionEvent event) throws IOException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                ObservableList<Activities> getAllActivities = activityDAO.getAllActivities();


                Friends f = addActFriendComboBox.getValue();
                if (f == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Please select a Friend");
                        alert.show();
                        return;
                }



                String title = addActTitle.getText();
                String description = addActDescription.getText();
                String location = addActLocation.getText();
                String price = addActPrice.getText();
                String pet = addActPet.getText();
                LocalDateTime start = null;
                LocalDateTime end = null;
                LocalDate appDate = addActDatePicker.getValue();
                int friend_ID = f.getFriend_ID();


                if(appDate==null){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Please pick a date");
                        alert.showAndWait();
                        return;
                }
                if( title.isBlank() || description.isBlank() || location.isBlank() || price.isBlank() || pet.isBlank()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Please fill out all the sections");
                        alert.showAndWait();
                        return;
                }
                try{
                        start = LocalDateTime.of(addActDatePicker.getValue(), LocalTime.parse(addActStart.getText(),formatter));


                } catch (DateTimeParseException error){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Invalid start time, check for correct format");
                        alert.showAndWait();
                        return;

                }
                try{
                        end = LocalDateTime.of(addActDatePicker.getValue(), LocalTime.parse(addActEnd.getText(), formatter));
                } catch (DateTimeParseException error){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Invalid end time, check for correct format");
                        alert.showAndWait();
                        return;
                }


                int activity_ID = -1; //Integer.parseInt(addAppID.getText()); // -1 when adding appointment

                for (Activities activities: getAllActivities){
                        LocalDateTime checkOverlapStart = activities.getStart();
                        LocalDateTime checkOverlapEnd = activities.getEnd();

//
                        if((start.isBefore(checkOverlapStart)) && (end.isAfter(checkOverlapEnd))) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setContentText("Activity overlaps another existing activity");
                                alert.showAndWait();
                                return;
                        }
                        if( (start.isAfter(checkOverlapStart)) && (start.isBefore(checkOverlapEnd))){
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setContentText("Activity overlaps another existing activity");
                                alert.showAndWait();
                                return;
                        }
                        if( (end.isAfter(checkOverlapStart)) && (end.isBefore(checkOverlapEnd))){
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setContentText("Activity overlaps another existing activity");
                                alert.showAndWait();
                                return;
                        }
                        if((start.isEqual(checkOverlapStart)) || (end.isEqual(checkOverlapEnd))) {
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning");
                                alert.setContentText("Activity overlaps another existing activity");
                                alert.showAndWait();
                                return;
                        }

                }

                activityDAO.addActivity( title, description, location, price, pet, start, end, friend_ID);

                stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/view/activities.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
        }


        /**
         * Loads the friend combobox with all the friends
         * @param url
         * The location used to resolve relative paths for the root object, or
         * {@code null} if the location is not known.
         *
         * @param resourceBundle
         * The resources used to localize the root object, or {@code null} if
         * the root object was not localized.
         */
        @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                addActFriendComboBox.setItems(friendsDAO.getAllFriends());
    }
}

