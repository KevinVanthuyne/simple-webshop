package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class ShowCheckPasswordHandler extends RequestHandler {

	public ShowCheckPasswordHandler() {}
	
	public ShowCheckPasswordHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		String id = request.getParameter("id");
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("checkPassword.jsp").forward(request, response);
	}
}
