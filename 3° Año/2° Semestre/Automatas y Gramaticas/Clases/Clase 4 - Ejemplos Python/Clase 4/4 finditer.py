import re


# compilando la regex
patron = re.compile(r'\bfoo\b')
# busca la palabra foo
# texto de entrada
texto = """ bar foo bar
foo barbarfoo
foofoo foo bar
"""
# finditer devuelve un iterador
fi = patron.finditer(texto)
print(fi)
# Salida <callable_iterator at 0x7f413db74240>
# iterando por las distintas coincidencias
print(next(fi))
# Salida <_sre.SRE_Match object; span=(5, 8), match='foo'>
print(next(fi))
# Salida <_sre.SRE_Match object; span=(13, 16), match='foo'>
