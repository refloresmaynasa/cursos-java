<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="me.rflores.sistemaventas.modelos.entidades.Cliente" %>
<%
    Cliente cliente = (Cliente) request.getAttribute("cliente");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Cliente</title>
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
    <input type="submit" value="Actualizar">
</form>
<a href="clientes">Volver a la lista de clientes</a>
</body>
</html>
