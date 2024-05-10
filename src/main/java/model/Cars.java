package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cars
{
    private Database database;

    public List<Car> getCars()
    {
        return new ArrayList<>();
    }

    public void addCar(Car car) throws SQLException
    {
        database = new Database("jdbc:mysql://localhost:3306/cars", "root", "root");

        database.closeConnection();
    }
}
