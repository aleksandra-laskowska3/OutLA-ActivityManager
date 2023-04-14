package DAO;

import helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Activities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * activityDAO class
 * This is used to communicate data between the database and the application
 * @author Alekandra Laskowska
 */
public class activityDAO {
    /**
     * Populates a list of all the activities
     * @return activitiesObservableList
     */
    public static ObservableList<Activities> getAllActivities() {
        ObservableList<Activities> activitiesObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM activity_table";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int activity_ID = rs.getInt("activity_ID");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String price = rs.getString("price");
                String pet = rs.getString("pet");
                LocalDateTime start = rs.getTimestamp("start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("end").toLocalDateTime();
                int friend_ID = rs.getInt("friend_ID");
                Activities activities = new Activities(activity_ID, title, description, location, price, pet, start, end, friend_ID);
                activitiesObservableList.add(activities);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activitiesObservableList;
    }

    /**
     * Populates a list of activities this month
     * @return monthObservableList
     */
    public static ObservableList<Activities> getMonthActivites() {
        ObservableList<Activities> monthObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM activity_table WHERE MONTH(start) = month(curdate())";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int activity_ID = rs.getInt("activity_ID");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String price = rs.getString("price");
                String pet = rs.getString("pet");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int friend_ID = rs.getInt("friend_ID");
                Activities monthActivities = new Activities(activity_ID, title, description, location, price, pet, start, end, friend_ID);
                monthObservableList.add(monthActivities);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return monthObservableList;
    }

    /**
     * Populates a list of activities this week
     * @return weekObservableList
     */
    public static ObservableList<Activities> getWeekActivities() {
        ObservableList<Activities> weekObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM activity_table WHERE start >= curdate() AND start <= DATE_ADD(curdate(), INTERVAL 7 DAY)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int activity_ID = rs.getInt("activity_ID");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String price = rs.getString("price");
                String pet = rs.getString("pet");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                int friend_ID = rs.getInt("friend_ID");
                Activities weekActivities = new Activities(activity_ID, title, description, location, price, pet, start, end, friend_ID);
                weekObservableList.add(weekActivities);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weekObservableList;
    }

    /**
     * Adds an activity to the database
     * @param title
     * @param description
     * @param location
     * @param price
     * @param pet
     * @param start
     * @param end
     * @param friend_ID
     * @return 0
     */
    public static int addActivity(String title, String description, String location, String price, String pet, LocalDateTime start, LocalDateTime end, int friend_ID) {
        try {
            String sql = "INSERT INTO activity_table ( title, description, location, price, pet, start, end, friend_ID) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, price);
            ps.setString(5, pet);
            ps.setTimestamp(6, Timestamp.valueOf(start));
            ps.setTimestamp(7, Timestamp.valueOf(end));
            ps.setInt(8, friend_ID);
            int newActivity = ps.executeUpdate();

            return newActivity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Updates an activity to the database
     * @param activity_ID
     * @param title
     * @param description
     * @param location
     * @param price
     * @param pet
     * @param start
     * @param end
     * @param friend_ID
     * @return 0
     */
    public static int updateActivity(int activity_ID, String title, String description, String location, String price, String pet, LocalDateTime start, LocalDateTime end, int friend_ID) {
        try {
            String sql = "UPDATE activity_table SET title=?, description=?, location=?, price=?, pet=?, start=?, end=?, friend_ID=? WHERE activity_ID=?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setString(3, location);
            ps.setString(4, price);
            ps.setString(5, pet);
            ps.setTimestamp(6, Timestamp.valueOf(start));
            ps.setTimestamp(7, Timestamp.valueOf(end));
            ps.setInt(8, friend_ID);
            ps.setInt(9, activity_ID);
            int updatedActivity = ps.executeUpdate();

            return updatedActivity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Deletes the selected activity from the database
     * @param activity_ID
     * @return 0
     */
    public static int deleteActivity(int activity_ID) {
        try {
            String sql = "DELETE FROM activity_table WHERE activity_ID=?";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ps.setInt(1, activity_ID);
            int deleteActivity = ps.executeUpdate();

            return deleteActivity;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * Checks for activities based on friend_ID
     * @param friend_ID
     * @return false
     */
    public static Boolean getActByFriendID(int friend_ID) {
        try {
            String sql = "SELECT * FROM activity_table WHERE friend_ID= " + friend_ID;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Populates a list of activities by friend_ID
     * @param friend_ID
     * @return actByFriendIDList
     */
    public static ObservableList<Activities> getActListByFriendID(int friend_ID) {
        ObservableList<Activities> actByFriendIDList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM activity_table WHERE friend_ID= " + friend_ID;
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int activity_ID = rs.getInt("activity_ID");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String price = rs.getString("price");
                String pet = rs.getString("pet");
                LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
                friend_ID = rs.getInt("friend_ID");
                Activities newActList = new Activities(activity_ID, title, description, location, price, pet, start, end, friend_ID);
                actByFriendIDList.add(newActList);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actByFriendIDList;
    }

    /**
     * Deletes an activity based on the friend_ID selected
     * @param friend_ID
     * @throws SQLException
     */
    public static void deleteActByFriendID(int friend_ID) throws SQLException {
        String sql = "DELETE FROM activity_table WHERE friend_ID= " + friend_ID;
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.executeUpdate();


    }


    }


