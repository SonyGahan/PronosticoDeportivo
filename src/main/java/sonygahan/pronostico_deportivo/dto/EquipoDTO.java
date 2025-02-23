package sonygahan.pronostico_deportivo.dto;

public class EquipoDTO {
    private Long id;
    private String nombre;

    // 📌 Constructor
    public EquipoDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // 📌Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
