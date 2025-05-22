package cl.duoc.servicioseguro.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.servicioseguro.model.Usuario;
import cl.duoc.servicioseguro.repository.UsuarioRepository;
import cl.duoc.servicioseguro.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return repository.findByUsername(username);
    }

}
