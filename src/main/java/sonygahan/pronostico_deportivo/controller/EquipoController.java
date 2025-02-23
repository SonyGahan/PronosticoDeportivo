package sonygahan.pronostico_deportivo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sonygahan.pronostico_deportivo.dto.EquipoDTO;
import sonygahan.pronostico_deportivo.model.Equipo;
import sonygahan.pronostico_deportivo.service.EquipoService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@Validated
public class EquipoController {

    private final EquipoService equipoService;

    // 📌 Constructor
    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // 🟢 Método para listar equipos
    @GetMapping
    public List<EquipoDTO> listarEquipos() {
        return equipoService.listarEquipos();
    }

    // 🟢 Método para crear equipos
    @PostMapping
    public ResponseEntity<?> crearEquipo(@Valid @RequestBody Equipo equipo) {
        try {
            return ResponseEntity.ok(equipoService.crearEquipo(equipo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 🟢 Método para actualizar equipos por ID
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarEquipo(
            @PathVariable Long id,
            @Valid @RequestBody Equipo equipo) {
        try {
            return ResponseEntity.ok(equipoService.actualizarEquipo(id, equipo));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //Devuelve un mensaje de error en JSON
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado: " + e.getMessage());
        }
    }

    // 🟢 Método para borrar equipos por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.noContent().build();
    }
}



