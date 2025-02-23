package sonygahan.pronostico_deportivo.service;

import org.springframework.stereotype.Service;
import sonygahan.pronostico_deportivo.dto.EquipoDTO;
import sonygahan.pronostico_deportivo.model.Equipo;
import sonygahan.pronostico_deportivo.repository.EquipoRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;

    // 📌 Constructor
    public EquipoService(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    // 🔹 Método para listar equipos
    public List<EquipoDTO> listarEquipos() {
        return equipoRepository.findAll().stream()
                .map(equipo -> new EquipoDTO(equipo.getId(), equipo.getNombre()))
                .collect(Collectors.toList());
    }

    // 🔹 Método para crear equipos
    public EquipoDTO crearEquipo(@Valid Equipo equipo) {
        // ✅ Evitamos la duplicación de equipos verificando si ya existe
        Optional<Equipo> existente = equipoRepository.findByNombre(equipo.getNombre());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("El equipo ya existe en la base de datos");
        }

        Equipo equipoGuardado = equipoRepository.save(equipo);
        return new EquipoDTO(equipoGuardado.getId(), equipoGuardado.getNombre());
    }

    // 🔹 Método para actualizar equipos por ID
    public EquipoDTO actualizarEquipo(Long id, @Valid Equipo equipo) {
        // ✅ Verifica si el equipo existe antes de actualizar
        Optional<Equipo> equipoExistente = equipoRepository.findById(id);

        if (equipoExistente.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el equipo con ID: " + id);
        }

        // ✅ Actualiza solo los campos necesarios
        Equipo equipoActualizado = equipoExistente.get();
        equipoActualizado.setNombre(equipo.getNombre());
        equipoActualizado.setDescripcion(equipo.getDescripcion());

        equipoRepository.save(equipoActualizado);
        return new EquipoDTO(equipoActualizado.getId(), equipoActualizado.getNombre());
    }

    // 🔹 Método para borrar equipos por ID
    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }
}



