package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class CheckPasswordHandler extends RequestHandler {

	public CheckPasswordHandler() {}
	
	public CheckPasswordHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		String password = request.getParameter("password");
		String id = request.getParameter("id");
		Person person = shop.getPerson(id);
		String message = null;
		
		if (person.isPasswordCorrect(password)) {
			message = "Password is correct!";
		}
		else {
			message = "Password is not correct.";
		}
		request.setAttribute("message", message);
		
		request.getRequestDispatcher("checkPassword.jsp").forward(request, response);
	}
}
