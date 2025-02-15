package sonygahan.pronostico_deportivo.service;

import org.springframework.stereotype.Service;
import sonygahan.pronostico_deportivo.dto.ParticipanteDTO;
import sonygahan.pronostico_deportivo.dto.PronosticoDTO;
import sonygahan.pronostico_deportivo.model.Participante;
import sonygahan.pronostico_deportivo.repository.ParticipanteRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public List<ParticipanteDTO> listarParticipantes() {
        return participanteRepository.findAll().stream()
                .map(participante -> new ParticipanteDTO(
                        participante.getId(),
                        participante.getNombre(),
                        participante.getPuntaje(),
                        participante.getPronosticos().stream()
                                .map(pronostico -> new PronosticoDTO(
                                        pronostico.getId(),
                                        null, // Podemos dejar el partido como null si no es necesario aquí
                                        pronostico.getResultadoPronosticado()
                                ))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public ParticipanteDTO crearParticipante(@Valid Participante participante) {
        Participante participanteGuardado = participanteRepository.save(participante);
        return new ParticipanteDTO(
                participanteGuardado.getId(),
                participanteGuardado.getNombre(),
                participanteGuardado.getPuntaje(),
                null
        );
    }

    public ParticipanteDTO actualizarParticipante(Long id, @Valid Participante participante) {
        participante.setId(id);
        Participante participanteActualizado = participanteRepository.save(participante);
        return new ParticipanteDTO(
                participanteActualizado.getId(),
                participanteActualizado.getNombre(),
                participanteActualizado.getPuntaje(),
                null
        );
    }

    public void eliminarParticipante(Long id) {
        participanteRepository.deleteById(id);
    }
}


