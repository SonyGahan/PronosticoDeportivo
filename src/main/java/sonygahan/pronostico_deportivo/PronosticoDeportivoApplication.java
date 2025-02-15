package sonygahan.pronostico_deportivo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sonygahan.pronostico_deportivo.service.PronosticoService;

@SpringBootApplication
public class PronosticoDeportivoApplication implements CommandLineRunner {

	private final PronosticoService pronosticoService;

	public PronosticoDeportivoApplication(PronosticoService pronosticoService) {
		this.pronosticoService = pronosticoService;
	}

	public static void main(String[] args) {
		SpringApplication.run(PronosticoDeportivoApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("=== Cargando Pronósticos Iniciales ===");
		pronosticoService.cargarPronosticos();
		System.out.println("✅ Pronósticos cargados exitosamente.");
	}
}



