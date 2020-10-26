USE ComiditasYaDW;

INSERT INTO Dim_Ubicacion(departamento, provincia) VALUES
	('Godoy Cruz', 'Mendoza'),
	('Lujan de Cuyo', 'Mendoza'),
	('Olavarria', 'Buenos Aires'),
	('Zapala', 'Neuquen'),
	('Tandil', 'Buenos Aires'),
	('Neuquen', 'Neuquen'),
	('Rosario', 'Santa Fe')
;

INSERT INTO Dim_Tiempo(agno, mes, dia, hora, dia_semana) VALUES
	(2020, 05, 09, 16, 'sabado'),
	(2020, 05, 09, 00, 'sabado'),
	(2020, 03, 23, 16, 'lunes'),
	(2020, 07, 30, 16, 'jueves'),
	(2020, 10, 03, 12, 'sabado'),
	(2020, 02, 15, 07, 'sabado'),
	(2020, 02, 15, 23, 'sabado'),
	(2020, 01, 05, 20, 'domingo'),
	(2020, 02, 19, 18, 'miercoles'),
	(2020, 05, 26, 16, 'martes')
;

INSERT INTO Dim_Restaurante (tipo_empresa, tipo_restaurante, nombre) VALUES
	('franquicia', 'comida rapida', 'McDonalds S.A.'),
	('franquicia', 'comida rapida', 'Burger King S.A.'),
	('franquicia', 'comida rapida', 'Wendys S.A.'),
	('franquicia', 'comida rapida', 'KFC S.A.'),
	('local', 'italiana', 'Nipotti'),
	('local', 'italiana', 'Zitto'),
	('local', 'heladeria', 'Alfredo'),
	('local', 'heladeria', 'Helados de Chacras.'),
	('franquicia', 'heladeria', 'Freddo S.A.'),
	('local', 'sushi', 'Yoko Sushi'),
	('local', 'sushi', 'Ai Sushi'),
	('local', 'panaderia', 'Don Luis'),
	('franquicia', 'panaderia', 'La Vene'),
	('local', 'panaderia', 'Del Barrio'),
	('local', 'panaderia', 'Virgen del Valle'),
	('local', 'cafeteria', 'Crepas Palmares'),
	('local', 'cafeteria', 'Kato Chacras'),
	('local', 'resto', 'El Club de la Milanesa'),
	('local', 'resto', 'Hilario'),
	('franquicia', 'bebidas', 'Go Bar'),
	('local', 'resto', 'Anastasio el Pollo')
;

INSERT INTO Dim_Comida (clasificacion, categoria, tipo_comida, nombre, precio) VALUES
	('lunch', 'pasta', 'pizza', 'Margarita', 250.0),
	('lunch', 'carne', 'milanesa', 'Clasica', 200.0),
	('lunch', 'carne', 'milanesa', 'Napolitana', 350.0),
	('lunch', 'carne', 'pollo', 'A la parrila', 350.0),
	('lunch', 'carne', 'pollo', 'spiedo', 400.0),
	('dessert', 'helado', 'crema', 'Chocolate', 50.0),
	('dessert', 'helado', 'crema', 'Chocolate Almendrado', 50.0),
	('dessert', 'helado', 'crema', 'Dulce de Leche', 50.0),
	('dessert', 'helado', 'crema', 'Banana Split', 50.0),
	('dessert', 'helado', 'agua', 'Limon', 50.0),
	('dessert', 'helado', 'agua', 'Frutilla', 50.0),
	('dessert', 'helado', 'agua', 'Maracuya', 50.0),
	('dessert', 'helado', 'crema', 'Tramontana', 50.0),
	('lunch', 'pasta', 'fideos', 'Bolognesa', 200.0),
	('lunch', 'pasta', 'fideos', 'Salsa Blanca', 150.0),
	('lunch', 'pasta', 'fideos', 'Pesto', 150.0),
	('lunch', 'pasta', 'ravioles', 'Bolognesa', 250.0),
	('lunch', 'pasta', 'ravioles', 'Salsa Blanca', 210.0),
	('dinner', 'sandwich', 'lomo', 'Clasico', 450.0),
	('dinner', 'sandwich', 'lomo', 'Completo', 500.0),
	('dinner', 'sandwich', 'lomo', 'Lomo Pizza', 1000.0)
;

INSERT INTO Fact_Pedidos (id_tiempo, id_restaurante, id_ubicacion, id_comida, importe_comida, importe_envio, importe_total) VALUES
	(1, 7, 1, 1, 500, 30, 530),
	(1, 7, 1, 21, 1000, 50, 1050),
	(1, 9, 1, 5, 400, 25, 425),
	(1, 12, 1, 7, 150, 30, 180),
	(1, 20, 1, 8, 50, 20, 70),
	(2, 5, 7, 2, 800, 17, 817),
	(2, 6, 7, 1, 750, 30, 780),
	(2, 1, 7, 4, 350, 25, 375),
	(3, 21, 1, 7, 50, 50, 100),
	(4, 3, 4, 17, 250, 10, 260),
	(5, 4, 5, 12, 50, 20, 70),
	(6, 2, 6, 9, 250, 60, 310),
	(7, 1, 2, 19, 450, 80, 530),
	(8, 15, 2, 20, 1500, 50, 1550),
	(9, 16, 3, 3, 350, 20, 370),
	(10, 17, 1, 1, 250, 30, 280),
	(10, 18, 7, 6, 50, 40, 90),
	(10, 19, 7, 9, 150, 90, 240)
;
