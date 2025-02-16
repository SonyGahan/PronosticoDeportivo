package sonygahan.pronostico_deportivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sonygahan.pronostico_deportivo.dto.PronosticoDTO;
import sonygahan.pronostico_deportivo.model.Participante;
import sonygahan.pronostico_deportivo.model.Partido;
import sonygahan.pronostico_deportivo.model.Pronostico;
import sonygahan.pronostico_deportivo.repository.ParticipanteRepository;
import sonygahan.pronostico_deportivo.repository.PartidoRepository;
import sonygahan.pronostico_deportivo.repository.PronosticoRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PronosticoService {

    private final PronosticoRepository pronosticoRepository;
    private final ParticipanteRepository participanteRepository;
    private final PartidoRepository partidoRepository;

    @Autowired
    public PronosticoService(PronosticoRepository pronosticoRepository,
                             ParticipanteRepository participanteRepository,
                             PartidoRepository partidoRepository) {
        this.pronosticoRepository = pronosticoRepository;
        this.participanteRepository = participanteRepository;
        this.partidoRepository = partidoRepository;
    }

    // 🔹 Cargar pronósticos iniciales solo si no hay datos en la BD
    public void cargarPronosticos() {
        if (pronosticoRepository.count() > 0) return; // ✅ Evita duplicaciones

        List<Participante> participantes = participanteRepository.findAll();
        List<Partido> partidos = partidoRepository.findAll();
        Random rnd = new Random();

        for (Participante participante : participantes) {
            for (Partido partido : partidos) {
                String resultado = rnd.nextBoolean() ? "G" : "P"; // Generación aleatoria del resultado
                Pronostico pronostico = new Pronostico(participante, partido, resultado);
                pronosticoRepository.save(pronostico);
            }
        }
    }

    // 🔹 Listar todos los pronósticos como DTOs para la interfaz
    public List<PronosticoDTO> listarPronosticos() {
        return pronosticoRepository.findAll().stream()
                .map(pronostico -> new PronosticoDTO(
                        pronostico.getId(),
                        pronostico.getParticipante().getNombre(),
                        pronostico.getPartido().getEquipo1().getNombre(),
                        pronostico.getPartido().getEquipo2().getNombre(),
                        pronostico.getResultadoPronosticado()))
                .collect(Collectors.toList());
    }

    // 🔹 Crear un nuevo pronóstico con validación
    public PronosticoDTO crearPronostico(@Valid Pronostico pronostico) {
        if (pronostico.getParticipante() == null || pronostico.getPartido() == null) {
            throw new IllegalArgumentException("El participante y el partido no pueden ser nulos.");
        }

        Pronostico pronosticoGuardado = pronosticoRepository.save(pronostico);
        return new PronosticoDTO(
                pronosticoGuardado.getId(),
                pronosticoGuardado.getParticipante().getNombre(),
                pronosticoGuardado.getPartido().getEquipo1().getNombre(),
                pronosticoGuardado.getPartido().getEquipo2().getNombre(),
                pronosticoGuardado.getResultadoPronosticado());
    }

    // 🔹 Actualizar un pronóstico existente
    public PronosticoDTO actualizarPronostico(Long id, @Valid Pronostico pronostico) {
        Optional<Pronostico> pronosticoExistente = pronosticoRepository.findById(id);

        if (pronosticoExistente.isPresent()) {
            Pronostico pronosticoActualizado = pronosticoExistente.get();
            pronosticoActualizado.setResultadoPronosticado(pronostico.getResultadoPronosticado());
            pronosticoRepository.save(pronosticoActualizado);

            return new PronosticoDTO(
                    pronosticoActualizado.getId(),
                    pronosticoActualizado.getParticipante().getNombre(),
                    pronosticoActualizado.getPartido().getEquipo1().getNombre(),
                    pronosticoActualizado.getPartido().getEquipo2().getNombre(),
                    pronosticoActualizado.getResultadoPronosticado());
        } else {
            throw new IllegalArgumentException("No se encontró el pronóstico con ID: " + id);
        }
    }

    // 🔹 Eliminar un pronóstico por ID con control de existencia
    public void eliminarPronostico(Long id) {
        if (!pronosticoRepository.existsById(id)) {
            throw new IllegalArgumentException("No se encontró el pronóstico con ID: " + id);
        }
        pronosticoRepository.deleteById(id);
    }

    // 🔹 Contar cuántos pronósticos existen en la base de datos
    public long contarPronosticos() {
        return pronosticoRepository.count();
    }
}




