<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Cliente</title>
</head>
<body>
<h1>Agregar Nuevo Cliente</h1>
<form action="clientes" method="post">
    <input type="hidden" name="action" value="add">
    <label for="nombre">Nombre:</label><br>
    <input type="text" id="nombre" name="nombre" required><br><br>
    <label for="apellido">Apellido:</label><br>
    <input type="text" id="apellido" name="apellido" required><br><br>
    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" required><br><br>
    <label for="telefono">Teléfono:</label><br>
    <input type="text" id="telefono" name="telefono" required><br><br>
    <input type="submit" value="Agregar">
</form>
<a href="clientes">Volver a la lista de clientes</a>
</body>
</html>
