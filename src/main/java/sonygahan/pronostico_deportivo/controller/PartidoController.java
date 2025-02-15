package sonygahan.pronostico_deportivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sonygahan.pronostico_deportivo.dto.PartidoDTO;
import sonygahan.pronostico_deportivo.model.Partido;
import sonygahan.pronostico_deportivo.service.PartidoService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/partidos")
@Validated
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    @GetMapping
    public List<PartidoDTO> listarPartidos() {
        return partidoService.listarPartidos();
    }

    @PostMapping
    public ResponseEntity<PartidoDTO> crearPartido(@Valid @RequestBody Partido partido) {
        return ResponseEntity.ok(partidoService.crearPartido(partido));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidoDTO> actualizarPartido(
            @PathVariable Long id,
            @Valid @RequestBody Partido partido) {
        return ResponseEntity.ok(partidoService.actualizarPartido(id, partido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable Long id) {
        partidoService.eliminarPartido(id);
        return ResponseEntity.noContent().build();
    }
}

