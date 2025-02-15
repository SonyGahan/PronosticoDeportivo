package sonygahan.pronostico_deportivo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    // Relación con los partidos donde el equipo es local
    @OneToMany(mappedBy = "equipo1", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partido> partidosComoLocal = new ArrayList<>();

    // Relación con los partidos donde el equipo es visitante
    @OneToMany(mappedBy = "equipo2", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partido> partidosComoVisitante = new ArrayList<>();


    //Constructores
    public Equipo() {} // Constructor vacío requerido por JPA

    public Equipo(String nombre, String descripcion) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede ser nulo o vacío");
        }
        this.nombre = nombre;
        this.descripcion = descripcion;
    }


    //Getters and Setters
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
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del equipo no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
