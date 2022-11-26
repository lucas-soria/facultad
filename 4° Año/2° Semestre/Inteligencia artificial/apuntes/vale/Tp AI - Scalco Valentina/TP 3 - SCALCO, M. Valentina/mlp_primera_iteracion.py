from math import e


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

    def resolucion(self):
        i = 0
        for i in range(0, 1):
            xa = (self.w[1] * self.valor_e1[i]) + (self.w[2] * self.valor_e2[i]) + (self.w[0] * self.valor_w0)
            ya = 1 / (1 + e**(-xa))
            print("ya:", ya)
            xb = (self.w[4] * self.valor_e1[i]) + (self.w[5] * self.valor_e2[i]) + (self.w[3] * self.valor_w0)
            yb = 1 / (1 + e**(-xb))
            print("yb:", yb)
            xc = (self.w[7] * self.valor_e1[i]) + (self.w[8] * self.valor_e2[i]) + (self.w[6] * self.valor_w0)
            yc = 1 / (1 + e**(-xc))
            print("yc:", yc)
            xd = (self.w[9] * self.valor_w0) + (self.w[10] * ya) + (self.w[11] * yb) + (self.w[12] * yc)
            yd = 1 / (1 + e**(-xd))
            print("xd:", xd)
            print('Salida real:', yd)


def main():
    g = Perceptron()
    g.compuerta_XOR()


if __name__ == "__main__":
    main()
