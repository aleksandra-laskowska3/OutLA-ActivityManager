package helper;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class connecting the application to the MySQL server
 */
public abstract class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//34.102.127.178:3306/";
    private static final String databaseName = "OutLAdb";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "root"; // Username
    private static String password = "database"; // Password
    public static Connection connection;  // Connection Interface
    private static Connection databaseConnection = null;

    /**
     * Method that opens the connection between the database and the application
     */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
    public static Connection getConnection(){
        return databaseConnection;
    }
    /**
     * Method that closes the connection between the database and the application
     */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
