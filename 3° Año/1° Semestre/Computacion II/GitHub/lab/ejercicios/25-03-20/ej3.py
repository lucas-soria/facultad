import matplotlib.pyplot as plt

def histograma():
    vals = []
    val = ''
    i = 0
    mayor = 0
    while True:
        val = raw_input('Ingrese proximo valor entero (x sale):')
        if val != "x":
            vals.append(val) #suponiendo que son enteros
            i +=1
            if int(val) > mayor:
                mayor = int(val)
        else:
            print val
            break
    print ('Valores ingresados: ',vals)

    #plt.hist(vals, bins=20)
    plt.plot(vals)
   # plt.xticks(range(1,(mayor+1)))
   # plt.title("Histograma valores ingresados")
   #plt.xlabel("Numeros")
   #plt.ylabel("Veces")
    plt.show()

if __name__ == "__main__":
    histograma()
