package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class ConfirmDeletePersonHandler extends RequestHandler {

	public ConfirmDeletePersonHandler() {}
	
	public ConfirmDeletePersonHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		String userid = request.getParameter("id");
		Person person = shop.getPerson(userid);
		
		request.setAttribute("type", "person");
		request.setAttribute("id", userid);
		request.setAttribute("firstName", person.getFirstName());
		request.setAttribute("lastName", person.getLastName());
		request.setAttribute("email", person.getEmail());
		
		request.getRequestDispatcher("confirmDelete.jsp").forward(request, response);
	}
}
