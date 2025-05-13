# 🧰 Tutorial: Crear un Web Service SOAP con Spring Boot

## 📁 1. **Estructura del Proyecto**

    src/
    ├── main/
    │   ├── java/
    │   │   └── cl/duoc/productossoap/       → Paquete de lógica del servicio
    │   │       ├── config/       → Configuración del WS
    │   │       ├── endpoint/     → Endpoints SOAP
    │   │       └── generated/    → Clases generadas por JAXB
    │   └── resources/            → productos.xsd


## 📄 2. **Archivo `productos.xsd`**

Define el contrato del servicio. Guárdalo en `src/main/resources/xsd/productos.xsd`.


    <?xml version="1.0" encoding="UTF-8"?>
    <xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
               targetNamespace="http://duoc.cl/ws"
               xmlns:tns="http://duoc.cl/ws"
               elementFormDefault="qualified">
    
      <xs:complexType name="Producto">
        <xs:sequence>
          <xs:element name="id" type="xs:int"/>
          <xs:element name="nombre" type="xs:string"/>
          <xs:element name="descripcion" type="xs:string"/>
          <xs:element name="precio" type="xs:decimal"/>
          <xs:element name="stock" type="xs:int"/>
        </xs:sequence>
      </xs:complexType>
    
      <xs:element name="CrearProductoRequest">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="producto" type="tns:Producto"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="CrearProductoResponse">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="idGenerado" type="xs:int"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ListarProductosRequest">
        <xs:complexType>
          <xs:sequence/>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ListarProductosResponse">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="productos" type="tns:Producto" maxOccurs="unbounded"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ObtenerProductoPorIdRequest">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="id" type="xs:int"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ObtenerProductoPorIdResponse">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="producto" type="tns:Producto" minOccurs="0"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ActualizarProductoRequest">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="producto" type="tns:Producto"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ActualizarProductoResponse">
        <xs:complexType>
          <xs:sequence/>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="EliminarProductoRequest">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="id" type="xs:int"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="EliminarProductoResponse">
        <xs:complexType>
          <xs:sequence/>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="BuscarProductosRequest">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="texto" type="xs:string"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="BuscarProductosResponse">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="productos" type="tns:Producto" maxOccurs="unbounded"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ParcharProductoRequest">
        <xs:complexType>
          <xs:sequence>
            <xs:element name="id" type="xs:int"/>
            <xs:element name="campo" type="xs:string"/>
            <xs:element name="valor" type="xs:string"/>
          </xs:sequence>
        </xs:complexType>
      </xs:element>
    
      <xs:element name="ParcharProductoResponse">
        <xs:complexType>
          <xs:sequence/>
        </xs:complexType>
      </xs:element>
    
    </xs:schema>


## ⚙️ 3. **Configura el `pom.xml`**

Agrega dependencias necesarias:


    <?xml version="1.0" encoding="UTF-8"?>
    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    
        <modelVersion>4.0.0</modelVersion>
    
        <parent>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-parent</artifactId>
            <version>3.4.5</version>
            <relativePath/>
        </parent>
    
        <groupId>cl.duoc</groupId>
        <artifactId>soapservice</artifactId>
        <version>0.0.1-SNAPSHOT</version>
        <name>soapservice</name>
        <description>Servicio SOAP con Jakarta XML Bind y Spring Boot 3</description>
    
        <properties>
    		<java.version>17</java.version>
    		<jaxb.runtime.noUnsafe>true</jaxb.runtime.noUnsafe>
    	</properties>
    
        <dependencies>
    
            <!-- Spring Web Services -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-web-services</artifactId>
            </dependency>
    
            <!-- Jakarta JAXB API (compatible con Spring Boot 3) -->
            <dependency>
                <groupId>jakarta.xml.bind</groupId>
                <artifactId>jakarta.xml.bind-api</artifactId>
                <version>3.0.1</version>
            </dependency>
    
            <!-- JAXB Runtime (implementación Glassfish para Jakarta) -->
            <dependency>
                <groupId>org.glassfish.jaxb</groupId>
                <artifactId>jaxb-runtime</artifactId>
                <version>4.0.3</version>
            </dependency>
    
            <!-- WSDL4J requerido para DefaultWsdl11Definition -->
            <dependency>
                <groupId>wsdl4j</groupId>
                <artifactId>wsdl4j</artifactId>
                <version>1.6.3</version>
            </dependency>
    
            <!-- Tests -->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-test</artifactId>
                <scope>test</scope>
            </dependency>
        </dependencies>
        <build>
            <plugins>
                
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.1</version>
                    <configuration>
                        <source>17</source>
                        <target>17</target>
                    </configuration>
                </plugin>
    
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
            </plugins>
        </build>
    </project>



## 🔧 4. Generar Clases JAXB

### 📝 Estructura de Archivos
1. Crea carpeta `jaxb-generator` y subcarpetas `src/main/resources` y `output`.    
2. Coloca `productos.xsd` dentro de `src/main/resources`.

    mkdir jaxb-generator
    cd jaxb-generator
    mkdir -p src/main/resources
    mkdir output

Coloca tu archivo `productos.xsd` dentro de `src/main/resources`.

### 📝  Crear el archivo `pom.xml`

Crea un `pom.xml` mínimo para descargar los JARs necesarios de JAXB:

    <project xmlns="http://maven.apache.org/POM/4.0.0"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
        <modelVersion>4.0.0</modelVersion>
        <groupId>cl.duoc</groupId>
        <artifactId>jaxb-generator</artifactId>
        <version>1.0-SNAPSHOT</version>
    
        <dependencies>
            <dependency>
                <groupId>org.glassfish.jaxb</groupId>
                <artifactId>jaxb-xjc</artifactId>
                <version>4.0.3</version>
            </dependency>
            <dependency>
                <groupId>org.glassfish.jaxb</groupId>
                <artifactId>jaxb-runtime</artifactId>
                <version>4.0.3</version>
            </dependency>
        </dependencies>
    </project>



### 🔄 Descargar dependencias

    mvn dependency:copy-dependencies 

Esto dejará los `.jar` necesarios en `target/dependency`.


### 🔄 Generar las clases del modelo

       java -cp "target/dependency/*" com.sun.tools.xjc.XJCFacade \
      -d output \
      -p cl.duoc.productossoap.generated \
      -extension \
      src/main/resources/productos.xsd


### ✅ Verificar las clases generadas

Las clases se generarán en:

    output/cl/duoc/productossoap/generated/


## 📡 5. Creación del Endpoint SOAP

Ejemplo de `ProductoEndpoint.java`:
package cl.duoc.productossoap.endpoint;

    import org.springframework.ws.server.endpoint.annotation.*;
    
    import cl.duoc.productossoap.generated.ActualizarProductoRequest;
    import cl.duoc.productossoap.generated.ActualizarProductoResponse;
    import cl.duoc.productossoap.generated.BuscarProductosRequest;
    import cl.duoc.productossoap.generated.BuscarProductosResponse;
    import cl.duoc.productossoap.generated.CrearProductoRequest;
    import cl.duoc.productossoap.generated.CrearProductoResponse;
    import cl.duoc.productossoap.generated.EliminarProductoRequest;
    import cl.duoc.productossoap.generated.EliminarProductoResponse;
    import cl.duoc.productossoap.generated.ListarProductosRequest;
    import cl.duoc.productossoap.generated.ListarProductosResponse;
    import cl.duoc.productossoap.generated.ObtenerProductoPorIdRequest;
    import cl.duoc.productossoap.generated.ObtenerProductoPorIdResponse;
    import cl.duoc.productossoap.generated.ParcharProductoRequest;
    import cl.duoc.productossoap.generated.ParcharProductoResponse;
    import cl.duoc.productossoap.generated.Producto;
    
    import org.springframework.stereotype.Component;
    
    import java.math.BigDecimal;
    import java.util.*;
    
    @Endpoint
    @Component
    public class ProductoEndpoint {
    
        private static final String NAMESPACE_URI = "http://duoc.cl/ws";
    
        private final Map<Integer, Producto> baseDatos = new HashMap<>();
        private int idSecuencia = 0;
    
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListarProductosRequest")
        @ResponsePayload
        public ListarProductosResponse listar(@RequestPayload ListarProductosRequest request) {
            ListarProductosResponse response = new ListarProductosResponse();
            response.getProductos().addAll(baseDatos.values());
            return response;
        }
    
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenerProductoPorIdRequest")
        @ResponsePayload
        public ObtenerProductoPorIdResponse obtener(@RequestPayload ObtenerProductoPorIdRequest request) {
            ObtenerProductoPorIdResponse response = new ObtenerProductoPorIdResponse();
            Producto producto = baseDatos.get(request.getId());
            if (producto != null) {
                response.setProducto(producto);
            }
            return response;
        }
    
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "BuscarProductosRequest")
        @ResponsePayload
        public BuscarProductosResponse buscar(@RequestPayload BuscarProductosRequest request) {
            BuscarProductosResponse response = new BuscarProductosResponse();
            for (Producto p : baseDatos.values()) {
                if (p.getNombre().toLowerCase().contains(request.getTexto().toLowerCase()) ||
                    p.getDescripcion().toLowerCase().contains(request.getTexto().toLowerCase())) {
                    response.getProductos().add(p);
                }
            }
            return response;
        }
    
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CrearProductoRequest")
        @ResponsePayload
        public CrearProductoResponse crear(@RequestPayload CrearProductoRequest request) {
            Producto nuevo = request.getProducto();
            idSecuencia = idSecuencia + 1;
            nuevo.setId(idSecuencia);
            baseDatos.put(nuevo.getId(), nuevo);
    
            CrearProductoResponse response = new CrearProductoResponse();
            response.setIdGenerado(idSecuencia);
            return response;
        }
    
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ActualizarProductoRequest")
        @ResponsePayload
        public ActualizarProductoResponse actualizar(@RequestPayload ActualizarProductoRequest request) {
            Producto producto = request.getProducto();
            if (baseDatos.containsKey(producto.getId())) {
                baseDatos.put(producto.getId(), producto);
            }
            return new ActualizarProductoResponse();
        }
    
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "EliminarProductoRequest")
        @ResponsePayload
        public EliminarProductoResponse eliminar(@RequestPayload EliminarProductoRequest request) {
            baseDatos.remove(request.getId());
            return new EliminarProductoResponse();
        }
    
        @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ParcharProductoRequest")
        @ResponsePayload
        public ParcharProductoResponse parchar(@RequestPayload ParcharProductoRequest request) {
            Producto producto = baseDatos.get(request.getId());
            if (producto != null) {
                String campo = request.getCampo();
                String valor = request.getValor();
    
                switch (campo) {
                    case "nombre" -> producto.setNombre(valor);
                    case "descripcion" -> producto.setDescripcion(valor);
                    case "precio" -> producto.setPrecio(new BigDecimal(valor));
                    case "stock" -> producto.setStock(Integer.parseInt(valor));
                    // O puedes manejar error si campo no existe
                }
            }
            return new ParcharProductoResponse();
        }
    }

## 🧩 6. Configuración del Web Service `WebServiceConfig.java`

    package cl.duoc.productossoap.config;
    
    import org.springframework.boot.web.servlet.ServletRegistrationBean;
    import org.springframework.context.ApplicationContext;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.core.io.ClassPathResource;
    import org.springframework.oxm.jaxb.Jaxb2Marshaller;
    import org.springframework.ws.config.annotation.EnableWs;
    import org.springframework.ws.transport.http.MessageDispatcherServlet;
    import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
    import org.springframework.xml.xsd.SimpleXsdSchema;
    import org.springframework.xml.xsd.XsdSchema;
    
    @EnableWs
    @Configuration
    public class WebServiceConfig {
    
        @Bean
        public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
            MessageDispatcherServlet servlet = new MessageDispatcherServlet();
            servlet.setApplicationContext(context);
            servlet.setTransformWsdlLocations(true);
            return new ServletRegistrationBean<>(servlet, "/ws/*");
        }
    
        @Bean(name = "productos")
        public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema productosSchema) {
            DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
            wsdl.setPortTypeName("ProductoPort");
            wsdl.setLocationUri("/ws");
            wsdl.setTargetNamespace("http://duoc.cl/ws");
            wsdl.setSchema(productosSchema);
            return wsdl;
        }
    
        @Bean
        public XsdSchema productosSchema() {
            return new SimpleXsdSchema(new ClassPathResource("productos.xsd"));
        }
    
        @Bean
        public Jaxb2Marshaller marshaller() {
            Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
            marshaller.setContextPath("cl.duoc.productossoap.generated");
            return marshaller;
        }
    
    }



## 🚀 7. Ejecutar y Probar el Servicio

### validamos el wsdl

    http://localhost:8080/ws/productos.wsdl

### configuraciones postman

    POST http://localhost:8080/ws
    Content-Type: text/xml
    SOAPAction: ""


### Crear Producto

       <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://duoc.cl/ws">
       <soapenv:Header/>
       <soapenv:Body>
          <ws:CrearProductoRequest>
             <ws:producto>
                <ws:nombre>Taladro</ws:nombre>
                <ws:descripcion>Taladro inalámbrico 20V</ws:descripcion>
                <ws:precio>89.99</ws:precio>
                <ws:stock>50</ws:stock>
             </ws:producto>
          </ws:CrearProductoRequest>
       </soapenv:Body>
    </soapenv:Envelope>

### Listar Productos

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://duoc.cl/ws">
       <soapenv:Header/>
       <soapenv:Body>
          <ws:ListarProductosRequest/>
       </soapenv:Body>
    </soapenv:Envelope>

### Buscar Productos

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://duoc.cl/ws">
       <soapenv:Header/>
       <soapenv:Body>
          <ws:BuscarProductosRequest>
             <ws:texto>taladro</ws:texto>
          </ws:BuscarProductosRequest>
       </soapenv:Body>
    </soapenv:Envelope>

### Obtener Producto por ID

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://duoc.cl/ws">
       <soapenv:Header/>
       <soapenv:Body>
          <ws:ObtenerProductoPorIdRequest>
             <ws:id>1</ws:id>
          </ws:ObtenerProductoPorIdRequest>
       </soapenv:Body>
    </soapenv:Envelope>

### Actualizar Producto

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://duoc.cl/ws">
       <soapenv:Header/>
       <soapenv:Body>
          <ws:ActualizarProductoRequest>
             <ws:producto>
                <ws:id>1</ws:id>
                <ws:nombre>Taladro PRO</ws:nombre>
                <ws:descripcion>Taladro PRO con batería extra</ws:descripcion>
                <ws:precio>129.90</ws:precio>
                <ws:stock>30</ws:stock>
             </ws:producto>
          </ws:ActualizarProductoRequest>
       </soapenv:Body>
    </soapenv:Envelope>


### Eliminar Producto

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://duoc.cl/ws">
       <soapenv:Header/>
       <soapenv:Body>
          <ws:EliminarProductoRequest>
             <ws:id>1</ws:id>
          </ws:EliminarProductoRequest>
       </soapenv:Body>
    </soapenv:Envelope>

### Parchar Producto

    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://duoc.cl/ws">
       <soapenv:Header/>
       <soapenv:Body>
          <ws:ParcharProductoRequest>
             <ws:id>1</ws:id>
             <ws:campo>stock</ws:campo>
             <ws:valor>99</ws:valor>
          </ws:ParcharProductoRequest>
       </soapenv:Body>
    </soapenv:Envelope>




















