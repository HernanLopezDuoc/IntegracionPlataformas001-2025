package cl.duoc.ws.endpoint;


import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.*;

import cl.duoc.ws.model.ActualizarProductoRequest;
import cl.duoc.ws.model.ActualizarProductoResponse;
import cl.duoc.ws.model.BuscarProductosRequest;
import cl.duoc.ws.model.BuscarProductosResponse;
import cl.duoc.ws.model.CrearProductoRequest;
import cl.duoc.ws.model.CrearProductoResponse;
import cl.duoc.ws.model.EliminarProductoRequest;
import cl.duoc.ws.model.EliminarProductoResponse;
import cl.duoc.ws.model.ListarProductosRequest;
import cl.duoc.ws.model.ListarProductosResponse;
import cl.duoc.ws.model.ObtenerProductoPorIdRequest;
import cl.duoc.ws.model.ObtenerProductoPorIdResponse;
import cl.duoc.ws.model.ParcharProductoRequest;
import cl.duoc.ws.model.ParcharProductoResponse;
import cl.duoc.ws.model.Producto;

import java.math.BigDecimal;
import java.util.*;

@Endpoint
@Component
public class ProductoEndpoint {

    private static final String NAMESPACE_URI = "http://duoc.cl/ws";

    // Simula una base de datos en memoria
    private final Map<Integer, Producto> baseDatos = new HashMap<>();
    private int idSecuencia = 1;

    public ProductoEndpoint() {
        // Producto de ejemplo inicial
        Producto p = new Producto();
        p.setId(idSecuencia++);
        p.setNombre("Martillo");
        p.setDescripcion("Martillo de acero 16oz");
        p.setPrecio(new BigDecimal("5.50"));
        p.setStock(100);
        baseDatos.put(p.getId(), p);
    }

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
        // Producto nuevo = request.getProducto();
        // nuevo.setId(idSecuencia++);
        // baseDatos.put(nuevo.getId(), nuevo);

        System.out.println("Clase recibida: " + request.getClass());
        CrearProductoResponse response = new CrearProductoResponse();
        response.setIdGenerado(++idSecuencia);
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
