import re


regex = re.compile(r"jpg|png|gif|bmp|svg")
img_ext = input("Ingrese una extensión de una imagen: ")
if regex.match(img_ext):
    print(f'La extensión {img_ext} se corresponde con una imagen')
else:
    print(f'La extensión {img_ext} no se corresponde con una imagen')
