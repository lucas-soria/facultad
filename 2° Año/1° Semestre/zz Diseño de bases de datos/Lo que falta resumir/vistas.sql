
insert into departamento values (10,'Ciudad','Sexta');
insert into departamento values (20,'Ciudad','Tercera');
insert into departamento values (30,'Ciudad','Primera');
insert into departamento values (40,'Lujan','Vistalba');
insert into departamento values (50,'Lujan','Carrodilla');
insert into departamento values (60,'Maipu','Barrancas');

insert into empleado values (1,'15532798','CARLOS ALBERTO SANCHEZ','INGENIERO','JEFE',null,STR_TO_DATE('15-07-2000', '%d-%m-%Y'),40000,4,10);
insert into empleado values (2,'23453298','JUAN JOSE GODOY','HERRERO','SUPERVISOR',1,STR_TO_DATE('1-01-2012', '%d-%m-%Y'),20000,3,10);
insert into empleado values (3,'23153233','PEDRO SANCHEZ','MECANICO','ENCARGADO',2,STR_TO_DATE('1-01-2013', '%d-%m-%Y'),26000,2,10);
insert into empleado values (4,'12342748','LUIS VAZQUEZ','MECANICO','AYUDANTE',1,STR_TO_DATE('1-11-2013', '%d-%m-%Y'),16000,2,10);
insert into empleado values (5,'22543765','ENRIQUE GOMEZ','MECANICO','ASISTENTE',1,STR_TO_DATE('1-11-2014', '%d-%m-%Y'),18000,2,20);
insert into empleado values (6,'18923406','RAUL MARTINEZ','MECANICO','ASISTENTE',1,STR_TO_DATE('1-11-2011', '%d-%m-%Y'),18000,2,30);
insert into empleado values (7,'11995348','HUGO PEREZ','MECANICO','AYUDANTE',1,STR_TO_DATE('1-11-2014', '%d-%m-%Y'),18000,2,40);
insert into empleado values (8,'24163900','RICARDO MENDEZ','MECANICO','ASISTENTE',1,STR_TO_DATE('1-11-2014', '%d-%m-%Y'),18000,2,60);
/* vista con los empleados que son oficio = mecanico */
DROP VIEW vista_mecanico;
CREATE 
VIEW `vista_mecanico` AS
    SELECT 
        `empleado`.`nombreEmpleado` AS `nombreempleado`,
        `empleado`.`ingreso` AS `ingreso`,
        `empleado`.`salario` AS `salario`,
        `departamento`.`nombre` AS `departamento`,
        `departamento`.`ciudad` AS `ciudad`
    FROM
        (`departamento`
        JOIN `empleado`)
    WHERE
        ((`departamento`.`idDepartamento` = `empleado`.`idDepartamento`)
            AND (`empleado`.`oficio` = 'mecanico'));
            
 select * from vista_mecanico;
 
 /* vista de todo los empleados */           
DROP VIEW vista_nombre;
CREATE 
VIEW `vista_nombre` AS
    SELECT 
   `empleado`.`idempleado` AS `idempleado`, 
        `empleado`.`nombreEmpleado` AS `nombre`
    FROM
        (`empleado`);
select * from vista_nombre;

/* vista datos empleados y de su jefe del departamento ciudad*/
DROP VIEW vista_ciudad;
CREATE 
VIEW `vista_ciudad` AS
    SELECT 
        `empleado`.`nro_doc` AS `documento`,
        `empleado`.`nombreEmpleado` AS `nombreempleado`,
        `empleado`.`oficio` AS `oficio`,
        `empleado`.`ingreso` AS `ingreso`,
        `departamento`.`nombre` AS `departamento`,
        `departamento`.`ciudad` AS `ciudad`,
        `vista_nombre`.`nombre` AS `jefe`
    FROM
        ((`empleado`
        JOIN `departamento`)
        JOIN `vista_nombre`)
    WHERE
        ((`departamento`.`idDepartamento` = `empleado`.`idDepartamento`)
            AND (`departamento`.`nombre` = 'Ciudad')
            AND (`vista_nombre`.`idempleado` = `empleado`.`jefe`));
 select * from vista_ciudad;           

select * from departamento;
select * from empleado;

select * from vista_mecanico;
select * from vista_nombre;
select * from vista_ciudad;

/* Grant al usuario usuario*/
grant select on vista_ciudad to usuario;
grant delete on vista_ciudad to usuario;
revoke select,delete on vista_ciudad from usuario;

