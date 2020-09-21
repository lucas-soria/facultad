import re


def search():
    regex = re.compile(r"(^https?:\/\/www\.youtube\.com\/watch\?v=)(?P<id>[a-zA-Z0-9_-]{11,})(&?.*$)")
    exp = input("Ingrese la url de un video a validar: ")
    s = regex.search(exp)
    if s:
        print(f'"{s.group("id")}": es la id del video')
    else:
        print(f'"{exp}": NO es una url de video valida')


if __name__ == "__main__":
    search()
