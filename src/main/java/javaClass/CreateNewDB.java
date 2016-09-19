package javaClass;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */
public class CreateNewDB {

    private final static String DB_Drive = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://78.46.204.123:3306/TestDB9";
    private final static String username = "testusr9";
    private final static String password = "Test1234";

    private static Connection connection;
    private static Statement statement;

    private final static String creatNewDB = "CREATE DATABASE BOOKS CHARACTER SET utf8 COLLATE utf8_general_ci";

    public static void createNewDB(){
        try {
            statement = connection.createStatement();
            statement.executeUpdate(creatNewDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            CloseConnection.closeConnection();
        }
    }
}
