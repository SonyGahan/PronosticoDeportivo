package sonygahan.pronostico_deportivo.service;

import org.springframework.stereotype.Service;
import sonygahan.pronostico_deportivo.dto.ParticipanteDTO;
import sonygahan.pronostico_deportivo.dto.PronosticoDTO;
import sonygahan.pronostico_deportivo.model.Participante;
import sonygahan.pronostico_deportivo.repository.ParticipanteRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    // 📌 Constructor
    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    // 🔹 Lista participantes con DTOs, manejando posibles valores nulos
    public List<ParticipanteDTO> listarParticipantes() {
        return participanteRepository.findAll().stream()
                .map(participante -> new ParticipanteDTO(
                        participante.getId(),
                        participante.getNombre(),
                        participante.getPuntaje(),
                        participante.getPronosticos() != null ? participante.getPronosticos().stream()
                                .map(pronostico -> new PronosticoDTO(
                                        pronostico.getId(),
                                        pronostico.getParticipante().getNombre(),
                                        pronostico.getPartido().getEquipo1().getNombre(),
                                        pronostico.getPartido().getEquipo2().getNombre(),
                                        pronostico.getResultadoPronosticado()
                                ))
                                .collect(Collectors.toList()) : List.of() // ✅ Evita `NullPointerException`
                ))
                .collect(Collectors.toList());
    }

    // 🔹 Crea un nuevo participante con puntaje inicializado
    public ParticipanteDTO crearParticipante(@Valid Participante participante) {
        if (participante.getPuntaje() == 0) { // ✅ Ya es 0 por defecto, pero nos aseguramos
            participante.setPuntaje(0);
        }

        Participante participanteGuardado = participanteRepository.save(participante);

        return new ParticipanteDTO(
                participanteGuardado.getId(),
                participanteGuardado.getNombre(),
                participanteGuardado.getPuntaje(),
                List.of() // ❌ No devolvemos pronósticos en la creación
        );
    }

    // 🔹 Actualiza un participante existente con validación
    public ParticipanteDTO actualizarParticipante(Long id, @Valid Participante participante) {
        Optional<Participante> participanteExistente = participanteRepository.findById(id);

        if (participanteExistente.isPresent()) {
            Participante participanteActualizado = participanteExistente.get();
            participanteActualizado.setNombre(participante.getNombre());
            participanteActualizado.setPuntaje(participante.getPuntaje());

            participanteRepository.save(participanteActualizado);

            return new ParticipanteDTO(
                    participanteActualizado.getId(),
                    participanteActualizado.getNombre(),
                    participanteActualizado.getPuntaje(),
                    List.of() // ❌ No devolvemos pronósticos aquí
            );
        } else {
            throw new IllegalArgumentException("No se encontró el participante con ID: " + id);
        }
    }

    // 🔹 Elimina un participante con verificación previa
    public void eliminarParticipante(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el participante con ID: " + id);
        }
        participanteRepository.deleteById(id);
    }
}



