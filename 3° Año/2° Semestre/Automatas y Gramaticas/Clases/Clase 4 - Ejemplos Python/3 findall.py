import re


# compilando la regex
patron = re.compile(r'\bfoo\b')
# busca la palabra foo
# texto de entrada
texto = """ bar foo bar
foo barbarfoo
foofoo foo bar
"""
# findall devuelve una lista con todas las coincidencias
fa = patron.findall(texto)
print(fa)
