import re


# Accediendo a los grupos por sus indices
patron = re.compile(r"(\w+) (\w+)")
s = patron.search("Raul Lopez")
# grupo 1
print(s.group(1))
# grupo 2
print(s.group(2))
# Accediendo a los grupos por nombres
patron = re.compile(r"""(?P<nombre>\w+) (?P<apellido>\w+) (?P<edad>\d+)""")
s = patron.search("Raul Lopez 50")
# grupo nombre
print(s.group("nombre"))
# grupo apellido
print(s.group("apellido"))
# grupo edad
print(s.group("edad"))
