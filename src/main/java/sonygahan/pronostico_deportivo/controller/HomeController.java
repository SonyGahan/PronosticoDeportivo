package sonygahan.pronostico_deportivo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sonygahan.pronostico_deportivo.service.EquipoService;
import sonygahan.pronostico_deportivo.service.PartidoService;
import sonygahan.pronostico_deportivo.service.ParticipanteService;
import sonygahan.pronostico_deportivo.service.PronosticoService;

@Controller
public class HomeController {

    private final EquipoService equipoService;
    private final PartidoService partidoService;
    private final ParticipanteService participanteService;
    private final PronosticoService pronosticoService;

    // 📌 Constructor
    public HomeController(EquipoService equipoService, PartidoService partidoService,
                          ParticipanteService participanteService, PronosticoService pronosticoService) {
        this.equipoService = equipoService;
        this.partidoService = partidoService;
        this.participanteService = participanteService;
        this.pronosticoService = pronosticoService;
    }

    // 🟢 Método para mostrar los datos en la interfaz
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("equipos", equipoService.listarEquipos());
        model.addAttribute("partidos", partidoService.listarPartidos());
        model.addAttribute("participantes", participanteService.listarParticipantes());
        model.addAttribute("pronosticos", pronosticoService.listarPronosticos());
        return "index";
    }
}
