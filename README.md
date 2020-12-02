# Proyecto final DAGSS 2010
Proyecto Java EE 8 


# PREVIO

* Descargar copia del proyecto desde GitHub
  ```
  git clone https://github.com/esei-si-dagss/facturaaas-20.git
  ```
  


* Crear base de datos `facturaaas` en MySQL
   ```
   $ mysql -u root -p    [pedirá la contraseña de MySQL]

   mysql> create user facturaaas@localhost identified by "facturaaas";
   mysql> create database facturaaas;
   mysql> grant all privileges on facturaaas.* to facturaaas@localhost;
   ```
   
   Adicionalmente, en algunos casos (errores MySQL de formato de fecha) puede ser necesario establecer un formato de fecha compatible
   ```
   mysql> set @@global.time_zone = '+00:00';
   mysql> set @@session.time_zone = '+00:00';
   ```

* Crear tabla de usuarios y cargar usuarios iniciales

  * Desde el directorio `facturaaas`
  
  ```
  cd facturaaas

  mysql -u facturaaas -p -D facturaaas < sql/usuarios_iniciales.sql
                                                      <pedirá la contraseña facturaaaas>
  ```
  
* Abrir el proyecto y ejecutarlo
En el primer despligue se generarán el resto de tablas de la base de datos.
* En caso de incorporar nuevas entidades, también se generán sus respectivas tablas una vez desplegada la nueva versión de la aplicación.
* Dependiendo de las modificacionesr realiazdas sobre entidades existentes (bien las de partida o las incorporadas posteriormente) puede requerirse eliminar manualmente las 
tablas implicadas (o la BD completa) para que el motor JPA vuelva a generarlas.

# Dependencias (ver `pom.xml`)

* **Bootsfaces**: biblioteca de componentes JSF basada en Bootstrap

  URL: [https://www.bootsfaces.net/](https://www.bootsfaces.net/)

* **Omnifaces**: colección de utilidades para simplificar el desarrollo de aplicaciones JSP

  URL: [http://omnifaces.org/](http://omnifaces.org/)

  Elementos utilizados (_converters_)

  * [SelectItemsConverter](http://showcase.omnifaces.org/converters/SelectItemsConverter): conversor empleado en los componentes `<b:selectOneMnu>`
  * [GenericEnumConverter](http://showcase.omnifaces.org/converters/GenericEnumConverter): conversor empleado para presentar los Enum del proyecto

* **Jsypt**: biblioteca para dar soporte la gestión de contraseñas hasheadas

  URL: [http://www.jasypt.org/](http://www.jasypt.org/)
