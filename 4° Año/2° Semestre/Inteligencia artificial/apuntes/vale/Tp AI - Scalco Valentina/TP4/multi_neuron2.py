from math import e
import matplotlib.pyplot as plt
import random


class Perceptron:
    def __init__(self):
        self.initialize_game()

    def initialize_game(self):
        #self.w = [0.9, 0.7, 0.5, 0.3, -0.9, -1,
        #          0.8, 0.35, 0.1, -0.23, -0.79,
        #          0.56, 0.6]
        self.valor_w0 = 1

    def compuerta_XOR(self):
        self.valor_e1 = [0, 0, 1, 1]
        self.valor_e2 = [0, 1, 0, 1]
        self.salida_d = [0, 1, 1, 0]

    def grafico_error(self, error0, error1, error2, error3, count):
        plt.figure(figsize=(10, 7))
        count += 1
        plt.plot(range(count), error0, label="error 0")
        plt.plot(range(count), error1, label="error 1")
        plt.plot(range(count), error2, label="error 2")
        plt.plot(range(count), error3, label="error 3")
        plt.title("Errores")
        plt.legend()
        plt.savefig("Errores.png")
        plt.show()
        plt.close()

    def suma_oculta(self, z):
        y = []
        pesos_oculto = len(self.w) - (self.resp + 1)
        #print('w', len(self.w))
        #print('peso', pesos_oculto)
        for g in range(0, pesos_oculto, 3):
            x = (self.w[g] * self.valor_w0) + (self.w[g+1] * self.valor_e1[z]) + (self.w[g+2] * self.valor_e2[z])
            y.append(1 / (1 + e**(-x)))
        y = self.suma_salida(y, pesos_oculto)
        #print('y', len(y))
        return (y)

    def suma_salida(self, y_oculta, pesos_oculto):
        valor_suma = (self.w[pesos_oculto] * self.valor_w0)
        for p in range(self.resp):
            pesos_oculto += 1
            #print('w', len(self.w))
            #print('peso', pesos_oculto)
            #print('y', len(y_oculta))
            #print('p', p)
            valor_suma = valor_suma + (self.w[pesos_oculto] * y_oculta[p])
        y_oculta.append(1 / (1 + e**(-valor_suma)))
        return (y_oculta)

    def resolucion(self):
        # delta_w = woc
        woc = []
        soc = []
        error0 = []
        error1 = []
        error2 = []
        error3 = []
        lr = 0.5
        t = 0
        self.w = []
        for i in range(self.resp+1):
            #print('i', i)
            if i == self.resp:
                #print(len(self.w))
                for j in range(self.resp + 1):
                    self.w.append(round(random.uniform(-1, 1), 2))
                
                #print(len(self.w))
            else:
                for j in range(3):
                    self.w.append(round(random.uniform(-1, 1), 2))
        i = 0
        for t in range(0, 10000):
            for i in range(0, 4):
                #print('vuelta', i)
                valores_y = self.suma_oculta(i)
                #print('valores x', valores_x)
                #print('valores y', valores_y)
                error = self.salida_d[i] - valores_y[self.resp]
                sf = valores_y[self.resp] * (1 - valores_y[self.resp]) * error
                # Neuronas
                woc = []
                for k in range(0, self.resp):
                    soc = (valores_y[k] * (1 - valores_y[k]) * sf)
                    woc.append(lr * self.valor_w0 * soc)
                    woc.append(lr * self.valor_e1[i] * soc)
                    woc.append(lr * self.valor_e2[i] * soc)
                # Ultima neurona
                woc.append(lr * self.valor_w0 * sf)
                for y in range(0, self.resp):
                    woc.append(lr * valores_y[y] * sf)
                # Cambiar pesos
                for pesos in range(0, len(self.w)):
                    self.w[pesos] = self.w[pesos] + woc[pesos]
                if i == 0:
                    error0.append(error)
                if i == 1:
                    error1.append(error)
                if i == 2:
                    error2.append(error)
                if i == 3:
                    error3.append(error)
                if t == 9999:
                    print('Salida real para salida deseada', self.salida_d[i], ':', valores_y[self.resp])
        print('Entrenamiento realizado:', t+1, 'veces')
        #print('y', len(valores_y))
        self.grafico_error(error0, error1, error2, error3, t)

    def numero_neuronas(self):
        self.resp = 0
        self.resp = int(input('Ingrese numero de perceptrones en capa oculta: \n'))
        self.compuerta_XOR()
        self.resolucion()


def main():
    g = Perceptron()
    g.numero_neuronas()


if __name__ == "__main__":
    main()
