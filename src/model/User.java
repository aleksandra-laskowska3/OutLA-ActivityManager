package model;

/**
 * User Class
 */
public class User {

    private String user_Name;
    private String password;

    /**
     * Constructor for User
     * @param user_Name
     * @param password
     */
    public User(String user_Name, String password) {
        this.user_Name = user_Name;
        this.password = password;
    }


    /**
     * Getter for user_Name
     * @return user_Name
     */
    public String getUser_Name() {
        return user_Name;
    }

    /**
     * Setter for user_Name
     * @param user_Name the user's user_Name
     */
    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    /**
     * Getter for password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the user's password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
