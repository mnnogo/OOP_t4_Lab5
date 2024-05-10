package model;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private final Connection connection;

    public Database(String url, String user, String password) throws SQLException
    {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        DriverManager.registerDriver(driver);

        connection = DriverManager.getConnection(url, user, password);
        if (connection.isClosed())
            throw new SQLException("Connection is closed");
    }

    public void closeConnection() throws SQLException
    {
        if (!connection.isClosed())
            connection.close();
    }
}
