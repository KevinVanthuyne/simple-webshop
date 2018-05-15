package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class ShowUpdatePersonHandler extends RequestHandler {

	public ShowUpdatePersonHandler() {}
	
	public ShowUpdatePersonHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		String userid = request.getParameter("id");
		Person person = shop.getPerson(userid);
		request.setAttribute("person", person);
		request.setAttribute("roles", Role.values()); // pass all available roles to form
		
		request.getRequestDispatcher("updatePerson.jsp").forward(request, response);
	}
}
		
	
