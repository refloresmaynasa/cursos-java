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
import me.rflores.sistemaventas.servicios.ClienteServicio;
import me.rflores.sistemaventas.servicios.impl.ClienteServicioImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
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

    private ClienteServicio servicio;

    @FXML
    public void initialize() {
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        columnaTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        columnaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnaFechaRegistro.setCellValueFactory(new PropertyValueFactory<>("fechaRegistro"));

        servicio = new ClienteServicioImpl();

        listaClientes = FXCollections.observableArrayList(
                servicio.listar()
        );

        tablaClientes.setItems(listaClientes);
    }

    @FXML
    private void añadirCliente() {
        Cliente tempCliente = new Cliente(0, "", "", "", "");
        boolean guardarClicked = showAgregarClienteDialog(tempCliente);
        if (guardarClicked) {
            servicio.grabar(tempCliente);
            refreshListaClientes(null);
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
                servicio.actualizar(selectedCliente);
                refreshListaClientes(null);
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
                servicio.eliminar(selectedCliente.getCodigo());
                refreshListaClientes(null);
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
    private void resetearClientes() {
        refreshListaClientes(null);
    }

    @FXML
    private void filtrarClientes() {
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

            dialogStage.showAndWait();

            if (controller.isBuscarClicked()) {
                var clientesFiltrados = controller.getClientesFiltrados();
                if (clientesFiltrados != null && !clientesFiltrados.isEmpty()) {
                    refreshListaClientes(clientesFiltrados);
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

    private void refreshListaClientes(List<Cliente> clientes) {
        if (clientes == null) {
            listaClientes.setAll(servicio.listar());
        } else {
            listaClientes.setAll(clientes);
        }
    }
}
