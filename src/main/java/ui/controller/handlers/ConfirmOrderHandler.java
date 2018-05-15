package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class ConfirmOrderHandler extends RequestHandler {

	public ConfirmOrderHandler() {}

	public ConfirmOrderHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		WebshopController.checkRole(request, roles);
		
		HttpSession session = request.getSession();
		session.removeAttribute("shoppingCart");
		session.removeAttribute("shoppingCartSize");
		session.removeAttribute("shoppingCartTotal");
		session.removeAttribute("shoppingCartItems");
		
		request.getRequestDispatcher("orderConfirmed.jsp").forward(request, response);
	}
}