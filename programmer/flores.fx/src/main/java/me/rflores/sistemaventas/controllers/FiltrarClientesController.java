package me.rflores.sistemaventas.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import me.rflores.sistemaventas.modelos.entidades.Cliente;
import me.rflores.sistemaventas.servicios.ClienteServicio;
import me.rflores.sistemaventas.servicios.impl.ClienteServicioImpl;

import java.util.List;

public class FiltrarClientesController {

    @FXML
    private RadioButton codigoRadio;
    @FXML
    private RadioButton nombreRadio;
    @FXML
    private RadioButton apellidoRadio;
    @FXML
    private TextField valorBusquedaField;
    @FXML
    private ToggleGroup grupo;

    private Stage dialogStage;
    private List<Cliente> clientesFiltrados;
    private boolean buscarClicked = false;

    private ClienteServicio servicio;

    @FXML
    private void initialize() {
        if (grupo == null) {
            grupo = new ToggleGroup();
            codigoRadio.setToggleGroup(grupo);
            nombreRadio.setToggleGroup(grupo);
            apellidoRadio.setToggleGroup(grupo);
        }

        servicio = new ClienteServicioImpl();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public boolean isBuscarClicked() {
        return buscarClicked;
    }

    @FXML
    private void buscarCliente() {
        var valorBusqueda = valorBusquedaField.getText();

        if (valorBusqueda == null || valorBusqueda.trim().isEmpty()) {
            mostrarAlerta("Valor de búsqueda vacío", "Por favor ingrese un valor para buscar.");
            return;
        }

        if (codigoRadio.isSelected()) {
            buscarPorCodigo(valorBusqueda);
        } else if (nombreRadio.isSelected()) {
            buscarPorNombre(valorBusqueda);
        } else if (apellidoRadio.isSelected()) {
            buscarPorApellido(valorBusqueda);
        } else {
            mostrarAlerta("Ningún criterio seleccionado", "Por favor seleccione un criterio de búsqueda.");
        }

        buscarClicked = true;
        dialogStage.close();
    }

    private void buscarPorCodigo(String codigo) {
        try {
            int codigoInt = Integer.parseInt(codigo);
            var cliente = servicio.buscar(codigoInt);

            clientesFiltrados = cliente == null ? List.of() : List.of(cliente);

            if (clientesFiltrados.isEmpty()) {
                mostrarAlerta("No se encontró ningún cliente", "No se encontró ningún cliente con el código proporcionado.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Código inválido", "El código debe ser un número entero.");
        }
    }

    private void buscarPorNombre(String nombre) {
        clientesFiltrados = servicio.listarPorNombre(nombre);

        if (clientesFiltrados.isEmpty()) {
            mostrarAlerta("No se encontró ningún cliente", "No se encontró ningún cliente con el nombre proporcionado.");
        }
    }

    private void buscarPorApellido(String apellido) {
        clientesFiltrados = servicio.listarPorApellido(apellido);

        if (clientesFiltrados.isEmpty()) {
            mostrarAlerta("No se encontró ningún cliente", "No se encontró ningún cliente con el apellido proporcionado.");
        }
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    private void cancelar() {
        dialogStage.close();
    }
}
