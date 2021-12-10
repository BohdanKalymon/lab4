package trains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trains.domain.Station;

public interface StationRepository extends JpaRepository<Station, Long> {
}
