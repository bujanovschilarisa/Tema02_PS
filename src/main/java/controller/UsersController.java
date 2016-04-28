package controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserRepository;

@WebServlet(urlPatterns = "/UsersController")
public class UsersController extends HttpServlet {
	private static final String ADMIN = "admin";
	private static final String EMPLOYEE = "employee";
	private UserRepository userRepository = new UserRepository();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("loginuserbutton") != null) {
			System.out.println("in UserController");
			String un = request.getParameter("username");
			String pw = request.getParameter("password");
			String pwMd5 = getMd5Hash(pw);

			String role = userRepository.findUser(un, pwMd5);
			if (role != null && role.equals(ADMIN)) {
				response.sendRedirect("admin.jsp");
			} else if (role != null && role.equals(EMPLOYEE)) {
				response.sendRedirect("employee.jsp");
			} else {
				response.sendRedirect("index.jsp");
			}
		} else if (request.getParameter("addemployeebutton") != null)

		{
			String nameNewEmployee = request.getParameter("nameNewEmployee");
			String usernameNewEmployee = request.getParameter("usernameNewEmployee");
			String passwordNewEmployee = request.getParameter("passwordNewEmployee");
			String passwordMd5 = getMd5Hash(passwordNewEmployee);
			User user = new User();
			user.setName(nameNewEmployee);
			user.setPassword(passwordMd5);
			user.setRol(EMPLOYEE);
			user.setUsername(usernameNewEmployee);
			boolean isAdd = userRepository.addEmployeeInDB(user);
			if (isAdd) {
				System.out.println("The employee was added!");
			} else {
				System.out.println("The process was stopped! Try again!");
			}
			response.sendRedirect("admin.jsp");
		}
	}

	public static String getMd5Hash(String input) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}

}
