from ExpressionCompositeSum import ExpressionCompositeSum as suma
from ExpressionCompositeRes import ExpressionCompositeRes as rest
from ExpressionCompositeMul import ExpressionCompositeMul as mult
from ExpressionCompositeDiv import ExpressionCompositeDiv as divi
from ExpressionLeaf import ExpressionLeaf as leaf


if __name__ == "__main__":
    sum1 = suma()
    sum2 = suma()
    sum3 = suma()
    res1 = rest()
    res2 = rest()
    mul = mult()
    div = divi()
    uno = leaf(1)
    dos = leaf(2)
    tres = leaf(3)
    cuatro = leaf(4)

    # ((((1+2)+(4-3))+(2*3))-(2/4))

    sum1.add(uno)
    sum1.add(dos)
    res1.add(cuatro)
    res1.add(tres)
    sum2.add(sum1)
    sum2.add(res1)
    mul.add(dos)
    mul.add(tres)
    sum3.add(sum2)
    sum3.add(mul)
    div.add(dos)
    div.add(cuatro)
    res2.add(sum3)
    res2.add(div)

    cuenta = res2.operation()
    print("La expresion da como resultado: ", cuenta)
