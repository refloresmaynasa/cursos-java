package me.rflores.sistemaventas.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.rflores.sistemaventas.modelos.entidades.Cliente;
import me.rflores.sistemaventas.servicios.ClienteServicio;
import me.rflores.sistemaventas.servicios.impl.ClienteServicioImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/clientes")
public class ClienteController extends HttpServlet {

    private List<Cliente> clientes = new ArrayList<>();
    private ClienteServicio servicio = new ClienteServicioImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("edit".equals(action)) {
            showEditForm(request, response);
        } else if ("delete".equals(action)) {
            deleteCliente(request, response);
        } else {
            listClientes(request, response);
        }
    }

    private void listClientes(HttpServletRequest request, HttpServletResponse response) throws IOException {
        clientes = servicio.listar();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"stylesheet\" href=\"styles.css\">\n" +
                "    <title>Agregar un nuevo Cliente</title>\n" +
                "</head>");
        out.println("<html><body>");
        out.println("<h1>Lista de Clientes</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Código</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Teléfono</th><th>Total Compras</th><th>Fecha Registro</th><th>Acciones</th></tr>");

        for (Cliente cliente : clientes) {
            out.println("<tr><td>" + cliente.getCodigo() + "</td><td>" + cliente.getNombre() + "</td><td>" + cliente.getApellido() + "</td><td>"
                    + cliente.getEmail() + "</td><td>" + cliente.getTelefono()
                    + "</td><td>" + cliente.getTotalCompras() + "</td>"
                    + "<td>" + cliente.getFechaRegistro() + "</td>"
                    + "<td><a href='clientes?action=edit&codigo=" + cliente.getCodigo() + "' class='button secondary'>Editar</a> | "
                    + "<a href='clientes?action=delete&codigo=" + cliente.getCodigo() + "' class='button danger'>Borrar</a></td></tr>");
        }

        out.println("</table>");
        out.println("<div class='toolbar'><a href='addCliente.jsp' class='button success'>Agregar Cliente</a>  ");
        out.println("<a href='file-generation?format=pdf' class='button secondary'>Generar PDF</a>  ");
        out.println("<a href='file-generation?format=excel' class='button secondary'>Generar Excel</a>  ");
        out.println("<a href='chart-generation?format=excel' class='button danger'>Generar Grafico</a> </div>");
        out.println("</body></html>");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        Cliente existingCliente = findCliente(codigo);

        request.setAttribute("cliente", existingCliente);
        request.getRequestDispatcher("editCliente.jsp").forward(request, response);
    }

    private void deleteCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));

        servicio.eliminar(codigo);

        response.sendRedirect("clientes");
    }

    private Cliente findCliente(int codigo) {
        return clientes.stream().filter(cliente -> cliente.getCodigo() == codigo).findFirst().orElse(null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("update".equals(action)) {
            updateCliente(request, response);
        } else {
            addCliente(request, response);
        }
    }

    private void addCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var nombre = request.getParameter("nombre");
        var apellido = request.getParameter("apellido");
        var email = request.getParameter("email");
        var telefono = request.getParameter("telefono");
        var totalCompras = Double.parseDouble(request.getParameter("totalCompras"));

        var cliente = new Cliente(0, nombre, apellido, telefono, email, totalCompras);
        servicio.grabar(cliente);

        response.sendRedirect("clientes");
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        var codigo = Integer.parseInt(request.getParameter("codigo"));
        var nombre = request.getParameter("nombre");
        var apellido = request.getParameter("apellido");
        var email = request.getParameter("email");
        var telefono = request.getParameter("telefono");
        var totalCompras = Double.parseDouble(request.getParameter("totalCompras"));

        var existingCliente = servicio.buscar(codigo);
        if (existingCliente != null) {
            existingCliente.setNombre(nombre);
            existingCliente.setApellido(apellido);
            existingCliente.setEmail(email);
            existingCliente.setTelefono(telefono);
            existingCliente.setTotalCompras(totalCompras);

            servicio.actualizar(existingCliente);
        }

        response.sendRedirect("clientes");
    }
}
