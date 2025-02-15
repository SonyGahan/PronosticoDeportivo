package sonygahan.pronostico_deportivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sonygahan.pronostico_deportivo.model.Participante;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
}
