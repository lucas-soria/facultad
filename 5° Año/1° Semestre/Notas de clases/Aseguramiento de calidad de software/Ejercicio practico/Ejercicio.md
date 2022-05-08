# Ejercicio

## Pantalla App: Mensajeria instantanea
![[2022-04-20 2022-04-20 20.42.27.excalidraw]]

## n° test cases para tener covertura aceptable
15


## Nombre de al menos 3 TC
- Chat muestra nombre del receptor correcto
- Enviar mensaje correctamente
- Recibir mensaje correctamente
- Eliminar mensaje correctamente
- Volver a vista de chats
- Escribir mensaje


## Desarrollo de TestCase
**Codigo / ID:** xxx
**Nombre:** Enviar mensaje correctamente
**Precondiciones:**
- Conexion a internet
- Tener una cuenta registrada
- Estar logueado
- Tener un contacto agregado
**Prioridad:** Alta
**Entorno:** Android

| N   | pasos                               | resultado esperado                                                                           |
| --- | ----------------------------------- | -------------------------------------------------------------------------------------------- |
| 1   | Ingresar al Chat                    | Muestra los mensajes anteriores de ese chat                                                  |
| 2   | Escribir un mensaje                 | En la parte inferior de la aplicacion se puede visualizar el mensaje a medida que se escribe |
| 3   | Tocar boton enviar                  | El mensaje se borra del TextBox y se visualiza correctamente en el chat                      |
| 4   | El mensaje le llega al destinatario | Se marca un doble tick en el mensaje                                                         |
**Creado por:** Lucas y Alejandro