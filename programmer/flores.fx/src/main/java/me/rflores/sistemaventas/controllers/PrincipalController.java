package me.rflores.sistemaventas.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import me.rflores.sistemaventas.VentasApp;
import me.rflores.sistemaventas.modelos.entidades.Cliente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class PrincipalController {
    @FXML
    private TableView<Cliente> tablaClientes;
    @FXML
    private TableColumn<Cliente, Integer> columnaCodigo;
    @FXML
    private TableColumn<Cliente, String> columnaNombre;
    @FXML
    private TableColumn<Cliente, String> columnaApellido;
    @FXML
    private TableColumn<Cliente, String> columnaTelefono;
    @FXML
    private TableColumn<Cliente, String> columnaEmail;
    @FXML
    private TableColumn<Cliente, LocalDate> columnaFechaRegistro;

    private ObservableList<Cliente> listaClientes;

    @FXML
    public void initialize() {
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        listaClientes = FXCollections.observableArrayList(
            new Cliente(1, "Juan", "Pérez", "123456789", "juan@example.com"),
            new Cliente(2, "María", "González", "987654321", "maria@example.com"),
            new Cliente(3, "Carlos", "Rodríguez", "555123456", "carlos@example.com")
        );

        tablaClientes.setItems(listaClientes);
    }

    @FXML
    private void añadirCliente() {
        Cliente tempCliente = new Cliente(0, "", "", "", "");
        boolean guardarClicked = showAgregarClienteDialog(tempCliente);
        if (guardarClicked) {
            listaClientes.add(tempCliente);
        }
    }

    private boolean showAgregarClienteDialog(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VentasApp.class.getResource("AgregarCliente.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Agregar Cliente");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AgregarClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);

            dialogStage.showAndWait();

            return controller.isGuardarClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void actualizarCliente() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean guardarClicked = showActualizarClienteDialog(selectedCliente);
            if (guardarClicked) {
                tablaClientes.refresh();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error de Actualizacion");
            alert.setHeaderText("Cliente No Seleccionado");
            alert.setContentText("Por favor, seleccione un cliente de la tabla.");
            alert.showAndWait();
        }
    }

    private boolean showActualizarClienteDialog(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VentasApp.class.getResource("ActualizarCliente.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Actualizar Cliente");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ActualizarClienteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);

            dialogStage.showAndWait();

            return controller.isGuardarClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    private void eliminarCliente() {
        Cliente selectedCliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar Borrado");
            alert.setHeaderText("Borrar Cliente");
            alert.setContentText("¿Está seguro de que desea borrar el cliente seleccionado?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                listaClientes.remove(selectedCliente);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Seleccionado");
            alert.setHeaderText("No Cliente Seleccionado");
            alert.setContentText("Por favor, seleccione un cliente de la tabla.");
            alert.showAndWait();
        }
    }

    @FXML
    private void filtrarClientes() {
        // Open the search dialog
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(VentasApp.class.getResource("FiltrarClientes.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Filtrar Clientes");
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            FiltrarClientesController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setListaClientes(listaClientes);

            dialogStage.showAndWait();

            if (controller.isBuscarClicked()) {
                ObservableList<Cliente> clientesFiltrados = controller.getClientesFiltrados();
                if (clientesFiltrados != null && !clientesFiltrados.isEmpty()) {
                    tablaClientes.setItems(clientesFiltrados);
                } else {
                    tablaClientes.setItems(listaClientes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void salir() {
        System.exit(0);
    }
}
