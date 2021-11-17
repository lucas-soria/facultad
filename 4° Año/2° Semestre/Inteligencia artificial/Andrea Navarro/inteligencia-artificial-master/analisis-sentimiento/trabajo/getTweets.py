import os
from dotenv import load_dotenv
import pandas as pd
import requests

def get_data(url, params):
    results = []
    while True:
        response = requests.get(url, headers=headers, params=params)
        # Generar excepción si la respuesta no es exitosa
        if response.status_code != 200:
            raise Exception(response.status_code, response.text)
        data = response.json()['data']
        meta_data = dict(response.json())['meta']
        results.append(pd.json_normalize(data))
        if 'next_token' not in meta_data:
            break
        else:
            token = meta_data['next_token']
            print(token)
            params = {
                'query': f'{words} lang:en -is:retweet',
                'next_token': token,
            }
    return pd.concat(results)


load_dotenv()  # Cargar valores del archivo .env en las variables de entorno

bearer_token = os.environ.get("BEARER_TOKEN")
url = "https://api.twitter.com/2/tweets/search/recent"
headers = {
    "Authorization": f"Bearer {bearer_token}",
    "User-Agent": "v2FullArchiveSearchPython"
}
words = '#techtember OR #techtober OR tectober OR tectember'
params = {
    'query': f'{words} lang:en -is:retweet',
    'max_results': 100
}

df = get_data(url, params)
print(df)
df.to_csv('TechTweets1.csv')

# nube de palabras de hashtags usados para TechTober
# tablas de frecuencia con los emoticones y analizar contexto del emoticon
# filtrado de spam -> si se repite un mensaje es SPAM, ver las menciones
# ver polaridad de las #MacBook
# ver de que productos se hablan (que cuente)

# Explicar que salio bien y que mal, que problemas
# Que conclusiones saco, que es lo que pense que iba a pasar, que pasó
# Explicar el proceso