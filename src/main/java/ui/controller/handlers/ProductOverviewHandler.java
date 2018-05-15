package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class ProductOverviewHandler extends RequestHandler {
	
	public ProductOverviewHandler() {}
	
	public ProductOverviewHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		WebshopController.checkRole(request, roles);
		
		request.setAttribute("producten", shop.getProducts());
		request.getRequestDispatcher("productOverview.jsp").forward(request, response);
	}
	
}
