package jm.task.core.jdbc.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/mydb";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "rootUSER";

    public static Connection getDBConnection() /*throws ClassNotFoundException, SQLException*/ {

        Connection connection = null;
        try {
//            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection is established");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection error");
        }
        return connection;
    }
}
