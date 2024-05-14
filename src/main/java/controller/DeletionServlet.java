package controller;

import model.Car;
import model.Cars;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = {"/delete"})
public class DeletionServlet extends HttpServlet
{
    private final Cars cars = new Cars();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        int carId = Integer.parseInt(req.getParameter("carId"));

        try
        {
            cars.removeCarById(carId);

            updatePage(req, resp);
        }
        catch (SQLException e)
        {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка базы данных: " + e.getMessage());
        }

    }

    private void updatePage(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException
    {
        List<Car> carsList = cars.getCars();
        req.setAttribute("carsList", carsList);

        req.getRequestDispatcher("view/index.jsp").forward(req, resp);
    }
}
