import re


def match():
    regex = re.compile(r"^@[a-zA-Z0-9_]{1,15}$")
    exp = input("Ingrese una cuenta de Twitter a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es una cuenta valida')
    else:
        print(f'"{exp}": NO es una cuenta valida')


if __name__ == "__main__":
    match()
