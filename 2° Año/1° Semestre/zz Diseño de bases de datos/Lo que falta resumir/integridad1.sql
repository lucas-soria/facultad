CREATE TABLE departamento (
   idDepartamento  INT, 
   nombre          CHAR(15)  NOT NULL, 
   ciudad          CHAR(10) NOT NULL, 
   CONSTRAINT DOM_Ciudad
       CHECK (ciudad IN ('Mendoza', 'Cordoba', 'Rosario', 'La Plata')), 
   CONSTRAINT PK_Departamento PRIMARY KEY (idDepartamento)
);


CREATE TABLE  empleado(
  idEmpleado      INTEGER,
  nro_doc         CHAR(14),  
  nombreEmpleado  CHAR(40) NOT NULL, 
  oficio          CHAR(11) NOT NULL, 
  cargo           CHAR(12) NOT NULL,
  jefe            INTEGER, 
  ingreso                     DATE NOT NULL, 
  salario         DECIMAL(10,2), 
  comision        DECIMAL(10,2), 
  idDepartamento  INT  NOT NULL, 
  CONSTRAINT PK_Empleado PRIMARY KEY (idEmpleado), 
  CONSTRAINT AK_Empleado UNIQUE (NRO_DOC),
  CONSTRAINT FK_EmpJefe FOREIGN KEY (jefe) REFERENCES empleado (idEmpleado),
  CONSTRAINT  FK_Empleado  FOREIGN KEY (idDepartamento)
               REFERENCES departamento (idDepartamento),
  CONSTRAINT D_Salario CHECK ( salario > 0 ),           
  CONSTRAINT D_NombreEmpleado CHECK (nombreEmpleado = UPPER(nombreEmpleado))
); 




select * from empleado;
/* Analizar los siguientes errores*/
insert into empleado values (1,'15522698','CARLOS ALBERTO SANCHEZ','INGENIERO','JEFE',NULL,'20000715',40000,4,10);

insert into departamento values(10,'MONTAJE','MENDOZA');
insert into empleado values (1,'15522698','CARLOS ALBERTO SANCHEZ','INGENIERO','JEFE',NULL,'20000715',40000,4,10);

insert into empleado values (1,'15522698','CARLOS ALBERTO SANCHEZ','INGENIERO','JEFE',NULL,'20000715',40000,2,10);

insert into empleado values (2,'15522698','CARLOS ALBERTO SANCHEZ','INGENIERO','JEFE',NULL,'20000715',40000,4,10);

insert into empleado values (2,'23453298','JUAN JOSE GODOY','HERRERO','SUPERVISOR',3,'20120101',20000,3,10);

insert into empleado values (2,'23453298','JUAN JOSE GODOY','HERRERO','SUPERVISOR',1,'20120101',20000,3,10);


insert into departamento values(20,'TALLER','MENDOZA');
insert into empleado values (3,'35522698','RICARDO PEREZ','TORNERO','ESPECIALISTA',1,'20000715',10000,4,20);


/*si borramos departamento*/
delete from departamento where idDepartamento=20;


/* implementar el borrado en cascada si borramos un departamento borrar los empleados que trabajan en el*/
ALTER TABLE empleado DROP CONSTRAINT FK_Empleado;
ALTER TABLE empleado ADD CONSTRAINT FK_Empleado
          FOREIGN KEY (idDepartamento)
          REFERENCES departamento (idDepartamento)
          ON DELETE CASCADE;
select * from empleado;
select * from departamento;
delete from departamento where idDepartamento=20;
select * from empleado;
select * from departamento;

delete from empleado where idEmpleado=1;
ALTER TABLE empleado DROP CONSTRAINT FK_EmpJefe;         
ALTER TABLE empleado ADD CONSTRAINT FK_EmpJefe
          FOREIGN KEY (jefe) REFERENCES empleado (idEmpleado)
          ON DELETE CASCADE;
delete from empleado where idEmpleado=1;
select * from empleado;

insert into empleado values (1,'15522698','CARLOS ALBERTO SANCHEZ','INGENIERO','JEFE',NULL,'20000715',40000,4,10);

/* implementar que al querer borrar un departamento lo ignore*/
ALTER TABLE empleado DROP CONSTRAINT FK_Empleado;
ALTER TABLE empleado ADD CONSTRAINT FK_Empleado
          FOREIGN KEY (idDepartamento)
          REFERENCES departamento (idDepartamento)
          ON DELETE NO ACTION;
select * from empleado;
select * from departamento;
delete from departamento where idDepartamento=20;
select * from empleado;
select * from departamento;

/*========================================================================================*/

drop table empleado;
drop table departamento;