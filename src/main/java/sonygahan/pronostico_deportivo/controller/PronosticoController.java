package sonygahan.pronostico_deportivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sonygahan.pronostico_deportivo.dto.PronosticoDTO;
import sonygahan.pronostico_deportivo.model.Participante;
import sonygahan.pronostico_deportivo.model.Partido;
import sonygahan.pronostico_deportivo.model.Pronostico;
import sonygahan.pronostico_deportivo.repository.ParticipanteRepository;
import sonygahan.pronostico_deportivo.repository.PartidoRepository;
import sonygahan.pronostico_deportivo.repository.PronosticoRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pronosticos")
public class PronosticoController {

    private final PronosticoRepository pronosticoRepository;
    private final ParticipanteRepository participanteRepository;
    private final PartidoRepository partidoRepository;

    // 📌 Constructor
    public PronosticoController(
            PronosticoRepository pronosticoRepository,
            ParticipanteRepository participanteRepository,
            PartidoRepository partidoRepository) {
        this.pronosticoRepository = pronosticoRepository;
        this.participanteRepository = participanteRepository;
        this.partidoRepository = partidoRepository;
    }

    // 🔹 Lista todos los Pronósticos
    @GetMapping
    public List<PronosticoDTO> listarPronosticos() {
        return pronosticoRepository.findAll().stream()
                .map(pronostico -> new PronosticoDTO(
                        pronostico.getId(),
                        pronostico.getResultadoPronosticado(),
                        pronostico.getParticipante().getNombre(), // ✅ Pasamos el nombre del participante
                        pronostico.getPartido().getEquipo1().getNombre(), // ✅ Nombre equipo 1
                        pronostico.getPartido().getEquipo2().getNombre() // ✅ Nombre equipo 2
                ))
                .collect(Collectors.toList());
    }

    // 🔹 Crea un nuevo Pronóstico
    @PostMapping
    public ResponseEntity<PronosticoDTO> crearPronostico(@RequestBody Map<String, Object> request) {
        Long participanteId = ((Number) request.get("participanteId")).longValue();
        Long partidoId = ((Number) request.get("partidoId")).longValue();
        String resultadoPronosticado = (String) request.get("resultadoPronosticado");

        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Participante no encontrado"));
        Partido partido = partidoRepository.findById(partidoId)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Partido no encontrado"));

        Pronostico pronostico = new Pronostico(participante, partido, resultadoPronosticado);
        Pronostico pronosticoGuardado = pronosticoRepository.save(pronostico);

        PronosticoDTO dto = new PronosticoDTO(
                pronosticoGuardado.getId(),
                pronosticoGuardado.getResultadoPronosticado(),
                participante.getNombre(), // ✅ Pasamos el nombre del participante
                partido.getEquipo1().getNombre(), // ✅ Nombre equipo 1
                partido.getEquipo2().getNombre() // ✅ Nombre equipo 2
        );

        return ResponseEntity.ok(dto);
    }

    // 🔹 Elimina un Pronóstico según el ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPronostico(@PathVariable Long id) {
        if (!pronosticoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pronosticoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}




