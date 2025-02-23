package sonygahan.pronostico_deportivo.dto;

public class PartidoDTO {
    private Long id;
    private EquipoDTO equipo1;
    private EquipoDTO equipo2;
    private String resultado;

    // 📌 Constructor
    public PartidoDTO(Long id, EquipoDTO equipo1, EquipoDTO equipo2, String resultado) {
        this.id = id;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.resultado = resultado;
    }

    // 📌Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EquipoDTO getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(EquipoDTO equipo1) {
        this.equipo1 = equipo1;
    }

    public EquipoDTO getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(EquipoDTO equipo2) {
        this.equipo2 = equipo2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
}

