package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Product;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class ShowUpdateProductHandler extends RequestHandler {

	public ShowUpdateProductHandler() {}
	
	public ShowUpdateProductHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		int productId = Integer.parseInt(request.getParameter("id"));
		Product product = shop.getProductDb().get(productId);
		request.setAttribute("product", product);
		request.getRequestDispatcher("updateProduct.jsp").forward(request, response);
	}
}
