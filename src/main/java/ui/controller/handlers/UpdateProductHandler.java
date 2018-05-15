package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class UpdateProductHandler extends RequestHandler {

	public UpdateProductHandler() {}
	
	public UpdateProductHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		int productId = Integer.parseInt(request.getParameter("productid"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		double price = Double.parseDouble(request.getParameter("price"));
		
		Product product = new Product(productId, name, description, price);
		shop.updateProduct(product);
		
		response.sendRedirect("Controller?action=productOverview");
	}
}
	
