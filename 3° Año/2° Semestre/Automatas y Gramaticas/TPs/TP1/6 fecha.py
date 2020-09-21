import re


def match():
    regex = re.compile(r"^((3[01]|[12][0-9]|0[1-9])/(0[1-9]|1[12])/\d{4})|(3[01]|[12][0-9]|0[1-9])-(0[1-9]|1[12])-\d{4}$")
    exp = input("Ingrese una fecha a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es una fecha valida')
    else:
        print(f'"{exp}": NO es una fecha valida')


if __name__ == "__main__":
    match()
