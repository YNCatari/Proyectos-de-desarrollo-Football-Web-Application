package App.Football.Providers;

import java.sql.*;
import java.util.Properties;

public final class MySQLConnection {
    public Connection connection;
    private static MySQLConnection instance;
    private MySQLConnection()
    {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "dbfootball";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456789");
        properties.setProperty("useSSL", "false");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("characterEncoding","UTF-8");
        properties.setProperty("noAccessToProcedureBodies", "true");
        try
        {
            Class.forName(driver).newInstance();
            this.connection = DriverManager.getConnection(url+dbName,properties);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static synchronized MySQLConnection getInstance()
    {
        if(instance == null)
        {
            instance = new MySQLConnection();
        }
        return instance;
    }
}
