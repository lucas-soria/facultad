import re


patron = re.compile(r'''^(?P<idcon>[0-9a-z]{16});\
(?P<usuario>[a-zA-Z0-9\-\_\.]+);\
(?P<fechaini>(([0-2][0-9])|(3[01]))\/(08|09|10)\/2019\b(([01][0-9])|(2[0-3])):([0-5][0-9]));\
(?P<fechafin>(([0-2][0-9])|(3[01]))\/(08|09|10)\/2019\b(([01][0-9])|(2[0-3])):([0-5][0-9]));\
(?P<tiempo>(\d+));\
(?P<input>(\d+));\
(?P<output>(\d+));\
(?P<macap>(([A-F0-9]{2}-){5}[A-F0-9]{2}:UM));\
(?P<maccliente>(([A-F0-9]{2}-){5}[A-F0-9]{2}))$''')

linea = "b83a1489ae7fbdc5;invitede-pansew;28/08/2019 10:06;26/09/2019 12:48;119;184328;3323107;04-18-D6-21-38-E1:UM;60-1D-91-78-43-74"
p = patron.match(linea)
if p:
    print("{} es correcta\n".format(linea))
    print(p.group('idcon'), p.group('usuario'), p.group('fechaini'), p.group('fechafin'), p.group('tiempo'), p.group('input'), p.group('output'), p.group('macap'), p.group('maccliente'))
else:
    print("{} NO es correcta\n".format(linea))

# (([0-2][0-9])|(3[01]))\/(08|09|10)\/2019\b(([01][0-9])|(2[0-3])):([0-5][0-9])