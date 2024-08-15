<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="me.rflores.sistemaventas.modelos.entidades.Cliente" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <title>Editar un Cliente</title>
</head>
<body>
<h1>Editar Cliente</h1>
<form action="clientes" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="codigo" value="<%= cliente.getCodigo() %>">
    <label for="nombre">Nombre:</label><br>
    <input type="text" id="nombre" name="nombre" value="<%= cliente.getNombre() %>" required><br><br>
    <label for="apellido">Apellido:</label><br>
    <input type="text" id="apellido" name="apellido" value="<%= cliente.getApellido() %>" required><br><br>
    <label for="email">Email:</label><br>
    <input type="email" id="email" name="email" value="<%= cliente.getEmail() %>" required><br><br>
    <label for="telefono">Teléfono:</label><br>
    <input type="text" id="telefono" name="telefono" value="<%= cliente.getTelefono() %>" required><br><br>
    <label for="totalCompras">Total Compras:</label><br>
    <input type="text" id="totalCompras" name="totalCompras" value="<%= cliente.getTotalCompras() %>" required><br><br>
    <input type="submit" value="Actualizar">
</form>
<div class="toolbar">
<a href="clientes" class="button">Volver a la lista de clientes</a>
</div>
</body>
</html>
