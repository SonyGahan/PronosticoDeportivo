package sonygahan.pronostico_deportivo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "pronosticos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"participante_id", "partido_id"})
})
public class Pronostico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "participante_id", nullable = false)
    @NotNull(message = "El participante no puede ser nulo")
    private Participante participante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partido_id", nullable = false)
    @NotNull(message = "El partido no puede ser nulo")
    @OnDelete(action = OnDeleteAction.NO_ACTION) // ❌ Evita que Hibernate elimine los pronósticos
    private Partido partido;

    @NotNull(message = "El resultado pronosticado no puede ser nulo")
    @Pattern(regexp = "[GPE]", message = "El resultado pronosticado debe ser G, P o E")
    @Column(nullable = false)
    private String resultadoPronosticado;

    public Pronostico() {}

    public Pronostico(Participante participante, Partido partido, String resultadoPronosticado) {
        if (participante == null || partido == null) {
            throw new IllegalArgumentException("El participante y el partido no pueden ser nulos");
        }
        if (!resultadoPronosticado.matches("[GPE]")) {
            throw new IllegalArgumentException("El resultado pronosticado debe ser G, P o E");
        }
        this.participante = participante;
        this.partido = partido;
        this.resultadoPronosticado = resultadoPronosticado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    public String getResultadoPronosticado() {
        return resultadoPronosticado;
    }

    public void setResultadoPronosticado(String resultadoPronosticado) {
        this.resultadoPronosticado = resultadoPronosticado;
    }

    @Override
    public String toString() {
        return "Pronostico{" +
                "id=" + id +
                ", participante=" + (participante != null ? participante.getNombre() : "null") +
                ", partido=" + (partido != null ? partido.getId() : "null") +
                ", resultadoPronosticado='" + resultadoPronosticado + '\'' +
                '}';
    }
}



