package cl.duoc.miapi.dto;

public class UsuarioRequestDTO {
    
    private String nombre;
    private String direccion;

    public UsuarioRequestDTO(){}

    public UsuarioRequestDTO(String nombre, String direccion){
        this.nombre = nombre;
        this.direccion = direccion;
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
