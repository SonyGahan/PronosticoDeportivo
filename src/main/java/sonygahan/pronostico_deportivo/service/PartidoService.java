package sonygahan.pronostico_deportivo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sonygahan.pronostico_deportivo.dto.EquipoDTO;
import sonygahan.pronostico_deportivo.dto.PartidoDTO;
import sonygahan.pronostico_deportivo.model.Equipo;
import sonygahan.pronostico_deportivo.model.Partido;
import sonygahan.pronostico_deportivo.repository.EquipoRepository;
import sonygahan.pronostico_deportivo.repository.PartidoRepository;
import sonygahan.pronostico_deportivo.repository.PronosticoRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;
    private final EquipoRepository equipoRepository;
    private final PronosticoRepository pronosticoRepository; // ✅ Agregado

    public PartidoService(PartidoRepository partidoRepository,
                          EquipoRepository equipoRepository,
                          PronosticoRepository pronosticoRepository) {
        this.partidoRepository = partidoRepository;
        this.equipoRepository = equipoRepository;
        this.pronosticoRepository = pronosticoRepository; // ✅ Agregado
    }

    public List<PartidoDTO> listarPartidos() {
        return partidoRepository.findAll().stream()
                .map(partido -> new PartidoDTO(
                        partido.getId(),
                        new EquipoDTO(partido.getEquipo1().getId(), partido.getEquipo1().getNombre()),
                        new EquipoDTO(partido.getEquipo2().getId(), partido.getEquipo2().getNombre()),
                        partido.getResultado()
                ))
                .collect(Collectors.toList());
    }

    public PartidoDTO crearPartido(Long equipo1Id, Long equipo2Id, @Valid String resultado) {
        Equipo equipo1 = equipoRepository.findById(equipo1Id)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Equipo 1 no encontrado"));
        Equipo equipo2 = equipoRepository.findById(equipo2Id)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Equipo 2 no encontrado"));

        Partido partido = new Partido(equipo1, equipo2, resultado);
        Partido partidoGuardado = partidoRepository.save(partido);

        return new PartidoDTO(
                partidoGuardado.getId(),
                new EquipoDTO(partidoGuardado.getEquipo1().getId(), partidoGuardado.getEquipo1().getNombre()),
                new EquipoDTO(partidoGuardado.getEquipo2().getId(), partidoGuardado.getEquipo2().getNombre()),
                partidoGuardado.getResultado()
        );
    }

    public PartidoDTO actualizarPartido(Long id, Long equipo1Id, Long equipo2Id, @Valid String resultado) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Partido con ID " + id + " no encontrado"));

        Equipo equipo1 = equipoRepository.findById(equipo1Id)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Equipo 1 no encontrado"));
        Equipo equipo2 = equipoRepository.findById(equipo2Id)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Equipo 2 no encontrado"));

        partido.setEquipo1(equipo1);
        partido.setEquipo2(equipo2);
        partido.setResultado(resultado);

        Partido partidoActualizado = partidoRepository.save(partido);

        return new PartidoDTO(
                partidoActualizado.getId(),
                new EquipoDTO(partidoActualizado.getEquipo1().getId(), partidoActualizado.getEquipo1().getNombre()),
                new EquipoDTO(partidoActualizado.getEquipo2().getId(), partidoActualizado.getEquipo2().getNombre()),
                partidoActualizado.getResultado()
        );
    }

    @Transactional // ✅ Agregamos transacción para evitar errores de Hibernate
    public void eliminarPartido(Long id) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("⚠️ Partido no encontrado"));

        // Primero eliminamos los pronósticos asociados
        pronosticoRepository.deleteByPartidoId(id);

        // Luego eliminamos el partido
        partidoRepository.delete(partido);
    }

}




