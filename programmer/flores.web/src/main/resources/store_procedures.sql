use ventas;

DELIMITER //

DROP PROCEDURE IF EXISTS AddCliente;

CREATE PROCEDURE AddCliente(
    IN p_nombre VARCHAR(100),
    IN p_apellido VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_fechaRegistro DATE,
    IN p_totalCompras DECIMAL(10,2)
)
BEGIN
    INSERT INTO Cliente (nombre, apellido, email, telefono, fechaRegistro, totalCompras)
    VALUES (p_nombre, p_apellido, p_email, p_telefono, p_fechaRegistro, p_totalCompras);
END //


DROP PROCEDURE IF EXISTS UpdateCliente;

CREATE PROCEDURE UpdateCliente(
    IN p_codigo INT,
    IN p_nombre VARCHAR(100),
    IN p_apellido VARCHAR(100),
    IN p_email VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_totalCompras DECIMAL(10,2)
)
BEGIN
    UPDATE Cliente
    SET nombre = p_nombre,
        apellido = p_apellido,
        email = p_email,
        telefono = p_telefono,
        totalCompras = p_totalCompras
    WHERE codigo = p_codigo;
END //


CREATE PROCEDURE DeleteCliente(
    IN p_codigo INT
)
BEGIN
    DELETE FROM Cliente WHERE codigo = p_codigo;
END //


CREATE PROCEDURE GetAllClientes()
BEGIN
    SELECT * FROM Cliente;
END //

CREATE PROCEDURE GetClienteByCodigo(
    IN p_codigo INT
)
BEGIN
    SELECT * FROM Cliente WHERE codigo = p_codigo;
END //

CREATE PROCEDURE FindClientesByNombre(
    IN p_nombre VARCHAR(100)
)
BEGIN
    SELECT * FROM Cliente
    WHERE nombre LIKE CONCAT('%', p_nombre, '%');
END //

CREATE PROCEDURE FindClientesByApellido(
    IN p_apellido VARCHAR(100)
)
BEGIN
    SELECT * FROM Cliente
    WHERE apellido LIKE CONCAT('%', p_apellido, '%');
END //

DELIMITER ;
