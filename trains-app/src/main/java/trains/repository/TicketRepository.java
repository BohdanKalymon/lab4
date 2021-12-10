package trains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trains.domain.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
