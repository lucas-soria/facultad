from math import e
import matplotlib.pyplot as plt


class Perceptron:
    def __init__(self):
        self.initialize_game()

    def initialize_game(self):
        self.w = [0.9, 0.7, 0.5, 0.3, -0.9, -1,
                  0.8, 0.35, 0.1, -0.23, -0.79,
                  0.56, 0.6]
        self.valor_w0 = 1

    def compuerta_XOR(self):
        self.valor_e1 = [0, 0, 1, 1]
        self.valor_e2 = [0, 1, 0, 1]
        self.salida_d = [0, 1, 1, 0]
        self.resolucion()

    def grafico_pesos(self, w0, w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, count):
        plt.figure(figsize=(10, 7))
        plt.plot(range(count), w0, label="W0")
        plt.plot(range(count), w1, label="W1")
        plt.plot(range(count), w2, label="W2")
        plt.plot(range(count), w3, label="W3")
        plt.plot(range(count), w4, label="W4")
        plt.plot(range(count), w5, label="W5")
        plt.plot(range(count), w6, label="W6")
        plt.plot(range(count), w7, label="W7")
        plt.plot(range(count), w8, label="W8")
        plt.plot(range(count), w9, label="W9")
        plt.plot(range(count), w10, label="W10")
        plt.plot(range(count), w11, label="W11")
        plt.plot(range(count), w12, label="W12")
        plt.title("Pesos")
        plt.legend()
        plt.savefig("pesos.png")
        plt.show()
        plt.close()

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

    def resolucion(self):
        veces = 0
        lr = 0.5
        w0 = []
        w1 = []
        w2 = []
        w3 = []
        w4 = []
        w5 = []
        w6 = []
        w7 = []
        w8 = []
        w9 = []
        w10 = []
        w11 = []
        w12 = []
        error0 = []
        error1 = []
        error2 = []
        error3 = []
        i = 0
        t = 0
        for t in range(0, 10000):
            for i in range(0, 4):
                xa = (self.w[1] * self.valor_e1[i]) + (self.w[2] * self.valor_e2[i]) + (self.w[0] * self.valor_w0)
                ya = 1 / (1 + e**(-xa))
                xb = (self.w[4] * self.valor_e1[i]) + (self.w[5] * self.valor_e2[i]) + (self.w[3] * self.valor_w0)
                yb = 1 / (1 + e**(-xb))
                xc = (self.w[7] * self.valor_e1[i]) + (self.w[8] * self.valor_e2[i]) + (self.w[6] * self.valor_w0)
                yc = 1 / (1 + e**(-xc))
                xd = (self.w[9] * self.valor_w0) + (self.w[10] * ya) + (self.w[11] * yb) + (self.w[12] * yc)
                # Salida Real (yd)
                yd = 1 / (1 + e**(-xd))
                error = self.salida_d[i] - yd
                sf = yd * (1 - yd) * error
                delta_w9 = lr * self.valor_w0 * sf
                delta_w10 = lr * ya * sf
                delta_w11 = lr * yb * sf
                delta_w12 = lr * yc * sf
                self.w[9] = self.w[9] + delta_w9
                self.w[10] = self.w[10] + delta_w10
                self.w[11] = self.w[11] + delta_w11
                self.w[12] = self.w[12] + delta_w12
                # primer neurona
                soc1 = ya * (1 - ya) * sf
                woc0 = lr * self.valor_w0 * soc1
                woc1 = lr * self.valor_e1[i] * soc1
                woc2 = lr * self.valor_e2[i] * soc1
                self.w[0] = self.w[0] + woc0
                self.w[1] = self.w[1] + woc1
                self.w[2] = self.w[2] + woc2
                # segunda neurona
                soc2 = yb * (1 - yb) * sf
                woc3 = lr * self.valor_w0 * soc2
                woc4 = lr * self.valor_e1[i] * soc2
                woc5 = lr * self.valor_e2[i] * soc2
                self.w[3] = self.w[3] + woc3
                self.w[4] = self.w[4] + woc4
                self.w[5] = self.w[5] + woc5
                # tercer neurona
                soc3 = yc * (1 - yc) * sf
                woc6 = lr * self.valor_w0 * soc3
                woc7 = lr * self.valor_e1[i] * soc3
                woc8 = lr * self.valor_e2[i] * soc3
                self.w[6] = self.w[6] + woc6
                self.w[7] = self.w[7] + woc7
                self.w[8] = self.w[8] + woc8
                # Grafico
                w0.append(self.w[0])
                w1.append(self.w[1])
                w2.append(self.w[2])
                w3.append(self.w[3])
                w4.append(self.w[4])
                w5.append(self.w[5])
                w6.append(self.w[6])
                w7.append(self.w[7])
                w8.append(self.w[8])
                w9.append(self.w[9])
                w10.append(self.w[10])
                w11.append(self.w[11])
                w12.append(self.w[12])
                if i == 0:
                    error0.append(error)
                if i == 1:
                    error1.append(error)
                if i == 2:
                    error2.append(error)
                if i == 3:
                    error3.append(error)
                if t == 9999:
                    print('Salida real para salida deseada', self.salida_d[i], ':', yd)
                veces += 1
        print('Entrenamiento realizado:', t+1, 'veces')
        self.grafico_pesos(w0, w1, w2, w3, w4, w5, w6, w7, w8, w9, w10, w11, w12, veces)
        self.grafico_error(error0, error1, error2, error3, t)


def main():
    g = Perceptron()
    g.compuerta_XOR()


if __name__ == "__main__":
    main()
