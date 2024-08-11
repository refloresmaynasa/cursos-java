package me.rflores.sistemaventas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import me.rflores.sistemaventas.modelos.entidades.Cliente;

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
    private ToggleGroup toggleGroup;

    private Stage dialogStage;
    private ObservableList<Cliente> listaClientes;
    private ObservableList<Cliente> clientesFiltrados;
    private boolean buscarClicked = false;

    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize toggle group if not already set by the FXML
        if (toggleGroup == null) {
            toggleGroup = new ToggleGroup();
            codigoRadio.setToggleGroup(toggleGroup);
            nombreRadio.setToggleGroup(toggleGroup);
            apellidoRadio.setToggleGroup(toggleGroup);
        }
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage the stage to set
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the client list to search within.
     *
     * @param listaClientes the list of clients
     */
    public void setListaClientes(ObservableList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    /**
     * Returns the filtered list of clients.
     *
     * @return filtered list of clients
     */
    public ObservableList<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    /**
     * Returns true if the user clicked Search, false otherwise.
     *
     * @return true if Search clicked, false otherwise
     */
    public boolean isBuscarClicked() {
        return buscarClicked;
    }

    /**
     * Called when the user clicks Search.
     */
    @FXML
    private void buscarCliente() {
        String valorBusqueda = valorBusquedaField.getText();

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

    /**
     * Performs the search by code.
     *
     * @param codigo the code to search for
     */
    private void buscarPorCodigo(String codigo) {
        try {
            int codigoInt = Integer.parseInt(codigo);
            clientesFiltrados = FXCollections.observableArrayList();

            for (Cliente cliente : listaClientes) {
                if (cliente.getCodigo() == codigoInt) {
                    clientesFiltrados.add(cliente);
                }
            }

            if (clientesFiltrados.isEmpty()) {
                mostrarAlerta("No se encontró ningún cliente", "No se encontró ningún cliente con el código proporcionado.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Código inválido", "El código debe ser un número entero.");
        }
    }

    /**
     * Performs the search by name.
     *
     * @param nombre the name to search for
     */
    private void buscarPorNombre(String nombre) {
        clientesFiltrados = FXCollections.observableArrayList();

        for (Cliente cliente : listaClientes) {
            if (cliente.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                clientesFiltrados.add(cliente);
            }
        }

        if (clientesFiltrados.isEmpty()) {
            mostrarAlerta("No se encontró ningún cliente", "No se encontró ningún cliente con el nombre proporcionado.");
        }
    }

    /**
     * Performs the search by last name.
     *
     * @param apellido the last name to search for
     */
    private void buscarPorApellido(String apellido) {
        clientesFiltrados = FXCollections.observableArrayList();

        for (Cliente cliente : listaClientes) {
            if (cliente.getApellido().toLowerCase().contains(apellido.toLowerCase())) {
                clientesFiltrados.add(cliente);
            }
        }

        if (clientesFiltrados.isEmpty()) {
            mostrarAlerta("No se encontró ningún cliente", "No se encontró ningún cliente con el apellido proporcionado.");
        }
    }

    /**
     * Shows an alert dialog with the specified title and content text.
     *
     * @param title       the title of the alert
     * @param contentText the content text of the alert
     */
    private void mostrarAlerta(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * Called when the user clicks Cancel.
     */
    @FXML
    private void cancelar() {
        dialogStage.close();
    }
}
