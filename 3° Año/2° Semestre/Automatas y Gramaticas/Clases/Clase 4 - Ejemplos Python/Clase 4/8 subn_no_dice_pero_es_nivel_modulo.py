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
# Utilizando la version no compilada de subn
print(re.subn(r'\b(P|p)odrá\b', "Puede", becquer))  # hizo 5 reemplazos
