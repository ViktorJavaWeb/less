package javaClass;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */
public class SelectDateBases {

    private final static String DB_Drive = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://78.46.204.123:3306/TestDB9";
    private final static String username = "testusr9";
    private final static String password = "Test1234";

    private static Connection connection;
    private static Statement statement;

    private final static String selectFromTab = "SELECT * FROM Books";

    private final static String selectWhereTab = "SELECT * FROM Books WHERE title = \"XXX";//????

    public static void selectFrom(){


        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(selectFromTab);
            printResults(resultSet);// ????????????????
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectWhere(){

        try {
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(selectFromTab);
            printResults(resultSet);// ????????????????
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printResults(ResultSet resultSet) { // ????????????????
    }


}
