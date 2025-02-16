package sonygahan.pronostico_deportivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidos", indexes = {
        @Index(name = "idx_resultado_partido", columnList = "resultado")
})
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // ❌ Se quitó REMOVE
    @JoinColumn(name = "equipo1_id", nullable = false)
    @NotNull(message = "El equipo1 no puede ser nulo")
    private Equipo equipo1;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}) // ❌ Se quitó REMOVE
    @JoinColumn(name = "equipo2_id", nullable = false)
    @NotNull(message = "El equipo2 no puede ser nulo")
    private Equipo equipo2;

    @NotNull(message = "El resultado no puede ser nulo")
    @Pattern(regexp = "[GPE]", message = "El resultado debe ser G, P o E")
    @Column(nullable = false)
    private String resultado;

    @OneToMany(mappedBy = "partido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pronostico> pronosticos = new ArrayList<>();

    public Partido() {}

    public Partido(Equipo equipo1, Equipo equipo2, String resultado) {
        if (equipo1 == null || equipo2 == null) {
            throw new IllegalArgumentException("Los equipos no pueden ser nulos");
        }
        if (equipo1.equals(equipo2)) {
            throw new IllegalArgumentException("Los equipos no pueden ser iguales");
        }
        if (!resultado.matches("[GPE]")) {
            throw new IllegalArgumentException("El resultado debe ser G, P o E");
        }
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.resultado = resultado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Equipo getEquipo1() { return equipo1; }
    public void setEquipo1(Equipo equipo1) { this.equipo1 = equipo1; }

    public Equipo getEquipo2() { return equipo2; }
    public void setEquipo2(Equipo equipo2) { this.equipo2 = equipo2; }

    public String getResultado() { return resultado; }
    public void setResultado(String resultado) { this.resultado = resultado; }

    public List<Pronostico> getPronosticos() { return pronosticos; }
    public void setPronosticos(List<Pronostico> pronosticos) { this.pronosticos = pronosticos; }

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", equipo1=" + (equipo1 != null ? equipo1.getNombre() : "null") +
                ", equipo2=" + (equipo2 != null ? equipo2.getNombre() : "null") +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}




