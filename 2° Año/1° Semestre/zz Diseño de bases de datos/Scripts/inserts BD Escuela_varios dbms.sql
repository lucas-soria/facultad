/* voy a insertar datos en la tabla Localidades */
/*script para ejecutar en SQLServer y Oracle */
INSERT INTO LOCALIDADES (idlocalidad,nom_localidad,cod_post) VALUES(1,'Mendoza',5500);
INSERT INTO LOCALIDADES (idlocalidad,nom_localidad,cod_post) VALUES(2,'Godoy Cruz',5501);
INSERT INTO LOCALIDADES (idlocalidad,nom_localidad,cod_post) VALUES(3,'Guaymallen',5519);
INSERT INTO LOCALIDADES (idlocalidad,nom_localidad,cod_post) VALUES(4,'Lujan de Cuyo',5507);

/*script para ejecutar en una línea en MySQL*/
INSERT INTO LOCALIDADES (idlocalidad,nom_localidad,cod_post) VALUES(1,'Mendoza',5500),(2,'Godoy Cruz',5501),(3,'Guaymallen',5519),(4,'Lujan de Cuyo',5507);

/* voy a insertar datos en la tabla Materias */
/*script para SQLServer y Oracle */

INSERT INTO MATERIAS(idmateria,nom_materia) VALUES (1, 'Lengua');
INSERT INTO MATERIAS(idmateria,nom_materia) VALUES (2, 'Matematica');
INSERT INTO MATERIAS(idmateria,nom_materia) VALUES (3, 'Historia');
INSERT INTO MATERIAS(idmateria,nom_materia) VALUES (4, 'Geografia');
INSERT INTO MATERIAS(idmateria,nom_materia) VALUES (5, 'Filosofia');
INSERT INTO MATERIAS(idmateria,nom_materia) VALUES (6, 'Quimica');

/*script para ejecutar en una línea en MySQL*/
INSERT INTO MATERIAS(idmateria,nom_materia) VALUES (1, 'Lengua'),(2, 'Matematica'), (3, 'Historia'),(4, 'Geografia'),(5, 'Filosofia'),(6, 'Quimica');

/*script para insertar datos en la tabla alumnos*/
/*script para  SQLServer */
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(42500600,'ALARCON','MARCELO','1995/10/12','malarcon@hotmail.com','422133','San Martin 1478',1);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(44100200,'FERNANDEZ','FLAVIA','1995/04/02','ffernandez@hotmail.com','4341256','San Juan 1025',2);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(451203366,'BALCARCE','ABEL','1995/08/28','abalcarce@hotmail.com','4984125','España 277',4);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(44100400,'MORALES','MARCELO','1995/12/07','mmorales@hotmail.com','4330278','Cobos 438',3);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(42500300,'FERNANDEZ','JONATHAN','1995/05/12','jfernandez@hotmail.com','441788','San Martin 248',2);

/*script para Oracle*/
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(42500600,'ALARCON','MARCELO',TO_DATE('1995/10/12 00:00:00' , 'yyyy/mm/dd hh24:mi:ss'),'malarcon@hotmail.com','422133','San Martin 1478',1);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(44100200,'FERNANDEZ','FLAVIA',TO_DATE('1995/04/02 00:00:00' , 'yyyy/mm/dd hh24:mi:ss'),'ffernandez@hotmail.com','4341256','San Juan 1025',2);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(451203366,'BALCARCE','ABEL',TO_DATE('1995/08/28 00:00:00' , 'yyyy/mm/dd hh24:mi:ss'),'abalcarce@hotmail.com','4984125','España 277',4);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(44100400,'MORALES','MARCELO',TO_DATE('1995/12/07 00:00:00' , 'yyyy/mm/dd hh24:mi:ss'),'mmorales@hotmail.com','4330278','Cobos 438',3);
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(42500300,'FERNANDEZ','JONATHAN',TO_DATE('1995/05/12 00:00:00' , 'yyyy/mm/dd hh24:mi:ss'),'jfernandez@hotmail.com','441788','San Martin 248',2);


/*para MySQL*/
INSERT INTO ALUMNOS(DNI,Apellido,Nombre,Fecha_nac,email,telefono,direccion,idlocalidad) VALUES
(42500600,'ALARCON','MARCELO','1995/10/12','malarcon@hotmail.com','422133','San Martin 1478',1),
(44100200,'FERNANDEZ','FLAVIA','1995/04/02','ffernandez@hotmail.com','4341256','San Juan 1025',2),
(45120336,'BALCARCE','ABEL','1995/08/28','abalcarce@hotmail.com','4984125','España 277',4),
(44100400,'MORALES','MARCELO','1995/12/07','mmorales@hotmail.com','4330278','Cobos 438',3),
(42500300,'FERNANDEZ','JONATHAN','1995/05/12','jfernandez@hotmail.com','441788','San Martin 248',2);

/*inserciones en la tabla Notas: los parciales no son obligatorios, entonces primero creamos los
cursos, en cierto modo*/
/*para SQL Server y Oracle*/

INSERT INTO NOTAS(idmateria,DNI) VALUES(1,42500600);
INSERT INTO NOTAS(idmateria,DNI) VALUES(2,42500600);
INSERT INTO NOTAS(idmateria,DNI) VALUES(3,42500600);
INSERT INTO NOTAS(idmateria,DNI) VALUES(4,42500600);
INSERT INTO NOTAS(idmateria,DNI) VALUES(5,42500600);
INSERT INTO NOTAS(idmateria,DNI) VALUES(6,42500600);

INSERT INTO NOTAS(idmateria,DNI) VALUES(1,44100200);
INSERT INTO NOTAS(idmateria,DNI) VALUES(2,44100200);
INSERT INTO NOTAS(idmateria,DNI) VALUES(3,44100200);
INSERT INTO NOTAS(idmateria,DNI) VALUES(4,44100200);
INSERT INTO NOTAS(idmateria,DNI) VALUES(5,44100200);
INSERT INTO NOTAS(idmateria,DNI) VALUES(6,44100200);
/*para MySQL*/

INSERT INTO NOTAS(DNI,idmateria) VALUES(42500600,1),(42500600,2),(42500600,3),(42500600,4),(42500600,5),(42500600,6);
INSERT INTO NOTAS(DNI,idmateria) VALUES(44100200,1),(44100200,2),(44100200,3),(44100200,4),(44100200,5),(44100200,6);
