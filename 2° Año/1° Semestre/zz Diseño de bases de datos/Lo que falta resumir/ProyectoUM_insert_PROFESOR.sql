use ProyectoUM;
--TRIGGER QUE CREA EL MAIL DEL PROFESOR
CREATE TRIGGER create_mail_profesor ON PROFESOR AFTER INSERT AS update PROFESOR SET email = replace(lower(PROFESOR.nombre),  ' ' , '' ) + '.' + replace(lower(PROFESOR.apellido), ' ', '') + '@um.ar';

--STORED PROCEDURE QUE HACE UNA CONSULTA DE LOS DATOS DEL PROFESOR Y TAMBIEN LE CALCULA SU ANTIGÜEDAD
CREATE PROCEDURE antiguedad AS SELECT *, DATEDIFF(YEAR, PROFESOR.fecha_inicio, SYSDATETIME()) AS antiguedad FROM PROFESOR;



INSERT INTO PROFESOR(nombre, apellido, DNI, telefono, fecha_inicio) VALUES 
		('Daniel', 'Acevedo', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Miguel Vicente', 'Agurto Rondoy', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19960226'), 
		('Christian Nelson', 'Negrón', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20051103'), 
		('Isela Flor', 'Rojas', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20100808'), 
		('Leoncia', 'Castillo', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20170903'), 
		('Luz Marina', 'Bedregal Canales', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20110630'), 
		('Ramiro Alberto', 'Torres', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20150711'), 
		('Javier', 'Espejo', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19991015'), 
		('Nelson', 'Rufino', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20080305'), 
		('Carlos', 'Lacotera', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20120709'), 
		('Doris', 'Moreno', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20160505'), 
		('Maribel Corina', 'Cortez', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20001107'), 
		('Angel', 'Lozano', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20150826'), 
		('Esther Aurora', 'Quispe', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20150826'), 
		('Olga', 'Fernandez Matta', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Edwin', 'Ferro', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Roberto', 'Flores', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Gloria', 'Romero', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20150826'), 
		('Miriam', 'Lozano', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Arturo', 'García' , floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20150826'), 
		('Gissela', 'Peralta', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19920307'), 
		('Cosme Adolfo', 'Gonzales', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19980505'), 
		('Sandra Monica', 'Maguiña', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20010301'), 
		('Jenny Maria', 'Maldonado', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Santiago', 'Maldonado', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Santiago Victor', 'Mallqui', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Arturo', 'Celestino', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20000505'), 
		('Enrique', 'Paredes', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20000705'), 
		('Sonia', 'Pastor', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Gerardo David', 'Nuñez', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Violeta Marilu', 'Vilchez', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Augusto', 'Riega Calle', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20000305'), 
		('Pedro Manuel', 'Salinas', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Angel', 'Sanchez', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19990101'), 
		('Jose Alberto', 'Santa cruz', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970308'), 
		('Angel ', 'Solano Vargas', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970912'), 
		('Blanca Katty', 'Tejedo Luna', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19971212'), 
		('Enrique Godofredo', 'Tenorio Davila', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970330'), 
		('Cecilia', 'Vilca Lucero', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970420'), 
		('Mariela Milagros', 'Alvarado', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '19970427'), 
		('Monica',  'Vega', floor(rand()*(30000000-2000000+1)+20000000), 2610000000 + floor(rand()*(9999999-2+1)+2), '20000305')

exec antiguedad;