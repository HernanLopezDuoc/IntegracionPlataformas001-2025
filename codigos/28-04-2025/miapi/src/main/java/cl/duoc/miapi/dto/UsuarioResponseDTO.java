package cl.duoc.miapi.dto;

public class UsuarioResponseDTO {
    
    private long id;
    private String nombre;
    private String direccion;

    public UsuarioResponseDTO(){}

    public UsuarioResponseDTO(long id, String nombre, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDirecion(){
        return this.direccion;
    }

    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
}
