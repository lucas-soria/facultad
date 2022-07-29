import re


print("validador de ip")
ip = input("\ningrese una ip\n")
if re.match("^(([0-9]|[1-9][0-9]|1[0-9]{2}|"
            "2[0-4][0-9]|25[0-5])\\.){3}([0-9]"
            "|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$", ip):
    print(f'El IP: {ip} ES correcto')
else:
    print(f'El IP: {ip} NO es correcto')
