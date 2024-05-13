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

@WebServlet(urlPatterns = {"/"})
public class MainServlet extends HttpServlet
{
    private final Cars cars = new Cars();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        try
        {
            List<Car> carsList = cars.getCars();
            StringBuilder tbodyBuilder = new StringBuilder();

            for (Car car : carsList) {
                tbodyBuilder.append("<tr>");
                tbodyBuilder.append("<td>").append(car.getBrand()).append("</td>");
                tbodyBuilder.append("<td>").append(car.getModel()).append("</td>");
                tbodyBuilder.append("<td>").append(car.getYear()).append("</td>");
                tbodyBuilder.append("<td>").append(car.getMileage()).append("</td>");
                tbodyBuilder.append("<td>").append(car.getColor()).append("</td>");
                tbodyBuilder.append("<td>").append(car.getPrice()).append("</td>");
                tbodyBuilder.append("</tr>");
            }

            String tbody = tbodyBuilder.toString();

            String htmlCode = "<!DOCTYPE html>\n" +
                    "<html lang=\"ru\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Автомобили</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "<div class=\"container\">\n" +
                    "    <h2 style=\"margin-top: 20px; margin-bottom: 20px;\">Список автомобилей</h2>\n" +
                    "    <table class=\"table\">\n" +
                    "        <thead>\n" +
                    "            <tr>\n" +
                    "                <th>Марка</th>\n" +
                    "                <th>Модель</th>\n" +
                    "                <th>Год выпуска</th>\n" +
                    "                <th>Пробег (км)</th>\n" +
                    "                <th>Цвет</th>\n" +
                    "                <th>Цена ($)</th>\n" +
                    "            </tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>\n" +
                    tbody +
                    "        </tbody>\n" +
                    "    </table>\n" +
                    "\n" +
                    "    <hr style=\"margin-top: 60px;\">\n" +
                    "\n" +
                    "    <div class=\"container\" style=\"margin-top: 60px; margin-bottom: 100px;\">\n" +
                    "      <h2>Добавить автомобиль</h2>\n" +
                    "      <form action=\"addition\" method=\"post\" id=\"addition\">\n" +
                    "          <div class=\"form-row\">\n" +
                    "              <div class=\"form-group col-md-6\">\n" +
                    "                  <label for=\"brand\">Марка:</label>\n" +
                    "                  <input type=\"text\" class=\"form-control\" id=\"brand\" name=\"brand\" required>\n" +
                    "              </div>\n" +
                    "              <div class=\"form-group col-md-6\">\n" +
                    "                  <label for=\"model\">Модель:</label>\n" +
                    "                  <input type=\"text\" class=\"form-control\" id=\"model\" name=\"model\" required>\n" +
                    "              </div>\n" +
                    "              <div class=\"form-group col-md-6\">\n" +
                    "                  <label for=\"year\">Год выпуска:</label>\n" +
                    "                  <input type=\"number\" class=\"form-control\" id=\"year\" name=\"year\" min=\"1901\" required>\n" +
                    "              </div>\n" +
                    "              <div class=\"form-group col-md-6\">\n" +
                    "                  <label for=\"mileage\">Пробег (км):</label>\n" +
                    "                  <input type=\"number\" class=\"form-control\" id=\"mileage\" name=\"mileage\" min=\"0\" required>\n" +
                    "              </div>\n" +
                    "              <div class=\"form-group col-md-6\">\n" +
                    "                  <label for=\"color\">Цвет:</label>\n" +
                    "                  <input type=\"text\" class=\"form-control\" id=\"color\" name=\"color\" required>\n" +
                    "              </div>\n" +
                    "              <div class=\"form-group col-md-6\">\n" +
                    "                  <label for=\"price\">Цена ($):</label>\n" +
                    "                  <input type=\"number\" class=\"form-control\" id=\"price\" name=\"price\" min=\"1\" required>\n" +
                    "              </div>\n" +
                    "          </div>\n" +
                    "          <button type=\"submit\" class=\"btn btn-primary\" id=\"add\">Добавить</button>\n" +
                    "      </form>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\"></script>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\"></script>\n" +
                    "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\"></script>\n" +
                    "</body>\n" +
                    "</html>\n";

            resp.getWriter().write(htmlCode);
        }
        catch (SQLException e)
        {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка базы данных: " + e.getMessage());
        }


    }
}
