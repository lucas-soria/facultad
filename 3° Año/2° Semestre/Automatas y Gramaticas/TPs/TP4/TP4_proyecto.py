# Consigna:
# Seguimiento de los usuarios que se han conectado días feriados y no
# laborables (sábados y domingos).Lucas Soria y Alejandro Marotta
import calendar
from datetime import datetime
import re
from pprint import pprint
import pandas as pd


# No hay dias feriado no laborables del 28/08 al 04/10 asi que decidimos agregar las fechas del año nuevo judio
feriados = {'29/09/2019': 'Año nuevo Judio día 1',
            '30/09/2019': 'Año nuevo Judio día 2',
            '01/10/2019': 'Año nuevo Judio día 3'}


patron = re.compile(r'^(?P<idcon>[0-9a-z]{16});(?P<usuario>(.+));(?P<fechaini>(([0-2][0-9])|(3[01]))\/(0[1-9]|1[0-2])\/\d{4} ((1?[0-9])|(2[0-3])):([0-5][0-9]));(?P<fechafin>(([0-2][0-9])|(3[01]))\/(0[1-9]|1[0-2])\/\d{4} ((1?[0-9])|(2[0-3])):([0-5][0-9]));(?P<tiempo>(\d+));(?P<input>(\d+));(?P<output>(\d+));(?P<macap>(([A-F0-9]{2}-){5}[A-F0-9]{2}:UM));(?P<maccliente>(([A-F0-9]{2}-){5}[A-F0-9]{2}))$')


def calendarios(fe):
    print('\nMeses a analizar:\n')
    tuplas = []
    calendario = calendar.TextCalendar(calendar.MONDAY)
    for i in fe:
        tupla = (i.split('/')[1], i.split('/')[2])
        if tupla not in tuplas:
            tuplas.append(tupla)
    for i in tuplas:
        calendario.prmonth(int(i[1]), int(i[0]))
        print('')


def conexiones(fe, fechas):
    print('\n+------------------------------------+\n')
    while True:
        respuesta = input('¿Desea visualizar la lista completa de conexiones en dias feriados y fines de semana? (Y/N) ')
        if respuesta.lower() == 'y':
            calendarios(fe)
            print('\nUsuarios que se conectaron en fines de semana y feriados:\n')
            for i in fe:
                print(f'{i}: {fechas[i][0]}')
                for j in fechas[i][1]:
                    print(f'\t{j}')
                print('')
            break
        elif respuesta.lower() == 'n':
            break
        else:
            print('Opción invalida')


def buscador(fe, fechas):
    while True:
        respuesta = input('\n¿Le interesa conocer los dias feriados y fines de semana en que un usuario especifico se conectó? (Y/N) ')
        if respuesta.lower() == 'y':
            user = input('Coloque el nombre de usuario: ')
            print('\nEl usuario se conectó los días:')
            for i in fe:
                if user in fechas[i][1]:
                    print(f'\t- {i}')
        elif respuesta.lower() == 'n':
            break
        else:
            print('Opción invalida')


def macs(usuarios, macaps):
    print('+------------------------------------+\n')
    while True:
        respuesta = input('¿Le interesa ver todas las MAC y usuarios? (Y/N) ')
        if respuesta.lower() == 'y':
            for u in usuarios.keys():
                print(f'\nUsuario: {u}')
                print('MACs:')
                pprint(usuarios[u])
            print('\nMACs AP:')
            pprint(macaps)
            break
        elif respuesta.lower() == 'n':
            break
        else:
            print('Opción invalida')


def csv(fe, fechas):
    data = {}
    columnas = ['Fecha', 'Dia', 'Usuarios']
    data['Fecha'] = []
    data['Dia'] = []
    data['Usuarios'] = []

    for i in fe:
        data['Fecha'].append(i)
        data['Dia'].append(fechas[i][0])
        usuarios = ''
        for j in fechas[i][1]:
            if usuarios == '':
                usuarios = f'{j}'
            else:
                usuarios = usuarios + f'; {j}'
        data['Usuarios'].append(usuarios)
    df = pd.DataFrame(data, columns=columnas)

    df.to_excel('wifi.xlsx', sheet_name='feriados', index=False)


def main():
    with open('./Wifi.txt', 'r') as registros:
        registros.readline()
        linea = registros.readline()
        c = 1
        fechas = {}
        usuarios = {}
        macaps = []
        while linea:
            match = patron.match(linea)
            if match:
                fecha = match.group('fechaini').split(' ')[0]
                usuario = match.group('usuario').lower().strip(' ')
                fecha = datetime.strptime(fecha, '%d/%m/%Y')
                dia = fecha.strftime('%A')
                fecha = fecha.strftime('%d/%m/%Y')
                if usuario not in usuarios.keys():
                    usuarios[usuario] = [match.group('maccliente')]
                else:
                    if match.group('maccliente') not in usuarios[usuario]:
                        usuarios[usuario].append(match.group('maccliente'))
                if match.group('macap') not in macaps:
                    macaps.append(match.group('macap'))
                if dia == 'Saturday' or dia == 'Sunday' or fecha in feriados.keys():
                    if fecha in fechas.keys():
                        if usuario not in fechas[fecha][1]:
                            fechas[fecha][1].append(usuario)
                    else:
                        if (dia == 'Saturday' or dia == 'Sunday') and fecha in feriados.keys():
                            fechas[fecha] = [dia + ' - ' + feriados[fecha], [usuario]]
                        elif dia == 'Saturday' or dia == 'Sunday':
                            fechas[fecha] = [dia, [usuario]]
                        else:
                            fechas[fecha] = [feriados[fecha], [usuario]]
            else:
                c += 1
            linea = registros.readline()
        print('\nIntegrantes del grupo:\n\t* Marotta, Alenjandro Adrian\n\t* Soria Gava, Lucas Damian\n')
        print(f'\n+------------------------------------+\n\nRegistros incorrectos: {c}\n')
        fe = fechas.keys()
        csv(fe, fechas)
        macs(usuarios, macaps)
        conexiones(fe, fechas)
        buscador(fe, fechas)


if __name__ == "__main__":
    main()
