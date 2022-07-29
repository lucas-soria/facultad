import re


# compilando la regex
patron = re.compile(r'\bfoo\b')
# busca la palabra foo
# texto de entrada
texto = """ bar foo bar
foo barbarfoo
foofoo foo bar
"""
# search devuelve la coincidencia en cualquier ubicacion.
s = patron.search(texto)
print(s)
