CREATE TABLE eventos (
    id INT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    duracion BIGINT NOT NULL, -- storing duration in minutes
    hora_ingreso TIME NOT NULL,
    hora_salida TIME NOT NULL,
    temporada_alta BOOLEAN NOT NULL,
    categoria ENUM('SILVER', 'GOLD', 'PLATINUM') NOT NULL,
    expositor JSON NOT NULL,
    asistentes JSON NOT NULL,
    fecha DATE NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    capacidad INT NOT NULL,
    costo DOUBLE NOT NULL
);