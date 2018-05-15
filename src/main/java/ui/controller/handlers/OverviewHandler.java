package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class OverviewHandler extends RequestHandler {

	public OverviewHandler() {}
	
	public OverviewHandler(ShopService service) {
		setService(service);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// check if user has permission to access page
		Role[] roles = {Role.ADMINISTRATOR}; // only admin can access
		WebshopController.checkRole(request, roles);
		
		request.setAttribute("personen", shop.getPersons());
		request.getRequestDispatcher("personoverview.jsp").forward(request, response);
	}

}
