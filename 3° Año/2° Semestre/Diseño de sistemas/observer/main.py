# Observers
# Cuando existe una relacion de dependencia de uno a muchos que puede requerir 
# que un objeto notifique a multiples objetos que dependen de el cuando cambia su estado.

from biblioteca import Biblioteca
from libro import Libro

if __name__ == "__main__":
    Biblioteca.devolver_libro(Libro("Sistemas Operativos - William Stalling - 5ta Edicion","bueno"))
    Biblioteca.devolver_libro(Libro("Python para todos","destruido"))