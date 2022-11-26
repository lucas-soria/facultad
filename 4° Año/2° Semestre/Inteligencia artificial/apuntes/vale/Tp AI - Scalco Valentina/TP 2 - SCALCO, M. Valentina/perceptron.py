# Perceptron simple - Alumna: M. Valentina Scalco - Legajo: 58103
# Programa perceptron simple que se comporta como una compuerta OR, AND o XOR
# dependiendo la opcion que elijamos.
# Para el caso de la XOR, trataremos de buscar el numero mas cercano que podamos.
# Los pesos fueron elegidos de forma arbitraria, al igual, que el valor de lr.
from math import e
import matplotlib.pyplot as plt


class Perceptron:
    def __init__(self):
        self.initialize_game()

    def initialize_game(self):
        self.peso_e1 = 0.66
        self.peso_e2 = -0.2
        self.peso_w0 = 0.9
        self.valor_w0 = 1

    def compuerta_OR(self):
        self.valor_e1 = [0, 0, 1, 1]
        self.valor_e2 = [0, 1, 0, 1]
        self.salida_d = [0, 1, 1, 1]
        self.resolucion()

    def compuerta_AND(self):
        self.valor_e1 = [0, 0, 1, 1]
        self.valor_e2 = [0, 1, 0, 1]
        self.salida_d = [0, 0, 0, 1]
        self.resolucion()

    def sumatoria(self, i):
        x = (self.peso_e1 * self.valor_e1[i]) + (self.peso_e2 * self.valor_e2[i]) + (self.peso_w0 * self.valor_w0)
        return x

    def grafico_pesos(self, peso0, peso1, peso2, count):
        plt.figure(figsize=(10, 7))
        plt.plot(range(count), peso0, label="W0")
        plt.plot(range(count), peso1, label="W1")
        plt.plot(range(count), peso2, label="W2")
        plt.title("Pesos")
        plt.legend()
        plt.savefig("pesos.png")
        plt.show()
        plt.close()

    def grafico_error(self, error0, error1, error2, error3, counts):
        plt.figure(figsize=(10, 7))
        if self.resp == 1:
            plt.plot(range(counts+1), error0, markersize=5, label="error 0")
        else:
            plt.plot(range(counts), error0, markersize=5, label="error 0")
        plt.plot(range(counts), error1, markersize=5, label="error 1")
        plt.plot(range(counts), error2, markersize=5, label="error 2")
        plt.plot(range(counts), error3, markersize=5, label="error 3")
        plt.title("Errores")
        plt.legend()
        plt.savefig("Errores.png")
        plt.show()
        plt.close()

    def resolucion(self):
        veces = 0
        f = 0
        lr = 0.1
        delta_absoluto = [1, 1, 1, 1]
        salida_r = [1, 1, 1, 1]
        peso0 = []
        peso1 = []
        peso2 = []
        error0 = []
        error1 = []
        error2 = []
        error3 = []
        count = 0
        counts = 0
        while True:
            i = 0
            for i in range(0, 4):
                x = self.sumatoria(i)
                salida_r[i] = 1 / (1 + e**(-x))
                # delta_absoluto = error
                delta_absoluto[i] = self.salida_d[i] - salida_r[i]
                if i == 0:
                    error0.append(delta_absoluto[i])
                if i == 1:
                    error1.append(delta_absoluto[i])
                if i == 2:
                    error2.append(delta_absoluto[i])
                if i == 3:
                    error3.append(delta_absoluto[i])
                    counts += 1
                delta_relativo = salida_r[i] * (1 - salida_r[i]) * delta_absoluto[i]
                delta_w0 = lr * self.valor_w0 * delta_relativo
                delta_peso_e1 = lr * self.valor_e1[i] * delta_relativo
                delta_peso_e2 = lr * self.valor_e2[i] * delta_relativo
                self.peso_w0 = self.peso_w0 + delta_w0
                self.peso_e1 = self.peso_e1 + delta_peso_e1
                self.peso_e2 = self.peso_e2 + delta_peso_e2
                if delta_absoluto[0] <= 0.01 and delta_absoluto[0] >= -0.01 and delta_absoluto[1] <= 0.01 and delta_absoluto[1] >= -0.01 and delta_absoluto[2] <= 0.01 and delta_absoluto[2] >= -0.01 and delta_absoluto[3] <= 0.01 and delta_absoluto[3] >= -0.01:
                    print('Numero de veces que se realizo el entrenamiento:', veces)
                    for r in range(0, 4):
                        print('-----------------------------------------------------------------------------------')
                        print('La salida deseada es', self.salida_d[r])
                        print('La salida real tiene un valor de:', salida_r[r],
                              'para e1 igual a', self.valor_e1[r],
                              'y para e2 igual a', self.valor_e2[r])
                        f += 1
                    break
                peso0.append(self.peso_w0)
                peso1.append(self.peso_e1)
                peso2.append(self.peso_e2)
                count += 1
            veces += 1
            if f == 4:
                break
        self.grafico_pesos(peso0, peso1, peso2, count)
        self.grafico_error(error0, error1, error2, error3, counts)

    def eleccion(self):
        self.resp = 0
        while self.resp != 1 and self.resp != 2 and self.resp != 3:
            self.resp = int(input('Ingrese 1 para compuerta OR o 2 para compuerta AND: \n'))
            if self.resp == 1:
                self.compuerta_OR()
            elif self.resp == 2:
                self.compuerta_AND()
            else:
                print('Opcion no valida. Vuelva a intentarlo.')


def main():
    g = Perceptron()
    g.eleccion()


if __name__ == "__main__":
    main()
