package javaClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */
public class CreateNewTab {

    private final static String DB_Drive = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://78.46.204.123:3306/TestDB9";
    private final static String username = "testusr9";
    private final static String password = "Test1234";

    private static Connection connection;
    private static Statement statement;


   private final static String newTab = "CREATE TABLE Books(\n" +
           "id INT UNSIGNED NOT NULL AUTO_INCREMENT KEY) ENGINE MyISAM, " +
           "title VARCHAR(128), " +
           "auvtor VARCHAR(128), " +
           "page INT(5), ";

    public static void createNewTab(){
        ConnectionDataBases.ConnectionDataBases();
        try {
            statement.executeUpdate(newTab);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
