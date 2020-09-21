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
print(m)
