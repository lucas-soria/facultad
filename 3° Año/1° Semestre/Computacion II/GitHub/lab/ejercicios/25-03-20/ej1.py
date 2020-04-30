#!/usr/bin/python3
import sys
def multiplicador(n):
    temp = 0
    for i in range (1,4):
        string = str(n) * i
        temp = temp + int(string)
    return temp

if __name__ == "__main__":

    if (len(sys.argv) > 1):
        print(multiplicador(sys.argv[1]))
    else:
        print("con argumento 9: " + str( multiplicador(9)))

