import os
FIN = False
while FIN == False:
    leido = os.read(0,1024)
    os.write(1,"salida estandar\n")
    os.write(1,leido)
    if len(leido) < 1024:
        FIN = True
#os.write(2,"salida de error\n")
