class persona :

    def __init__(self, nombre, apellido):
        self.nombre = nombre
        self.apellido = apellido

    def get_nombre(self):
        return self.nombre

    def get_apellido(self):
        return self.apellido

    def set_nombre(self, nombre):
        self.nombre = nombre

    def set_apellido(self, apellido):
        self.apellido = apellido

# Main
# import persona
persona1 = persona("Carlos",
                           "Lopez")  # persona.persona("carlos", "lopez")
persona2 = persona("Mario", "Rodrigez")# persona.persona("mario", "rodrigez")

print("Listado de personas : \n")
print("El nombre es: ", persona1.get_nombre())
print("El apellido es: ", persona1.get_apellido())
print("\nEl nombre es: ", persona2.get_nombre())
print("El apellido es: ", persona2.get_apellido())
