import re


regex = re.compile(r"^(?:(?:25[0-5]|2[0-4][0-9]|"
                   "[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|"
                   "2[0-4][0-9]|[01]?[0-9][0-9]?)$")
ip = input("Ingrese una IP: ")
if regex.match(ip):
    print('La extensión ', ip, 'SE corresponde con una IP')
else:
    print('La extensión ', ip, 'NO se corresponde con una IP')
