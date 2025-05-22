package cl.duoc.miprimeraapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.miprimeraapi.model.Usuario;



@RestController
public class UsuarioController {
    
    private Map<Long, Usuario> data = new HashMap<>();
    private Long contadorId = 0L;

    @GetMapping("usuarios/{id}")
    public Usuario getUsuarios(@PathVariable Long id) {
        return data.get(id);
    }

    @PostMapping("usuarios")
    public Usuario postUsuarios(@RequestBody Usuario usuario) {
        usuario.setId(++contadorId);
        data.put(contadorId, usuario);
        
        return data.get(contadorId);
    }

    

}
