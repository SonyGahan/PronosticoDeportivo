package sonygahan.pronostico_deportivo.dto;

public class PronosticoDTO {
    private Long id;
    private PartidoDTO partido;
    private String resultadoPronosticado;

    public PronosticoDTO(Long id, PartidoDTO partido, String resultadoPronosticado) {
        this.id = id;
        this.partido = partido;
        this.resultadoPronosticado = resultadoPronosticado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PartidoDTO getPartido() {
        return partido;
    }

    public void setPartido(PartidoDTO partido) {
        this.partido = partido;
    }

    public String getResultadoPronosticado() {
        return resultadoPronosticado;
    }

    public void setResultadoPronosticado(String resultadoPronosticado) {
        this.resultadoPronosticado = resultadoPronosticado;
    }
}

