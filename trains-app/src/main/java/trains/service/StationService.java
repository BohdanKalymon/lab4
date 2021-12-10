package trains.service;

import trains.domain.Station;

import java.util.List;
import java.util.Optional;

public interface StationService {

    List<Station> getAllStations();

    void createStation(Station station);

    Optional<Station> findStationById(Long id);

}
