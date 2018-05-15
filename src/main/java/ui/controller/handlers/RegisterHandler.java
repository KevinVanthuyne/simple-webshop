package ui.controller.handlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbException;
import domain.Person;
import domain.ShopService;

public class RegisterHandler extends RequestHandler {
	
	public RegisterHandler() {}
	
	public RegisterHandler(ShopService shop) {
		setService(shop);
	}
	
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Person person = new Person();
		ArrayList<String> errors = new ArrayList<>();
		
		try {
			person.setUserid(userid);
			request.setAttribute("useridPreviousValue", userid);
		}
		catch(IllegalArgumentException exc) {
			errors.add(exc.getMessage());
		}
		
		try {
			person.setFirstName(firstName);
			request.setAttribute("firstNamePreviousValue", firstName);
		}
		catch(IllegalArgumentException exc) {
			errors.add(exc.getMessage());
		}
		
		try {
			person.setLastName(lastName);
			request.setAttribute("lastNamePreviousValue", lastName);
		}
		catch(IllegalArgumentException exc) {
			errors.add(exc.getMessage());
		}
		
		try {
			person.setEmail(email);
			request.setAttribute("emailPreviousValue", email);
		}
		catch(IllegalArgumentException exc) {
			errors.add(exc.getMessage());
		}
		
		try {
			person.setHashedPassword(password);
			// request.setAttribute("passwordPreviousValue", password);
		}
		catch(IllegalArgumentException exc) {
			errors.add(exc.getMessage());
		}
		
		if (!errors.isEmpty()) {
			for(String error: errors) {
				System.out.println(error);
			}
			
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("signUp.jsp").forward(request, response);
		}
		else {
			try {
				shop.addPerson(person);
			}
			catch (DbException exc) {
				errors.add(exc.getMessage());
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("signUp.jsp").forward(request, response);
			}
			response.sendRedirect("Controller?action=overview");
		}
	}
	
}
