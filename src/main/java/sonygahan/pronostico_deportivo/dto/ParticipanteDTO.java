package sonygahan.pronostico_deportivo.dto;

import java.util.List;

public class ParticipanteDTO {
    private Long id;
    private String nombre;
    private int puntaje;
    private List<PronosticoDTO> pronosticos;

    public ParticipanteDTO(Long id, String nombre, int puntaje, List<PronosticoDTO> pronosticos) {
        this.id = id;
        this.nombre = nombre;
        this.puntaje = puntaje;
        this.pronosticos = pronosticos;
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

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public List<PronosticoDTO> getPronosticos() {
        return pronosticos;
    }

    public void setPronosticos(List<PronosticoDTO> pronosticos) {
        this.pronosticos = pronosticos;
    }
}

