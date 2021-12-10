package trains.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;
import trains.domain.Ticket;
import trains.service.StationService;
import trains.service.TicketService;

import javax.servlet.http.HttpSession;

@Controller
public class TicketController {

    private final StationService stationService;
    private final TicketService ticketService;

    public TicketController(StationService stationService, TicketService ticketService) {
        this.stationService = stationService;
        this.ticketService = ticketService;
    }

    @GetMapping("/get-tickets")
    public String getGetTickets(Model model) {
        model.addAttribute("tickets", ticketService.getAllTickets());
        return "get-tickets";
    }

    @GetMapping("/add-ticket")
    public String getAddTicket(Model model) {
        model.addAttribute("stations", stationService.getAllStations());
        model.addAttribute("ticketTypes", ticketService.getAllTicketTypes());
        return "add-ticket";
    }

    @PostMapping("/add-ticket")
    public String postAddTicket(@RequestParam Long from, @RequestParam Long to, @RequestParam Long type, HttpSession session) {
        Ticket ticket = new Ticket();
        ticket.setTicketType(ticketService.findTicketTypeById(type).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        ticket.setFrom(stationService.findStationById(from).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        ticket.setTo(stationService.findStationById(to).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
        session.setAttribute("ticket", ticket);
        return "redirect:/add-ticket-confirmation";
    }

    @GetMapping("/add-ticket-confirmation")
    public String getAddTicketConfirmation(Model model, @SessionAttribute Ticket ticket) {
        model.addAttribute("ticket", ticket);
        model.addAttribute("price", ticketService.getTicketPrice(ticket));
        return "add-ticket-confirmation";
    }

    @PostMapping("/add-ticket-confirmation")
    public String postAddTicketConfirmation(@SessionAttribute Ticket ticket, HttpSession session) {
        ticket.setConfirmedPrice(ticketService.getTicketPrice(ticket));
        ticketService.createTicket(ticket);
        session.removeAttribute("ticket");
        return "redirect:/get-tickets";
    }

}
