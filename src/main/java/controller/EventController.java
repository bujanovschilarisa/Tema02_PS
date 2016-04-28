package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Event;
import model.EventRepository;
import model.TicketRepository;

@WebServlet(urlPatterns = "/EventController")
public class EventController extends HttpServlet {
	private EventRepository eventRepository = new EventRepository();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String titleShowSelected = request.getParameter("combo");
		if (request.getParameter("addeventbutton") != null) {
			System.out.println("in EventController");
			String titleShow = request.getParameter("titleShow");
			String regieShow = request.getParameter("regieShow");
			String distributieShow = request.getParameter("distributieShow");
			String dataPremiereiShow = request.getParameter("dataPremiereiShow");
			int numberOfTickets = Integer.valueOf(request.getParameter("numberOfTicketsShow"));
			Event event = new Event();
			event.setDataPremierei(dataPremiereiShow);
			event.setDistributie(distributieShow);
			event.setName(titleShow);
			event.setNumberOfTockets(numberOfTickets);
			event.setRegie(regieShow);
			boolean isAdd = eventRepository.addEventInDB(event);
			if (isAdd) {
				System.out.println("The event was added!");
			} else {
				System.out.println("The process was stopped! Try again!");
			}
			response.sendRedirect("admin.jsp");

		} else if (request.getParameter("deleteeventbutton") != null) {
			Event event = null;
			ArrayList<Event> events = eventRepository.getEventsFromDB();
			for (int i = 0; i < events.size(); i++) {
				event = events.get(i);
				if (event.getName().equals(titleShowSelected)) {
					break;
				}
			}
			boolean isDeleted = eventRepository.deleteEventFromDB(event);
			if (isDeleted) {
				System.out.println("The event was deleted!");
			} else {
				System.out.println("The process was stopped! Try again!");
			}
			response.sendRedirect("admin.jsp");

		} else if (request.getParameter("updateeventbutton") != null) {
			String regieShow = request.getParameter("regieShow");
			System.out.println(regieShow);
			String distributieShow = request.getParameter("distributieShow");
			String dataPremiereiShow = request.getParameter("dataPremiereiShow");
			int numberOfTickets = Integer.valueOf(request.getParameter("numberOfTicketsShow"));
			Event event = new Event();
			event.setDataPremierei(dataPremiereiShow);
			event.setDistributie(distributieShow);
			event.setName(titleShowSelected);
			event.setNumberOfTockets(numberOfTickets);
			event.setRegie(regieShow);
			boolean isAdd = eventRepository.addEventInDB(event);
			if (isAdd) {
				System.out.println("The event was updated!");
			} else {
				System.out.println("The process was stopped! Try again!");
			}
			response.sendRedirect("admin.jsp");

		}
	}

}
