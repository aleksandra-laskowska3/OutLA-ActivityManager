package DAO;

import helper.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Time;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * timeDAO class
 * This is used to communicate data between the database and the application
 * @author Alekandra Laskowska
 */
public class timeDAO {
    /**
     * Method that produces all the time options from the databse
     * @return timeObservableList
     */
    public static ObservableList<Time> getAllTime() {
        ObservableList<Time> timeObservableList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM time_table";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int time_ID = rs.getInt("time_ID");
                String timeOfDay = rs.getString("timeOfDay");
                Time time = new Time(time_ID, timeOfDay);
                timeObservableList.add(time);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timeObservableList;
    }


}
