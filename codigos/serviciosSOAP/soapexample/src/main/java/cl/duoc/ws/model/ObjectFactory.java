//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2025.05.13 a las 01:58:49 AM CLT 
//


package cl.duoc.ws.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.duoc.ws.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ListarProductosResponse_QNAME = new QName("http://duoc.cl/ws", "ListarProductosResponse");
    private final static QName _BuscarProductosResponse_QNAME = new QName("http://duoc.cl/ws", "BuscarProductosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.duoc.ws.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListarProductosRequest }
     * 
     */
    public ListarProductosRequest createListarProductosRequest() {
        return new ListarProductosRequest();
    }

    /**
     * Create an instance of {@link ListarProductosResponse }
     * 
     */
    public ListarProductosResponse createListarProductosResponse() {
        return new ListarProductosResponse();
    }

    /**
     * Create an instance of {@link BuscarProductosRequest }
     * 
     */
    public BuscarProductosRequest createBuscarProductosRequest() {
        return new BuscarProductosRequest();
    }

    /**
     * Create an instance of {@link BuscarProductosResponse }
     * 
     */
    public BuscarProductosResponse createBuscarProductosResponse() {
        return new BuscarProductosResponse();
    }

    /**
     * Create an instance of {@link ObtenerProductoPorIdRequest }
     * 
     */
    public ObtenerProductoPorIdRequest createObtenerProductoPorIdRequest() {
        return new ObtenerProductoPorIdRequest();
    }

    /**
     * Create an instance of {@link ObtenerProductoPorIdResponse }
     * 
     */
    public ObtenerProductoPorIdResponse createObtenerProductoPorIdResponse() {
        return new ObtenerProductoPorIdResponse();
    }

    /**
     * Create an instance of {@link Producto }
     * 
     */
    public Producto createProducto() {
        return new Producto();
    }

    /**
     * Create an instance of {@link CrearProductoRequest }
     * 
     */
    public CrearProductoRequest createCrearProductoRequest() {
        return new CrearProductoRequest();
    }

    /**
     * Create an instance of {@link CrearProductoResponse }
     * 
     */
    public CrearProductoResponse createCrearProductoResponse() {
        return new CrearProductoResponse();
    }

    /**
     * Create an instance of {@link ActualizarProductoRequest }
     * 
     */
    public ActualizarProductoRequest createActualizarProductoRequest() {
        return new ActualizarProductoRequest();
    }

    /**
     * Create an instance of {@link ActualizarProductoResponse }
     * 
     */
    public ActualizarProductoResponse createActualizarProductoResponse() {
        return new ActualizarProductoResponse();
    }

    /**
     * Create an instance of {@link EliminarProductoRequest }
     * 
     */
    public EliminarProductoRequest createEliminarProductoRequest() {
        return new EliminarProductoRequest();
    }

    /**
     * Create an instance of {@link EliminarProductoResponse }
     * 
     */
    public EliminarProductoResponse createEliminarProductoResponse() {
        return new EliminarProductoResponse();
    }

    /**
     * Create an instance of {@link ParcharProductoRequest }
     * 
     */
    public ParcharProductoRequest createParcharProductoRequest() {
        return new ParcharProductoRequest();
    }

    /**
     * Create an instance of {@link ParcharProductoResponse }
     * 
     */
    public ParcharProductoResponse createParcharProductoResponse() {
        return new ParcharProductoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarProductosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ListarProductosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://duoc.cl/ws", name = "ListarProductosResponse")
    public JAXBElement<ListarProductosResponse> createListarProductosResponse(ListarProductosResponse value) {
        return new JAXBElement<ListarProductosResponse>(_ListarProductosResponse_QNAME, ListarProductosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscarProductosResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link BuscarProductosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://duoc.cl/ws", name = "BuscarProductosResponse")
    public JAXBElement<BuscarProductosResponse> createBuscarProductosResponse(BuscarProductosResponse value) {
        return new JAXBElement<BuscarProductosResponse>(_BuscarProductosResponse_QNAME, BuscarProductosResponse.class, null, value);
    }

}
