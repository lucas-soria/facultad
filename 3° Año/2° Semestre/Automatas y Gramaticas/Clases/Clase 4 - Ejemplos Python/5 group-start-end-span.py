import re


# compilando la regex
patron = re.compile(r'\bfoo\b')
# busca la palabra foo
# texto de entrada
texto = """ bar foo bar
foo barbarfoo
foofoo foo bar
"""
# match devuelve None porque no hubo coincidencia al comienzo del texto
print(patron.match(texto))
# match encuentra una coindencia en el comienzo del texto
m = patron.match('foo bar')
# Métodos del objeto de coincidencia
print(m.group(), m.start(), m.end(), m.span())
# salida ('foo', 0, 3, (0, 3))
# search devuelve la coincidencia en cualquier ubicacion.
s = patron.search(texto)
print(s.group(), s.start(), s.end(), s.span())
# salida ('foo', 5, 8, (5, 8))
