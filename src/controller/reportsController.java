package controller;

import model.*;
import DAO.activityDAO;
import DAO.foodDAO;
import DAO.friendsDAO;
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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

/**
 *  Controller class that controls reports
 * @author Alekandra Laskowska
 */
public class reportsController implements Initializable {
        Stage stage;
        Parent scene;


        @FXML
        private TableColumn<Friends, String> addressColumn;

        @FXML
        private TableColumn<Activities, String> descriptionCol;

        @FXML
        private TableColumn<Activities, ZonedDateTime> endCol;

        @FXML
        private ComboBox<Food> foodComboBox;

        @FXML
        private ComboBox<Friends> friendComboBox;

        @FXML
        private TableColumn<Friends, Integer> friendIDCol;

        @FXML
        private TableView<Activities> friendScheduleTableView;

        @FXML
        private TableView<Friends> friendTableView;

        @FXML
        private TableColumn<Activities, Integer> idCol;

        @FXML
        private TableColumn<Activities, String> locationCol;

        @FXML
        private TableColumn<Friends, String> nameColumn;

        @FXML
        private TableColumn<Activities, String> petCol;

        @FXML
        private TableColumn<Friends, String> phoneCol;

        @FXML
        private TableColumn<Activities, String> priceCol;

        @FXML
        private Button reportMainMenuButton;

        @FXML
        private AnchorPane reportsForm;

        @FXML
        private TableColumn<Activities, ZonedDateTime> startCol;

        @FXML
        private TableColumn<Friends, Integer> timeCol;

        @FXML
        private TableColumn<Activities, String> titleCol;

        /**
         * ActionEvent that occurs when a selection is made in the food combo box.
         * Loads the table with activities based on the selection made.
         * @param event
         */
        @FXML
        void foodComboBox(ActionEvent event) {
                Callback<ListView<Food>, ListCell<Food>> foodList = lv -> new ListCell<Food>() {
                        @Override
                        protected void updateItem(Food item, boolean empty){
                                super.updateItem(item, empty);
                                setText(empty ? "" : (item.getFood_ID() + "- " + item.getType()));
                        }
                };
                Callback<ListView<Food>, ListCell<Food>> foodPicked = lv -> new ListCell<Food>() {
                        @Override
                        protected void updateItem(Food item, boolean empty){
                                super.updateItem(item, empty);
                                setText(empty ? "" : (item.getFood_ID() + "- " + item.getType()));
                        }
                };
                foodComboBox.setCellFactory(foodList);
                foodComboBox.setButtonCell(foodPicked.call(null));

                Food food = foodComboBox.getValue();
                ObservableList<Friends> filteredFriendsByFood = friendsDAO.getFriendListByFood(food.getFood_ID());
                ObservableList<Friends> friendList = FXCollections.observableArrayList();
                //Lambda #2
                filteredFriendsByFood.forEach(friend -> {
                        friendList.add(friend);
                });
                friendTableView.setItems(friendList);
        }

        /**
         * ActionEvent that occurs when a selection is made from the friend combo box.
         * The selection will load a friend list in the table below.
         * @param event
         */
        @FXML
        void friendComboBoxHandler(ActionEvent event) {
                Callback<ListView<Friends>, ListCell<Friends>> friendList = lv -> new ListCell<Friends>() {
                        @Override
                        protected void updateItem(Friends item, boolean empty){
                                super.updateItem(item, empty);
                                setText(empty ? "" : (item.getFriend_ID() + "- " + item.getName()));
                        }
                };
                Callback<ListView<Friends>, ListCell<Friends>> friendPicked = lv -> new ListCell<Friends>() {
                        @Override
                        protected void updateItem(Friends item, boolean empty){
                                super.updateItem(item, empty);
                                setText(empty ? "" : (item.getFriend_ID() + "- " + item.getName()));
                        }
                };
                friendComboBox.setCellFactory(friendList);
                friendComboBox.setButtonCell(friendPicked.call(null));

                Friends f = friendComboBox.getValue();
                ObservableList<Activities> filteredActByFriend = activityDAO.getActListByFriendID(f.getFriend_ID());
                ObservableList<Activities> actList = FXCollections.observableArrayList();
                //Lambda #2
                filteredActByFriend.forEach(act -> {
                        actList.add(act);
                });
                friendScheduleTableView.setItems(actList);
        }

        /**
         * Takes the user back to the main menu page.
         * @param event
         * @throws IOException
         */
        @FXML
        void reportMainMenuButtonHandler(ActionEvent event) throws IOException {
            stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/mainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }










        /**
         * Initializes data to the correct columns
         * Loads the food selections in the food combo box
         * Loads the friend selections in the friend combo box
         * @param url
         * @param resourceBundle
         */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            friendIDCol.setCellValueFactory(new PropertyValueFactory<>("friend_ID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            timeCol.setCellValueFactory(new PropertyValueFactory<>("time_ID"));

            idCol.setCellValueFactory(new PropertyValueFactory<>("activity_ID"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
            locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
            priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
            petCol.setCellValueFactory(new PropertyValueFactory<>("pet"));
            startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
            endCol.setCellValueFactory(new PropertyValueFactory<>("end"));


                friendComboBox.setItems(friendsDAO.getAllFriends());
                foodComboBox.setItems(foodDAO.getAllFood());
    }
}

