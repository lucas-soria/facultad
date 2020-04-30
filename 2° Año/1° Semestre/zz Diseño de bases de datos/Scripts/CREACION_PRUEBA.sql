CREATE TABLE test.prueba (
fecha char(10) default NULL,
sucursal decimal (10,0) NOT NULL,
terminal decimal (10,0) NOT NULL,
producto varchar (45) NOT NULL,
cantidad decimal (10,0) NOT NULL
);

LOAD DATA INFILE 'C:\Users\Lucas\Documents\01 A Facultad\2° Año\1° Semestre\Diseño de bases de datos\Ing. Carlos Palacio-20190417\archivo_plano1.txt' INTO TABLE prueba FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';
LOAD DATA INFILE 'C:\Users\Lucas\Documents\01 A Facultad\2° Año\1° Semestre\Diseño de bases de datos\Ing. Carlos Palacio-20190417\archivo_plano2.txt' INTO TABLE prueba FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';