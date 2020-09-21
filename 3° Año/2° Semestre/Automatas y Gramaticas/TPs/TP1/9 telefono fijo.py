import re


def match():
    regex = re.compile(r"^\+54(11|((2|3)(\d){2,3}))4(\d{6})$")
    exp = input("Ingrese un telefono fijo de Argentina a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es un telefono fijo de Argentina valido')
    else:
        print(f'"{exp}": NO es un telefono fijo de Argentina valido')


if __name__ == "__main__":
    match()
