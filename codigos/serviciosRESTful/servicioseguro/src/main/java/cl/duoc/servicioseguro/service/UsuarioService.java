package cl.duoc.servicioseguro.service;

import java.util.Optional;

import cl.duoc.servicioseguro.model.Usuario;

public interface UsuarioService {
    Optional<Usuario> findByUsername(String username);
}

