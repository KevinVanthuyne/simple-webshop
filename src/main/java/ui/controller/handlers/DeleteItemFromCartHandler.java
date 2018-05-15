package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Role;
import domain.ShopService;
import domain.ShoppingCart;
import domain.ShoppingCartItem;
import ui.controller.WebshopController;

public class DeleteItemFromCartHandler extends RequestHandler {

	public DeleteItemFromCartHandler() {}

	public DeleteItemFromCartHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		WebshopController.checkRole(request, roles);
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		HttpSession session = request.getSession();
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
		ShoppingCartItem item = cart.getItemContainingProduct(productId);
		cart.removeItem(item);
		
		request.setAttribute("shoppingCartMessage", "Item removed.");
		
		request.getRequestDispatcher("Controller?action=showShoppingCart").forward(request, response);
	}
}
