module me.rflores.sistemaventas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens me.rflores.sistemaventas to javafx.fxml, javafx.base;
    exports me.rflores.sistemaventas;
    exports me.rflores.sistemaventas.controllers;
    opens me.rflores.sistemaventas.controllers to javafx.fxml, javafx.base;
    exports me.rflores.sistemaventas.modelos.entidades;
    opens me.rflores.sistemaventas.modelos.entidades to javafx.fxml, javafx.base;
}