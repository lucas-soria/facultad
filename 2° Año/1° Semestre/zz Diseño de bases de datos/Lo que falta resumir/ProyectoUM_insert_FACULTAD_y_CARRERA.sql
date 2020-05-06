use ProyectoUM

INSERT INTO FACULTAD (nombre, direccion) VALUES
	('Ingenieria','Boulogne Sur Mer 683, Mendoza, Argentina'),
	('Ciencias Juridicas y sociales','Boulogne Sur Mer 683, Mendoza, Argentina'),
	('Ciencias Economicas','Boulogne Sur Mer 683, Mendoza, Argentina'),
	('Arquitectora, urbanismo y diseño','Boulogne Sur Mer 683, Mendoza, Argentina'),
	('Ciencias Medicas y de la Salud','Boulogne Sur Mer 683, Mendoza, Argentina');

SELECT * FROM FACULTAD;

INSERT INTO CARRERA(ID_facultad, nombre, duracion) VALUES 
	(1,'Ingenieria Electronica',5),
	(1,'Ingenieria en Computacion',5),
	(1,'Ingenieria en Informatica',5),
	(1,'Bioingenieria',5),
	(1,'Ingenieria Industrial',5),
	(1,'Tecnicatura en Desarrollo de Aplicaciones Web',2.5),
	(1,'Tecnicatura en Desarrollo de Videojuegos',2.5),
	(1,'Tecnicatura Universitaria en Sonido y Acústica',3),
	(1,'Tecnicatura Universitaria en Mantenimiento Agroindustrial',2.5),
	(1,'Profesorado para Profesionales con Titulo de Grado',1.5),
	(1,'Ingenieria Electrica',5),
	(2,'Abogacia',5),
	(2,'Escribania',4),
	(2,'Procuracion',3),
	(3,'Contador Publico',4),
	(3,'Licenciatura en Administracion de Negocios',4),
	(4,'Arquitectura y Urbanismo',5),
	(4,'Licenciatura en Diseño',4),
	(4,'Diseño de Interiores',3),
	(4,'Diseño Grafico',3),
	(4,'Diseño de Indumentaria y Textil',3),
	(4,'Corredor Inmobiliario',2.5),
	(4,'Licenciatura en Emprendimientos Inmobiliarios',3),
	(5,'Medicina',5),
	(5,'Odontologia',5),
	(5,'Licenciatura en Kinesiologia',4),
	(5,'Licenciatura en Psicologia',5),
	(5,'Licenciatura en Nutricion',5),
	(5,'Tecnicatura Universitaria en Gestion de Emergencias y Desastres',3);

SELECT * FROM CARRERA;