# Busqueda en anchura - Alumna: M. Valentina Scalco - Legajo: 58103
# self.current_state contiene la matriz de desordenada, la cual se puede
# cambiar por la matriz que queramos.
# self.original_state contiene la matriz ordenada a la cual hay que llegar, en
# otras palabras, nuestra matriz ideal
# self.movimientos es un diccionario el cual contiene las posiciones de los
# movimientos que se pueden realizar segun la posicion del espacio vacio
class Tablero:
    def __init__(self):
        self.initialize_game()

    def initialize_game(self):
        self.current_state = [1, 2, 3,
                              4, '_', 5,
                              7, 8, 6]
        self.original_state = [1, 2, 3,
                               4, 5, 6,
                               7, 8, '_']
        self.movimientos = {0: [1, 3], 1: [0, 2, 4], 2: [1, 5],
                            3: [0, 4, 6], 4: [1, 3, 5, 7], 5: [2, 4, 8],
                            6: [3, 7], 7: [4, 6, 8], 8: [5, 7]}

    def resolver_anchura(self):
        positions = []
        soluciones = []
        done = False
        positions.append(self.current_state.index('_'))
        soluciones.append(self.current_state.copy())
        i = 0
        while done is False:
            moves = soluciones[i].index('_')
            for move in self.movimientos[moves]:
                nodo = soluciones[i].copy()
                positions.append(move)
                empty_space = nodo.index('_')
                nodo[empty_space] = nodo[move]
                nodo[move] = '_'
                if nodo not in soluciones:
                    soluciones.append(nodo)
                if self.original_state in soluciones:
                    k = len(soluciones)
                    print('Se ha resuelto en el movimiento numero', i)
                    for j in range(0, 9):
                        print('|{}|'.format(soluciones[k-1][j]), end=" ")
                        if j == 2:
                            print()
                        elif j == 5:
                            print()
                    done = True
                    break
            i += 1


def main():
    g = Tablero()
    g.resolver_anchura()


if __name__ == "__main__":
    main()
