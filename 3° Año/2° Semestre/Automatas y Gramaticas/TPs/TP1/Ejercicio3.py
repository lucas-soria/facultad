import re


def match(expre):
    exp = input("A validar: ")
    if re.match(expre, exp):
        print(f'{exp}: es correcto')
    else:
        print(f'{exp}: NO es correcto')


if __name__ == "__main__":
    contrasena = r"((?=.*[A-Z])(?=.*[a-z])(?=.*(\d|\W))){8,}$"
    twitter = r"@([a-zA-Z0-9_]{1,15}(?=.*!(twitter|admin)))$"
    url = r"^https?:\/\/[\w\-\.]+(\.[\w\-]+)+[/#?]?.*$"
    mail = r"^[^@]{1,64}@([^@_])+(\.[^@_])*$"
    youtube = r"^https?:\/\/www\.youtube\.com\/watch\?v=[a-zA-Z0-9_-]{11}$"
    fecha = r"^((3[01]|[12][0-9]|0[1-9])/(0[1-9]|1[12])/\d{4})|(3[01]|[12][0-9]|0[1-9])-(0[1-9]|1[12])-\d{4}$"
    telefono = r"^\+549(11|((2|3)(\d){2,3}))15(\d{7})$"
    postal = r"^[A-Z][\d]{4}[A-Z]{3}$"
    fijo = r"^\+54(11|((2|3)(\d){2,3}))4(\d{6})$"
    match(fijo)
