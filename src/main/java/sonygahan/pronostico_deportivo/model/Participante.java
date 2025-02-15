package sonygahan.pronostico_deportivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "participantes")
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del participante no puede estar vacío")
    @Column(nullable = false, unique = true)
    private String nombre;

    @Min(value = 0, message = "El puntaje no puede ser negativo")
    @Column(nullable = false)
    private int puntaje;

    @OneToMany(mappedBy = "participante", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pronostico> pronosticos;

    public Participante() {}

    public Participante(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
    }

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

    public int getPuntaje() {
        return puntaje;
    }

    public void sumarPuntos(int puntos) {
        this.puntaje += puntos;
    }

    public List<Pronostico> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(List<Pronostico> pronosticos) {
        this.pronosticos = pronosticos;
    }

    @Override
    public String toString() {
        return "Participante{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", puntaje=" + puntaje +
                '}';
    }
}


