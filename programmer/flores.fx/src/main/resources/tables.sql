CREATE Database ventas;

use ventas;

CREATE TABLE Cliente
(
    codigo        INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(100) NOT NULL,
    apellido      VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL,
    telefono      VARCHAR(20)  NOT NULL,
    fechaRegistro DATE         NOT NULL
);
