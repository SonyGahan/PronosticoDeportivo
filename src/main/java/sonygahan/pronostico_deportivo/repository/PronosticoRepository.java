package sonygahan.pronostico_deportivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sonygahan.pronostico_deportivo.model.Pronostico;

public interface PronosticoRepository extends JpaRepository<Pronostico, Long> {

    @Modifying
    @Transactional // ✅ Agregamos la anotación para que la eliminación sea parte de una transacción
    @Query("DELETE FROM Pronostico p WHERE p.partido.id = :partidoId")
    void deleteByPartidoId(@Param("partidoId") Long partidoId);

    long countByPartidoId(Long partidoId);
}

