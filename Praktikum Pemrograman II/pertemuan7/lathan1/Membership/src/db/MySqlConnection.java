package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnection {
    private final static String DB_URL = "jdbc:mysql://membership";
    private final static String DB_USER = "root";
    private final static String DB_PASS = "1234";

    private static MySqlConnection instance()
    {
        if (instance == null)
        {
            instance = ne MySqlConnection();
        }
        return instance;
    }

    public Connection getConnection()
    {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                DB_URL, DB_USER,DB_PASS);
        } catch (Exception e){
            e.printStacckTrace();
        }
        return connection;
    }
}