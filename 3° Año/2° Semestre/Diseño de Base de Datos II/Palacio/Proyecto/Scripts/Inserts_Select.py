import pyodbc
import csv


def crear_conexion():
    conn = pyodbc.connect('Driver={SQL Server};'
                          'Server=LUCAS;'
                          'Database=PedidosYaArgentina;'
                          'Trusted_Connection=yes;')
    return conn, conn.cursor()


def leer_archivo(path, columnas):
    archivo = path
    lista = []
    with open(archivo) as csvfile:
        reader = csv.DictReader(csvfile)
        for row in reader:
            listac = []
            for i in columnas:
                listac.append(row[i])
            lista.append(listac)
    return lista


def insert(tabla, columnas, path, col):
    lista = leer_archivo(path, col)
    cant_valores = []
    for _ in range(len(columnas)):
        cant_valores.append("?")
    insert_records = f'''INSERT INTO PedidosYaArgentina.dbo.{tabla}({",".join(columnas)}) VALUES({",".join(cant_valores)}) '''
    cursor.executemany(insert_records, lista)
    conn.commit()


def select(tabla, *columnas):
    if columnas:
        cursor.execute(f'SELECT {",".join(columnas)} FROM PedidosYaArgentina.dbo.{tabla}')
    else:
        cursor.execute(f'SELECT * FROM PedidosYaArgentina.dbo.{tabla}')
    for row in cursor:
        print(row)


def inserts():
    # Tabla, columnas, archivo, columnas en archivo

    # inserts de la provincia
    insert("Provincia",
           ["nombre"],
           "C:\\Users\\Lucas\\Documents\\01 A Facultad\\3° Año\\2° Semestre\\Diseño de Base de Datos II\\Palacio\\Proyecto\\Datos\\utiles\\provincias_ARG.csv",
           ["nombre"])

    # inserts de los deptos
    insert("Departamento",
           ["nombre", "id_provincia"],
           "C:\\Users\\Lucas\\Documents\\01 A Facultad\\3° Año\\2° Semestre\\Diseño de Base de Datos II\\Palacio\\Proyecto\\Datos\\utiles\\departamentos.csv",
           ["nombre", "provincia_nombre"])

    # inserts de los clientes
    insert(
        "Cliente", [
            "nombre", "apellido", "email", "direccion", "telefono",
            "id_departamento"
        ],
        "C:\\Users\\Lucas\\Documents\\01 A Facultad\\3° Año\\2° Semestre\\Diseño de Base de Datos II\\Palacio\\Proyecto\\Datos\\utiles\\personas.csv",
        ["name", "surname", "email", "address", "phone", "dep"])


def consultas():
    # Tabla (siempre), colmunas (opcional)
    select("Provincia", "id_provincia", "nombre")
    select("Provincia", "nombre")
    select("Departamento")
    select("Cliente")


if __name__ == "__main__":
    conn, cursor = crear_conexion()
    inserts()
    consultas()
