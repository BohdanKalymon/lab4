package trains.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import trains.domain.Station;
import trains.service.StationService;

@Controller
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @GetMapping("/get-stations")
    public String getGetStations(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        return "get-stations";
    }

    @GetMapping("/add-station")
    public String getAddStation() {
        return "add-station";
    }

    @PostMapping("/add-station")
    public String postAddStation(@RequestParam String name, @RequestParam Double x, @RequestParam Double y) {
        Station station = new Station();
        station.setName(name);
        station.setX(x);
        station.setY(y);
        try {
            stationService.createStation(station);
            return "redirect:/get-stations";
        } catch (DataIntegrityViolationException e) {
            return "redirect:/add-station?error";
        }
    }

}
