package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cars
{
    private Database database;

    public List<Car> getCars() throws SQLException
    {
        List<Car> carsList = new ArrayList<>();

        database = new Database("jdbc:mysql://localhost:3306/cars", "root", "root");

        ResultSet resultSet = database.executeQuery("SELECT brand, model, year, mileage, color, price FROM cars");
        while (resultSet.next())
        {
            carsList.add(new Car(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getString(5),
                    resultSet.getInt(6)
            ));
        }

        return carsList;
    }

    public void addCar(Car car) throws SQLException
    {
        database = new Database("jdbc:mysql://localhost:3306/cars", "root", "root");

        database.executeQuery(String.format("INSERT INTO cars (brand, model, year, mileage, color, price) " +
                        "VALUES ('%s', '%s', %d, %d, '%s', %d)",
                        car.getBrand(), car.getModel(), car.getYear(), car.getMileage(), car.getColor(), car.getPrice()));

        database.closeConnection();
    }
}
