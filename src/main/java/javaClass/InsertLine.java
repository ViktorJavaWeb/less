package javaClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by AL on 19.09.2016.
 */
public class InsertLine {

    private static Connection connection;
    private static Statement statement;

    private final static String insertTabLine = "INSERT INTO Book(" +
                    "title, auvtor, page)" +
                    "VALUES(" +
                    "'Cris_Stall', 'Garri', '500')";


    public static void insertNewLine(){

        connection = ConnectionDataBases.ConnectionDataBases();

        try {
            statement = connection.createStatement();
            statement.executeUpdate(insertTabLine);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseConnection.closeConnection();
        }
    }

}
