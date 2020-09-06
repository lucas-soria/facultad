USE master;

DROP DATABASE IF EXISTS PedidosYaArgentina;

CREATE DATABASE PedidosYaArgentina;

USE PedidosYaArgentina;

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

CREATE TABLE Tipo_establecimiento (
    id_tipo_establecimiento int NOT NULL IDENTITY(1,1),
    descripcion varchar(50),
    PRIMARY KEY (id_tipo_establecimiento),
);

CREATE TABLE Establecimiento (
    id_establecimiento int NOT NULL IDENTITY(1,1),
    descripcion varchar(50),
    id_departamento int NOT NULL,
    id_tipo_establecimiento int NOT NULL,
    PRIMARY KEY (id_establecimiento),
    CONSTRAINT FK_Establecimiento_Departamento FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Establecimiento_Tipo_establecimiento FOREIGN KEY (id_tipo_establecimiento) REFERENCES Tipo_establecimiento(id_tipo_establecimiento) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE Tipo_comida (
    id_tipo_comida int NOT NULL IDENTITY(1,1),
    descripcion varchar(50),
    PRIMARY KEY (id_tipo_comida)
);

CREATE TABLE Comida (
    id_comida int NOT NULL IDENTITY(1,1),
    descripcion varchar(50),
    precio int NOT NULL,
    id_tipo_comida int NOT NULL,
    PRIMARY KEY (id_comida),
    CONSTRAINT FK_Comida_Tipo_comida FOREIGN KEY (id_tipo_comida) REFERENCES Tipo_comida(id_tipo_comida) ON DELETE CASCADE ON UPDATE CASCADE,
);


CREATE TABLE Establecimiento_comida (
    id_comida int NOT NULL,
    id_establecimiento int NOT NULL,
    PRIMARY KEY (id_comida, id_establecimiento),
    CONSTRAINT FK_EC_Comida FOREIGN KEY (id_comida) REFERENCES Comida(id_comida) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_EC_Establecimiento FOREIGN KEY (id_establecimiento) REFERENCES Establecimiento(id_establecimiento) ON DELETE CASCADE ON UPDATE CASCADE,
);

CREATE TABLE Pedido (
    id_pedido int NOT NULL IDENTITY(1,1),
    fecha smalldatetime,
    id_cliente int NOT NULL,
    id_comida int NOT NULL,
    id_repartidor int NOT NULL,
    PRIMARY KEY (id_pedido),
    CONSTRAINT FK_Pedido_Cliente FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
    CONSTRAINT FK_Pedido_Comida FOREIGN KEY (id_comida) REFERENCES Comida(id_comida),
    CONSTRAINT FK_Pedido_Repartidor FOREIGN KEY (id_repartidor) REFERENCES Repartidor(id_repartidor),
);
