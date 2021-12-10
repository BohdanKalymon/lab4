package trains.service.impl;

import org.springframework.stereotype.Service;
import trains.domain.Station;
import trains.repository.StationRepository;
import trains.service.StationService;

import java.util.List;
import java.util.Optional;

@Service
public class StationServiceImpl implements StationService {

    private final StationRepository stationRepository;

    public StationServiceImpl(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Override
    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    @Override
    public void createStation(Station station) {
        station.setId(null);
        stationRepository.save(station);
    }

    @Override
    public Optional<Station> findStationById(Long id) {
        return stationRepository.findById(id);
    }

}
