package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class DeletePersonConfirmedHandler extends RequestHandler {

	public DeletePersonConfirmedHandler() {}
	
	public DeletePersonConfirmedHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		String confirm = request.getParameter("confirmButton").toLowerCase();
		
		if (confirm.equals("yes")) {
			String id = request.getParameter("id");
			shop.deletePerson(id);
		}
		
		response.sendRedirect("Controller?action=overview");
	}
}
