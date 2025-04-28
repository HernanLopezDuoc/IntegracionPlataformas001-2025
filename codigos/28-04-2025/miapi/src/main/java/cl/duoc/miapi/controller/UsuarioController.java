package cl.duoc.miapi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.miapi.dto.UsuarioRequestDTO;
import cl.duoc.miapi.dto.UsuarioResponseDTO;
import cl.duoc.miapi.model.Usuario;



@RestController
public class UsuarioController {
    
    private Map<Long, Usuario> data = new HashMap<>();
    private long countId = 0;

    @GetMapping("usuarios/{id}")
    public UsuarioResponseDTO obtenerPorId(@PathVariable long id) {
        Usuario user = data.get(id);
        UsuarioResponseDTO response = new UsuarioResponseDTO(user.getId(), user.getNombre(), user.getDirecion());

        return response;
    }

    @GetMapping("usuarios")
    public List<Usuario> obtenerTodos() {
        return new ArrayList<>(data.values());
    }

    @PostMapping("usuarios")
    public UsuarioResponseDTO postUsuario(@RequestBody UsuarioRequestDTO usuario) {
        
        Usuario user = new Usuario(++countId, usuario.getNombre(), usuario.getDirecion());
        data.put(countId, user);

        UsuarioResponseDTO response = new UsuarioResponseDTO(user.getId(), user.getNombre(), user.getDirecion());

        return response;
    }
    
    
}
