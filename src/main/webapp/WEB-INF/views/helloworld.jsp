<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring 4 MVC -HelloWorld</title>
</head>
<body>
	<center>
		<h2>Teatrul National Cluj-Napoca</h2>
	</center>

	<form action="UsersController" method="post" id="loginuser">
		Introduceti usename-ul :<input type="text" name="username" > <br>
		Introduceti parola :<input type="password" name="password"> <br>
		<input type="submit" name = "loginuserbutton" value="Logheaza-te">
	</form>
</body>
</html>