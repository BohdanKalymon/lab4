package trains.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import trains.domain.Ticket;
import trains.domain.TicketType;
import trains.repository.TicketRepository;
import trains.repository.TicketTypeRepository;
import trains.service.TicketService;

import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final double pricePerKilometer;

    public TicketServiceImpl(TicketRepository ticketRepository, TicketTypeRepository ticketTypeRepository,
            @Value("${price-per-kilometer}") double pricePerKilometer) {
        this.ticketRepository = ticketRepository;
        this.ticketTypeRepository = ticketTypeRepository;
        this.pricePerKilometer = pricePerKilometer;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public List<TicketType> getAllTicketTypes() {
        return ticketTypeRepository.findAll();
    }

    @Override
    public void createTicket(Ticket ticket) {
        ticket.setId(null);
        ticketRepository.save(ticket);
    }

    @Override
    public double getTicketPrice(Ticket ticket) {
        return getDistanceKm(ticket) * pricePerKilometer * ticket.getTicketType().getMultiplier();
    }

    @Override
    public Optional<TicketType> findTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id);
    }

    private double getDistanceKm(Ticket ticket) {
        double dx = ticket.getTo().getX() - ticket.getFrom().getX();
        double dy = ticket.getTo().getY() - ticket.getFrom().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}
