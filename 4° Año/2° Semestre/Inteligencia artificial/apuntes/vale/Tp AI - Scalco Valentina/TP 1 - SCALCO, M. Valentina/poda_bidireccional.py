# Busqueda bidireccional - Alumna: M. Valentina Scalco - Legajo: 58103
# self.current_state contiene la matriz de desordenada, la cual se puede
# cambiar por la matriz que queramos.
# self.original_state contiene la matriz ordenada.
# self.movimientos es un diccionario el cual contiene las posiciones de los
# movimientos que se pueden realizar segun la posicion del espacio vacio.
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

    def resolver_bidireccional(self):
        positions = []
        soluciones = []
        done = False
        positions.append(self.original_state.index('_'))
        soluciones.append(self.original_state.copy())
        i = 0
        positions_m = []
        soluciones_m = []
        positions_m.append(self.current_state.index('_'))
        soluciones_m.append(self.current_state.copy())
        i_m = 0
        while done is False:
            moves = soluciones[i].index('_')
            moves_m = soluciones_m[i_m].index('_')
            for move in self.movimientos[moves]:
                nodo = soluciones[i].copy()
                positions.append(move)
                empty_space = nodo.index('_')
                nodo[empty_space] = nodo[move]
                nodo[move] = '_'
                if nodo not in soluciones:
                    soluciones.append(nodo)
            i += 1
            if nodo in soluciones_m:
                k = len(soluciones_m) + len(soluciones)
                print('Se ha resuelto en un total de', k, 'movimientos')
                print('Nodo en comun:')
                for j in range(0, 9):
                    print('|{}|'.format(nodo[j]), end=" ")
                    if j == 2:
                        print()
                    elif j == 5:
                        print()
                done = True
                break
            for move_m in self.movimientos[moves_m]:
                nodo_m = soluciones_m[i_m].copy()
                positions_m.append(move_m)
                empty_space_m = nodo_m.index('_')
                nodo_m[empty_space_m] = nodo_m[move_m]
                nodo_m[move_m] = '_'
                if nodo_m not in soluciones_m:
                    soluciones_m.append(nodo_m)
            i_m += 1
            if nodo_m in soluciones:
                k = len(soluciones_m) + len(soluciones)
                print('Se ha resuelto en un total de', k, 'movimientos')
                print('Nodo en comun:')
                for j in range(0, 9):
                    print('|{}|'.format(nodo_m[j]), end=" ")
                    if j == 2:
                        print()
                    elif j == 5:
                        print()
                done = True
                break


def main():
    g = Tablero()
    g.resolver_bidireccional()


if __name__ == "__main__":
    main()
