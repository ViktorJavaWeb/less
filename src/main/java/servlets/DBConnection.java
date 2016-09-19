package servlets;

import javaClass.ConnectionDataBases;
import javaClass.CreateNewTab;
import javaClass.InsertLine;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by ustenko on 19.09.16.
 */


public class DBConnection {


    private final static String DB_Drive = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://78.46.204.123:3306/TestDB9";
    private final static String username = "testusr9";
    private final static String password = "Test1234";

    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {

        //ConnectionDriverDB.connectionDriver();
        //ConnectionDataBases.ConnectionDataBases();
        CreateNewTab.createNewTab();
        InsertLine.insertNewLine();

    }

}
