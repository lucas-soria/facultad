from TreeNumbersComposite import TreeNumbersComposite as composite
from TreeNumbersLeaf import ExpressionLeaf as leaf


if __name__ == "__main__":
    comp1 = composite("Central")
    comp2 = composite("Uno")
    comp3 = composite("Dos")
    uno = leaf(1)
    dos = leaf(2)
    tres = leaf(3)
    cuatro = leaf(4)

    # ((((1+2)+(4-3))+(2*3))-(2/4))

    comp2.add(uno)
    comp2.add(dos)
    comp3.add(tres)
    comp3.add(cuatro)
    comp1.add(comp2)
    comp1.add(comp3)

    cuenta = comp1.sum()
    print("La suma de numeros es: ", cuenta)
    print("El numero mas alto es: ", comp1.higher())
    print("El numero de nodos es: ", comp1.number())
