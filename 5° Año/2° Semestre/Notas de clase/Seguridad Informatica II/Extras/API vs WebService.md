# Diferencia entre API y WebService
## API (Application programming interface)
- Código que pone a disposición funciones que permiten que dos programas no relacionados **interactúen** entre sí.
- Sin la necesidad de que se conozca su implementación o código, usando sus funcionalidades agnósticamente.
- No necesitan protocolos específicos.
- Se supone que son open source...
- Se pueden encontrar en programas instalados o en el sistema operativo.

### APIs web:
- A diferencia de los web services, no precisan o hacen público un WSDL (Web Service Description Language) basado en XML para describir las funcionalidades ofrecidas.
- Inteactua con HTTP y verbos (GET, POST, etc.).
- No están limitadas solo al protocolo HTTP.
- Emplean XML, JSON o lo que quieran, porque no están limitadas.
- Usan una arquitectura liviana que lo hace piola para dispositivos de bajo ancho de banda (móviles), aunque estos consumen mucho, mucho tiempo.

## Web Service
- Todo Web Service es una API, no toda API es un Web Service.
- Forma de comunicar dos máquinas a través de internet.
- API que opera mayormente sobre el protocolo HTTP y brinda servicios a otros programas a través de la web.
- Tiene requerimientos más estrictos en cuanto a su funcionamiento y funcionalidades.
- Es un conjunto de protocolos y estándares para intercambiar datos.
	- Estandariza la forma de intercambiar datos a través de la web.
	- SOA, SOAP (Simple Object Access Protocol), XML-RPC.
	- Intercambio a través de XML.
- Parece ser el predecesor de las APIs web.

---

- La verdad nadie sabe explicarlo bien: Web Service es API, API no es Web Service, pero Web API es Web Service. Web Service necesita operar si o si en web (obvio), API no. Web API es una forma de interactuar bajo ciertos protocolos específicos a través de la red... lo que se parece mucho a un web service...
- Diferencia más importante es la limitación del Web Service al uso de XML, SOAP y la red.
