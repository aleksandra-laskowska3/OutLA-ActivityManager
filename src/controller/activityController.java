package controller;

import DAO.activityDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Activities;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that controls the activities page
  * @author Alekandra Laskowska
 */
public class activityController implements Initializable {

        Stage stage;
        Parent scene;


    @FXML
    private RadioButton actMonthRadioButton;

    @FXML
    private RadioButton actWeekRadioButton;

    @FXML
    private TableColumn<Activities, Integer> activityIDCol;

    @FXML
    private TableView<Activities> activityTableView;

    @FXML
    private TextField activitySearch;
    @FXML
    private Button addActButton;

    @FXML
    private RadioButton allActRadioButton;

    @FXML
    private ToggleGroup appButtons;

    @FXML
    private AnchorPane appointmentsForm;

    @FXML
    private TableColumn<Activities, String> descriptionColumn;

    @FXML
    private TableColumn<Activities, ZonedDateTime> endColumn;

    @FXML
    private TableColumn<Activities, Integer> friendIDCol;

    @FXML
    private TableColumn<Activities, String> locationColumn;

    @FXML
    private TableColumn<Activities, String> petCol;

    @FXML
    private TableColumn<Activities, String> priceCol;

    @FXML
    private TableColumn<Activities, ZonedDateTime> startColumn;

    @FXML
    private TableColumn<Activities, String> titleColumn;

    /**
     * Takes the user to the main menu page
     * @param event
     * @throws IOException
     */
    @FXML
    void actMainMenuButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Signs the user out and takes them to the login page.
     * @param event
     * @throws IOException
     */
    @FXML
    void actSignOutButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Populates activities based on the Activity ID or Title entered in the search bar.
     * @param event
     */
    @FXML
    void activityLookUp(ActionEvent event) {
        ObservableList<Activities> allActivities = activityDAO.getAllActivities();
        ObservableList<Activities> activityFound = FXCollections.observableArrayList();
        String searchTxt = activitySearch.getText();

        for (Activities activity : allActivities) {
            if(String.valueOf(activity.getActivity_ID()).contains(searchTxt) || activity.getTitle().contains(searchTxt)){
                activityFound.add(activity);
                activityTableView.setItems(activityFound);
            }
        }
        activityTableView.setItems(activityFound);
        if (activityFound.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Activity not found");
            alert.showAndWait();
            activityTableView.setItems(allActivities);
        }
    }

    /**
     * Takes the user to the Add Activity page.
     * @param event
     * @throws IOException
     */
    @FXML
    void addActButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addActivity.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes the selected activity.
     * @param event
     */
    @FXML
    void deleteActButton(ActionEvent event) {
        Activities selectedActivity = activityTableView.getSelectionModel().getSelectedItem();
        int deletedActID = activityTableView.getSelectionModel().getSelectedItem().getActivity_ID();
        if(selectedActivity==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No activity has been selected for deletion");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete this activity?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK) {
                activityDAO.deleteActivity(selectedActivity.getActivity_ID());
                Alert newAlert = new Alert(Alert.AlertType.CONFIRMATION);
                newAlert.setTitle("Confirmation");
                newAlert.setContentText("You have successfully deleted the activity with ID: " + deletedActID);
                newAlert.showAndWait();
                activityTableView.setItems(activityDAO.getAllActivities());
            }

        }
    }

    /**
     * Populates all the activities.
     * @param event
     */
    @FXML
    void switchToAll(ActionEvent event) {
        activityTableView.setItems(activityDAO.getAllActivities());
    }

    /**
     * Populates all the activities this month.
     * @param event
     */
    @FXML
    void switchToMonth(ActionEvent event) {
        activityTableView.setItems(activityDAO.getMonthActivites());
    }

    /**
     * Populates all the activities this week.
     * @param event
     */
    @FXML
    void switchToWeek(ActionEvent event) {
        activityTableView.setItems(activityDAO.getWeekActivities());
    }

    /**
     * Loads the selected activity to the Update Activity page and takes the user there.
     * @param event
     */
    @FXML
    void updateActButton(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateActivity.fxml"));
            loader.load();

            updateActivityController uaController = loader.getController();
            uaController.sendActivity(activityTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select an activity to update");
            alert.showAndWait();
        }
    }


    /**
     * Initializes data to the correct columns
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        activityTableView.setItems(activityDAO.getAllActivities());
        activityIDCol.setCellValueFactory(new PropertyValueFactory<>("activity_ID"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        petCol.setCellValueFactory(new PropertyValueFactory<>("pet"));
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        friendIDCol.setCellValueFactory(new PropertyValueFactory<>("friend_ID"));
    }
}


