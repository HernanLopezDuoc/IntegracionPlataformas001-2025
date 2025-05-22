package cl.duoc.ws.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.endpoint.adapter.MarshallingMethodEndpointAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

    // Configura el servlet que manejará las peticiones SOAP
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(context);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    // Define el WSDL: cómo se verá al acceder vía navegador
    @Bean(name = "productos") // ← genera productos.wsdl
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema productosSchema) {
        DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
        wsdl.setPortTypeName("ProductoPort"); // ← nombre del puerto lógico
        wsdl.setLocationUri("/ws");           // ← URL base del servicio SOAP
        wsdl.setTargetNamespace("http://duoc.cl/ws"); // ← debe coincidir con tu XSD
        wsdl.setSchema(productosSchema);      // ← inyecta el esquema
        return wsdl;
    }

    // Carga el archivo productos.xsd desde resources/xsd
    @Bean
    public XsdSchema productosSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/productos.xsd"));
    }

    // @Bean
    // public Jaxb2Marshaller marshaller() {
    //     Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    //     //marshaller.setContextPath("cl.duoc.ws.model"); // ← Asegúrate de que este paquete es correcto
    //     marshaller.setPackagesToScan("cl.duoc.ws.model");
    //     return marshaller;
    // }
    
    // @Bean
    // public MarshallingMethodEndpointAdapter marshallingMethodEndpointAdapter(Jaxb2Marshaller marshaller) {
    //     return new MarshallingMethodEndpointAdapter(marshaller, marshaller);
    // }

}
