CREATE DATABASE ProyectoUM;

USE ProyectoUM;

CREATE TABLE ALUMNO (
    ID_alumno int NOT NULL IDENTITY(1,1),
    nombre varchar(50) NOT NULL,
	apellido varchar(50) NOT NULL,
	DNI int NOT NULL,
	email varchar(50),
	direccion varchar(75),
	telefono bigint,
	pago bit NOT NULL,
	PRIMARY KEY (ID_Alumno)
); 

CREATE TABLE FACULTAD (
	ID_facultad int NOT NULL IDENTITY(1,1),
	nombre varchar(50) NOT NULL,
	direccion varchar(75) NOT NULL
	PRIMARY KEY (ID_facultad)
);

CREATE TABLE PROFESOR (
	ID_profesor int NOT NULL IDENTITY(1,1),
	nombre varchar(50) NOT NULL,
	apellido varchar(50) NOT NULL,
	DNI int NOT NULL,
	email varchar(50),
	direccion varchar(75),
	telefono bigint,
	CUIT bigint,
	fecha_inicio date NOT NULL,
	PRIMARY KEY (ID_profesor)
);

CREATE TABLE CARRERA (
	ID_carrera int NOT NULL IDENTITY(1,1),
	nombre varchar(100) NOT NULL,
	duracion float NOT NULL,
	ID_facultad int NOT NULL,
	PRIMARY KEY (ID_carrera),
	FOREIGN KEY (ID_facultad) REFERENCES FACULTAD(ID_facultad) ON DELETE CASCADE
);

CREATE TABLE MATERIA (
	ID_materia  int NOT NULL IDENTITY(1,1),
	nombre  varchar(100) NOT NULL,
	duracion  int NOT NULL,
	PRIMARY KEY (ID_materia)
);

CREATE TABLE MESA (
	fecha date NOT NULL,
	PRIMARY KEY (fecha)
);

CREATE TABLE CAR_ALU (
	ID_carrera int NOT NULL,
	ID_alumno int NOT NULL
	PRIMARY KEY (ID_carrera, ID_alumno),
	FOREIGN KEY (ID_carrera) REFERENCES CARRERA(ID_carrera) ON DELETE CASCADE,
	FOREIGN KEY (ID_alumno) REFERENCES ALUMNO(ID_alumno) ON DELETE CASCADE
);

CREATE TABLE CAR_MAT (
	ID_carrera int NOT NULL,
	ID_materia int NOT NULL,
	PRIMARY KEY (ID_carrera, ID_materia),
	FOREIGN KEY (ID_carrera) REFERENCES CARRERA(ID_carrera) ON DELETE CASCADE,
	FOREIGN KEY (ID_materia) REFERENCES MATERIA(ID_materia) ON DELETE CASCADE
);

CREATE TABLE PRO_MAT (
	ID_materia int NOT NULL,
	ID_profesor int NOT NULL,
	PRIMARY KEY (ID_materia, ID_profesor),
	FOREIGN KEY (ID_materia) REFERENCES MATERIA(ID_materia) ON DELETE CASCADE,
	FOREIGN KEY (ID_profesor) REFERENCES PROFESOR(ID_profesor) ON DELETE CASCADE
);

CREATE TABLE ALU_MAT (
	ID_materia int NOT NULL,
	ID_alumno int NOT NULL,
	PRIMARY KEY (ID_materia, ID_alumno),
	FOREIGN KEY (ID_materia) REFERENCES MATERIA(ID_materia) ON DELETE CASCADE,
	FOREIGN KEY (ID_alumno) REFERENCES ALUMNO(ID_alumno) ON DELETE CASCADE
);

CREATE TABLE MAT_MESA (
	ID_mesa int NOT NULL IDENTITY(1,1),
	fecha date NOT NULL,
	ID_materia int NOT NULL,
	PRIMARY KEY (ID_mesa),
	FOREIGN KEY (ID_materia) REFERENCES MATERIA(ID_materia) ON DELETE CASCADE,
	FOREIGN KEY (fecha) REFERENCES MESA(fecha) ON DELETE CASCADE
);

CREATE TABLE ALU_MESA (
	ID_alumno int NOT NULL,
	ID_mesa int NOT NULL,
	PRIMARY KEY (ID_mesa, ID_alumno),
	FOREIGN KEY (ID_mesa) REFERENCES MAT_MESA(ID_mesa) ON DELETE CASCADE,
	FOREIGN KEY (ID_alumno) REFERENCES ALUMNO(ID_alumno) ON DELETE CASCADE
);