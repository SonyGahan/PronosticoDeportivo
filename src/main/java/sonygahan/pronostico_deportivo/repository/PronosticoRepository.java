package sonygahan.pronostico_deportivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sonygahan.pronostico_deportivo.model.Pronostico;

@Repository
public interface PronosticoRepository extends JpaRepository<Pronostico, Long> {
}
