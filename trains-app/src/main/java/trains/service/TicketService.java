package trains.service;

import trains.domain.Ticket;
import trains.domain.TicketType;

import java.util.List;
import java.util.Optional;

public interface TicketService {

    List<Ticket> getAllTickets();

    List<TicketType> getAllTicketTypes();

    void createTicket(Ticket ticket);

    double getTicketPrice(Ticket ticket);

    Optional<TicketType> findTicketTypeById(Long id);

}
