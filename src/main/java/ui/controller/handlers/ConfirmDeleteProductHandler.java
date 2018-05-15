package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class ConfirmDeleteProductHandler extends RequestHandler {

	public ConfirmDeleteProductHandler() {}
	
	public ConfirmDeleteProductHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		int id = Integer.parseInt(request.getParameter("id"));
		Product product = shop.getProductDb().get(id);
		
		request.setAttribute("type", "product");
		request.setAttribute("id", id);
		request.setAttribute("name", product.getName());
		request.setAttribute("description", product.getDescription());
		request.setAttribute("price", product.getPrice());
		
		request.getRequestDispatcher("confirmDelete.jsp").forward(request, response);
	}
}