INSERT INTO clientes (apellido, nombre, correo, telefono) VALUES('González', 'Carlos', 'carlos.gonzalez@example.com', '1234567890');
INSERT INTO clientes (apellido, nombre, correo, telefono) VALUES('Martínez', 'Ana', 'ana.martinez@example.com', '0987654321');
INSERT INTO clientes (apellido, nombre, correo, telefono) VALUES('López', 'Javier', 'javier.lopez@example.com', '1122334455');
INSERT INTO clientes (apellido, nombre, correo, telefono) VALUES('Pérez', 'María', 'maria.perez@example.com', '5566778899');
INSERT INTO clientes (apellido, nombre, correo, telefono) VALUES('Rodríguez', 'Luis', 'luis.rodriguez@example.com', '2233445566');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('1234567812345678', '2024-09-15');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('2345678923456789', '2024-10-10');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('3456789034567890', '2024-07-20');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES('4567890145678901', '2024-08-01');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('5678901256789012', '2024-09-30');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('6789012367890123', '2024-06-15');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('7890123478901234', '2024-05-01');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('8901234589012345', '2024-08-15');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('9012345690123456', '2024-07-05');
INSERT INTO tarjeta_credito (numero, ultimo_pago) VALUES ('0123456701234567', '2024-10-01');


-- Autogenerated: do not edit this file
DROP TABLE  IF EXISTS BATCH_STEP_EXECUTION_CONTEXT;
DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION_CONTEXT;
DROP TABLE  IF EXISTS BATCH_STEP_EXECUTION;
DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION_PARAMS;
DROP TABLE  IF EXISTS BATCH_JOB_EXECUTION;
DROP TABLE  IF EXISTS BATCH_JOB_INSTANCE;

DROP SEQUENCE  IF EXISTS BATCH_STEP_EXECUTION_SEQ;
DROP SEQUENCE  IF EXISTS BATCH_JOB_EXECUTION_SEQ;
DROP SEQUENCE  IF EXISTS BATCH_JOB_SEQ;


-- Autogenerated: do not edit this file
CREATE TABLE BATCH_JOB_INSTANCE (JOB_INSTANCE_ID BIGINT NOT NULL PRIMARY KEY, VERSION BIGINT, JOB_NAME VARCHAR(100) NOT NULL, JOB_KEY VARCHAR(32) NOT NULL, constraint JOB_INST_UN unique (JOB_NAME, JOB_KEY));

CREATE TABLE BATCH_JOB_EXECUTION (JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY, VERSION BIGINT, JOB_INSTANCE_ID BIGINT NOT NULL, CREATE_TIME TIMESTAMP NOT NULL, START_TIME TIMESTAMP DEFAULT NULL, END_TIME TIMESTAMP DEFAULT NULL, STATUS VARCHAR(10), EXIT_CODE VARCHAR(2500), EXIT_MESSAGE VARCHAR(2500), LAST_UPDATED TIMESTAMP, constraint JOB_INST_EXEC_FK foreign key (JOB_INSTANCE_ID) references BATCH_JOB_INSTANCE(JOB_INSTANCE_ID));

CREATE TABLE BATCH_JOB_EXECUTION_PARAMS (JOB_EXECUTION_ID BIGINT NOT NULL, PARAMETER_NAME VARCHAR(100) NOT NULL, PARAMETER_TYPE VARCHAR(100) NOT NULL, PARAMETER_VALUE VARCHAR(2500), IDENTIFYING CHAR(1) NOT NULL, constraint JOB_EXEC_PARAMS_FK foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID));

CREATE TABLE BATCH_STEP_EXECUTION (STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY, VERSION BIGINT NOT NULL, STEP_NAME VARCHAR(100) NOT NULL, JOB_EXECUTION_ID BIGINT NOT NULL, CREATE_TIME TIMESTAMP NOT NULL, START_TIME TIMESTAMP DEFAULT NULL, END_TIME TIMESTAMP DEFAULT NULL, STATUS VARCHAR(10), COMMIT_COUNT BIGINT, READ_COUNT BIGINT, FILTER_COUNT BIGINT, WRITE_COUNT BIGINT, READ_SKIP_COUNT BIGINT, WRITE_SKIP_COUNT BIGINT, PROCESS_SKIP_COUNT BIGINT, ROLLBACK_COUNT BIGINT, EXIT_CODE VARCHAR(2500), EXIT_MESSAGE VARCHAR(2500), LAST_UPDATED TIMESTAMP, constraint JOB_EXEC_STEP_FK foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID));

CREATE TABLE BATCH_STEP_EXECUTION_CONTEXT (STEP_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY, SHORT_CONTEXT VARCHAR(2500) NOT NULL, SERIALIZED_CONTEXT TEXT, constraint STEP_EXEC_CTX_FK foreign key (STEP_EXECUTION_ID) references BATCH_STEP_EXECUTION(STEP_EXECUTION_ID));

CREATE TABLE BATCH_JOB_EXECUTION_CONTEXT (JOB_EXECUTION_ID BIGINT NOT NULL PRIMARY KEY, SHORT_CONTEXT VARCHAR(2500) NOT NULL, SERIALIZED_CONTEXT TEXT, constraint JOB_EXEC_CTX_FK foreign key (JOB_EXECUTION_ID) references BATCH_JOB_EXECUTION(JOB_EXECUTION_ID));

CREATE SEQUENCE BATCH_STEP_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE BATCH_JOB_EXECUTION_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
CREATE SEQUENCE BATCH_JOB_SEQ MAXVALUE 9223372036854775807 NO CYCLE;
