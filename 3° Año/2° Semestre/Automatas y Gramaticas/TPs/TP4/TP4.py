# Consigna:
# Seguimiento de los usuarios que se han conectado días feriados y no
# laborables (sábados y domingos).Lucas Soria y Alejandro Marotta
# import calendar
# c = calendar.TextCalendar(calendar.MONDAY)
# c.prmonth(2019, 8)
# from datetime import datetime
import re
# from pprint import pprint


# print(datetime.now().strftime("%A"))
dic = {}

patron = re.compile(r"""
^(?P<idcon>[0-9a-z]{16});
(?P<usuario>.*);
(?P<inicon>([0-2][0-9]|3(0|1))\/(08|09|10)   );  # 28/08/2019 10:06
(?P<fincon>);
(?P<tiempo>);
(?P<input>);
(?P<output>);
(?P<macap>([0-9A-F]{2}-){5}([0-9A-F]{2}):UM);
(?P<maccli>([0-9A-F]{2}-){5}([0-9A-F]{2}))$""", re.X)

with open('./acts-user1.txt', 'r') as registros:
    linea = registros.readline()
    print(linea)
    while linea:
        # print(linea)
        match = patron.match(linea)
        linea = registros.readline()


# formato mac: mayusculas y numeros separados por -
