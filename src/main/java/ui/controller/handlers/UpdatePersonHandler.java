package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Person;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class UpdatePersonHandler extends RequestHandler {

	public UpdatePersonHandler() {}
	
	public UpdatePersonHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		String userid = request.getParameter("id");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Role role = Role.valueOf(request.getParameter("role"));
		
		Person person = shop.getPerson(userid);
		String password = person.getPassword();
		String salt = person.getSalt();
		
		Person updatedPerson = new Person(userid, email, password, firstName, lastName, salt, role);
		shop.updatePerson(updatedPerson);
		
		response.sendRedirect("Controller?action=overview");
	}
}