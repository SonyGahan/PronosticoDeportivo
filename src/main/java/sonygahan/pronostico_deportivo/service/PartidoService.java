package sonygahan.pronostico_deportivo.service;

import org.springframework.stereotype.Service;
import sonygahan.pronostico_deportivo.dto.EquipoDTO;
import sonygahan.pronostico_deportivo.dto.PartidoDTO;
import sonygahan.pronostico_deportivo.model.Partido;
import sonygahan.pronostico_deportivo.repository.PartidoRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;

    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
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

    public PartidoDTO crearPartido(@Valid Partido partido) {
        Partido partidoGuardado = partidoRepository.save(partido);
        return new PartidoDTO(
                partidoGuardado.getId(),
                new EquipoDTO(partidoGuardado.getEquipo1().getId(), partidoGuardado.getEquipo1().getNombre()),
                new EquipoDTO(partidoGuardado.getEquipo2().getId(), partidoGuardado.getEquipo2().getNombre()),
                partidoGuardado.getResultado()
        );
    }

    public PartidoDTO actualizarPartido(Long id, @Valid Partido partido) {
        partido.setId(id);
        Partido partidoActualizado = partidoRepository.save(partido);
        return new PartidoDTO(
                partidoActualizado.getId(),
                new EquipoDTO(partidoActualizado.getEquipo1().getId(), partidoActualizado.getEquipo1().getNombre()),
                new EquipoDTO(partidoActualizado.getEquipo2().getId(), partidoActualizado.getEquipo2().getNombre()),
                partidoActualizado.getResultado()
        );
    }

    public void eliminarPartido(Long id) {
        partidoRepository.deleteById(id);
    }
}


