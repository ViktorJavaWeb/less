package javaClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */
public class ConnectionDataBases {

    private final static String DB_Drive = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://78.46.204.123:3306/TestDB9";
    private final static String username = "testusr9";
    private final static String password = "Test1234";

    private static Connection connection;
    private static Statement statement;

    public static void ConnectionDataBases(){

        try {
            ConnectionDriverDB.connectionDriver();
            try {

                connection = DriverManager.getConnection(URL, username, password);
                System.out.println("Connected.");
                connection.close();
                System.out.println("Disconnected.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




//        finally {
//            CloseConnection.closeConnection();
//        }
    }


}
