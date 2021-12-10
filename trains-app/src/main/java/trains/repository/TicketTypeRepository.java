package trains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trains.domain.TicketType;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}
