package controller;

import model.Car;
import model.Cars;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/addition"})
public class AdditionServlet extends HttpServlet
{
    private final Cars cars = new Cars();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String brand = req.getParameter("brand");
        String model = req.getParameter("model");
        int year = Integer.parseInt(req.getParameter("year"));
        int mileage = Integer.parseInt(req.getParameter("mileage"));
        String color = req.getParameter("color");
        int price = Integer.parseInt(req.getParameter("price"));

        Car newCar = new Car(brand, model, year, mileage, color, price);

        try
        {
            cars.addCar(newCar);

            List<Car> carsList = cars.getCars();
            req.setAttribute("carsList", carsList);

            req.getRequestDispatcher("view/index.jsp").forward(req, resp);
        }
        catch (SQLException e)
        {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка базы данных: " + e.getMessage());
        }
    }
}
