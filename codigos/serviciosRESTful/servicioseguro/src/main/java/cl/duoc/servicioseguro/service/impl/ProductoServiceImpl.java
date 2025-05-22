package cl.duoc.servicioseguro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.servicioseguro.model.Producto;
import cl.duoc.servicioseguro.repository.ProductoRepository;
import cl.duoc.servicioseguro.service.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;


    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del producto no es válido.");
        }
        return productoRepository.findById(id);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> actualizarProducto(Long id, Producto datosActualizados) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del producto no es válido.");
        }

        return productoRepository.findById(id).map(existente -> {
            if (datosActualizados.getNombre() != null) {
                existente.setNombre(datosActualizados.getNombre());
            }
            if (datosActualizados.getPrecio() != null && datosActualizados.getPrecio() > 0) {
                existente.setPrecio(datosActualizados.getPrecio());
            }
            return productoRepository.save(existente);
        });
    }

    @Override
    public boolean eliminarProducto(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del producto no es válido.");
        }
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }

    private void validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (producto.getPrecio() == null || producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a cero.");
        }
    }
}

