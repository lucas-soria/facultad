'''
The universe of the Game of Life is an infinite, two-dimensional orthogonal grid of square cells, 
each of which is in one of two possible states, alive or dead, (or populated and unpopulated, respectively). 
Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically,
or diagonally adjacent. At each step in time, the following transitions occur:

    Any live cell with fewer than two live neighbours dies, as if by underpopulation.
    Any live cell with two or three live neighbours lives on to the next generation.
    Any live cell with more than three live neighbours dies, as if by overpopulation.
    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

These rules, which compare the behavior of the automaton to real life, can be condensed into the following:

    Any live cell with two or three neighbors survives.
    Any dead cell with three live neighbors becomes a live cell.
    All other live cells die in the next generation. Similarly, all other dead cells stay dead.

The initial pattern constitutes the seed of the system. 
The first generation is created by applying the above rules simultaneously to every cell in the seed; 
births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick. 
Each generation is a pure function of the preceding one. The rules continue to be applied repeatedly to create further generations. 
'''
import time
import random

def randgen(al, la):
    matrix = []
    matrixc = []
    for i in range(al):
        matrix.append([])
        matrixc.append([])
        for j in range(la):
            rannum = random.randrange(4)
            if rannum == 0:
                matrix[i].append("\u2B1C")
                matrixc[i].append(True)
            else:
                matrix[i].append("\u2B1B")
                matrixc[i].append(False)
    return matrix, matrixc

def neighbors(radius, rowNumber, columnNumber, matrix):
    return [[
        matrix[i][j] if i >= 0 and i < len(matrix) and j >= 0 and j < len(matrix[0]) else 0
        for j in range(columnNumber - 1 - radius, columnNumber + radius)
    ] for i in range(rowNumber - 1 - radius, rowNumber + radius)]

def viveomuere(matrix, y, x):
    vivos = 0
    matrixch = neighbors(1, y+1, x+1, matrix)
    for i in range(3):
        for j in range(3):
            if matrixch[i][j] == "\u2B1C":
                vivos += 1
    if matrix[y][x] == "\u2B1C":
        vivos -= 1
    if vivos == 2 or vivos == 3:
        if matrix[y][x] == "\u2B1C":
            return True
        else:
            if vivos == 3:
                return True
            else:
                return False
    else:
        return False

def iterar(matrix, matrixc, al, la):
    for i in range(al):
        for j in range(la):
            matrixc[i][j] = viveomuere(matrix, i, j)
    for i in range(al):
        for j in range(la):
            if matrixc[i][j] == True:
                matrix[i][j] = "\u2B1C"
            else:
                matrix[i][j] = "\u2B1B"

def showTable(al, la):
    TableInString = "\n     "
    TableInString += "\n"
    for i in range(al):
        for j in range(la):
            TableInString += str(matrix[i][j].replace("", " "))
        TableInString += "\n"
    return TableInString

la = int(input("Ingresa el largo de la tabla: "))
al = int(input("Ingresa el alto de la tabla: "))
while True:
    matrix, matrixc = randgen(al, la)
    print(showTable(al, la))
    time.sleep(1)
    iterar(matrix, matrixc, al, la)
