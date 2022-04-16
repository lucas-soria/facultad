# Sujeto Concreto
from subject import Subject


class Libro(Subject):
    def __init__(self, titulo, estado):
        self.titulo = titulo
        self.estado = estado

    def getTitulo(self):
        return self.titulo

    def setTitulo(self, titulo):
        self.titulo = titulo

    def setEstado(self, estado):
        self.estado = estado

    def getEstado(self):
        return self.estado
