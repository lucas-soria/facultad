

###### EJERCICIOS CON INDICES :
DROP TABLE IF EXISTS `test`.`prueba`;
CREATE TABLE  `test`.`prueba` (
  `fecha` char(10) default NULL,
  `sucursal` decimal(10,0) NOT NULL,
  `terminal` decimal(10,0) NOT NULL,
  `producto` varchar(45) NOT NULL,
  `cantidad` decimal(10,0) NOT NULL
) ;

SHOW VARIABLES;
#para poder cargar una tabla desde un archivo plano;
# 1- edita el C:\ProgramData\MySQL\MySQL Server 5.6\my.ini y poner secure_file_priv=""
# 2- reiniciar el servicio mysql
# 3- SET GLOBAL local_infile = 1;
# 4- poner el archivo plano en C:\ProgramData\MySQL\MySQL Server 5.6\data\test donde test es la base de datos en donde esta la tabla
# 5- LOAD DATA INFILE 'p.txt' INTO TABLE prueba FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';
LOAD DATA INFILE 'p.txt' INTO TABLE prueba FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';
# 6- si es necesario duplicar la cantidad de registros ejecutar el siguiente comando
LOAD DATA INFILE 'pp.txt' INTO TABLE prueba FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';

select * from prueba;

select SUBSTR(fecha, 7, 4) from prueba group by 1;

select count(*) from prueba where sucursal=136; #duration=1547 sec

create index prueba1 on prueba(sucursal);
SHOW INDEXES FROM prueba;

select count(*) from prueba where sucursal=136; #duration=0.063 sec

drop index prueba1 on prueba;

CREATE TABLE  `test`.`prueba2` (
  `fecha` char(10) default NULL,
  `sucursal` decimal(10,0) NOT NULL,
  `terminal` decimal(10,0) NOT NULL,
  `producto` varchar(45) NOT NULL,
  `cantidad` decimal(10,0) NOT NULL
) ;
#LOAD DATA INFILE 'p.txt' INTO TABLE prueba2 FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';
LOAD DATA INFILE 'pp.txt' INTO TABLE prueba2 FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';
#LOAD DATA INFILE 'ppp.txt' INTO TABLE prueba2 FIELDS TERMINATED BY '|' LINES TERMINATED BY '\r\n';

select count(*) from prueba2;


select SUBSTR(fecha, 7, 4),count(*) from prueba2 group by 1;

select count(*) from prueba  where prueba.sucursal in (select prueba2.sucursal from prueba2 where  prueba2.sucursal=136);

create index prueba21 on prueba2(sucursal);

drop index prueba21 on prueba2;

select sucursal,count(*) from prueba group by 1;

