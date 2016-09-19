package javaClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */
public class CloseConnection {
    private static Connection connection;
    private static Statement statement;

    public static void closeConnection(){

        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }System.out.println("Disconected. statement");

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Disconected. connection");
        }


    }
}
