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
 * Controller class that controls updating activities
 * @author Alekandra Laskowska
 */
public class updateActivityController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    private AnchorPane addAppointmentForm;

    @FXML
    private DatePicker updateActDatePicker;

    @FXML
    private TextField updateActDescription;

    @FXML
    private TextField updateActEnd;

    @FXML
    private ComboBox<Friends> updateActFriendComboBox;

    @FXML
    private TextField updateActID;

    @FXML
    private TextField updateActLocation;

    @FXML
    private TextField updateActPet;

    @FXML
    private TextField updateActPrice;

    @FXML
    private TextField updateActStart;

    @FXML
    private TextField updateActTitle;

    /**
     * Takes the user back to the activities page
     * @param event
     * @throws IOException
     */
    @FXML
    void updateActCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/activities.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Checks the entered data for correctness and overlap.
     * Saves the activity into the database.
     * @param event
     * @throws IOException
     */
    @FXML
    void updateActSaveButton(ActionEvent event) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        ObservableList<Activities> getAllActivities = activityDAO.getAllActivities();

        Friends f = updateActFriendComboBox.getValue();
        if (f == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select a Friend");
            alert.show();
            return;
        }



        int activity_ID = Integer.parseInt(updateActID.getText());
        String title = updateActTitle.getText();
        String description = updateActDescription.getText();
        String location = updateActLocation.getText();
        String price = updateActPrice.getText();
        String pet = updateActPet.getText();
        LocalDateTime start = null;
        LocalDateTime end = null;
        LocalDate appDate = updateActDatePicker.getValue();
        int friend_ID = f.getFriend_ID();


        if (title.isBlank() || description.isBlank() || location.isBlank() || price.isBlank() || pet.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out all the sections");
            alert.showAndWait();
            return;
        }
        if (appDate == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please pick a date");
            alert.showAndWait();
            return;
        }
        try {
            start = LocalDateTime.of(updateActDatePicker.getValue(), LocalTime.parse(updateActStart.getText(), formatter));


        } catch (DateTimeParseException error) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid start time, check for correct format");
            alert.showAndWait();
            return;

        }
        try {
            end = LocalDateTime.of(updateActDatePicker.getValue(), LocalTime.parse(updateActEnd.getText(), formatter));
        } catch (DateTimeParseException error) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid end time, check for correct format");
            alert.showAndWait();
            return;
        }


        for (Activities activities : getAllActivities) {
            LocalDateTime checkOverlapStart = activities.getStart();
            LocalDateTime checkOverlapEnd = activities.getEnd();



            if ((start.isBefore(checkOverlapStart)) && (end.isAfter(checkOverlapEnd))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Activity overlaps another existing activity");
                alert.showAndWait();
                return;
            }
            if ((start.isAfter(checkOverlapStart)) && (start.isBefore(checkOverlapEnd))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Activity overlaps another existing activity");
                alert.showAndWait();
                return;
            }
            if ((end.isAfter(checkOverlapStart)) && (end.isBefore(checkOverlapEnd))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Activity overlaps another existing activity");
                alert.showAndWait();
                return;
            }
            if ((start.isEqual(checkOverlapStart)) || (end.isEqual(checkOverlapEnd))) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setContentText("Activity overlaps another existing activity");
                alert.showAndWait();
                return;
            }

        }


        activityDAO.updateActivity(activity_ID, title, description, location, price, pet, start, end, friend_ID);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/activities.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Method that takes the data from the selectedActivity and loads it into the update activity page
     * @param selectedActivity
     */
        public void sendActivity (Activities selectedActivity){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            updateActDatePicker.setValue(selectedActivity.getStart().toLocalDate());
            updateActID.setText(String.valueOf(selectedActivity.getActivity_ID()));
            updateActTitle.setText(selectedActivity.getTitle());
            updateActDescription.setText(selectedActivity.getDescription());
            updateActLocation.setText(selectedActivity.getLocation());
            updateActPrice.setText(selectedActivity.getPrice());
            updateActPet.setText(selectedActivity.getPet());
            updateActStart.setText(String.valueOf(selectedActivity.getStart().toLocalTime()));
            updateActEnd.setText(String.valueOf(selectedActivity.getEnd().toLocalTime()));
            updateActFriendComboBox.setItems(friendsDAO.getAllFriends());
            for (Friends f : updateActFriendComboBox.getItems()) {
                if (f.getFriend_ID() == selectedActivity.getFriend_ID()) {
                    updateActFriendComboBox.getSelectionModel().select(f);
                    break;
                }
            }


        }

        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

        }
    }

