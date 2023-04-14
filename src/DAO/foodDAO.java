package DAO;

import helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * foodDAO class
 * This is used to communicate data between the database and the application
 * @author Alekandra Laskowska
 */
public class foodDAO {
    /**
     * Method that makes a list of all foods from database
     * @return foodObservableList
     */
    public static ObservableList<Food> getAllFood() {
        ObservableList<Food> foodObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM food_table";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int food_ID = rs.getInt("food_ID");
                String type = rs.getString("type");
                Food food = new Food(food_ID, type);
                foodObservableList.add(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foodObservableList;
    }


}
