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

@Service  // 🔹 Ahora esta clase es el único servicio de pronósticos
public class PronosticoService {

    @Autowired
    private PronosticoRepository pronosticoRepository;

    @Autowired
    private ParticipanteRepository participanteRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    // 🔹 Carga de pronósticos iniciales, reemplaza a PronosticoDeportivo
    public void cargarPronosticos() {
        if (pronosticoRepository.count() > 0) return; // ✅ Evita duplicaciones

        List<Participante> participantes = participanteRepository.findAll();
        List<Partido> partidos = partidoRepository.findAll();
        Random rnd = new Random();

        for (Participante participante : participantes) {
            for (Partido partido : partidos) {
                Pronostico pronostico = new Pronostico(participante, partido, rnd.nextBoolean() ? "G" : "P");
                pronosticoRepository.save(pronostico); // 🔹 Se usa directamente el repositorio
            }
        }
    }

    // 🔹 Operación para listar todos los pronósticos con DTOs
    public List<PronosticoDTO> listarPronosticos() {
        return pronosticoRepository.findAll().stream()
                .map(pronostico -> new PronosticoDTO(
                        pronostico.getId(),
                        null, // ⚠️ Se puede completar con el DTO del partido si es necesario
                        pronostico.getResultadoPronosticado()))
                .collect(Collectors.toList());
    }

    // 🔹 Método para crear un nuevo pronóstico
    public PronosticoDTO crearPronostico(@Valid Pronostico pronostico) {
        Pronostico pronosticoGuardado = pronosticoRepository.save(pronostico);
        return new PronosticoDTO(pronosticoGuardado.getId(), null, pronosticoGuardado.getResultadoPronosticado());
    }

    // 🔹 Método para actualizar un pronóstico existente
    public PronosticoDTO actualizarPronostico(Long id, @Valid Pronostico pronostico) {
        Optional<Pronostico> pronosticoExistente = pronosticoRepository.findById(id);

        if (pronosticoExistente.isPresent()) {
            Pronostico pronosticoActualizado = pronosticoExistente.get();
            pronosticoActualizado.setResultadoPronosticado(pronostico.getResultadoPronosticado());
            pronosticoRepository.save(pronosticoActualizado);
            return new PronosticoDTO(pronosticoActualizado.getId(), null, pronosticoActualizado.getResultadoPronosticado());
        } else {
            throw new IllegalArgumentException("No se encontró el pronóstico con ID: " + id);
        }
    }

    // 🔹 Método para eliminar un pronóstico por ID
    public void eliminarPronostico(Long id) {
        pronosticoRepository.deleteById(id);
    }

    // 🔹 Método para contar los pronósticos existentes
    public long contarPronosticos() {
        return pronosticoRepository.count();
    }
}



