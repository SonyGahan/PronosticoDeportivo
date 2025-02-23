package sonygahan.pronostico_deportivo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sonygahan.pronostico_deportivo.service.DatabaseService;


@RestController
@RequestMapping("/api/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    // 📌 Constructor
    @Autowired
    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    // 🟢 Método para verificar la conexion a la BD
    @GetMapping("/status")
    public String verificarConexion() {
        return databaseService.verificarConexion() ? "Conexión exitosa" : "Error en la conexión";
    }
}
