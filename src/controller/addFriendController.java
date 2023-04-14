package controller;

import DAO.foodDAO;
import DAO.friendsDAO;
import DAO.timeDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Food;
import model.Time;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that controls the add friend page
  * @author Alekandra Laskowska
 */
public class addFriendController implements Initializable {
    Stage stage;
    Parent scene;


    @FXML
    private TextField addAddressTxt;

    @FXML
    private Button addCancelButton;

    @FXML
    private AnchorPane addCustomerForm;

    @FXML
    private ComboBox<Food> addFoodComboBox;

    @FXML
    private TextField addNameTxt;

    @FXML
    private TextField addPhoneTxt;

    @FXML
    private Button addSaveButton;

    @FXML
    private ComboBox<Time> addTimeComboBox;



    @FXML
    void foodComboBox(ActionEvent event) {

    }

    @FXML
    void timeComboBox(ActionEvent event) {

    }

    /**
     * Takes the user back to the friend page.
     * @param event
     * @throws IOException
     */
    @FXML
    void addCancelButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/friends.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Checks the data entered for correctness and saves the friend to the database.
     * @param event
     * @throws IOException
     */
    @FXML
    void addSaveButtonHandler(ActionEvent event) throws IOException {

        Time time = addTimeComboBox.getValue();
        if(time== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select the best free time");
            alert.show();
            return;
        }
        Food food = addFoodComboBox.getValue();
        if(food== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select favorite cuisine");
            alert.show();
            return;
        }
        int time_ID = time.getTime_ID();
        int food_ID = food.getFood_ID();
        String name = addNameTxt.getText();
        String address = addAddressTxt.getText();
        String phone = addPhoneTxt.getText();


        if (name.isBlank() || address.isBlank() || phone.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out all the sections");
            alert.showAndWait();
            return;
        }

        friendsDAO.addFriend(name, address, phone, food_ID, time_ID);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/friends.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Loads the food combo box with all the food types
     * Loads the time combo box with all the timeOfDay.
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

                addFoodComboBox.setItems(foodDAO.getAllFood());
                addTimeComboBox.setItems(timeDAO.getAllTime());
    }
}


