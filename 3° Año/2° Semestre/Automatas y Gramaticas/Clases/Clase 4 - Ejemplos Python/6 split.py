import re


# texto de entrada
becquer = """Podrá nublarse el sol eternamente;
Podrá secarse en un instante el mar;
Podrá romperse el eje de la tierra
como un débil cristal.
¡todo sucederá! Podrá la muerte
cubrirme con su fúnebre crespón;
Pero jamás en mí podrá apagarse
la llama de tu amor."""
# patron para dividir donde no encuentre un caracter alfanumerico
patron = re.compile(r'\W+')
palabras = patron.split(becquer)
palabras[:10]
# 10 primeras palabras
print(palabras[:10])  # en este caso como son palabras ignora --> ; . ¡ !
# Utilizando la version no compilada de split.
print(re.split(r'\n', becquer))  # Dividiendo por linea.
# Utilizando el tope de divisiones
print(patron.split(becquer, 5))
