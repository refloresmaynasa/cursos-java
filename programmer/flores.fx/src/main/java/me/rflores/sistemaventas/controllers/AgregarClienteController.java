package me.rflores.sistemaventas.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import me.rflores.sistemaventas.modelos.entidades.Cliente;

public class AgregarClienteController {

    @FXML
    private TextField codigoField;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField emailField;

    private Stage dialogStage;
    private Cliente cliente;
    private boolean guardarClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        if (cliente != null) {
            codigoField.setText(String.valueOf(cliente.getCodigo()));
            nombreField.setText(cliente.getNombre());
            apellidoField.setText(cliente.getApellido());
            telefonoField.setText(cliente.getTelefono());
            emailField.setText(cliente.getEmail());
        }
    }

    public boolean isGuardarClicked() {
        return guardarClicked;
    }

    @FXML
    private void guardarCliente() {
        if (validarEntradas()) {
            cliente.setCodigo(Integer.parseInt(codigoField.getText()));
            cliente.setNombre(nombreField.getText());
            cliente.setApellido(apellidoField.getText());
            cliente.setTelefono(telefonoField.getText());
            cliente.setEmail(emailField.getText());

            guardarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void cancelar() {
        dialogStage.close();
    }

    private boolean validarEntradas() {
        String errorMessage = "";

        if (nombreField.getText() == null || nombreField.getText().length() == 0) {
            errorMessage += "Nombre inválido!\n";
        }

        if (apellidoField.getText() == null || apellidoField.getText().length() == 0) {
            errorMessage += "Apellido inválido!\n";
        }

        if (telefonoField.getText() == null || telefonoField.getText().length() == 0) {
            errorMessage += "Teléfono inválido!\n";
        }

        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage += "Email inválido!\n";
        } else {
            if (!emailField.getText().contains("@")) {
                errorMessage += "Email no válido!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Campos Inválidos");
            alert.setHeaderText("Por favor corrija los campos inválidos");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
