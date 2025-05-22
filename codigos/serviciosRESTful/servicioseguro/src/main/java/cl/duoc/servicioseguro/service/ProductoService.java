package cl.duoc.servicioseguro.service;

import java.util.List;
import java.util.Optional;

import cl.duoc.servicioseguro.model.Producto;

public interface ProductoService {

    List<Producto> obtenerTodos();
    Optional<Producto> obtenerPorId(Long id);
    Producto crearProducto(Producto producto);
    Optional<Producto> actualizarProducto(Long id, Producto producto);
    boolean eliminarProducto(Long id);
    
}

