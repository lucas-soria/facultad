#!/usr/bin/python3
import sys
def multiplicador(n,m):
    temp = 0
    for i in range (1,(int(m)+1)):
        string = str(n) * i
        temp = temp + int(string)
    return temp

if __name__ == "__main__":
    if (len(sys.argv) == 3):
        print(multiplicador(sys.argv[1],sys.argv[2]))
    else:
        print("argumento 4 y 5: " + str( multiplicador(4,5)))
