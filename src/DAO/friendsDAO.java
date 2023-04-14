package DAO;

import helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Friends;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * friendsDAO class
 * This is used to communicate data between the database and the application
 * @author Alekandra Laskowska
 */
public class friendsDAO {
    /**
     * Method to get a list of all the friends from the database
     * @return friendsObservableList
     */
    public static ObservableList<Friends> getAllFriends() {
        ObservableList<Friends> friendsObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT friend_ID, name, address, phone, food_ID, time_ID FROM friend_table";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int friend_ID = rs.getInt("friend_ID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                int food_ID = rs.getInt("food_ID");
                int time_ID = rs.getInt("time_ID");
                friendsObservableList.add(new Friends(friend_ID, name, address, phone, food_ID, time_ID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendsObservableList;
    }

    /**
     * Method that adds a friend to the database
     * @param name
     * @param address
     * @param phone
     * @param food_ID
     * @param time_ID
     * @return 0
     */
    public static int addFriend(String name, String address, String phone, int food_ID, int time_ID) {
        try {
            String sql = "INSERT INTO friend_table ( name, address,  phone, food_ID, time_ID) VALUES ( ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setInt(4, food_ID);
            ps.setInt(5, time_ID);
            int newFriend = ps.executeUpdate();

            return newFriend;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Method that updates a friend to the database
     * @param name
     * @param address
     * @param phone
     * @param food_ID
     * @param time_ID
     * @param friend_ID
     * @return 0
     */
    public static int updateFriend(String name, String address, String phone, int food_ID, int time_ID, int friend_ID) {
        try {
            String sql = "UPDATE friend_table SET name=?, address=?, phone=?, food_ID=?, time_ID=? WHERE friend_ID=?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, phone);
            ps.setInt(4, food_ID);
            ps.setInt(5, time_ID);
            ps.setInt(6, friend_ID);
            int updatedFriend = ps.executeUpdate();

            return updatedFriend;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Method that deletes a friend from the database
     * @param friend_ID
     * @return
     */
    public static int deleteFriend(int friend_ID) {
        try {
            String sql = "DELETE FROM friend_table WHERE friend_ID=?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, friend_ID);
            int deletedFriend = ps.executeUpdate();

            return deletedFriend;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * Method that produces a list of friends based on food_ID
     * @param food_ID
     * @return friendsByFoodIDList
     */
    public static ObservableList<Friends> getFriendListByFood(int food_ID) {
        ObservableList<Friends> friendsByFoodIDList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM friend_table WHERE food_ID= " + food_ID;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int friend_ID = rs.getInt("friend_ID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                food_ID = rs.getInt("food_ID");
                int time_ID = rs.getInt("time_ID");
                Friends newFriendList = new Friends(friend_ID, name, address, phone, food_ID, time_ID);
                friendsByFoodIDList.add(newFriendList);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friendsByFoodIDList;
    }

}
