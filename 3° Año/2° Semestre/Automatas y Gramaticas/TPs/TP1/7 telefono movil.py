import re


def match():
    regex = re.compile(r"^\+549(11|((2|3)(\d){2,3}))15(\d{7})$")
    exp = input("Ingrese un telefono movil de Argentina a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es un telefono movil de Argentina valido')
    else:
        print(f'"{exp}": NO es un telefono movil de Argentina valido')


if __name__ == "__main__":
    match()
