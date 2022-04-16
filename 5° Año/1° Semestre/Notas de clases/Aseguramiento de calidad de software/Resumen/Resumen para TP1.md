~~~toc
  style: number
  min_depth: 1
~~~
---
# Calidad y calidad de software: introduccion y videos

## Ejemplos de calidad de software
- Un aeropuerto donde el sistema de pago fallo tuvo vuelos cancelados y otros retrasados, lo que llevó a un mayor consumo de recursos del mismo. Un sotfware de mala calidad puede llevar a  un aumento en costos (lo "barato" sale caro).
- Nissan tuvo un problema con sus sistema para detectar pasajeros sentados, como consecuencia en caso de accidente sus airbags no eran desplegados.
- Un software mal documentado, explicado o diseñado, como en el caso del hospital que modifico el software para otras cantidades de radiacion en pacientes con cancer que luego murieron intoxicados.

## Concepto
- Producto eficaz y que da valor al usuario -> entonces conviene usar  metodologias agiles
- Usar metodologias agiles para testing a tiempo
- Reduce el costo de la correccion de errores
- Tiene atributos explicitos e implicitos
- Calidad detecta fallos y evita perdida de dinero y retrabajo
- Quality control -> Proceso que cumple con minimos estandares de la empresa -> supongo que se ve con tests 
- Para las pruebas existen diferentes herramientas para las distintas partes del proceso
- CI & CD

### Usar Culturas DevOps:

![[Screenshot_20220413_110518.png]]
![[Screenshot_20220413_110601.png]]
![[Screenshot_20220413_110628.png]]
### Atributos
- **Funcional**: adaptable, exacto, interoperable, cumple, seguro
- **Confiable**: maduro, tolera fallos, robusto
- **Usable**: entendible, intuitivo, operable
- **Eficaz**: en recursos de computacion, tiempo
- **De facil mantenimiento**: analizable, cambiable, estable, sometible a pruebas
- **Portable**: adaptable, instalable, confortable, sustituible

---

# Calidad:
Definida en la ISO 9000, que sustituye a la iso 8204. “Totality of characteristics of an entity that bears on its ability to satisfy stated and implied needs.”

Uno puede preguntarse si el software:
- **hace** lo que tiene que hacer? lo que se le **pidió**?
- Es **confiable**?
- utiliza los **recursos** adecuadamente?
- Es **seguro**?
- Es **facil** || **comodo** || **intuitivo**?

## Puntos de vista:
### Trascendental:
El *punto de vista del usuario* concibe la calidad en términos de **metas** específicas del usuario final. Si un producto las **satisface**, tiene calidad.

### Del fabricante:
Si **cumple** con las  **especificaciones originales** del producto, este tiene calidad.

### Del producto:
Características inherentes de un producto: funciones y características
Cuanto de lo que se **desea** está presente en el producto?
El consumidor confunde ==“ calidad con precio”==, entiende que mientras más caro es el producto, más cantidad de atributo está presente en el mismo.

### Basado en el valor:
Lo que el cliente esta dispuesto a pagar mas por una funcionalidad especifica (seguridad o usabilidad o...)

## Roles
AMBOS TIENE COMO HERRAMIENTA PRINCIPAL LOS **==TestCases==**.

### Tester
- Es aquel que **busca** lo que se le pidió (**errores**) con *reglas explicitas*. Evita que se **propaguen**.
- **No** le importa **como** fue hecho el software.
- Es **reemplezable** o prescindible.

### QA
QA se utiliza tanto para el rol, como para la accion (como testear y tester, solo que no son lo mismo).
- Tambien busca errores, fallos y defectos para que no se **propaguen**.
- **Evita** o **reduce** los *riesgos* y el *re-trabajo*
- **Comparte** con el desarrollador la **responsabilidad** de los fallos del software.

Por lo que **va mas allá** que el tester y resulta una persona imprescindible

#### Como lo hace?
- Testea el código y el **proceso**.
- Testea la **integracion** del sistema y su **consistencia**.
- Analiza los **datos** y su **dependencia**.
- Realiza analisis de **impacto**.

## Gran problema  de calidad!
En los 90s las empresas invertian mucho (y gastaban mucho) en software que no hacia lo que debia o habia prometido. ==No hacia lo que se suponia que haga==.

## Tipos:
### Calidad Funcional
- ==QUE HACE?==
- Que ==TAN BIEN== el producto **cumple** o cumplimenta un diseño provisto por el cliente.
- Relacionado con los **requerimientos funcionales** o especificaciones.

### Calidad Estructural
- ==COMO LO HACE?==
- ==COMO== el producto **cumple** los **requisitos no funcionales** que soportan a los funcionales 
	- robusto
	- mantenibilidad
	- portabilidad

### SQM
El objetivo del Software Quality Management es el de administrar la calidad de software, **definir los procesos** y los **requisitos** para esos procesos. Debe a su vez implementar **métricas** para cada proceso, sus salidas y un canal de **feedback**.

## Herramientas
- Casos de Uso (Use Case) -> Caso de Prueba (Test Case)
- Diagramas: Clase, Secuencia, ER
- ==Wireframe:== Representacion visual del requisito -> Mockups
- Bases de datos
- Documentacion del sistema bajo prueba

---
# Conceptos sueltos
Software Engineering, Software Quality, Quality Assurance & Control, Software Process, Review & Inspections, Test Strategy, Test Planning, Test Estimation, Test Requirements, Test Design, Test Techniques, Static Test Techniques, Test Metrics, Test Execution, Test Environment, Test Reporting, Test Process, Testing Standards, Test Tools, Configuration Management, Test Team Management, Defect Prevention

---
## Test Case


## Issue Tracker

## Use Case

## Acceptance Criteria

==Esfuerzo:== Gente, recursos...

==Peer review:== Estandares, calidad, documentacion ...

==Issue tracker:== Trello, Jira...


