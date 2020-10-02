from subject import Subject

class AlarmaLibro(Subject):
    observers = []

    @classmethod
    def attach(cls,observer):
        cls.observers.append(observer)
    
    @classmethod
    def deatach(cls,observer):
        try:
            cls.observers.remove(observer)
        except ValueError:
            print("No existe el observer a remover")

    @classmethod
    def limpiar_observers(cls):
        for observer in range(len(cls.observers)):
            cls.observers.pop()

    @classmethod
    def notificar_observers(cls,libro):
        for observer in cls.observers:
            observer.update(libro)
        cls.limpiar_observers()

    
