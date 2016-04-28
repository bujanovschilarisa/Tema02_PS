<!DOCTYPE html>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.Event"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.EventRepository"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Login success for employee!
	<br>
	<br>

	<form name="form1" action="TicketController" method="post" id="addticket">
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
		</select> 
		<br>
		Alegeti randul :<input type="text" name="randBilet"> <br>
		Alegeti numarul :<input type="text" name="numarBilet"> <br>
		<input type="submit" name="submit" value="Cumpara bilet">
	</form>

	
</body>
</html>