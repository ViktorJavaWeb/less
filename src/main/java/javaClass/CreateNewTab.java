package javaClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */
public class CreateNewTab {


    private static Connection connection;
    private static Statement statement;


    private final static String newTab = "CREATE TABLE Book(" +
            "title VARCHAR(128)," +
            "auvtor VARCHAR(128), " +
            "page INT(5))";

// id INT UNSIGNED NOT NULL AUTO_INCREMENT KEY) ENGINE MyISAM;



//    private final static String newTab = "CREATE TABLE Books(" +
//           "id NUMBER (5) NOT  NULL , " +
//           "title VARCHAR(128), " +
//           "auvtor VARCHAR(128), " +
//           "page INT(5)) ";

    public static void createNewTab(){

        try {
            connection = ConnectionDataBases.ConnectionDataBases();
            statement = connection.createStatement();
            statement.executeUpdate(newTab);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }  finally {
            CloseConnection.closeConnection();
        }


    }
}
