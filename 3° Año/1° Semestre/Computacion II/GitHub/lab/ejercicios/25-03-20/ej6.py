
def ordenar():
    vals = []
    val = ''
    val = raw_input('Ingrese la sucesion de numeros separados por ",":')
    print ('Valores ingresados: ',val)
    vals = val.split(',')
    vals = [int(string) for string in vals]
    vals.sort(reverse=True)
    print ('valores en lista ordenada:',vals)


if __name__ == "__main__":
    ordenar()
    
