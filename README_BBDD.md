# 🧰 Tutorial: Crear proyecto Spring Boot con conexión a H2


## 1. Crear un un proyecto spring boot 

Sigue el siguiente [tutorial base en GitHub](https://github.com/HernanLoGo/duoc-integracion-plataformas/blob/main/README.md) para generar un proyecto inicial.

Agrega las siguientes dependencias en tu archivo `pom.xml`:
* Spring Web
* Spring Data JPA
* H2 Database

## 2. Estructura del proyecto

```plaintext
src/
└── main/
    ├── java/
    │   └── cl/duoc/integracion/
    │       ├── controller/      ← Controladores REST
    │       ├── service/         ← Interfaces de lógica de negocio
    │       │   └── impl/        ← Implementaciones del servicio
    │       ├── repository/      ← Repositorios JPA
    │       ├── model/           ← Entidades JPA
    │       ├── dto/             ← Objetos de transferencia de datos (opcional)
    │       └── utils/           ← Clases utilitarias (opcional)
    └── resources/
        ├── application.properties
        ├── schema.sql
        ├── data.sql
        ├── static/              ← Archivos estáticos (HTML, CSS, JS)
        └── templates/           ← Plantillas Thymeleaf (si aplica)
```



## 3. Configurar `application.properties`

Ubica este archivo en `src/main/resources/application.properties` y agrega lo siguiente:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

| Propiedad | Descripción |
|--|--|
| `spring.datasource.url` | Indica que se usará una base de datos en memoria llamada `testdb`. Esta base se borra cada vez que se reinicia la aplicación. |
| `spring.datasource.driverClassName` | Indica a Spring Boot qué clase de driver JDBC usar para conectarse. |
| `spring.datasource.username`, `spring.datasource.password` | H2 usa por defecto el usuario `sa` y no requiere contraseña. Estos se puede customizar |
| `org.hibernate.dialect.H2Dialect` | Dialecto de Hibernate específico para H2. Cada motor de base de datos usa una especificación de SQL por lo que le indica cual usar. |
| `spring.jpa.show-sql` | Muestra las sentencias SQL generadas en consola (opcional, útil para debug) |
| `spring.jpa.hibernate.ddl-auto` | permite que Spring cree la base de datos a partir de las entidades JPA, opciones comunes: `none`, `create`, `update`, `create-drop` |
| `spring.h2.console.enabled` | activar consola web de H2 para consultar visualmente los datos, habilita la consola web para consultar la base: `http://localhost:8080/h2-console` |
| `spring.h2.console.path` | permite cambiar la ruta de la consola h2. Por defecto es `/h2-console` |


## 4. Crear Entidad JPA

Creamos la clases Persona junto con sus atributos y mediante diversas anotaciones nos permite crear una tabla en base de datos sin necesidad de crear sentencias SQL explicitas

```java
import jakarta.persistence.*;

@Entity  // Esta clase será una entidad JPA (tabla en la BD)
@Table(name = "persona") // Opcional: nombre explícito de la tabla
public class Persona {

    @Id  // Indica que este campo es la clave primaria (PRIMARY KEY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // La estrategia IDENTITY hace que el ID se autogenere (auto_increment)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    // Define el nombre de la columna, si puede ser nulo y su longitud
    private String nombre;

    @Column(name = "email", unique = true)
    // Esta columna será única en la tabla
    private String email;

    // Constructores

    public Persona() {
    }

    public Persona(Long id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    // Constructores, Getters y Setters ...
    
}
```

| Anotación | Obligatoria | Descripción |
|-----------|-------------|-------------|
| `@Entity` | ✅ | Declara la clase como una entidad persistente. |
| `@Table(name = "persona")` | ❌ | Nombre personalizado de la tabla en la BD. |
| `@Id` | ✅ | Define la clave primaria. |
| `@GeneratedValue` | ❌ | Autogenera el valor del ID. |
| `@Column` | ❌ | Personaliza la columna (nombre, longitud, unicidad, etc.). |
| `@OneToMany` / `@ManyToOne` | ❌ | Define relaciones entre entidades. |
| `@JoinColumn` | ❌ | Define la columna de clave foránea. |



## 5. Repositorio JPA

Un repositorio JPA es una **interfaz** que extiende una interfaz de Spring Data como `JpaRepository`, `CrudRepository` o `PagingAndSortingRepository`.

Con esto:
* NO necesitas escribir código SQL ni implementar nada.
* Spring lo implementa automáticamente en tiempo de ejecución.

En una arquitectura Spring Boot + JPA, el **repositorio** es la capa que se encarga de interactuar con la base de datos para realizar operaciones como:

-   Guardar (`save`)
-   Buscar (`findById`, `findAll`)
-   Eliminar (`delete`)
-   Consultas personalizadas (`findByNombre`, etc.)


Ejemplo código repositorio


```java
package cl.duoc.integracion.repository;

import cl.duoc.integracion.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    Persona findByEmail(String email);
    List<Persona> findByNombreContaining(String texto);
}
```

* `@Repository`, Anotación opcional que marca la interfaz como un componente de repositorio (Spring la detecta aunque no la pongas, porque JpaRepository ya está anotado internamente).
* `<Persona, Long>`
	* `Persona`: es la **entidad** que manejará este repositorio.
	* `Long`: es el tipo de la clave primaria (`id` de `Persona`).

## 6. Poblar BBDD al iniciar servicio
Crea el archivo `data.sql` en `src/main/resources` con:

```sql
INSERT INTO persona (nombre, email) VALUES ('Juan Pérez', 'juan@correo.com');
INSERT INTO persona (nombre, email) VALUES ('Ana Gómez', 'ana@correo.com');
```
Asegúrate de que las tablas ya estén creadas (por Hibernate o `schema.sql`).


## 7. Acceder a la consola H2

1.  Levanta la aplicación.
2.  Abre en el navegador: `http://localhost:8080/h2-console`
3.  Configura:
    -   JDBC URL: `jdbc:h2:mem:testdb`
    -   Usuario: `sa`
    -   Contraseña: _(vacía)_

Haz clic en **Connect** para consultar la base de datos.


