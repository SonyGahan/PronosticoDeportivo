package sonygahan.pronostico_deportivo.dto;

public class PronosticoDTO {
    private Long id;
    private String resultadoPronosticado;
    private String participanteNombre;  // Se añade el nombre del participante
    private String equipo1Nombre;  // Se añade el nombre del primer equipo
    private String equipo2Nombre;  // Se añade el nombre del segundo equipo

    // 📌 Constructor
    public PronosticoDTO(Long id, String resultadoPronosticado, String participanteNombre, String equipo1Nombre, String equipo2Nombre) {
        this.id = id;
        this.resultadoPronosticado = resultadoPronosticado;
        this.participanteNombre = participanteNombre;
        this.equipo1Nombre = equipo1Nombre;
        this.equipo2Nombre = equipo2Nombre;
    }

    // 📌Getters and Setters
    public Long getId() {
        return id;
    }

    public String getResultadoPronosticado() {
        return resultadoPronosticado;
    }

    public String getParticipanteNombre() {
        return participanteNombre;
    }

    public String getEquipo1Nombre() {
        return equipo1Nombre;
    }

    public String getEquipo2Nombre() {
        return equipo2Nombre;
    }
}

