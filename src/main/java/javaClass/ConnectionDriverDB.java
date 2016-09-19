package javaClass;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */
public class ConnectionDriverDB {

    private final static String DB_Drive = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://78.46.204.123:3306/TestDB9";
    private final static String username = "testusr9";
    private final static String password = "Test1234";

    private static Connection connection;
    private static Statement statement;


    public static void connectionDriver() throws ClassNotFoundException {
            Class.forName(DB_Drive);
            System.out.println("Driver loader assec!");
    }
}
