USE master;

DROP DATABASE IF EXISTS ComiditasYaDW;

CREATE DATABASE ComiditasYaDW;

USE ComiditasYaDW;

CREATE TABLE Dim_Tiempo (
    id_tiempo int NOT NULL IDENTITY(1,1),
	agno int NOT NULL,
	mes int NOT NULL,
	dia int NOT NULL,
	hora int NOT NULL,
    dia_semana varchar(15) NOT NULL,
	PRIMARY KEY (id_tiempo),
);

CREATE TABLE Dim_Restaurante (
    id_restaurante int NOT NULL IDENTITY(1,1),
	tipo_empresa varchar(50) NOT NULL,
	tipo_restaurante varchar(50) NOT NULL,
    nombre varchar(150) NOT NULL,
	PRIMARY KEY (id_restaurante),
);

CREATE TABLE Dim_Ubicacion (
    id_ubicacion int NOT NULL IDENTITY(1,1),
	departamento varchar(100) NOT NULL,
	provincia varchar(100) NOT NULL,
	PRIMARY KEY (id_ubicacion),
);

CREATE TABLE Dim_Comida (
    id_comida int NOT NULL IDENTITY(1,1),
	clasificacion varchar(50) NOT NULL,
	categoria varchar(50) NOT NULL,
	tipo_comida varchar(50) NOT NULL,
    nombre varchar(100) NOT NULL,
	precio float NOT NULL,
	PRIMARY KEY (id_comida),
);

CREATE TABLE Fact_Pedido(
	id_pedido int NOT NULL IDENTITY(1,1),
	id_tiempo int NOT NULL,
	id_restaurante int NOT NULL,
	id_ubicacion int NOT NULL,
	id_comida int NOT NULL,
	importe_comida float NOT NULL,
	importe_envio float NOT NULL,
	importe_total float NOT NULL,
	CONSTRAINT FK_FactPedido_DimTiempo FOREIGN KEY (id_tiempo) REFERENCES Dim_Tiempo(id_tiempo),
    CONSTRAINT FK_FactPedido_DimRestaurante FOREIGN KEY (id_restaurante) REFERENCES Dim_Restaurante(id_restaurante),
    CONSTRAINT FK_FactPedido_DimUbicacion FOREIGN KEY (id_ubicacion) REFERENCES Dim_Ubicacion(id_ubicacion),
	CONSTRAINT FK_FactPedido_DimComida FOREIGN KEY (id_comida) REFERENCES Dim_Comida(id_comida),
	PRIMARY KEY (id_pedido, id_tiempo, id_restaurante, id_ubicacion, id_comida),
);
