import re


patron = re.compile(r'^(?P<tiempo>(\d+))$')

linea = "-254211"
p = patron.match(linea)
if p:
    print("{} es correcta\n".format(linea))
    print(p.group('tiempo'))
else:
    print("{} NO es correcta\n".format(linea))

# (([0-2][0-9])|(3[01]))\/(08|09|10)\/2019\b(([01][0-9])|(2[0-3])):([0-5][0-9])