package DAO;

import helper.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * userDAO class
 * This is used to communicate data between the database and the application
 * @author Alekandra Laskowska
 */
public class userDAO {

    /**
     * Method that checks if the entered userName and passWord match to the ones in the database
     * @param userName the userName that is entered at login
     * @param passWord the passWord that is entered at login
     * @return If the username and password match then a 1 is returned
     * If the username and password do not match then a -1 is returned
     */
    public static int checkUser(String userName, String passWord) {
        try{
            String sql = "SELECT * FROM login_table WHERE username= '" + userName + "' AND password= '" + passWord + "'";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                if(rs.getString("username").equals(userName)){
                    if(rs.getString("password").equals(passWord)){
                        return 1;
                    }
                }
            }

            }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
                }


    }