package controller;

import model.Car;
import model.Cars;
import model.Database;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
        String year = req.getParameter("year");
        String mileage = req.getParameter("mileage");
        String color = req.getParameter("color");
        String price = req.getParameter("price");

        Car newCar = new Car(brand, model, year, mileage, color, price);

        try
        {
            cars.addCar(newCar);

            updatePage(req, resp);
        }
        catch (SQLException e)
        {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка базы данных: " + e.getMessage());
        }
    }

    private void updatePage(ServletRequest request, ServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher view = request.getRequestDispatcher("view/index.html");
        view.forward(request, response);
    }

}
