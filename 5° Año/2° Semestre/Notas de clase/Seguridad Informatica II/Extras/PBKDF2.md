# Password-Based Key Derivation Function
En criptografía, PBKDF1 y 2 son funciones que se utilizan para disminuir vulnerabilidades de ataques por fuerza bruta.
PBKDF2 es parte del estándar de criptografía RSA de llave pública (PKCS).

## Propósito y operación
PBKDF2 aplica una función pseudorandom, como HMAC, para hacer de contraseña, junto con un _salteo_ que repite el proceso muchas veces para producir una clave derivada que puede ser usada como clave criptográfica en subsecuentes operaciones. El agregar complejidad al cálculo de la clave se llama 'key stretching' y la hace más difícil de crackear.
Con el tiempo las iteraciones se han ido aumentando y a estándares 'actuales', OWASP recomienda emplear, 310.000 iteraciones para PBKDF2-HMAC-SHA256 y 120.000 para PBKDF2-HMAC-SHA512.
El utilizar un _salt_ reduce la posibilidad de usar hashes pre computados (conocido como rainbow tables) para ataques, y significa que múltiples contraseñas necesitan ser testeadas individualmente, no todas de una. El estándar recomendado para el _salt_ es de al menos 64b (preferible 128b).

## Proceso de derivación de la clave
La derivación de clave de PBKDF2 tiene 4 inputs:

$$DK = PBKDF2(PRF, Password, Salt, c, dkLen)$$

Donde:
- **DK** clave derivada generada.
- **PRF** función pseudorandom de 2 parámetros con una longitud de output h (Ej: HMAC).
- **Password** clave maestra de la que deriva la _'clave derivada'_ generada.
- **Salt** secuencia de bits de _salt criptográficos_.
- **c** número de iteraciones deseadas.
- **dkLen** largo deseado en bits de la clave derivada.

Cada bloque $T_i$ de largo hLen bits de la clave derivada **DK** es computado como sigue:
$$DK = T_1 + T_2 + ⋯ + \frac{T_{dkLen}}{hLen}$$

Donde:
- + representa la concatenación de Strings.
- $$Ti = F(Password, Salt, c, i)$$

La función **F** es un XOR (^) de **c** iteraciones de PRFs encadenados. La primera iteración de PRF usa _Password_ como clave PRF y _salt_ concatenados con _i_ encodeado como un gran entero de 32bits como entrada. Subsecuentes iteraciones de PRF usan _Password_ como la clave PRF y la salida de los previos PRF como input.

$$F(Password, Salt, c, i) = U_1 \wedge U_2 \wedge ⋯ \wedge U_c$$

Donde:

$$\begin{align*}
U_1 &= PRF(Password, Salt + INT_{32_{BE}}(i))\\
U_2 &= PRF(Password, U_1)\\
\vdots\\
U_c &= PRF(Password, U_{c−1})
\end{align*}$$

Por ejemplo, WAP2 usa:

$$DK = PBKDF2(HMAC\textrm{-}SHA1, passphrase, ssid, 4096, 256)$$

PBKDF1 tenía un proceso más simple, el **U** inicial es creado por PRF (Password + Salt), y los siguientes solo utilizan el PRF anterior. La clave es extraída como el primer largo dkLen en bits del final del hash, razón por la que hay un tamaño límite.

## Colisiones HMAC
PBKDF2 tienen una propiedad interesante cuando usa HMAC como su función pseudo-random. Es posible construir cualquier número de pares de contraseñas diferentes con colisiones dentro de cada par. Si la contraseña provista es más larga que el bloque de la función hash HMAC, la contraseña es primero pre-hasheada. Por ejemplo, esta contraseña demasiado larga:

`Password: plnlrtfpijpuhqylxbgqiiyipieyxvfsavzgxbbcfusqkozwpngsyejqlmjsytrmd`

Por lo tanto, si se usa por ejemplo HMAC-SHA1, se pre-hashea usando SHA-1:

`SHA1 (hex): 65426b585154667542717027635463617226672a`

Que puede ser representado en ASCII como:

`SHA1 (ASCII): eBkXQTfuBqp'cTcar&g*`

Esto significa que sin importar el _salt_ o las _iteraciones_, PBKDF2-HMAC-SHA1 va a generar la misma clave para las contraseñas:

~~~
"plnlrtfpijpuhqylxbgqiiyipieyxvfsavzgxbbcfusqkozwpngsyejqlmjsytrmd"
"eBkXQTfuBqp'cTcar&g*"
~~~

Por ejemplo, usando:

~~~
PRF: HMAC-SHA1
Salt: A009C1A485912C6AE630D3E744240B04
Iterations: 1,000
Derived key length: 16 bytes
~~~

Las siguientes 2 llamadas a funciones será:

~~~
PBKDF2-HMAC-SHA1("plnlrtfpijpuhqylxbgqiiyipieyxvfsavzgxbbcfusqkozwpngs ejqlmjsytrmd", ...)
PBKDF2-HMAC-SHA1 ("eBkXQTfuBqp'cTcar&g*", ...) 
~~~

Va a generar la misma clave derivada en bytes (`17EB4014C8C461C300E9B61518B9A18B`). Estas colisiones de claves derivadas no representan una vulnerabilidad de seguridad, ya que uno debe conocer la contraseña original para poder generar el hash de esa contraseña.

## Alternativas a PBKDF2
Una debilidad de PBKDF2 es que mientras el número de iteraciones puede ser ajustado para hacerlo tomar una cantidad arbitraria de tiempo computacional, puede ser implementado con un pequeño circuito y poca RAM, lo que hace que ataques por fuerza bruta utilizando circuitos integrados de aplicación específica o unidades de procesamiento gráfico relativamente barato. El hasheo de contraseñas bcrypt requiere una mayor cantidad de RAM y es un poco más fuerte a estos ataques, mientras que las funciones de derivación bcrypt modernas pueden usar una cantidad arbitrariamente mas grande de memoria y es, por lo tanto, mas resistente a ataques ASIC y/o GPU.
En 2013 la Competencia de Hasheo de Contraseñas (PHC) se dió para encontrar / desarrollar un acercamiento mas resistente y en 2015 se eligió ganador a Argon2. NIST a su vez recomienda usar Balloon hashing.