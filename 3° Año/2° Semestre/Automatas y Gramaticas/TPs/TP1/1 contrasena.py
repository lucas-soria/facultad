import re


def match():
    regex = re.compile(r"^(?=.*[a-z])(?=.*[A-Z])((?=.*\d)|(?=.*\W))[A-Za-z\d\W]{8,}$")
    exp = input("Ingrese una contraseña a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es una contraseña valida')
    else:
        print(f'"{exp}": NO es una contraseña valida')


if __name__ == "__main__":
    match()
