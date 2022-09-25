**Índice:**
~~~toc
  min_depth: 1
~~~
# Information Technology Infrastructure Library (by UK, 1989. 2019)
Ver: [[SRE]]

Es un marco de trabajo / mejores prácticas / técnicas para la **gestión del servicio de IT**  (ITSM) que alinea los servicios IT con las necesidades del negocio.
Permite seleccionar, planear, desplegar y mantener un servicio de IT dentro del negocio, alineando las acciones y costos dentro de un _ambiente con necesidades del negocio cambiantes_.
Acercamiento holístico a la gestión y permite a los negocios poder colaborar con el quipo de TI para desplegar / entregar servicios de IT a las partes interesadas / inversores (stackeholders).

---
## Apartado para ITIL v3: Gestión de la seguridad
![[Screenshot_20220827_164109.png]]

### Soporte del servicio
Tiene que ver con poder:
- Capturar Cambios.
- Capturar Incidentes.
- Liberar cambios dentro de la organización.

El proceso empieza por **"la mesa de entrada"**, donde los clientes pueden reportar errores o pedir cambios, de ahí se mueve este ticket a **"Gestión de incidentes"**, donde se lo toma, sin importar si es un problema / error o un pedido de cambio.
Luego pasa a la sección de **"Gestión de problemas"**, donde se busca por la raíz del problema y se busca una respuesta. Continúa por la sección de **"Gestión del cambio"**, donde se evalúa el cambio y se le asigna un _riesgo_ y una _prioridad_. **"Gestión de lanzamiento" (release)** es responsable de tomar este cambio y generar un release, testearlo y distribuirlo.
Cada paso de este proceso es registrado en la base de datos de **"Gestión de la configuración"** (CMDB). Esta es el repositorio central de todas las configuraciones de las cosas o _"activos corporativos"_.

### Despliegue del servicio
Esta parte tiene que ver con el despliegue de servicios IT a lo largo de toda la organización y unidades del negocio.

**"Gestión del nivel de servicios"** o **"Acuerdos del nivel del servicio"**, donde se construyen acuerdos entre la organización de IT y unidades de negocio individuales o se fijan metas y objetivos para los niveles de servicio, en caso de que algo salga mal.
**"Gestión de la disponibilidad"** tiene que ver con la disponibilidad del servicio, que tan rápido se puede responder a incidentes para asegurar que el negocio pueda operar.
**"Gestión de la continuidad del negocio"** es construir planes de recuperación tras desastres y planes de continuidad del negocio. Los cuales deben estar disponibles y comunicados al personal, en caso de algún incidente.
**"Gestión financiera"** tiene que ver con todo lo que sea plata: presupuestos, aprovisionamiento, pedido de nuevo equipo, etc.
**"Gestión de capacidad"**, para asegurar que se puede seguir entregando el servicio en un estándar de alto nivel. Supongo capacidad de equipos y rendimiento y eso.

---
## Beneficios
- Menores costos.
- Servicios IT de alta calidad.
- Mayor productividad del negocio.
- Mayor retorno de inversión (ROI).
- Muy buena satisfacción del cliente.
- Mejor utilización de recursos.
- Se tienen procesos más maduros.
- Se reduce la probabilidad de error, downtime -> clientes insatisfechos, 

## 5 etapas del ciclo de vida
### Estrategia del servicio:


### Diseño del servicio:


### Transición del servicio:


### Operación del servicio:


### Mejora continua del servicio:



![[Pasted image 20220827170603.png]]

---
# ITIL 4 Acquiring and Managing Cloud Services - AXELOS


---
# Resources
- https://www.youtube.com/watch?v=951xIKQmols&list=PLEiEAq2VkUULCGKcQrKdlaJ0o2xgeQdoa
- https://www.youtube.com/watch?v=T0gRXDPpRmU&list=PLf8XMtbjh0dXYbPWjg-d5GifMXWuiKv5-
- https://www.blameless.com/devops/itil-devops-sre-work-together
- https://medium.com/swlh/itil-vs-sre-457f88c61cac
- https://enterprisersproject.com/article/2019/11/devops-vs-itil4-vs-SRE
- https://learn.g2.com/what-is-itil
