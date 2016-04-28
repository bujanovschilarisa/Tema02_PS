package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import model.EventRepository;
import model.Ticket;
import model.TicketRepository;

@WebServlet(urlPatterns = "/TicketController")
public class TicketController extends HttpServlet {
	private TicketRepository ticketRepository = new TicketRepository();
	private EventRepository eventRepository = new EventRepository();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in TicketController");
		int randTicket = Integer.valueOf(request.getParameter("randBilet"));
		int numberTicket = Integer.valueOf(request.getParameter("numarBilet"));
		String titleShow = request.getParameter("combo");
		Ticket ticket = new Ticket();
		ticket.setNumber(numberTicket);
		ticket.setRow(randTicket);
		ArrayList<Event> events = eventRepository.getEventsFromDB();
		int numberOfTickets = 0;
		boolean isAdd = true;
		for (int i = 0; i < events.size(); i++) {
			if (events.get(i).getName().equals(titleShow)) {
				Event event = events.get(i);
				ticket.setNameShow(event.getName());
				numberOfTickets = event.getNumberOfTockets();
				numberOfTickets = numberOfTickets - 1;
				event.setNumberOfTockets(numberOfTickets);
				eventRepository.updateEventInDB(event);
			}
		}
		if (numberOfTickets > 0) {
			ArrayList<Ticket> tickets = ticketRepository.getTicketsFromDB();
			for (int i = 0; i < tickets.size(); i++) {
				Ticket t = tickets.get(i);
				if (t.getNameShow().equals(ticket.getNameShow()) && t.getNumber() == ticket.getNumber()
						&& t.getRow() == ticket.getRow()) {
					isAdd = false;
					break;
				}
			}
			if (isAdd) {
				isAdd = ticketRepository.addTicketInDB(ticket);
			}
		}

		if (isAdd) {
			System.out.println("Ticket was added!");
		} else {
			System.out.println("The process was stopped! Try again!");
		}
		response.sendRedirect("employee.jsp");
	}
}
