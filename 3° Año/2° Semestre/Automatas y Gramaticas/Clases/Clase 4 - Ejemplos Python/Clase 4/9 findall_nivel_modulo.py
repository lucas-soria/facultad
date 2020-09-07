import re


# Ejemplo de findall con la funcion a nivel del modulo
# findall nos devuelve una lista con todas las coincidencias
texto = """ bar foo bar
foo barbarfoo
foofoo foo bar
"""
print(re.findall(r'\bfoo\b', texto))
