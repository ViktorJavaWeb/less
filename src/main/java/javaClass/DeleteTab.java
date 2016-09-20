package javaClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 20.09.16.
 */
public class DeleteTab {
    private static Connection connection;
    private static Statement statement;

    static String nameTab = "Book";

    private final static String deleteTabBases = "'DROP TABLE' + nameTab";

    public static void deleteTab(){
        ConnectionDataBases.ConnectionDataBases();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(deleteTabBases);
            System.out.println("Delete table " + nameTab);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
