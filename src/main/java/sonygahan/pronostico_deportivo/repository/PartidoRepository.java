package sonygahan.pronostico_deportivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sonygahan.pronostico_deportivo.model.Partido;

import java.util.List;

public interface PartidoRepository extends JpaRepository<Partido, Long> {

    @Query("SELECT p FROM Partido p JOIN FETCH p.equipo1 JOIN FETCH p.equipo2")
    List<Partido> findAllWithTeams();
}
