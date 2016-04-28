<!DOCTYPE html>
<%@page import="model.Event"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.EventRepository"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Login success for admin!
	<br>
	<br>

	<form action="EventController" method="post" id="addshow">
		<select name="combo">
			<option value="0" selected>(please select:)</option>
			<%
				EventRepository eventRepository = new EventRepository();
				ArrayList<Event> events = eventRepository.getEventsFromDB();
				for (int i = 0; i < events.size(); i++) {
					Event event = events.get(i);
					out.println("<option value='>" + event.getName() + "'> " + event.getName() + "</option>");
				}
			%>
		</select> <br> Introduceti titlul spectacolului :<input type="text"
			name="titleShow"> <br> Introduceti regia spectacolului :<input
			type="text" name="regieShow"> <br> Introduceti
		distributia spectacolului :<input type="text" name="distributieShow">
		<br> Introduceti data premierei spectacolului :<input type="text"
			name="dataPremiereiShow"> <br> Introduceti numarul de
		bilete disponibil :<input type="text" name="numberOfTicketsShow">
		<br> <input type="submit" name="addeventbutton"
			value="Creaza spectacol"> <br> <br>
		<input type="submit" name="updateeventbutton" value="Update eveniment">
	</form>

	<form action="UsersController" method="post" id="addemployee">
		Introduceti numele noului angajat :<input type="text"
			name="nameNewEmployee"> <br> Introduceti username-ul
		noului angajat :<input type="text" name="usernameNewEmployee">
		<br> Introduceti parola noului angajat :<input type="text"
			name="passwordNewEmployee"> <br> <input type="submit"
			name="addemployeebutton" value="Deschide cont angajat">
	</form>

	<form action="EventController" method="post" id="deleteEvent">
		<br> <input type="submit" name="deleteeventbutton"
			value="Sterge eveniment">
	</form>

</body>
</html>