package javaClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 20.09.16.
 */
public class DeleteLine {
    private static Connection connection;
    private static Statement statement;

    private final static String deleteDate = "DELETE Book WHERE title";
    public static void deleteLine(){
        ConnectionDataBases.ConnectionDataBases();
        try {
            statement = connection.createStatement();
            System.out.println(deleteDate);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
