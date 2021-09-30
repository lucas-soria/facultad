from random import randrange
#0 - Limpio
#1 - Poco sucio - Necesita una pasada
#2 - Sucio - Necesita dos pasadas
#3 - Mancha permanente - No se limpia nunca

#Agente basado en modelo

# La aspiradora solo sabe si el piso está limpio o sucio
# La aspiradora sabe el tamaño del pasillo
# La aspiradora puede limpiar, cambiar dirección, ver si el piso está sucio, mover
# La aspiradora sabe si ya limpió una baldosa
# Termina cuando no hay más baldosas por limpiar

class Baldosa:
    def __init__(self):
        self.sucio = randrange(4)
        self.limpiado = False
    def mostrar(self):
        if self.sucio == 0:
            return "| |"
        elif self.sucio == 1:
            return "|+|"
        elif self.sucio == 2:
            return "|X|"
        elif self.sucio == 3:
            return "|#|"
    def limpiar(self):
        if self.sucio == 1:SI
            self.sucio = 0
        elif self.sucio == 2:
            self.sucio = 1

class Piso:
     baldosas = []
     def __init__(self, size):
         self.size = size
         for i in range(self.size):
             self.baldosas.append(Baldosa())
     def mostrar(self):
         out =""
         for i in range(self.size):
             out += self.baldosas[i].mostrar()
         return out
     def limpiadas(self):
         contador = 0
         for i in range(self.size):
             if self.baldosas[i].limpiado:
                 contador += 1
         return contador

class Aspiradora:
     def __init__(self, piso):
         self.posicion = randrange(size)
         if self.posicion >= 5:
            self.direccion = "Derecha"
         else:
            self.direccion = "Izquierda"
         self.piso = piso
         self.movimientos = 0
     def mostrar(self):
         out =""
         for i in range(self.piso.size):
             if self.posicion == i:
                 out += "|O|"
             elif self.piso.baldosas[i].limpiado:
                 out += "|-|"
             else:
                 out += "| |"
         print(out)
         print(self.piso.mostrar())
     def mover(self):
         try:
             if self.direccion == "Derecha" :
                 movimiento = 1
             else:
                 movimiento = -1
             self.piso.baldosas[self.posicion + movimiento]
             if (self.posicion + movimiento) < 0:
                 raise
             print("Moviendo hacia la  "+self.direccion)
             self.movimientos += 1
             self.posicion = self.posicion + movimiento
         except:
             if self.direccion == "Derecha" :
                 self.direccion = "Izquierda"
             else:
                 self.direccion = "Derecha"
             print("Dirección cambiada a: "+self.direccion)
             self.mover()

     def iniciar(self):
         while self.piso.limpiadas() < self.piso.size:
             self.mostrar()
             for j in range(2):
                 if self.piso.baldosas[self.posicion].sucio != 0 or self.piso.baldosas[self.posicion].limpiado:
                     print("Limpiando ...")
                     self.movimientos += 1
                     self.piso.baldosas[self.posicion].limpiar()
             print("Seteando a limpiado"+str(self.posicion))
             self.piso.baldosas[self.posicion].limpiado = True
             self.mover()

size = 10
piso = Piso(size)
aspiradora = Aspiradora(piso)
aspiradora.iniciar()
print("Movimientos: "+str(aspiradora.movimientos))
