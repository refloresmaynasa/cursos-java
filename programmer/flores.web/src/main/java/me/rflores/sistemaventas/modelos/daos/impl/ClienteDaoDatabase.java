package me.rflores.sistemaventas.modelos.daos.impl;

import me.rflores.sistemaventas.modelos.daos.ClienteDao;
import me.rflores.sistemaventas.modelos.entidades.Cliente;
import me.rflores.sistemaventas.utiles.DatabaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoDatabase implements ClienteDao {
    @Override
    public void create(Cliente cliente) {
        String sql = "{CALL AddCliente(?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setString(1, cliente.getNombre());
            callableStatement.setString(2, cliente.getApellido());
            callableStatement.setString(3, cliente.getEmail());
            callableStatement.setString(4, cliente.getTelefono());
            callableStatement.setDate(5, java.sql.Date.valueOf(cliente.getFechaRegistro()));
            callableStatement.setDouble(6, cliente.getTotalCompras());

            callableStatement.executeUpdate();
            System.out.println("Cliente agregado exitosamente!");

        } catch (SQLException e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    @Override
    public void update(Cliente cliente) {
        String sql = "{CALL UpdateCliente(?, ?, ?, ?, ?, ?)}";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, cliente.getCodigo());
            callableStatement.setString(2, cliente.getNombre());
            callableStatement.setString(3, cliente.getApellido());
            callableStatement.setString(4, cliente.getEmail());
            callableStatement.setString(5, cliente.getTelefono());
            callableStatement.setDouble(6, cliente.getTotalCompras());

            callableStatement.executeUpdate();
            System.out.println("Cliente actualizado exitosamente!");

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }

    @Override
    public void delete(Integer codigo) {
        String sql = "{CALL DeleteCliente(?)}";
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, codigo);

            callableStatement.executeUpdate();
            System.out.println("Cliente eliminado exitosamente!");

        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "{CALL GetAllClientes()}";
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                var cliente = generateCliente(resultSet);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener todos los clientes: " + e.getMessage());
        }
        return clientes;
    }

    @Override
    public Cliente find(Integer codigo) {
        String sql = "{CALL GetClienteByCodigo(?)}";
        Cliente cliente = null;
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, codigo);
            ResultSet resultSet = callableStatement.executeQuery();

            if (resultSet.next()) {
                cliente = generateCliente(resultSet);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener cliente: " + e.getMessage());
        }
        return cliente;
    }

    private Cliente generateCliente(ResultSet resultSet) throws SQLException {
        var cliente = new Cliente();
        cliente.setCodigo(resultSet.getInt("codigo"));
        cliente.setNombre(resultSet.getString("nombre"));
        cliente.setApellido(resultSet.getString("apellido"));
        cliente.setEmail(resultSet.getString("email"));
        cliente.setTelefono(resultSet.getString("telefono"));
        cliente.setFechaRegistro(resultSet.getDate("fechaRegistro").toLocalDate());
        cliente.setTotalCompras(resultSet.getDouble("totalCompras"));
        return cliente;
    }

    @Override
    public List<Cliente> findAllByNombre(String nombre) {
        String sql = "{CALL FindClientesByNombre(?)}";
        return getClientes(nombre, sql);
    }

    @Override
    public List<Cliente> findAllByApellido(String apellido) {
        String sql = "{CALL FindClientesByApellido(?)}";
        return getClientes(apellido, sql);
    }

    private List<Cliente> getClientes(String filtro, String sql) {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setString(1, filtro);

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                var cliente = generateCliente(resultSet);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }
}
