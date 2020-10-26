# import json
# import csv
import random


# with open('./provincias.json', 'r') as file:
#     data = json.load(file)
#     for depto in data['departamentos']:
#         print("('" + depto['nombre'] + "', '" + depto['provincia']['nombre'] + "'),")
# ('franquicia', 'comida rapida', 'McDonalds S.A.'),
#   nombres = []
#   with open('./oferta_gastronomica.csv', 'r') as csvfile:
#       reader = csv.DictReader(csvfile)
#       for row in reader:
#           nombre = row["nombre"]
#           if nombre not in nombres and "'" not in nombre and "," not in nombre and '"' not in nombre and "&" not in nombre:
#               nombres.append(nombre)
#   emp = ['franquicia', 'local']
#   tipo = ['comida rapida', 'italiana', 'heladeria', 'sushi', 'parrillada', 'panaderia', 'cafeteria', 'mexicano', 'japones',
#            'vinoteca', 'licoreria', 'bebidas', 'restaurant', 'confiteria', 'tailandesa', 'arabe']
#   for i in nombres:
#       print("\t('" + random.choice(emp) + "', '" + random.choice(tipo) + "', '" + i + "'),")
lista = [250.0, 200.0, 350.0, 350.0, 400.0, 200.0, 150.0, 150.0, 250.0, 210.0, 50.0, 50.0, 50.0, 50.0, 25.0, 25.0,
         25.0, 50.0, 50.0, 50.0, 450.0, 500.0, 1000.0, 2000.0, 100.0, 200.0, 200.0, 200.0, 1499.99, 750.0]
# 44999 tiempos
# 2481 resta
# 528 ubic
# 29 comid
# import com
# import envio
# tot
# (1, 7, 1, 1, 500, 30, 530)
for i in range(1000000):
    tiempo = str(random.randint(0, 44999))
    resta = str(random.randint(0, 2481))
    ubic = str(random.randint(0, 528))
    comid = random.randint(0, 29)
    env = random.randint(25, 100)
    print("\t(" + tiempo + ", " + resta + ", " + ubic + ", " + str(comid) + ", " + str(lista[comid]) + ", " + str(env) + ", " + str(lista[comid] + env) + "),")
