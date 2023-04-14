package controller;

import DAO.activityDAO;
import DAO.friendsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Friends;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that controls the Friends page
  * @author Alekandra Laskowska
 */
public class friendController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Friends, String> addressColumn;

    @FXML
    private AnchorPane customerRecordsForm;

    @FXML
    private Button customerSignOutButton;

    @FXML
    private TableView<Friends> friendTableView;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Friends, Integer> foodCol;

    @FXML
    private TableColumn<Friends, Integer> friendIDCol;

    @FXML
    private Button mainMenuButton;

    @FXML
    private TableColumn<Friends, String> nameColumn;

    @FXML
    private TableColumn<Friends, String> phoneCol;

    @FXML
    private TableColumn<Friends, Integer> timeCol;

    @FXML
    private Button updateButton;



    /**
     * ActionEvent that occurs when the add button is pressed
     * Takes the user to the add friend page
     * @param event
     * @throws IOException
     */
    @FXML
    void addButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/addFriend.fxml"));
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
    void signOutButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * ActionEvent that deletes the friend customer
     * Checks if the friend has associated activities that need to be deleted before the friend can be deleted
     * @param event
     * @throws SQLException
     */
    @FXML
    void deleteButtonHandler(ActionEvent event) throws SQLException {
        Friends selectedFriend = friendTableView.getSelectionModel().getSelectedItem();
        int selectedFriendID = friendTableView.getSelectionModel().getSelectedItem().getFriend_ID();
        if (selectedFriend == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a friend to delete");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setContentText("Are you sure you want to delete this friend?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (activityDAO.getActByFriendID(selectedFriendID)) {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation");
                    alert.setContentText("The selected friend has scheduled activities which also will be deleted. Are you sure you want to delete this friend?");
                    Optional<ButtonType> newresult = alert.showAndWait();
                    if (newresult.isPresent() && newresult.get() == ButtonType.OK) {
                        activityDAO.deleteActByFriendID(selectedFriendID);
                        friendsDAO.deleteFriend(selectedFriend.getFriend_ID());
                        friendTableView.setItems(friendsDAO.getAllFriends());
                    }

                }
            }
        }
    }
    /**
     * ActionEvent that occurs when the main menu button is pressed
     * Takes the user to the main menu page
     * @param event
     * @throws IOException
     */
    @FXML
    void mainMenuButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * ActionEvent that occurs when the update button is pressed
     * The selected friend's information is sent to the update friend's page
     * The user is taken to the update friend's page
     * Error occurs if there is not a selected friend
     * @param event
     * @throws IOException
     */
    @FXML
    void updateButtonHandler(ActionEvent event) throws IOException {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/updateFriend.fxml"));
            loader.load();

            updateFriendController uFController = loader.getController();
            uFController.sendFriend(friendTableView.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();

        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Select a friend to update");
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
        friendTableView.setItems(friendsDAO.getAllFriends());
        friendIDCol.setCellValueFactory(new PropertyValueFactory<>("friend_ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        foodCol.setCellValueFactory(new PropertyValueFactory<>("food_ID"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time_ID"));


    }
}

