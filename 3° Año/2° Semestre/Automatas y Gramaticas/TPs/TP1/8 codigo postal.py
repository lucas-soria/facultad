import re


def match():
    regex = re.compile(r"^[A-Z][\d]{4}[A-Z]{3}$")
    exp = input("Ingrese un codigo postal a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es un codigo postal valido')
    else:
        print(f'"{exp}": NO es un codigo postal valido')


if __name__ == "__main__":
    match()
