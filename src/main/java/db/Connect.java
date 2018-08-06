package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


public class Connect {
    private static Connection connection;
    private static Statement statement;
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("resources/WEB-INF/db.properties"));
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection(){
        try {
            connection = DriverManager.getConnection(properties.getProperty("db.url"),properties.getProperty("db.username"), properties.getProperty("db.password"));
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Statement openStatement(){
        try {
            statement = connection.createStatement();
            return statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(){
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void init() {
        try {
            Class.forName(properties.getProperty("db.driverClassName"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
