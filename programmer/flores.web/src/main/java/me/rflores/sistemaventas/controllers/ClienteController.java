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
import java.time.LocalDate;
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

        out.println("<html><body>");
        out.println("<h1>Clientes</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>Código</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Teléfono</th><th>Fecha Registro</th><th>Acciones</th></tr>");

        for (Cliente cliente : clientes) {
            out.println("<tr><td>" + cliente.getCodigo() + "</td><td>" + cliente.getNombre() + "</td><td>" + cliente.getApellido() + "</td><td>" + cliente.getEmail() + "</td><td>" + cliente.getTelefono() + "</td><td>" + cliente.getFechaRegistro() + "</td>"
                    + "<td><a href='clientes?action=edit&codigo=" + cliente.getCodigo() + "'>Editar</a> | "
                    + "<a href='clientes?action=delete&codigo=" + cliente.getCodigo() + "'>Borrar</a></td></tr>");
        }

        out.println("</table>");
        out.println("<a href='addCliente.jsp'>Agregar Cliente</a><br>");
        out.println("<a href='file-generation?format=pdf'>Generar PDF</a><br>");
        out.println("<a href='file-generation?format=excel'>Generar Excel</a>");
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
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        LocalDate fechaRegistro = LocalDate.now();

        Cliente cliente = new Cliente(0, nombre, apellido, telefono, email);
        servicio.grabar(cliente);

        response.sendRedirect("clientes");
    }

    private void updateCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        Cliente existingCliente = servicio.buscar(codigo);
        if (existingCliente != null) {
            existingCliente.setNombre(nombre);
            existingCliente.setApellido(apellido);
            existingCliente.setEmail(email);
            existingCliente.setTelefono(telefono);

            servicio.actualizar(existingCliente);
        }

        response.sendRedirect("clientes");
    }
}
