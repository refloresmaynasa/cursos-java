<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Agregar un nuevo Cliente</title>
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
    <label for="totalCompras">Total Compras:</label><br>
    <input type="text" id="totalCompras" name="totalCompras" required><br><br>
    <input type="submit" value="Agregar">
</form>
<div class="toolbar">
<a href="clientes" class="button">Volver a la lista de clientes</a>
</div>
</body>
</html>
