--Consulta 1 ... Muestra la lista de los alumnos que estan en una misma materia y no pagaron la cuota
create procedure regulares_en_materias @materiaID int as select ALUMNO.ID_alumno, ALUMNO.nombre, ALUMNO.apellido, MATERIA.ID_materia, MATERIA.nombre as nombre_materia  from ALUMNO, MATERIA, ALU_MAT where ALUMNO.ID_alumno = ALU_MAT.ID_alumno and MATERIA.ID_materia = ALU_MAT.ID_materia and ALUMNO.pago = 0 and MATERIA.ID_materia = @materiaID;

exec regulares_en_materias @materiaID = 1;

--Consulta 2 ... Muestra la informacion de los alumnos que estan en una misma materia
create procedure alumnos_en_materias @materiaID int as select ALUMNO.* from ALUMNO, CAR_ALU, ALU_MAT, MATERIA where MATERIA.ID_materia=ALU_MAT.ID_materia AND MATERIA.ID_materia = @materiaID and Alumno.ID_alumno = CAR_ALU.ID_alumno and ALUMNO.ID_alumno = ALU_MAT.ID_alumno;

exec alumnos_en_materias @materiaID = 1;

--Consulta 3 ... Muestra todas las materias y su fecha de mesa
select MATERIA.nombre, MESA.fecha from MATERIA, MESA, MAT_MESA where MATERIA.ID_materia = MAT_MESA.ID_materia and MESA.fecha = MAT_MESA.fecha;

--Consulta 4 ... Muestra todos los profesores que dan una materia
create procedure profesores_en_materias @materiaID int as select * from PROFESOR, PRO_MAT, MATERIA where MATERIA.ID_materia = @materiaID and PROFESOR.ID_profesor = PRO_MAT.ID_profesor and PRO_MAT.ID_materia = MATERIA.ID_materia;

exec profesores_en_materias @materiaID = 239;

--Consulta 5 ... Muestra todos los alumnos que estan inscriptos en una mesa
create procedure alumnos_en_mesas @materiaID int, @mesa_fecha date as select ALUMNO.ID_alumno, ALUMNO.nombre, ALUMNO.apellido, MATERIA.nombre AS nombre_materia, MESA.fecha from MESA,ALUMNO, ALU_MESA, MATERIA, MAT_MESA where  MATERIA.ID_materia = @materiaID AND MESA.fecha = @mesa_fecha AND MESA.fecha = MAT_MESA.fecha AND MAT_MESA.ID_materia = MATERIA.ID_materia AND MAT_MESA.ID_mesa = ALU_MESA.ID_mesa AND ALUMNO.ID_alumno = ALU_MESA.ID_alumno;

exec alumnos_en_mesas @materiaID = 1, @mesa_fecha = '20190626';

--Consulta 6 ... Muestra el email de alumnos inscritos en una materia
create procedure email_alumnos @materiaID int as select ALUMNO.nombre, ALUMNO.apellido, ALUMNO.email from ALUMNO, ALU_MAT where ALUMNO.ID_alumno = ALU_MAT.ID_alumno and ALU_MAT.ID_materia = @materiaID;

exec email_alumnos @materiaID = 1;

--Consulta 7 ... Muestra el email profesores en una materia
create procedure email_profesores @materiaID int as select PROFESOR.nombre, PROFESOR.apellido, PROFESOR.email from PROFESOR, PRO_MAT where PROFESOR.ID_profesor = PRO_MAT.ID_profesor and PRO_MAT.ID_materia = @materiaID;

exec email_profesores @materiaID = 239;

--Consulta 8 ... Muestra todos los alumnos que no pagaron
select * from ALUMNO where ALUMNO.pago = 0;

--Consulta 9 ... Muestra el id de un alumno especifico
create procedure alumnoID @alumno_nombre varchar(50), @alumno_apellido varchar(50) as select ALUMNO.* from ALUMNO where ALUMNO.nombre = @alumno_nombre and ALUMNO.apellido = @alumno_apellido;

exec alumnoID @alumno_nombre = 'Lucas' , @alumno_apellido = 'Soria';

--Consulta 10 ... Muestra el telefono de un alumno y materias inscripto
select ALUMNO.ID_alumno, ALUMNO.nombre, ALUMNO.apellido, ALUMNO.telefono, MATERIA.nombre from ALUMNO, ALU_MAT, MATERIA where ALUMNO.ID_alumno = ALU_MAT.ID_alumno and MATERIA.ID_materia = ALU_MAT.ID_materia order by ALUMNO.ID_alumno;

--Consulta 11 ... El nombre de la materia y la fecha de la mesa de esa materia que da un profesor
create procedure mesa_profesor @profesorID int as SELECT MATERIA.nombre,MESA.fecha FROM PROFESOR,MESA,MAT_MESA,MATERIA,PRO_MAT WHERE PROFESOR.ID_profesor=PRO_MAT.ID_profesor AND PRO_MAT.ID_materia=MATERIA.ID_materia AND PROFESOR.ID_profesor= @profesorID AND MATERIA.ID_materia=MAT_MESA.ID_materia and MAT_MESA.fecha=MESA.fecha;

exec mesa_profesor @profesorID= 11;