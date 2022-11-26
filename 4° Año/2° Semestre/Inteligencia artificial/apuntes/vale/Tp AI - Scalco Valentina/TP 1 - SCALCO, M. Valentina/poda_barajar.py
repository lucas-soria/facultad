# Desordenar puzzle - Alumna: M. Valentina Scalco - Legajo: 58103
# self.original_state contiene la matriz ordenada, en la cual realizaremos 50
# movimientos aleatorios para "desordenarla".
import random


class Tablero:
    def __init__(self):
        self.initialize_game()

    def initialize_game(self):
        self.original_state = [['1', '2', '3'],
                               ['4', '5', '6'],
                               ['7', '8', '_']]

    def mix_board(self):
        rounds = 0
        numrandm = 0
        while rounds < 50:
            numrandm = random.randrange(0, 4)
            for i in range(0, 3):
                for j in range(0, 3):
                    if self.original_state[i][j] == '_':
                        if (j-1) >= 0 and numrandm == 0:
                            self.original_state[i][j] = self.original_state[i][j-1]
                            self.original_state[i][j-1] = '_'
                            # print('se movio a la izquierda')
                            rounds += 1
                        elif (i-1) >= 0 and numrandm == 2:
                            self.original_state[i][j] = self.original_state[i-1][j]
                            self.original_state[i-1][j] = '_'
                            # print('se movio para arriba')
                            rounds += 1
                        elif (j+1) <= 2 and numrandm == 3:
                            self.original_state[i][j] = self.original_state[i][j+1]
                            self.original_state[i][j+1] = '_'
                            # print('se movio a la derecha')
                            rounds += 1
                        elif (i+1) <= 2 and numrandm == 1:
                            self.original_state[i][j] = self.original_state[i+1][j]
                            self.original_state[i+1][j] = '_'
                            # print('se movio para abajo')
                            rounds += 1
                        else:
                            numrandm = random.randrange(0, 4)
            if rounds == 50:
                for i in range(0, 3):
                    for j in range(0, 3):
                        print('|{}|'.format(self.original_state[i][j]), end=" ")
                    print()
                print()
                print("Tablero mezclado:", rounds, "veces\n")


def main():
    g = Tablero()
    g.mix_board()


if __name__ == "__main__":
    main()
