USE master;

DROP DATABASE IF EXISTS ComiditasYa;

CREATE DATABASE ComiditasYa;

USE ComiditasYa;

CREATE TABLE Provincia (
    id_provincia int NOT NULL IDENTITY(1,1),
    nombre varchar(100) NOT NULL,
	PRIMARY KEY (id_provincia),
);

CREATE TABLE Departamento (
    id_departamento int NOT NULL IDENTITY(1,1),
    nombre varchar(50) NOT NULL,
    id_provincia int NOT NULL,
	PRIMARY KEY (id_departamento),
	CONSTRAINT FK_Departamento_Provincia FOREIGN KEY (id_provincia) REFERENCES Provincia(id_provincia) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE Cliente (
    id_cliente int NOT NULL IDENTITY(1,1),
    nombre varchar(50) NOT NULL,
	apellido varchar(50) NOT NULL,
	email varchar(50),
	direccion varchar(75),
	telefono varchar(50),
    id_departamento int NOT NULL,
	PRIMARY KEY (id_cliente),
	CONSTRAINT FK_Cliente_Departamento FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE Repartidor (
    id_repartidor int NOT NULL IDENTITY(1,1),
    nombre varchar(50) NOT NULL,
	apellido varchar(50) NOT NULL,
	email varchar(50),
	telefono varchar(50),
    id_departamento int NOT NULL,
	PRIMARY KEY (id_repartidor),
	CONSTRAINT FK_Repartidor_Departamento FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE Tipo_restaurante (
    id_tipo_restaurante int NOT NULL IDENTITY(1,1),
    tipo_empresa varchar(50) NOT NULL,
    tipo_restaurante varchar(50) NOT NULL,
    PRIMARY KEY (id_tipo_restaurante),
);

CREATE TABLE Restaurante (
    id_restaurante int NOT NULL IDENTITY(1,1),
    nombre varchar(50),
    id_departamento int NOT NULL,
    id_tipo_restaurante int NOT NULL,
    PRIMARY KEY (id_restaurante),
    CONSTRAINT FK_Restaurante_Departamento FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Testaurante_Tipo_restaurante FOREIGN KEY (id_tipo_restaurante) REFERENCES Tipo_restaurante(id_tipo_restaurante) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE Tipo_comida (
    id_tipo_comida int NOT NULL IDENTITY(1,1),
    tipo_comida varchar(50) NOT NULL,
    PRIMARY KEY (id_tipo_comida)
);

CREATE TABLE Comida (
    id_comida int NOT NULL IDENTITY(1,1),
    nombre varchar(50) NOT NULL,
    precio int NOT NULL,
    id_tipo_comida int NOT NULL,
    PRIMARY KEY (id_comida),
    CONSTRAINT FK_Comida_Tipo_comida FOREIGN KEY (id_tipo_comida) REFERENCES Tipo_comida(id_tipo_comida) ON DELETE CASCADE ON UPDATE CASCADE,
);


CREATE TABLE Restaurante_comida (
    id_comida int NOT NULL,
    id_restaurante int NOT NULL,
    PRIMARY KEY (id_comida, id_restaurante),
    CONSTRAINT FK_EC_Comida FOREIGN KEY (id_comida) REFERENCES Comida(id_comida) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_EC_Restaurante FOREIGN KEY (id_restaurante) REFERENCES Restaurante(id_restaurante) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE Pedido (
    id_pedido int NOT NULL IDENTITY(1,1),
    fecha smalldatetime NOT NULL,
    id_cliente int NOT NULL,
    id_comida int NOT NULL,
    id_repartidor int NOT NULL,
	importe_envio int NOT NULL,
    PRIMARY KEY (id_pedido),
    CONSTRAINT FK_Pedido_Cliente FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    CONSTRAINT FK_Pedido_Comida FOREIGN KEY (id_comida) REFERENCES Comida(id_comida),
    CONSTRAINT FK_Pedido_Repartidor FOREIGN KEY (id_repartidor) REFERENCES Repartidor(id_repartidor),
);
