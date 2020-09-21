import re


def match():
    regex = re.compile(r"^https?:\/\/[\w\-\.]+(\.[\w\-]+)+[/#?]?.*$")
    exp = input("Ingrese una url a validar: ")
    if regex.match(exp):
        print(f'"{exp}": es una url valida')
    else:
        print(f'"{exp}": NO es una url valida')


if __name__ == "__main__":
    match()
