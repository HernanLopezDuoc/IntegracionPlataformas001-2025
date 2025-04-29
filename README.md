# 🚀 Guía de instalación: Spring Boot en Visual Studio Code

Bienvenido a esta guía para configurar spring boot con VSCode

## 📋 Requisitos previos

Antes de comenzar, asegúrate de tener instalado:

#### Java JDK 17 o superior
* Descarga desde: [Java.net](https://jdk.java.net/)
* Verifica que esté instalado ejecutando en la terminal:
```bash
java -version
```
#### Visual Studio Code (VSCode)
* Descarga desde: https://code.visualstudio.com/
#### Extensiones necesarias en VSCode
* Abre VSCode, ve a la pestaña de extensiones (Ctrl + Shift + X) e instala:
    + Spring Boot Extension Pack


## 🛠️ Creación del primer proyecto Spring Boot

1. Abre Visual Studio Code.
2. Abre la paleta de comandos (Ctrl + Shift + P).
3. Escribe y selecciona:
```bash
Spring Initializr: Create a Maven Project
```
4. Completa los siguientes pasos:
    * Version: 3.4.5 (o versión que muestre por defecto)
    * Lenguaje: Java
    * Grupo: cl.duoc
    * Artefacto: miapi
    * Packaging: jar
    * Java version: 17 (o superior)
    * Dependencias: Spring Web (y otras que necesites)
5. Elige una carpeta donde guardar el proyecto.
6. Abre el proyecto recién creado cuando VSCode lo sugiera.

## 🧪 Ejecutar la aplicación
### Opción 1: Desde VSCode
* Abre el archivo principal:
src/main/java/cl/duoc/miapi/MiapiApplication.java
* Haz clic en el botón "play" (▶) que aparece arriba del método main.

### Opción 2: Desde la terminal
* Abre la terminal en el proyecto y ejecuta:
mvnw spring-boot:run

## 🌐 Probar que la aplicación funciona
Abre tu navegador y ve a:
http://localhost:8080
Si ves una página de error 404, ¡eso es normal! Significa que el servidor está funcionando.

## 🏗️ Estructura de un proyecto Spring Boot
```bash
cl.duoc.miapi/
├── controller/        // Controladores que reciben peticiones HTTP
│   └── UsuarioController.java
├── service/           // Interfaces que definen la lógica de negocio
│   └── UsuarioService.java
├── service/impl/      // Implementaciones de los servicios
│   └── UsuarioServiceImpl.java
├── repository/        // Interfaces para acceso a base de datos
│   └── UsuarioRepository.java
├── model/             // Entidades que representan las tablas de la base de datos
│   └── Usuario.java
├── utils/             // Clases utilitarias (conversores, helpers, etc.)
│   └── MapperUtils.java
└── MiapiApplication.java   // Clase principal que arranca la aplicación
```

## 📦 Explicación de cada carpeta

| Carpeta  | Proposito  |
| ------------ | ------------ |
|  controller/ | Recibe las peticiones HTTP y devuelve respuestas.  |
| service/  | Define qué operaciones se pueden hacer (interfaz).  |
|  service/impl/ | Implementa cómo se hacen esas operaciones.  |
| repository/  | Accede a la base de datos usando JPA.  |
| model/  | Representa las entidades de la base de datos.  |
| utils/  | Funciones de ayuda, como mapeos entre objetos.  |
| Clase principal  | Arranca la aplicación Spring Boot (@SpringBootApplication).  |

