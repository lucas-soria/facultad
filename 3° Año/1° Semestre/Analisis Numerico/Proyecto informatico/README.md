# Proyecto: "Metodo de diferencias hacia adelante"


## Indice:

* [Herramienta]
* [Uso de la herramienta]
* [Desarrollo y limitaciones]


## Herramienta:

La herramienta aplica el Metodo de diferencias hacia adelante para calcular la derivada de una funcion en el punto x0.


## Uso de la herramienta:

Para poder usar la herramienta se necesita tener instalada la version 3 de Python.
Al ser ejecutado solicitara una forma de ingreso de datos. Existen dos opciones: Manual o mediante el uso de un archivo.
Ambas opciones deben ser seleccionadas tecleando 1 o 2 y presionando ENTER.

### Opcion manual:

Para poder ingresar los datos de forma manual, se deben conocer todos los puntos que se utilizaran en la aplicacion del metodo.
Primero se solicitaran los datos de abscisas, estos deben estar separados por "," (coma) y pueden tener espacios. Si hay algun caracter distinto a un numero el programa terminara. El primer valor debe ser x0, el valor de abscisas donde se quiere conocer el valor de la derivada.

* [Ejemplo de la forma correcta de utilizar la herramienta:]
X: 23, 23.1, 23.2, 23.3, 23.4, 23.5, 23.6, 23.7, 23.8, 23.9

Una vez que se ingresan los datos de abscisas se procede a solicitar los datos de ordenadas, los cuales deben ser ingresados de la misma forma. El primer valor debe ser y0.

* [Ejemplo de la forma correcta de utilizar la herramienta:]
Y: 529, 533.61, 538.24, 542.89, 547.56, 552.25, 556.96, 561.69, 566.44, 571.21

Despues de ingresar estos datos la herramienta continua con los calculos y devuelve el resultado de la derivada en punto (x0, y0).

### Opcion archivo:

Para poder ingresar los datos a traves de un archivo .csv se debe crear un archivo de este formato ingresando los datos separados por comas, sin espacios y solamente 2 valores por fila. Se debe empezar por una fila especificando el orden de los valores, colocando "x" o "y" dependiendo de lo que se prefiera, y en las siguientes filas se colocaran con los valores de cada uno de los puntos a tener en cuenta.

* [Ejemplo de la forma correcta de crear el archivo .csv]
x,y
23,529
23.1,533.61
23.2,538.24
23.3,542.89
23.4,547.56
23.5,552.25
23.6,556.96
23.7,561.69
23.8,566.44
23.9,571.21

Cuando se utilice la herramienta con la opcion del archivo se solicitara que se ingrese la ruta o PATH del archivo, indicando su ubicacion. Una vez ingresada se corroborara que el archivo ingresado exista, sea del formato solicitado y que no hayan problemas con los datos ingresados. Luego se realizaran los calculos y devolvera el resultado, de la misma forma que en la opcion manual.


## Desarrollo y limitaciones:

El programa se desarrollo en el lenguaje Python 3.8, se utilizo la libreria csv y se siguio la guia de estilo flake8.
La herramienta maneja la mayoria de los errores que pueden surgir de su uso incorrecto, tales como:

### LetraError

Controla que no haya elementos no numericos en los datos ingresados.

### LargoError

Controla que los puntos esten completos, es decir, que esten los valores de "x" e "y" simlultaneamente para todos los puntos. 

### ArchivoError

Controla que el archivo exista, sea un archivo .scv o no tenga errores en sus datos.

### hError

Controla que la diferencia entre todos los valores consecutivos de x sean iguales, con un error de 0.000001. Esta toleracia se da ya que en el lenguaje los numeros con coma flotante poseen un error al rededor del decimo decimal. Se decidio realizar esto para evitar errores en el uso de la herramienta a la hora de ingresar los datos.
