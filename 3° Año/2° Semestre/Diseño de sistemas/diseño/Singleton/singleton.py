class Singleton(object):
    __instance = None  # atributo privado tipo clase

    def __new__(cls, val):  # constructor privado
        if Singleton.__instance is None:
            Singleton.__instance = object.__new__(cls)
        Singleton.__instance.val = val
        return Singleton.__instance


if __name__ == '__main__':
    Loggs = []
    for i in range(5):
        Loggs.append(Singleton(str(i)))
        print("Objeto: ", Loggs[i], "Numero de log: ", Loggs[i].val)
