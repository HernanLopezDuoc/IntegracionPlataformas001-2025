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
