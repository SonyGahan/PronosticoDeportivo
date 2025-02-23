package sonygahan.pronostico_deportivo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    // 📌 Constructor
    @Autowired
    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 🔹 Método para verificar la conexion a la BD
    public boolean verificarConexion() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
