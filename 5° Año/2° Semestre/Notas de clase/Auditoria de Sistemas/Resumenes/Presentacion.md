**Temario:**
~~~toc
  min_depth: 1
~~~

---
# Conceptos y modelos de auditoria:
## Antecedentes:
**Etimología:** Viene del inglés Auditory, también es un nervio del oído encargado del equilibrio.

El concepto que se tenía es que el auditor es alguien que busca errores y detecta problemas. En realidad es una persona que se encarga de:
1. Identificar oportunidades de mejora.
2. Emite propuestas de Valor Agregado.
3. Tiene un compromiso que va más allá de funcionar como mecanismo detector.

## Definición:
==Auditoría:== **Disciplina** que engloba **técnicas y procedimientos** aplicados por una persona en la organización (o sector o sistema o producto...) que es **independiente de la operación** de la misma. Evalúa el **cumplimiento de objetivos** institucionales, da **recomendaciones** o correcciones.

==Auditoría en Tecnología de Información:== Lo mismo pero con el propósito de evaluar la **función de tecnología** de información y su aportación al cumplimiento de los objetivos institucionales. Tiene también el rol de identificar y documentar, evaluar controles...

## Clasificación:
### ¿Quién realiza la auditoria?
#### Auditoria externa:
Ejecutada por auditor o grupo de auditores, cuyo relación con la misma se limita a un contrato o convenio de *servicios profesionales*.

#### Auditoria interna:
Ejecutada por un auditor o grupo de auditores formalmente empleados en la empresa, pero que sus funciones son ajenas a la operación del objeto de la auditoria. Esta es **fundamental** para tener un buen _sistema de administración_.

### ¿Cuál es el objetivo de la auditoria?
#### Auditoria financiera:
Tiene que ver con comprobar la integridad y veracidad de la información (dictaminaron de **estados** financieros). Legalmente, solo es válida si es emitida por auditores **externos**.

#### Auditoria administrativa:
Es la evaluación de la eficiencia y productividad de las operaciones de una empresa. Ambos tipos de auditores pueden ejecutarla.

#### Auditoria integral:
Evalúa todos los objetivos de la empresa/organización: financiera, administrativa, salvaguarda de activos, normatividad, etc.
Ambos lo pueden hacer, pero un auditor interno posee más herramientas y conocimiento de la empresa y puede llegar a hacer un trabajo más eficiente.

> No se menciona una **Auditoria en tecnología de la Información** porque se puede hacer con cualquiera de los tipos anteriores. Si las auditorias anteriores incorporan la tecnología para poder efectuar estas revisiones, se vuelve más transparente, ágil, efectivo, etc.

## Proceso:
![[Screenshot_20220822_173747.png|center]]

## Procedimientos:
Conjunto de técnicas de trabajo aplicadas en todas las etapas del proceso.
Es evidente en la etapa de APLICACIÓN DE LAS PRUEBAS (Diseño, cumplimiento o sustantivas).

### Inspección documental:
Análisis de procedimientos de control que implican documentación (del procedimiento o de los resultados).
Ej: Actas, manuales, contratos, planes, planos, etc.

### Entrevistas:
A personal con conocimiento y experiencia de interés. Aunque puede ser que sea un trabajador y que la auditoria refleje su falta de conocimiento sobre su área o tarea.

### Encuestas:
Cuestionarios predefinidos por grupo sobre un aspecto particular.
Comúnmente utilizada para medir niveles de satisfacción respecto a un servicio proporcionado por la empresa (servicio con soporte tecnológico o provisto por un sistema, aunque para mí puede ser sobre cualquier cosa).

### Sesiones facilitadas:
Desarrollo de una sesión de trabajo para obtener información de un grupo de expertos sobre un tema.
Ej: validar elementos de estrategia de la empresa, proponer soluciones a problemas específicos, obtener conocimiento sobre fragmentos de procesos no documentados y/o en los cuales trabajan varios departamentos/equipos.

### Certificación:
Apoyar el juicio del auditor con una opinión de un experto.

### Confirmación:
De información que la empresa provea por parte de un tercero. Si es masiva se la denomina **"circularización"**.
Ej: clientes, proveedores, acreedores, etc.

### Técnicas aplicadas a la tecnología:
Evalúa el funcionamiento de los **componentes tecnológicos** del control interno y no _productos_ o _resultados_.
Ej: Capacidad de manejo de altos volúmenes de transacciones, velocidad, consistencia de un canal de comunicaciones, efectividad de una planta de energía alterna, etc.

### Técnicas asistidas por tecnología:
Utilizan tecnología como habilitador para evaluar la efectividad del control interno mediante el examen (ahora si) de **productos** y **resultados**.

_Pueden ser implementadas:_
- Usando programas en las computadoras o instalaciones de la empresa.
- Usando paquetes de auditoria como: ACL, IDEA, GREDIT, etc.

## Compromiso ético:
En muchas ocasiones rebasa las obligaciones contractuales con el cliente directo para proteger los clientes indirectos.

![[Screenshot_20220824_211855.png]]

---
# Organización:
## Tipos de estructura:
| **_Nivel estratégico (equipo de apoyo de la dirección)_**                                                                                                                                                                                                                                                                                            | **_Nivel táctico (gerencias, jefaturas)_**                                                                                                                                                                                                                                                            |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| <ul><li>Independencia funcional.</li><br /><li>El proceso de auditoría opera estratégicamente.</li><br /><li>Existe un compromiso permanente con la alta dirección.</li><br /><li>Generalmente, se da en instituciones financieras, de crédito y en dependencias del gobierno.</li><br /><li>Personal de auditoría con visión del negocio.</li></ul> | <ul><li>No hay independencia funcional respecto a otras direcciones o gerencias.</li><br /><li>Se limita mucho al estilo de trabajo del nivel superior al que soporta.</li><br /><li>Generalmente, se da en instituciones financieras, de crédito, gubernamentales, industrial y educativo.</li></ul> |

## Estructura organizacional:
| **Área de quién depende la función de auditoría**                                | **Consideraciones clave de la función en el entorno del negocio**                                                                                                                                                                                                            | **Ventajas / áreas de oportunidad**                                                                                                                                                                                                                                                                                                                | **Desventajas / restricciones**                                                                                                                                                                                                                                                                                              |
| -------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **_Dirección o gerencia de auditoría_**                                          | <ul><li>Independiente de la función de informática y de las otras áreas de la empresa donde se dará la auditoría.</li><br /><li>Integración de los controles y políticas de informática a los establecidos para las otras áreas del negocio.</li></ul>                       | <ul><li>Objetividad en el desempeño de las auditorías.</li><br /><li>Hay una planeación y desarrollo conjunto de proyectos con las otras áreas de auditoría.</li><br /><li>Se asegura control y seguimiento sobre todos los recursos y proyectos de informática.</li></ul>                                                                       | <ul><li>Las áreas del negocio no aceptan con facilidad ser auditadas o evaluadas por personal de la misma empresa.</li><br /><li>Se corre el riesgo de desconocer el alcance y misión de informática en el negocio y el apoyo requerido por dicha área.</li></ul>                                                          |
| **_Dirección o gerencia de informática_**                                        | <ul><li>Hay dependencia de tipo funcional hacia el director o gerente de informática.</li><br /><li>El director o gerente de informática debe ser negociador y facilitador para impulsar el proceso de auditoría en todo el negocio, no solo en su área.</li></ul>           | <ul><li>Se facilita en alto grado de nivel de apoyo en informática.</li><br /><li>Conocimiento formal y oportuno de los proyectos e inversiones de informática.</li><br /><li>Se agiliza el proceso de concientización en el personal de informática en el cumplimiento de políticas y controles.</li></ul>                                      | <ul><li>Incertidumbre acerca de que anomalías, carencias e incumplimiento de la función de informática, se hagan del conocimiento de la alta dirección de manera formal y oportuna.</li><br /><li>El enfoque de la auditoría es limitarse a ser una entidad que "sugiere, no se controla y asegura".</li></ul>              |
| **_Personal de apoyo de la dirección general_**                                  | <ul><li>La función se ubica como una entidad estratégica dentro del negocio.</li><br /><li>El responsable de la función sebe tener una visión del negocio.</li><br /><li>Hay un compromiso de dar resultados que generen valor agregado.</li></ul>                           | <ul><li>Apoyo permanente de la alta dirección en la difusión e implantación de políticas, controles y procedimientos.</li><br /><li>Las áreas del negocio se comprometen a cumplir las políticas y controles inherentes a informática de una manera formal.</li><br /><li>Se justifica el perfil ejecutivo del auditor en informática.</li></ul> | <ul><li>La alta dirección debe dar seguimiento y autorización formal al desempeño de informática con conocimiento de causa.</li><br /><li>Se reduce el margen de error en cada uno de los proyectos de auditoría al ser evaluados por la alta dirección.</li><br /><li>Se orientan los proyectos de informática.</li></ul> |
| **_Personal de Función de auditoría informática ejercida por terceros_**         | <ul><li>Los proyectos con los asesores externos deben ser coordinados por la dirección o gerencia de auditoría o informática.</li><br /><li>Se da cuando se carece de la función de auditoría informática, o si se busca asegurar o validar información relevante.</li></ul> | <ul><li>Los asesores externos por lo general se apoyan en métodos, técnicas y estándares de auditoría en informática comúnmente aceptados a nivel nacional e internacional.</li><br /><li>Son personal de un nivel profesional más aceptable, debido a su experiencia y constante actualización.</li></ul>                                        | <ul><li>Pueden darse fugas de información.</li><br /><li>Costos altos y difíciles de controlar.</li><br /><li>El tiempo de asimilación de lo que es el negocio puede prolongarse.</li><br /><li>A veces las soluciones y recomendaciones no son adecuadas para el negocio.</li></ul>                                      |
| **_Personal de Función de auditoría informática ejercida por terceros (Cont.)_** | <ul><li>El personal externo ha de contar con alta experiencia en este ramo y ser conocido por su trayectoria en el mercado regional o nacional al menos.</li><br /><li>Debe evaluarse se desempeño una vez terminado su trabajo.</li></ul>                                   | <ul><li>Existe un compromiso moral y profesional del auditor en informática para ejercer la asesoría de manera ética e independiente.</li><br /><li>Se exigen resultados y beneficios desde el inicio de los proyectos.</li></ul>                                                                                                                | <ul><li>Si es contratado por el responsable de informática puede estar influido en el momento de elaborar y entregar el informe final de trabajo.</li><br /><li>Se requiere compromiso y participación formal de todos los involucrados.</li></ul>                                                                         | 

---
# Control interno:
## Conceptos:
![[Screenshot_20220921_202220.png]]


### Control interno:
Conjunto de políticas, procedimientos, procesos, funcionalidades, actividades, subsistemas, estructuras de la organización y gente (elementos de administración) que se encuentran agrupados o segregados conscientemente (en forma coordinada) para asegurar el logro efectivo de metas y objetivos, y prevenir eventos indeseables, así como detectarlos y corregirlos.
Es la columna vertebral del sistema de administración establecido por una organización y su propósito fundamental es apoyar el logro de los objetivos.
_Establecido por:_
- Consejo de Dirección
- Administración.
_Da certeza sobre el cumplimiento de objetivos de:_
- Efectividad y eficiencia de las operaciones.
- Confiabilidad de información financiera.
- Cumplimiento de leyes y regulaciones.

#### Control interno en TI:
Conjunto de elementos de administración que una organización establece en forma coordinada para que sus recursos apoyen en forma efectiva los objetivos de la organización.

##### Controles generales:
Es el control que se aplica a todas las actividades desarrolladas en el área de TI.
Si son adecuados, no asegura que lo sean los específicos, pero si son deficientes, los específicos también.
**Ejemplo:**
- Plan estratégico de sistemas.
- Organigrama del área.
- Plan de continuidad del negocio.
- Metodología de desarrollo de sistemas.

##### Controles específicos o controles de aplicación:
Se aplican específicamente a una aplicación para asegurar que las transacciones son manejadas de acuerdo a los objetivos de control, que la información conserva todos sus atributos y características y que los sistemas cumplen con los objetivos para los cuales fueron creados.
**Ejemplo:**
- Ingreso de datos:
	- Uso de passwords.
	- Validación de datos.
- Documentación del aplicativo cliente:
	- Objetivos y alcances.
	- Casos de usos, historias de usuario.
	- Diagrama de clase.

### Objetivo:
- Aspiraciones de una organización.
- Permiten obtener idea más precisa de lo que desea obtener y alinear los recursos necesarios para lograrlos.
- Todas tienen unos diferentes, dependiendo de:
	- Dimensión.
	- Contexto.
- Generalmente tratan de:
	- Rentabilidad.
	- Crecimiento institucional.
	- Calidad.
	- Posicionamiento competitivo.
	- Productos y servicios.
	- Recursos humanos.
	- Imagen.
	- Impacto en la comunidad.
	- Productividad.
	- Clientes.

### Meta:
- Representan propósitos específicos para lograr los objetivos.
- Son medibles.
- Tiene un límite de tiempo.
- Forma en la que se mide el cumplimiento de los objetivos.

### Misión:
- Razón principal por la que la empresa existe.
- Es bastante filosófico.
- No tiene un límite de tiempo.
- Establecer y mantener la consistencia y la claridad del propósito en toda la empresa.

## Componentes del control interno:
![[Screenshot_20220822_175504.png]]

### Objetivos de control:
Busca el control interno en TI y ayuda a dar soporte a los objetivos de negocio de la organización.

### Procedimientos de control:
Elementos de la administración que la organización establece con la intención de lograr sus objetivos de control.
_Puede manifestarse como:_
- Estructuras de organización.
- Procedimientos de trabajo.
- Estándares.
- Políticas.
- Reglamentos.
- Programas de capacitación.
- Planes de contingencia.
- Arquitecturas de seguridad de acceso a la información.
_Tiene carácter:_
- Preventivos.
- Detectivos.
- Correctivos.
- Proactivos.

### Procedimientos de auditoría / pruebas:
Conjunto de técnicas formales que el auditor aplica para conocer y evaluar el correcto y consistente funcionamiento de los procedimientos de control (procesos y resultados).

### Riesgos:
Probabilidad de ocurrencia de un evento no deseado (o falta de uno deseado) con impacto en los objetivos del negocio.

### Documentación del control interno:
El trabajo del auditor es susceptible de revisiones externas y de cuestionamientos que puede surgir de cualquiera de los interesados del trabajo del mismo. Todo trabajo de auditoria debe quedar debidamente documentado, sus conclusiones y recomendaciones.

---
# Etapas:
## Etapas del proceso:
### Planificación:
![[Screenshot_20220822_175659.png]]


### Ejecución:
![[Screenshot_20220822_175741.png]]


### Conclusión:
![[Screenshot_20220822_175842.png]]


![[Screenshot_20220822_175912.png]]


![[Screenshot_20220822_175943.png]]


![[Screenshot_20220822_180007.png]]


### Seguimiento:


## El proceso de auditoria en 4 etapas:
