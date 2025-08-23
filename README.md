# ICESI TRADE

- [Link Enunciado](https://docs.google.com/document/d/1Fg4nFzLBtSaOK7Rmn8kRuIUHuO65des1K24Dt66UWgI/edit?tab=t.0#heading=h.r2ge05vq56dz)
- [Link Entrega 1](https://docs.google.com/document/d/1tK_muqBr7ln06HvAxWgOfgS1exKQYK5S/edit?tab=t.0)
- [Link Entrega 2](https://docs.google.com/file/d/1Py-Sxh_E5yLJ0SNuNIP1N5Qo8y5lQbc0/edit?filetype=msword)
- [Link Entrega 3](https://docs.google.com/document/d/13w-40MIR2gQ3eCzkCI9nrP74LiEFxvMe/edit?tab=t.0)

Este proyecto es una aplicación backend desarrollada en Spring Boot que se conecta a una base de datos PostgreSQL. La integración se realiza a través de Docker y Docker Compose. Sigue esta guía para configurar y ejecutar el proyecto en tu máquina, ya sea con Linux o Windows.

- **Aplicación en producción aquí 🚀:  http://10.147.17.127:8080/IcesiTrade**
- **Pruebas de CRUD basico en Postman aquí: https://app.getpostman.com/join-team?invite_code=40db17d13fb29f1f87beb13c2ab48ed4c84415c478ca1f81c6d62117bce6a0e9&target_code=baea0ee117802cf0addd9b2fcd5d1a97** 
- **Documentación de api : http://localhost:8080/swagger-ui/index.html#/**

- **Credenciales de acceso cómo Administrador:**

    - **Usuario: alice@example.com**
    
    - **Contraseña: 1212**

## Requisitos Previos

Asegúrate de tener instaladas las siguientes herramientas:
- **Docker y Docker Compose:**
    - [Docker Desktop](https://www.docker.com/products/docker-desktop) para Windows o macOS
    - Docker Engine y docker-compose en Linux
- **Git:** Para clonar el repositorio (opcional si descargas el ZIP)  
  [Git SCM](https://git-scm.com/)
- *(Opcional)* **Java JDK 17 y Maven:** Solo si deseas compilar y ejecutar el proyecto de forma local sin Docker.

## Configuración y Setup

### 1. Clonar el Repositorio

Abre una terminal y clona el repositorio:
```bash
git clone https://github.com/tu_usuario/tu_repositorio.git
```
*Reemplaza `tu_usuario` y `tu_repositorio` con los datos correctos.*

### 2. Acceder al Directorio del Proyecto

```bash
cd nombre-del-proyecto
```

### 3. Verificar la Configuración

Revisa que:
- **Dockerfile** esté configurado para compilar la aplicación usando JDK 17.
- **docker-compose.yml** configure dos servicios:
    - La **app** se construye desde el Dockerfile y mapea el puerto interno 8080 al 8081 del host.
    - El servicio **postgres** utiliza la imagen `postgres:latest`, mapea el puerto interno 5432 al 5436 del host y establece las variables de entorno necesarias.

## Ejecución del Proyecto

### Para Windows y Linux

1. **Abrir la Terminal:**
    - **Windows:** Abre PowerShell o CMD.
    - **Linux:** Abre tu terminal favorita.

2. **Construir y Ejecutar los Contenedores:**

   En la raíz del proyecto, ejecuta:
   ```bash
   docker-compose up --build
   ```
   Esto compilará la imagen de la aplicación y levantará los contenedores tanto de la aplicación como de PostgreSQL.

3. **Acceder a la Aplicación:**

   Una vez iniciado, la aplicación estará disponible en:  
   [http://localhost:8081](http://localhost:8081)

## Verificación del Funcionamiento

- **Base de Datos PostgreSQL:**  
  El contenedor `postgres-taller` muestra en los logs que la base de datos ya existe (si es que ya fue inicializada previamente) y que está lista para aceptar conexiones.  
  Para conectarte manualmente, utiliza:
    - **Host:** `localhost`
    - **Puerto:** `5436`
    - **Usuario:** `myuser`
    - **Contraseña:** `mysecretpassword`
    - **Base de datos:** `mydatabase`

- **Aplicación Spring Boot:**  
  Revisa los logs del contenedor `app` para confirmar que:
    - La aplicación se inicia correctamente con Spring Boot y Tomcat.
    - Se establece la conexión a la base de datos mediante HikariCP.
    - No se reportan errores críticos durante la inicialización.

## Solución de Problemas

- **Puertos en Uso:**  
  Si aparece un error indicando que un puerto ya está en uso, revisa y ajusta los mapeos en el archivo `docker-compose.yml` (por ejemplo, cambia el puerto 8081 o 5436 a otro valor libre).

- **Errores de Compilación:**  
  Verifica que el Dockerfile esté usando la imagen correcta (`maven:3.8.5-openjdk-17`) y que el proyecto esté configurado para Java 17 en el pom.xml.

- **Conexión a la Base de Datos:**  
  Asegúrate de que la URL de conexión en `application.properties` (si está definida) coincida con la configurada en las variables de entorno del servicio `app` en docker-compose:
  ```properties
  spring.datasource.url=jdbc:postgresql://postgres:5432/mydatabase
  spring.datasource.username=myuser
  spring.datasource.password=mysecretpassword
  ```

## Notas Adicionales

- Para reconstruir los contenedores tras cambios en el código, ejecuta:
  ```bash
  docker-compose up --build
  ```
- Para detener los contenedores, presiona `Ctrl+C` en la terminal y luego ejecuta:
  ```bash
  docker-compose down
  ```
- Esta guía asume que usas Docker para ejecutar el proyecto. Si deseas ejecutar la aplicación localmente sin Docker, asegúrate de tener configurado Java 17 y Maven, y de ajustar la configuración de la base de datos en el archivo `application.properties`.

---
