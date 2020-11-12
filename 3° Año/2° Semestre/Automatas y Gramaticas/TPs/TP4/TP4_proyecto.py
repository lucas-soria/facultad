# Consigna:
# Seguimiento de los usuarios que se han conectado días feriados y no
# laborables (sábados y domingos).Lucas Soria y Alejandro Marotta
import calendar
from datetime import datetime
import re
from pprint import pprint


# No hay dias feriado no laborables del 28/08 al 04/10
# preguntar por alguna fecha que interese
patron = re.compile(r'^(?P<idcon>[0-9a-z]{16});(?P<usuario>(.+));(?P<fechaini>(([0-2][0-9])|(3[01]))\/(08|09|10)\/2019 ((1?[0-9])|(2[0-3])):([0-5][0-9]));(?P<fechafin>(([0-2][0-9])|(3[01]))\/(08|09|10)\/2019 ((1?[0-9])|(2[0-3])):([0-5][0-9]));(?P<tiempo>(\d+));(?P<input>(\d+));(?P<output>(\d+));(?P<macap>(([A-F0-9]{2}-){5}[A-F0-9]{2}:UM));(?P<maccliente>(([A-F0-9]{2}-){5}[A-F0-9]{2}))$')
with open('./acts-user1.txt', 'r') as registros:
    registros.readline()
    linea = registros.readline()
    c = 1
    fechas = {}
    usuarios = []
    macclientes = []
    macaps = []
    while linea:
        match = patron.match(linea)
        if match:
            fecha = match.group('fechaini').split(' ')[0]
            usuario = match.group('usuario').lower().strip(' ')
            dia = datetime.strptime(fecha, '%d/%m/%Y').strftime('%A')
            if usuario not in usuarios:
                usuarios.append(usuario)
            if match.group('macap') not in macaps:
                macaps.append(match.group('macap'))
            if match.group('maccliente') not in macclientes:
                macclientes.append(match.group('maccliente'))
            if dia == 'Saturday' or dia == 'Sunday':
                if fecha in fechas.keys():
                    if usuario not in fechas[fecha]:
                        fechas[fecha].append(usuario)
                else:
                    fechas[fecha] = [usuario]
        else:
            c += 1
        linea = registros.readline()
    calendario = calendar.TextCalendar(calendar.MONDAY)
    print('\nIntegrantes del grupo:\n\t* Marotta, Alenjandro Adrian\n\t* Soria Gava, Lucas Damian\n\n+------------------------------------+\n\nMeses a analizar:\n')
    calendario.prmonth(2019, 8)
    print('\n')
    calendario.prmonth(2019, 9)
    print('\n')
    calendario.prmonth(2019, 10)
    print('\n+------------------------------------+\n\nUsuarios que se conectaron en fines de semana y feriados:\n')
    pprint(fechas)
    print(f'\n+------------------------------------+\n\nRegistros incorrectos: {c}\n')
    print('+------------------------------------+\n\nUsuarios:')
    pprint(usuarios)
    print('\nMACs AP:')
    pprint(macaps)
    print('\nMACs Cliente:')
    pprint(macclientes)
