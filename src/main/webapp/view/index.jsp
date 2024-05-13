<%@ page import="model.Car" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Автомобили</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <h2 style="margin-top: 20px; margin-bottom: 20px;">Список автомобилей</h2>
    <table class="table">
        <thead>
            <tr>
                <th>Марка</th>
                <th>Модель</th>
                <th>Год выпуска</th>
                <th>Пробег (км)</th>
                <th>Цвет</th>
                <th>Цена ($)</th>
            </tr>
        </thead>
        <tbody>
            <% List<Car> carsList = (List<Car>) request.getAttribute("carsList");
                for (Car car : carsList) { %>
            <tr>
                <td><%= car.getBrand() %></td>
                <td><%= car.getModel() %></td>
                <td><%= car.getYear() %></td>
                <td><%= car.getMileage() %></td>
                <td><%= car.getColor() %></td>
                <td><%= car.getPrice() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <hr style="margin-top: 60px;">

    <div class="container" style="margin-top: 60px; margin-bottom: 100px;">
      <h2>Добавить автомобиль</h2>
      <form action="addition" method="post" id="addition">
          <div class="form-row">
              <div class="form-group col-md-6">
                  <label for="brand">Марка:</label>
                  <input type="text" class="form-control" id="brand" name="brand" required>
              </div>
              <div class="form-group col-md-6">
                  <label for="model">Модель:</label>
                  <input type="text" class="form-control" id="model" name="model" required>
              </div>
              <div class="form-group col-md-6">
                  <label for="year">Год выпуска:</label>
                  <input type="number" class="form-control" id="year" name="year" min="1901" required>
              </div>
              <div class="form-group col-md-6">
                  <label for="mileage">Пробег (км):</label>
                  <input type="number" class="form-control" id="mileage" name="mileage" min="0" required>
              </div>
              <div class="form-group col-md-6">
                  <label for="color">Цвет:</label>
                  <input type="text" class="form-control" id="color" name="color" required>
              </div>
              <div class="form-group col-md-6">
                  <label for="price">Цена ($):</label>
                  <input type="number" class="form-control" id="price" name="price" min="1" required>
              </div>
          </div>
          <button type="submit" class="btn btn-primary" id="add">Добавить</button>
      </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>