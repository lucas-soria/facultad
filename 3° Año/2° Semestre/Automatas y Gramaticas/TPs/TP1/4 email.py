import re


def match():
    regex = re.compile(r"^[^@]{1,64}@([^@_])+(\.[^@_])*$")
    exp = input("Ingrese un email a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es un email valido')
    else:
        print(f'"{exp}": NO es un email valido')


if __name__ == "__main__":
    match()
