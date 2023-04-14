package controller;
import model.*;
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
import model.Friends;
import model.Time;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class that controls updating a friend
 * @author Alekandra Laskowska
 */
public class updateFriendController implements Initializable {
        Stage stage;
        Parent scene;


    @FXML
    private AnchorPane addCustomerForm;

    @FXML
    private TextField updateAddressTxt;

    @FXML
    private ComboBox<Food> updateFoodComboBox;

    @FXML
    private TextField updateNameTxt;

    @FXML
    private TextField updateIDTxt;

    @FXML
    private TextField updatePhoneTxt;

    @FXML
    private ComboBox<Time> updateTimeComboBox;

    @FXML
    void foodComboBox(ActionEvent event) {

    }

    @FXML
    void timeComboBox(ActionEvent event) {

    }

    /**
     * Takes the user back to the friend page
     * @param event
     * @throws IOException
     */
    @FXML
    void updateCancelButton(ActionEvent event) throws IOException {
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/friends.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Checks the data for correctness and saves friend into database.
     * @param event
     * @throws IOException
     */
    @FXML
    void updateSaveButton(ActionEvent event) throws IOException {
        Time time = updateTimeComboBox.getValue();
        if(time== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select the best free time");
            alert.show();
            return;
        }
        Food food = updateFoodComboBox.getValue();
        if(food== null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please select favorite cuisine");
            alert.show();
            return;
        }
        int time_ID = time.getTime_ID();
        int food_ID = food.getFood_ID();
        String name = updateNameTxt.getText();
        String address = updateAddressTxt.getText();
        String phone = updatePhoneTxt.getText();
        int friend_ID = Integer.parseInt(updateIDTxt.getText());


        if (name.isBlank() || address.isBlank() ||phone.isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please fill out all the sections");
            alert.showAndWait();
            return;
        }

        friendsDAO.updateFriend(name, address, phone, food_ID, time_ID, friend_ID);

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/friends.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Method that takes the data from the selectedFriend and loads it into the update friend page
     * @param selectedFriend
     */
    public void sendFriend(Friends selectedFriend){

                updateIDTxt.setText(String.valueOf(selectedFriend.getFriend_ID()));
                updateNameTxt.setText(selectedFriend.getName());
                updateAddressTxt.setText(selectedFriend.getAddress());
                updatePhoneTxt.setText(selectedFriend.getPhone());

                updateFoodComboBox.setItems(foodDAO.getAllFood());
                for(Food f: updateFoodComboBox.getItems()){
                    if(f.getFood_ID()==selectedFriend.getFood_ID()){
                        updateFoodComboBox.getSelectionModel().select(f);
                        break;
                    }
                }
                updateTimeComboBox.setItems(timeDAO.getAllTime());
            for(Time t: updateTimeComboBox.getItems()){
                if(t.getTime_ID()==selectedFriend.getTime_ID()){
                    updateTimeComboBox.getSelectionModel().select(t);
                    break;
                }
            }



        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}

