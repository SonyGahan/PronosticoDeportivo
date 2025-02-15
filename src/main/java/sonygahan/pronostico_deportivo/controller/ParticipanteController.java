package sonygahan.pronostico_deportivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sonygahan.pronostico_deportivo.dto.ParticipanteDTO;
import sonygahan.pronostico_deportivo.model.Participante;
import sonygahan.pronostico_deportivo.service.ParticipanteService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/participantes")
@Validated
public class ParticipanteController {

    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @GetMapping
    public List<ParticipanteDTO> listarParticipantes() {
        return participanteService.listarParticipantes();
    }

    @PostMapping
    public ResponseEntity<ParticipanteDTO> crearParticipante(@Valid @RequestBody Participante participante) {
        return ResponseEntity.ok(participanteService.crearParticipante(participante));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParticipanteDTO> actualizarParticipante(
            @PathVariable Long id,
            @Valid @RequestBody Participante participante) {
        return ResponseEntity.ok(participanteService.actualizarParticipante(id, participante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarParticipante(@PathVariable Long id) {
        participanteService.eliminarParticipante(id);
        return ResponseEntity.noContent().build();
    }
}



