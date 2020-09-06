from Inserts_Select import leer_archivo
from random import randint


path = "C:\\Users\\Lucas\\Documents\\01 A Facultad\\3° Año\\2° Semestre\\Diseño de Base de Datos II\\Palacio\\Proyecto\\Datos\\utiles\\dataSep-5-2020.csv"
columnas = ["name", "surname", "address"]
datos = leer_archivo(path, columnas)
for i in range(len(datos)):
    datos[i].append(datos[i][0][0].lower() + "." + datos[i][1].lower() + "@pedidosya.com")


minim = 1
maxim = 529

file = "C:\\Users\\Lucas\\Documents\\01 A Facultad\\3° Año\\2° Semestre\\Diseño de Base de Datos II\\Palacio\\Proyecto\\Datos\\utiles\\personas.csv"
with open(file, "w") as csvfile:
    csvfile.write('"name","surname","address","email","phone","dep"\n')
    for i in datos:
        rand = randint(minim, maxim)
        num = randint(150000000, 159999999)
        csvfile.write('"' + '","'.join(i) + f'",{num},{rand}')
        csvfile.write('\n')

print("DONE")
