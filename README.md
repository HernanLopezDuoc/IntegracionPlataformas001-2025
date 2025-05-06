# 📘 Manual para la Creación de Servicios RESTful con Spring Boot

## 🧭 Índice

1. [📖 Introducción](#-introducción)  
2. [🛠️ Configuración del entorno](#-configuración-del-entorno)  
3. [🧱 Creación del proyecto Spring Boot](#-creación-del-proyecto-spring-boot)  
4. [⚙️ Configuración del proyecto](#-configuración-del-proyecto)  
5. [📦 Definición del modelo de datos](#-definición-del-modelo-de-datos)  
6. [📚 Creación del repositorio](#-creación-del-repositorio)  
7. [🧠 Implementación de la capa de servicio](#-implementación-de-la-capa-de-servicio)  
8. [🌐 Creación de controladores REST](#-creación-de-controladores-rest)  
9. [✅ Validación de datos](#-validación-de-datos)  
10. [🚨 Manejo de excepciones globales](#-manejo-de-excepciones-globales)  
11. [🔗 Consumo de APIs internas/externas](#-consumo-de-apis-internasexternas)  
12. [🧪 Pruebas automatizadas](#-pruebas-automatizadas)  
13. [🧰 Pruebas manuales](#-pruebas-manuales)  
14. [🔒 Manejo de seguridad (opcional)](#-manejo-de-seguridad-opcional)  
15. [📏 Buenas prácticas y convenciones](#-buenas-prácticas-y-convenciones)  
16. [📎 Anexos](#-anexos)  

---
## 📖 Introducción
Este manual tiene como propósito guiar a los estudiantes de la asignatura **Integración de Plataformas** de la carrera de **Ingeniería Informática** de **DUOC UC** en el aprendizaje y desarrollo de servicios web RESTful utilizando **Spring Boot**, un framework moderno y ampliamente utilizado en la industria para construir aplicaciones backend robustas y escalables.
### 🎯 Objetivo del manual
Brindar una guía práctica y estructurada que permita a los alumnos:
-   Comprender los fundamentos de las APIs RESTful.
-   Aprender a crear y consumir servicios web con Spring Boot.
-   Desarrollar buenas prácticas de arquitectura, validación, pruebas y seguridad.
-   Adquirir herramientas y habilidades técnicas necesarias para proyectos reales de integración de sistemas.
### 📌 Alcance
Este manual cubre el proceso completo desde la configuración del entorno de desarrollo hasta la creación, documentación y prueba de servicios RESTful. Se enfoca en la construcción de APIs backend utilizando **Java**, **Spring Boot** y herramientas comunes como **VSCode**, **Postman** y **Maven**, con ejemplos orientados a la práctica académica.
### 🧑‍💻 Audiencia objetivo
Este documento está dirigido principalmente a estudiantes de pregrado que cursan la asignatura **Integración de Plataformas** en DUOC UC, y que tienen conocimientos básicos de programación orientada a objetos y fundamentos de bases de datos relacionales.

---
## 🛠️ Configuración del entorno
Antes de comenzar a desarrollar servicios RESTful con Spring Boot, es fundamental preparar el entorno de desarrollo. Esta sección describe paso a paso cómo instalar y configurar las herramientas necesarias para trabajar de manera eficiente y sin errores desde el principio.
### ☕ Instalación de Java JDK
Spring Boot requiere Java para poder compilar y ejecutar los proyectos. Se recomienda instalar la **versión 17 de Java JDK**, que es compatible y estable con la mayoría de las herramientas modernas.
**Pasos:**
 1.  Ir al sitio oficial de [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) o usar [OpenJDK](https://adoptium.net).
 2. Descargar el instalador para tu sistema operativo (Windows, macOS o Linux).
 3. Seguir el proceso de instalación.
 4. Verificar la instalación con el comando:   
    `java -version` 
    Debe mostrar una versión igual o superior a Java 17.

### 🧩 Configuración del sistema (variables de entorno)
Una vez instalado Java, es importante configurar las variables de entorno para que el sistema pueda reconocer los comandos `java` y `javac`.
**En Windows:**
 1. Ir a **Propiedades del sistema > Variables de entorno**.
 2. En “Variables del sistema”, buscar `Path` y agregar la ruta donde está instalado el JDK (por ejemplo: `C:\Program Files\Java\jdk-17\bin`).
 3. Crear una nueva variable llamada `JAVA_HOME` con la ruta de instalación del JDK.

**Verificación:**

    echo %JAVA_HOME%   (en Windows)

### 💻 Instalación de Visual Studio Code (VSCode)
VSCode es un editor de código ligero y extensible, ideal para estudiantes. Se utilizará como entorno de desarrollo principal.
**Pasos:**

 1. Descargar desde [https://code.visualstudio.com/](https://code.visualstudio.com/)
 2. Instalar como cualquier otra aplicación.
 3. Verificar que funcione correctamente al abrirlo.


### 🌱 Instalación de Spring Boot Extension Pack
Este paquete de extensiones facilita el trabajo con proyectos Spring en VSCode.
**Incluye extensiones como:**

 - Spring Boot Tools
 - Java Dependency Viewer
 - Spring Initializr
 - Java Test Runner

**Instalación:**

 1. Abrir VSCode.
 2. Ir a la pestaña de extensiones (ícono de cuadritos).
 3. Buscar **"Spring Boot Extension Pack"** e instalar.
 4. Reiniciar VSCode si es necesario.

---
## 🧱 Creación del proyecto Spring Boot
En esta sección aprenderás a crear un nuevo proyecto Spring Boot utilizando **Spring Initializr**, una herramienta oficial que permite generar proyectos con la configuración básica necesaria. Se trabajará directamente desde **Visual Studio Code**, aprovechando las extensiones instaladas previamente.

### 🧰 Uso de Spring Initializr (desde VSCode)

**Spring Initializr** es un generador de proyectos que permite definir fácilmente las dependencias y la estructura base del proyecto.

**Pasos para generar un proyecto desde VSCode:**

 1. Abrir **Visual Studio Code**.
 2. Presionar `Ctrl+Shift+P` (o `Cmd+Shift+P` en macOS) para abrir la paleta de comandos.
 3. Buscar y seleccionar: `Spring Initializr: Generate a Maven Project`.
 4. Completar los siguientes datos:
    -   **Language:** Java
    -   **Group:** `cl.duoc.integracion`
    -   **Artifact:** `nombre-del-proyecto`
    -   **Name:** `nombre-del-proyecto`
    -   **Packaging:** Jar
    -   **Java version:** 17
 5. Seleccionar las dependencias mínimas necesarias, por ejemplo:
    -   Spring Web
    -   Spring Data JPA
    -   H2 Database o MySQL (según preferencia)
 6. Elegir una carpeta destino para guardar el proyecto y abrirlo en VSCode.

### 🧱 Estructura básica del proyecto
Una vez creado el proyecto, se generará una estructura de carpetas estándar que podrás adaptar según las buenas prácticas. La estructura sugerida es:

        src/
     └── main/
         ├── java/
         │    └── cl/duoc/integracion/
         │         ├── controller/      ← Controladores REST
         │         ├── service/         ← Interfaces de lógica de negocio
         │         │    └── impl/       ← Implementaciones concretas del servicio
         │         ├── repository/      ← Interfaces de acceso a datos
         │         ├── model/           ← Entidades del dominio (JPA)
         │         ├── dto/             ← Objetos de transferencia de datos
         │         └── utils/           ← Clases utilitarias (opcional)
         └── resources/
              ├── application.properties o application.yml
              └── static/, templates/ (si aplica para vistas o archivos estáticos)

Cada paquete tiene un propósito claro y está diseñado para mantener un código modular, limpio y fácil de mantener.

---

## ⚙️ Configuración del proyecto
Una vez creado el proyecto, es importante realizar algunas configuraciones esenciales para que la aplicación funcione correctamente. Esto incluye definir propiedades de conexión, establecer el puerto del servidor, y asegurar que las dependencias estén correctamente declaradas en el archivo `pom.xml`.

### 📄 Configuración de `application.properties` o `application.yml`

Este archivo se encuentra en la carpeta `src/main/resources/` y permite personalizar el comportamiento de la aplicación. Puedes elegir entre `.properties` o `.yml`; ambos cumplen la misma función. A continuación, se muestra un ejemplo básico en formato `.properties`:

    # Puerto del servidor
    server.port=8080
    
    # Configuración de la base de datos H2 (para pruebas)
    spring.datasource.url=jdbc:h2:mem:testdb
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=
    
    # JPA (Hibernate)
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

**Nota:** Si usas una base de datos como MySQL, deberás ajustar la URL, el driver y el dialecto.

### 🧬 Gestión de dependencias con Maven

El archivo `pom.xml` es el corazón de la gestión de dependencias en un proyecto Maven. Aquí se declaran los módulos necesarios para el funcionamiento del proyecto. Asegúrate de incluir al menos las siguientes dependencias:

    <dependencies>
        <!-- Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    
        <!-- JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
    
        <!-- Base de datos H2 (puedes cambiar por MySQL si deseas) -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
    
        <!-- Pruebas -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

Después de agregar dependencias, guarda el archivo y permite que Maven actualice el proyecto (VSCode lo hará automáticamente o puedes forzarlo con `Ctrl+Shift+P` → `Java: Clean Java Language Server Workspace`).

Con esta configuración lista, el proyecto ya está preparado para definir el modelo de datos y comenzar a desarrollar funcionalidades REST.

---
## 📦 Definición del modelo de datos
El modelo de datos representa las **entidades del dominio** de la aplicación, y se implementa mediante clases Java anotadas con **JPA (Java Persistence API)**. Esta sección te enseña cómo definir esas clases para que puedan ser almacenadas y gestionadas desde una base de datos relacional.

### 🧱 Creación de entidades con JPA

Las entidades son clases que representan tablas en la base de datos. Para definir una entidad básica, se utilizan anotaciones como `@Entity`, `@Id` y `@GeneratedValue`.

**Ejemplo:**

package cl.duoc.integracion.model;

import jakarta.persistence.*;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Double precio;
    private Integer stock;

    // Getters y Setters
}

**Anotaciones clave:**
-   `@Entity`: Indica que la clase es una entidad.
-   `@Id`: Identifica el campo clave primaria.
-   `@GeneratedValue`: Define cómo se genera el ID (por ejemplo, auto-incremental).
-   `@Column`: (opcional) Permite personalizar columnas (nombre, longitud, etc.).

### 🔗 Relacionamientos entre entidades

En sistemas reales, es común que las entidades estén relacionadas. JPA permite modelar estas relaciones de manera declarativa:

#### `@OneToMany` y `@ManyToOne`

**Ejemplo: una categoría con muchos productos:**

    @Entity
    public class Categoria {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
    
        @OneToMany(mappedBy = "categoria")
        private List<Producto> productos;
    }
    
    @Entity
    public class Producto {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String nombre;
    
        @ManyToOne
        @JoinColumn(name = "categoria_id")
        private Categoria categoria;
    }

#### `@OneToOne` y `@ManyToMany`

También puedes modelar relaciones uno a uno (por ejemplo, un usuario con un perfil único) o muchos a muchos (como estudiantes y cursos). Estas relaciones se configuran con anotaciones adicionales como `@JoinColumn`, `@JoinTable`, etc.

### 🛠 Recomendaciones

-   Usa nombres significativos para las clases y atributos.
-   No incluyas lógica de negocio dentro de las entidades.
-   Siempre incluye un constructor vacío y métodos getter/setter.
-   Si trabajas con fechas, usa `LocalDate` o `LocalDateTime` en lugar de `Date`.

Una vez definido el modelo de datos, el siguiente paso es crear las interfaces que permiten acceder a la base de datos utilizando **Spring Data JPA**.

---

## 📚 Creación del repositorio
En Spring Boot, los repositorios son interfaces que se encargan de acceder a la base de datos. Gracias a **Spring Data JPA**, no es necesario escribir código SQL o implementar métodos manualmente para operaciones comunes como guardar, buscar, actualizar o eliminar registros.

### 🧩 Interfaces que extienden JpaRepository o CrudRepository

Spring Boot proporciona dos interfaces base para trabajar con persistencia:

-   `CrudRepository<T, ID>`: incluye métodos básicos de CRUD (`save`, `findById`, `delete`, etc.).
-   `JpaRepository<T, ID>`: extiende `CrudRepository` y añade métodos adicionales como `findAll(Sort sort)`, `paging`, etc.
    
**Recomendación:** usar `JpaRepository`, ya que ofrece más funcionalidades.


### 🧪 Ejemplo básico de un repositorio

Supongamos que ya tienes la entidad `Producto` definida:
package cl.duoc.integracion.repository;

    import cl.duoc.integracion.model.Producto;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    
    @Repository
    public interface ProductoRepository extends JpaRepository<Producto, Long> {
        // Puedes agregar métodos personalizados si lo necesitas
        List<Producto> findByNombreContaining(String nombre);
    }


### 🔍 Métodos personalizados usando convenciones

Spring Data permite definir consultas automáticas con solo nombrar los métodos correctamente:

    List<Producto> findByPrecioGreaterThan(Double precio);
    Producto findByNombre(String nombre);

Estos métodos generan automáticamente las consultas SQL necesarias en base al nombre del método.

### 📌 Recomendaciones

-   Siempre anotar los repositorios con `@Repository` (aunque Spring los detecta automáticamente, es buena práctica).
-   Usa interfaces por entidad para mantener la organización (`ProductoRepository`, `CategoriaRepository`, etc.).
-   Evita incluir lógica de negocio aquí. Esa debe ir en la capa de servicio.

Con los repositorios definidos, ya puedes acceder a los datos desde la lógica de negocio, que abordaremos en la siguiente sección.

---

## 🧠 Implementación de la capa de servicio

La **capa de servicio** es responsable de encapsular la lógica de negocio de la aplicación. Actúa como intermediaria entre los controladores (que manejan las solicitudes del cliente) y los repositorios (que gestionan el acceso a la base de datos).

Esta separación permite mantener una arquitectura limpia, flexible y fácil de mantener.

### 🧩 Estructura recomendada
La estructura de paquetes puede ser la siguiente:

    service/
     ├── ProductoService.java         ← Interfaz
     └── impl/
         └── ProductoServiceImpl.java ← Implementación


### 📄 Interfaz del servicio
Define los métodos que ofrecerá tu servicio. Esto permite mantener el código desacoplado y facilita las pruebas.

    package cl.duoc.integracion.service;
    
    import cl.duoc.integracion.model.Producto;
    
    import java.util.List;
    import java.util.Optional;
    
    public interface ProductoService {
        List<Producto> listarProductos();
        Optional<Producto> obtenerProductoPorId(Long id);
        Producto crearProducto(Producto producto);
        Producto actualizarProducto(Long id, Producto producto);
        void eliminarProducto(Long id);
    }


### ⚙️ Implementación de la interfaz

Aquí se implementa la lógica real del servicio, normalmente utilizando el repositorio correspondiente.



    package cl.duoc.integracion.service.impl;
    
    import cl.duoc.integracion.model.Producto;
    import cl.duoc.integracion.repository.ProductoRepository;
    import cl.duoc.integracion.service.ProductoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    
    import java.util.List;
    import java.util.Optional;
    
    @Service
    public class ProductoServiceImpl implements ProductoService {
    
        @Autowired
        private ProductoRepository productoRepository;
    
        @Override
        public List<Producto> listarProductos() {
            return productoRepository.findAll();
        }
    
        @Override
        public Optional<Producto> obtenerProductoPorId(Long id) {
            return productoRepository.findById(id);
        }
    
        @Override
        public Producto crearProducto(Producto producto) {
            return productoRepository.save(producto);
        }
    
        @Override
        public Producto actualizarProducto(Long id, Producto producto) {
            producto.setId(id);
            return productoRepository.save(producto);
        }
    
        @Override
        public void eliminarProducto(Long id) {
            productoRepository.deleteById(id);
        }
    }


### 🧼 Buenas prácticas

-   Inyecta dependencias con `@Autowired` o constructor (mejor opción para testeo).
-   Valida las entradas y salidas (puede combinarse con la capa de validación).
-   No expongas directamente los métodos del repositorio desde el controlador.




---

## 🌐 Creación de controladores REST
Los **controladores REST** son los encargados de recibir las solicitudes HTTP de los clientes (por ejemplo, desde Postman o una app frontend), procesarlas y retornar una respuesta adecuada. En Spring Boot, estos se definen como clases anotadas con `@RestController`.

### 📁 Ubicación recomendada

Los controladores deben estar ubicados en el paquete:

    cl.duoc.integracion.controller

### 🔧 Anotaciones clave

-   `@RestController`: Indica que la clase manejará peticiones REST y que los métodos retornarán JSON por defecto.
-   `@RequestMapping`: Define la ruta base para todos los métodos del controlador.
-   `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: Mapean métodos HTTP específicos.
-   `@PathVariable`: Extrae variables de la URL.
-   `@RequestBody`: Captura el cuerpo JSON enviado por el cliente.

### 📄 Ejemplo de controlador básico

package cl.duoc.integracion.controller;

    import cl.duoc.integracion.model.Producto;
    import cl.duoc.integracion.service.ProductoService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    
    @RestController
    @RequestMapping("/api/productos")
    public class ProductoController {
    
        @Autowired
        private ProductoService productoService;
    
        @GetMapping
        public List<Producto> listarTodos() {
            return productoService.listarProductos();
        }
    
        @GetMapping("/{id}")
        public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
            return productoService.obtenerProductoPorId(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
    
        @PostMapping
        public Producto crear(@RequestBody Producto producto) {
            return productoService.crearProducto(producto);
        }
    
        @PutMapping("/{id}")
        public ResponseEntity<Producto> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
            return ResponseEntity.ok(productoService.actualizarProducto(id, producto));
        }
    
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Long id) {
            productoService.eliminarProducto(id);
            return ResponseEntity.noContent().build();
        }
    }


### ⚠️ Recomendaciones

-   Siempre usar `ResponseEntity` para tener más control sobre el código HTTP devuelto.
-   Mantén las rutas limpias, usando nombres en plural (`/productos`, `/categorias`).
-   Usa prefijos como `/api` para distinguir las rutas públicas.
-   Aplica validaciones y manejo de excepciones en los próximos pasos.

Con los controladores creados, ya tienes una API RESTful funcional capaz de recibir y responder solicitudes. En la siguiente sección, se configurará más a fondo el archivo `application.properties` y se profundizará en el uso de dependencias necesarias.




---
## ✅ Validación de datos
Validar los datos que recibe tu aplicación es esencial para evitar errores, inconsistencias o ataques. Spring Boot, junto con **Bean Validation (JSR-380)** e Hibernate Validator, permite implementar validaciones de forma declarativa usando anotaciones.

### 📦 Dependencia necesaria

Si usaste Spring Initializr con la dependencia **Spring Web**, probablemente ya esté incluida. De lo contrario, asegúrate de tener en tu `pom.xml`:

    <dependency>
        <groupId>jakarta.validation</groupId>
        <artifactId>jakarta.validation-api</artifactId>
    </dependency>
    <dependency>
        <groupId>org.hibernate.validator</groupId>
        <artifactId>hibernate-validator</artifactId>
    </dependency>

### 🧩 Uso de anotaciones de validación

Se aplican directamente en los atributos de los modelos o DTOs. Ejemplo en la clase `Producto`:

    import jakarta.validation.constraints.*;
    
    public class ProductoDTO {
    
        @NotBlank(message = "El nombre no puede estar vacío")
        private String nombre;
    
        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor que cero")
        private Double precio;
    
        @NotNull
        @Min(value = 0, message = "El stock no puede ser negativo")
        private Integer stock;
    
        // Getters y setters
    }


### 📩 Validación en el controlador

Para que las validaciones se activen al recibir una petición, usa `@Valid` junto con `@RequestBody`:


    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody ProductoDTO productoDto) {
        // lógica de conversión y guardado
    }


### 🚨 Manejo de errores de validación

Cuando los datos no son válidos, Spring devolverá automáticamente un error 400 (Bad Request). Para personalizar los mensajes de error, puedes usar un manejador global:



    @RestControllerAdvice
    public class GlobalExceptionHandler {
    
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
            Map<String, String> errores = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }
    }


### 🔐 Buenas prácticas

-   Utiliza DTOs para separar las entidades del modelo de entrada/salida.
-   Personaliza los mensajes para que sean comprensibles por el usuario.
-   Valida tanto en el backend como en el frontend (si existe uno).
 
Con esto, tu API puede asegurar que solo datos válidos ingresen al sistema. En la siguiente sección verás cómo manejar de forma global otros tipos de errores y excepciones personalizadas.

---

## 🚨 Manejo de excepciones globales
En una API profesional es esencial **capturar errores de forma centralizada** para entregar respuestas claras, coherentes y con códigos HTTP adecuados. Spring Boot facilita esto mediante la anotación `@RestControllerAdvice`.


### 🧩 ¿Qué es un manejador global de excepciones?

Es una clase dedicada a capturar y procesar excepciones lanzadas desde cualquier parte del controlador o servicio, y retornar una respuesta personalizada al cliente.

### 🛠 Ejemplo básico de manejador global


    package cl.duoc.integracion.exception;
    
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.MethodArgumentNotValidException;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.HashMap;
    import java.util.Map;
    
    @RestControllerAdvice
    public class GlobalExceptionHandler {
    
        // Errores de validación
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
            Map<String, String> errores = new HashMap<>();
            ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return ResponseEntity.badRequest().body(errores);
        }
    
        // Excepciones generales
        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGeneralException(Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocurrió un error inesperado: " + ex.getMessage());
        }
    }


### ❗ Excepciones personalizadas

Puedes definir tus propias clases de excepción para representar errores específicos del negocio:

    public class RecursoNoEncontradoException extends RuntimeException {
        public RecursoNoEncontradoException(String mensaje) {
            super(mensaje);
        }
    }

Y luego capturarlas en el manejador:

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> handleNotFound(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


### 📦 Estructura de respuesta sugerida (opcional)

Puedes definir un formato estándar para los errores:

    public class ErrorResponse {
        private String mensaje;
        private String detalle;
        private LocalDateTime timestamp;
        // Getters y setters
    }
Esto permite a los clientes de tu API manejar los errores de forma más predecible.


### ✅ Beneficios del manejo global de errores

-   Todas las respuestas de error tienen un **formato consistente**.
-   Evitas duplicar lógica de manejo de errores en cada controlador.
-   Mejoras la **experiencia del consumidor de la API**.


Con esta estructura, tu API ya está lista para manejar errores de forma clara y profesional. En la próxima sección aprenderás a **consumir APIs internas o externas**, una capacidad muy útil en sistemas integrados.

---
## 🔗 Consumo de APIs internas/externas

Una de las capacidades más importantes en aplicaciones modernas es la posibilidad de **consumir servicios externos** (de terceros o internos). Spring Boot ofrece distintas herramientas para lograrlo, siendo las más comunes `RestTemplate` y `WebClient`.

### 🔧 ¿Cuándo usar consumo de APIs?
-   Para **integrar servicios de terceros**, como servicios de clima, pagos, mapas, etc.
-   Para comunicar **módulos internos** distribuidos en una arquitectura de microservicios.
-   Para acceder a **APIs RESTful** desarrolladas por otras áreas o instituciones.

### 🧰 Herramientas para el consumo

#### 1. **RestTemplate** (sincrónico)

Es la forma tradicional y más simple de consumir APIs REST en Spring Boot. Funciona de forma **bloqueante**.

**Ejemplo:**

    import org.springframework.web.client.RestTemplate;
    import org.springframework.http.ResponseEntity;
    
    @RestController
    @RequestMapping("/api/clima")
    public class ClimaController {
    
        private final RestTemplate restTemplate = new RestTemplate();
    
        @GetMapping
        public String obtenerClima() {
            String url = "https://api.example.com/weather";
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getBody();
        }
    }

📌 _Nota:_ Si necesitas enviar datos en POST o manejar cabeceras, puedes usar `exchange()` o `postForEntity()`.

#### 2. **WebClient** (reactivo y no bloqueante)

Es la alternativa moderna basada en programación reactiva. Ideal para sistemas con muchas llamadas concurrentes.


    import org.springframework.web.reactive.function.client.WebClient;
    import reactor.core.publisher.Mono;
    
    @Service
    public class ClimaService {
    
        private final WebClient webClient = WebClient.create();
    
        public Mono<String> obtenerClima() {
            return webClient.get()
                    .uri("https://api.example.com/weather")
                    .retrieve()
                    .bodyToMono(String.class);
        }
    }

⚠️ _Para usar WebClient, agrega la dependencia de `spring-boot-starter-webflux`._

### 🔐 Consumo con autenticación

Muchas APIs externas requieren **tokens**, **API keys** o **cabeceras personalizadas**.

**Ejemplo con RestTemplate y headers:**

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", "Bearer TU_TOKEN");
    HttpEntity<?> entity = new HttpEntity<>(headers);
    
    ResponseEntity<String> response = restTemplate.exchange(
        "https://api.segura.com/data", HttpMethod.GET, entity, String.class);


### 🧪 Pruebas con APIs públicas (ejemplos educativos)

Puedes practicar con APIs abiertas como:
-   https://jsonplaceholder.typicode.com/
-   https://api.coindesk.com/v1/bpi/currentprice.json
-   [https://pokeapi.co/](https://pokeapi.co/)

### 🛠 Buenas prácticas
-   Centraliza URLs externas y tokens en `application.properties`.
-   Usa DTOs para mapear las respuestas externas.
-   Implementa manejo de errores en caso de fallas de red o respuestas no esperadas.




---

## 🧪 Pruebas automatizadas
Las pruebas automatizadas son una parte crítica del desarrollo moderno, ya que permiten detectar errores rápidamente, validar comportamientos esperados y facilitar cambios sin romper funcionalidades existentes. En Spring Boot se pueden realizar dos tipos principales de pruebas: **unitarias** e **integración**.

### 🧩 Herramientas incluidas por defecto

Si utilizaste la dependencia `spring-boot-starter-test` al crear el proyecto, ya cuentas con:
-   **JUnit 5**: framework de pruebas.
-   **Spring Boot Test**: integración con el contexto de Spring.
-   **AssertJ**: aserciones enriquecidas.
-   **Mockito**: para crear objetos simulados (mocks).

### 🧪 Pruebas unitarias (con JUnit y Mockito)

Estas pruebas validan **componentes aislados**, como servicios o funciones específicas, sin depender de bases de datos ni controladores reales.

**Ejemplo: prueba unitaria del servicio `ProductoServiceImpl`:**

    @SpringBootTest
    @ExtendWith(MockitoExtension.class)
    public class ProductoServiceTest {
    
        @Mock
        private ProductoRepository productoRepository;
    
        @InjectMocks
        private ProductoServiceImpl productoService;
    
        @Test
        void debeGuardarProducto() {
            Producto producto = new Producto();
            producto.setNombre("Teclado");
            producto.setPrecio(15000.0);
    
            Mockito.when(productoRepository.save(producto)).thenReturn(producto);
    
            Producto resultado = productoService.crearProducto(producto);
    
            Assertions.assertEquals("Teclado", resultado.getNombre());
        }
    }


### 🧬 Pruebas de integración (con Spring Boot Test)

Estas pruebas cargan el **contexto de Spring completo** y prueban el flujo completo entre controladores, servicios y repositorios. Ideal para simular el comportamiento real de la aplicación.

**Ejemplo: prueba con `MockMvc` de un controlador:**

    @SpringBootTest
    @AutoConfigureMockMvc
    public class ProductoControllerTest {
    
        @Autowired
        private MockMvc mockMvc;
    
        @Test
        void debeRetornarListaProductos() throws Exception {
            mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        }
    }


### 🧰 Recomendaciones
-   **Nombre de métodos claros:** qué se prueba y qué se espera.
-   **Evita acceso real a la base de datos** en pruebas unitarias.
-   **Agrupa pruebas por capas**: `service`, `controller`, etc.
-   Usa `@DataJpaTest` si solo deseas probar repositorios JPA.


🧪 Estructura sugerida para pruebas

    src/
     └── test/
          └── java/cl/duoc/integracion/
               ├── controller/
               ├── service/
               └── repository/


---

## 🧰 Pruebas manuales
Las **pruebas manuales** permiten verificar el comportamiento de los endpoints REST de forma directa, enviando solicitudes HTTP y observando las respuestas. Son especialmente útiles durante el desarrollo o para validar funcionalidades rápidamente antes de escribir pruebas automatizadas.

#### 🧪 **Postman** (GUI)

Postman es una herramienta gráfica muy usada para probar APIs REST. Permite enviar solicitudes HTTP, configurar cabeceras, cuerpos, autenticaciones y ver respuestas detalladas.

**Pasos básicos:**

1.  Descargar desde [https://www.postman.com/](https://www.postman.com/)
2.  Crear una nueva colección o petición.
3.  Ingresar la URL del endpoint, por ejemplo:   
    `GET http://localhost:8080/api/productos` 
4.  Elegir el método (GET, POST, etc.)
5.  Para métodos POST/PUT, ir a la pestaña **Body** → seleccionar `raw` y `JSON`.

**Ejemplo de cuerpo para POST:**

    {
        "nombre": "Mouse inalámbrico",
        "precio": 12000,
        "stock":  10
    } 

6.  Hacer clic en **Send** y revisar la respuesta (código, contenido, tiempo, etc.).

#### 💻 **curl** (línea de comandos)

`curl` es una herramienta de terminal para enviar solicitudes HTTP. Ideal para entornos de consola o scripts.

**Ejemplo GET:**

    curl -X GET http://localhost:8080/api/productos

**Ejemplo POST con JSON:**

    curl -X POST http://localhost:8080/api/productos \
     -H "Content-Type: application/json" \
     -d '{"nombre":"Teclado mecánico","precio":25000,"stock":15}'

### 📝 Qué debes validar al hacer pruebas

-   ✅ Que los endpoints responden con el **código HTTP correcto** (`200 OK`, `201 Created`, `404 Not Found`, etc.).
-   ✅ Que los datos enviados y recibidos sean **correctos y completos**.
-   ✅ Que las validaciones funcionen (ej: no permitir precios negativos).
-   ✅ Que los errores se devuelvan con un mensaje **claro y estructurado**.
-   ✅ Que el comportamiento sea consistente en distintos escenarios.

### 🛠 Buenas prácticas

-   Documenta las pruebas que realizas (Postman permite guardarlas en colecciones).
-   Usa variables y entornos en Postman para facilitar pruebas locales y de despliegue.
-   Compara manualmente los resultados con lo esperado antes de automatizar.

Las pruebas manuales te permiten validar rápidamente los endpoints y su comportamiento antes de implementar pruebas más robustas o integrarlas en un pipeline automatizado.

---

## 🔒 Manejo de seguridad
## 🔒 Manejo de seguridad (opcional)

En muchos proyectos reales es necesario **restringir el acceso a ciertos endpoints** de la API. Spring Boot ofrece integración nativa con **Spring Security**, un módulo poderoso para implementar autenticación, autorización y control de acceso.

Esta sección proporciona una introducción básica, ideal para asegurar tu API sin complicaciones excesivas.

### 🔐 ¿Qué protege Spring Security?

-   Controla **quién puede acceder** a ciertos endpoints (autenticación).
-   Define **qué puede hacer** cada usuario (autorización).
-   Agrega filtros para manejar tokens, sesiones, cabeceras de seguridad, etc.

### 📦 Dependencia necesaria

Si no la agregaste al crear el proyecto, inclúyela en `pom.xml`:

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

### 🧪 Seguridad por defecto (autenticación básica)

Al agregar la dependencia, Spring Security **activa automáticamente una autenticación básica** con un usuario y contraseña generados en consola:

    Using generated security password: 2f3a9bcd-1234-5678

Puedes probarlo en Postman, seleccionando **Basic Auth** y usando el usuario `user` con la contraseña mostrada.


### 🔧 Configuración personalizada con Java (Spring Security 6+)

Para controlar qué rutas están protegidas, puedes crear una clase de configuración:


    @Configuration
    @EnableWebSecurity
    public class SeguridadConfig {
    
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/publico/**").permitAll()
                    .requestMatchers("/api/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
    
            return http.build();
        }
    }


### Uso de JWT (JSON Web Tokens) _(más avanzado)_

En aplicaciones modernas, especialmente con frontend desacoplado (Angular, React), se utiliza **autenticación por tokens (JWT)**. Esto permite:

-   Login mediante endpoint (`/auth/login`)
-   Envío de token en cada petición mediante `Authorization: Bearer <token>`
-   Validación del token con filtros personalizados
    

⚠️ _Este enfoque requiere una configuración adicional (filtros, utilidades JWT, login controller), y se recomienda en etapas más avanzadas del curso o proyecto._

### ✅ Buenas prácticas

-   **Nunca expongas endpoints sensibles sin protección.**
-   Usa **roles** para definir qué puede hacer cada tipo de usuario.
-   **Desactiva CSRF** solo si no usas sesiones (ej. APIs REST).
-   Documenta los endpoints públicos y protegidos en tu README o Swagger.


Con esta configuración básica, tu API queda protegida frente a accesos no autorizados y preparada para integrarse con mecanismos de autenticación más avanzados.







---

## 📏 Buenas prácticas y convenciones
Aplicar buenas prácticas en el desarrollo de una API RESTful no solo mejora su calidad técnica, sino que también facilita el trabajo en equipo, el mantenimiento a largo plazo y la experiencia de los consumidores de la API.

A continuación se presentan recomendaciones organizadas por temática:

### 📂 Organización del proyecto

-   Usa una **estructura de paquetes clara y modular**, por capas:

    controller/
    service/
    service/impl/
    repository/
    model/
    dto/
    utils/
- No mezcles responsabilidades: cada clase debe tener un solo propósito (principio de responsabilidad única - SRP).

### 🌐 Convenciones para endpoints REST

-   Usa **sustantivos en plural** para los recursos:
    -   Correcto: `/api/productos`
    -   Incorrecto: `/api/obtenerProducto`
-   Utiliza los métodos HTTP adecuados:
    -   `GET /productos`: listar productos
    -   `GET /productos/{id}`: obtener uno
    -   `POST /productos`: crear
    -   `PUT /productos/{id}`: actualizar
    -   `DELETE /productos/{id}`: eliminar
-   Evita verbos en las URLs. El verbo lo define el método HTTP.
    -   `GET /getproductos/{id}`: Incorrecto

### 🧾 Estructura de respuestas

-   Usa `ResponseEntity` para controlar:
    -   Código HTTP
    -   Cabeceras
    -   Cuerpo de respuesta
-   Asegura una **estructura consistente** de errores y datos:

`
{
  "mensaje": "Producto no encontrado",
  "codigo": 404,
  "timestamp": "2025-04-29T12:00:00"
}`

### 💬 Convenciones de nombres
-   Clases: `ProductoController`, `ProductoService`, `ProductoRepository`
-   Variables: `producto`, `listaProductos`, `productoDto`
-   DTOs: termina en `Dto` o `Request`/`Response` si aplica

### 🛠 Versionado de la API
-   Usa prefijos de versión para tus rutas:  
    Ejemplo: `/api/v1/productos`
-   Esto facilita hacer cambios en el futuro sin romper la compatibilidad con clientes antiguos.

### 🔐 Seguridad y validación

-   Aplica validaciones en los DTOs con anotaciones como `@NotNull`, `@Size`, `@Email`.
-   Nunca expongas datos sensibles.
-   Protege endpoints con Spring Security, especialmente los de escritura (POST/PUT/DELETE).

### 📄 Documentación
-   Usa Swagger/OpenAPI para documentar tus endpoints (puedes usar SpringDoc).
-   Agrega comentarios Javadoc en servicios y modelos para mayor claridad.

### 🧪 Pruebas

-   Prueba tanto servicios como controladores.
-   Automatiza las pruebas clave con JUnit.
-   Usa Postman para validar manualmente en etapas tempranas.

Aplicar estas convenciones desde el principio facilitará enormemente el trabajo en equipo, la escalabilidad de tu código y la profesionalización de tus desarrollos.


---

## 📎 Anexos

Esta sección contiene recursos adicionales que te ayudarán a reforzar los conocimientos adquiridos, resolver dudas comunes y seguir profundizando en el desarrollo de APIs RESTful con Spring Boot.

### 📚 Recursos oficiales y documentación

-   **Documentación de Spring Boot:**  
    [https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
-   **Spring Guides (tutoriales oficiales):**  
    [https://spring.io/guides](https://spring.io/guides)
-   **Documentación JPA (Hibernate):**  
    https://hibernate.org/orm/documentation/


### 🧰 Herramientas recomendadas

-   **Postman:** [https://www.postman.com/](https://www.postman.com/)  
    Para pruebas manuales de endpoints REST.
-   **Insomnia:** [https://insomnia.rest/](https://insomnia.rest/)  
    Alternativa ligera a Postman.
-   **Swagger/OpenAPI con SpringDoc:**  
    [https://springdoc.org/](https://springdoc.org/)  
    Genera documentación interactiva para tu API.

### 🧪 APIs públicas para practicar

-   JSONPlaceholder (datos falsos):  
    https://jsonplaceholder.typicode.com/
-   PokeAPI (información de Pokémon):  
    [https://pokeapi.co/](https://pokeapi.co/)
-   API pública de criptomonedas:  
    https://api.coindesk.com/v1/bpi/currentprice.json

### 📦 Repositorio de ejemplo (recomendado)

Puedes revisar o clonar un proyecto base en GitHub con estructura completa para practicar.

**Ejemplo:**  
[https://github.com/spring-projects/spring-petclinic](https://github.com/spring-projects/spring-petclinic)  
(_Proyecto real con buenas prácticas en Spring Boot._)

### 🎓 Consejos para estudiantes DUOC UC

-   Realiza pruebas con servicios externos reales.
-   Practica el versionado en Git y sube tu proyecto a GitHub.
-   Trabaja en equipos y realiza revisiones cruzadas de código (peer reviews).
