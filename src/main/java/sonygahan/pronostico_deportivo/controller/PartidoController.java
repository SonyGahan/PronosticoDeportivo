package sonygahan.pronostico_deportivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sonygahan.pronostico_deportivo.dto.PartidoDTO;
import sonygahan.pronostico_deportivo.model.Partido;
import sonygahan.pronostico_deportivo.service.PartidoService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<PartidoDTO> crearPartido(@RequestBody Map<String, Object> datos) {
        Long equipo1Id = ((Number) datos.get("equipo1Id")).longValue();
        Long equipo2Id = ((Number) datos.get("equipo2Id")).longValue();
        String resultado = (String) datos.get("resultado");

        return ResponseEntity.ok(partidoService.crearPartido(equipo1Id, equipo2Id, resultado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPartido(
            @PathVariable Long id,
            @RequestBody Map<String, Object> datos) {
        try {
            Long equipo1Id = ((Number) datos.get("equipo1Id")).longValue();
            Long equipo2Id = ((Number) datos.get("equipo2Id")).longValue();
            String resultado = (String) datos.get("resultado");

            return ResponseEntity.ok(partidoService.actualizarPartido(id, equipo1Id, equipo2Id, resultado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPartido(@PathVariable Long id) {
        partidoService.eliminarPartido(id);
        return ResponseEntity.noContent().build();
    }
}

