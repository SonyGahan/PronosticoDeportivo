package sonygahan.pronostico_deportivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sonygahan.pronostico_deportivo.dto.PronosticoDTO;
import sonygahan.pronostico_deportivo.model.Pronostico;
import sonygahan.pronostico_deportivo.service.PronosticoService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pronosticos")
@Validated
public class PronosticoController {

    private final PronosticoService pronosticoService;

    public PronosticoController(PronosticoService pronosticoService) {
        this.pronosticoService = pronosticoService;
    }

    @GetMapping
    public List<PronosticoDTO> listarPronosticos() {
        return pronosticoService.listarPronosticos();
    }

    @PostMapping
    public ResponseEntity<PronosticoDTO> crearPronostico(@Valid @RequestBody Pronostico pronostico) {
        return ResponseEntity.ok(pronosticoService.crearPronostico(pronostico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPronostico(
            @PathVariable Long id,
            @Valid @RequestBody Pronostico pronostico) {
        try {
            return ResponseEntity.ok(pronosticoService.actualizarPronostico(id, pronostico));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPronostico(@PathVariable Long id) {
        pronosticoService.eliminarPronostico(id);
        return ResponseEntity.noContent().build();
    }
}



