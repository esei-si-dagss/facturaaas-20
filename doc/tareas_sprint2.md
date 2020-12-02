# SPRINT 2
**Objetivo**: Desarrollar los componentes de la aplicación responsables de la creación, edición y visualización de facturas, junto con la creación de los pagos previstos para las mismas.

**Historias de usuario a completar**: H14, H15, H16

**Inicio**:  (3|4)/12/2020

**Duración**: 4-? semanas (hasta 27/1/2021, día del examen)

## TAREAS SPRINT (organización orientativa, no obligatoria)
### Semana 1 (hasta (10|11)/12/2020)
* Estudio de la descripción del proyecto y las historias de usuario existentes y las incluidas en el Sprint 2
* Estudio del código de partida: entidades JPA y relaciones 
* Completar el modelo E-R con las entidades y relaciones necesarias para dar soporte a las historias del -Sprint 2 (_Factura_, _LineaFactura_, _Pago_)
* Implementar las entidades JPA correspondientes, junto con sus relaciones (integrándolas con las ya existentes)

![Entidades de partida](entidades.png?raw=true "Entidades de partida")

### Semana 2 (hasta (17|18)/12/2020)
* Implementar los objetos DAO (_data access object_) necesarios para las entidades definidas, creando las consultas JPQL necesarias para dar soporte a las funcionalidaes a usar en las historias de usuario incluidas en el Sprint 2.
* Plantear un primer esbozo (en papel, con herramientas de diseño, directamente en el proyecto) de las vistas JSF necesarias para dar soporte a las historias de usuario incluidas en el Sprint 2.

### Semana 3 (hasta jueves (8|14)/1/2021)
* Implementación de las vistas JSF y los objetos de respaldo necesarios para la creación del interfaz de usuario diseñado.

### Semana 4 (hasta 27/1/2021, día del examen)
* Implementación de las vistas JSF y los objetos de respaldo necesarios para la creación del interfaz de usuario diseñado.
* Redacción del documento entregable

## ENTREGA

* Proyecto individual
* Entrega en MOOVI (hasta 27/1/2021, fecha del examen)
  * Informe de grupo
  * Informe individual 

### Contenidos de los documentos entregables

#### Informe del proyecto

##### Material a entregar (en un único ZIP)

* Carpeta con código fuente del proyecto  (haced antes `mvn clean` para que sólo se incluya el código fuente)
* Memoria del proyecto (ver estructura propuesta)



##### Estructura propuesta para la memoria del proyecto (extensión orientativa:  hasta 6-7 páginas)

**Parte 1: Descripción técnica**
1. Breve descripción general del proyecto
2. Descripción de las funcionalidades cubiertas
3. Descripción de los elementos que conforman la implementación realizada: vistas JSF, objetos de respaldo, EJBs, entidades añadidas, ...
4. Instrucciones de compilación y uso (si fueran necesarias)

**Parte 2: Revisión crítica de la arquitectura del proyecto Java EE**
* Se trata de realizar una revisión de la arquitectura de la aplicación y de las cuestiones de diseño que pudieran ser incorrectas o mejorables en la solución propuesta (duplicidad de código, mejor uso de herencia, ''sobreingeniería'' provocada por el uso de JEE, etc), así como las impresiones personales sobre las soluciones adoptadas.
* El formato es libre, como orientación se pueden aborar los siguientes puntos:
   * Conclusiones, problemas y/o mejoras respecto a la arquitectura general del proyecto FACTURAaaS y de la idoneidad del empleo de las tecnologías de la especificación JEE 8.
   * Conclusiones, problemas y/o mejoras respecto al diseño del modelo de datos (entidades JPA) propuesto [aspectos poco claros, mejoras en la modelización, relaciones supérfluas, diseños alternativos, etc]
   * Conclusiones, problemas y/o mejoras respecto al diseño de la capa de lógica [limitaciones y mejoras de la arquitectura empleada, ideas para la mejora de la organziación y reutilización del código, dificultades de las tecnologías empleadas, etc]
   * Conclusiones, problemas y/o mejoras respecto al diseño de la capa de presentación [limitaciones y mejoras de la arquitectura empleada y de la organización de las vistas, ideas para la mejora de la organziación y reutilización del código, dificultades de las tecnologías empleadas, etc]
   * Conclusiones generales de la práctica y de la tecnología empleada
