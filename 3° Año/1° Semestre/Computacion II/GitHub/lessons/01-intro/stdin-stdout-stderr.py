import sys

leido = sys.stdin.read()
sys.stdout.write("salida estandar\n")
sys.stdout.write(leido)
sys.stderr.write("salida de error\n")
