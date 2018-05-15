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

public class ChangeQuantityOfShoppingCartItemHandler extends RequestHandler {

	public ChangeQuantityOfShoppingCartItemHandler() {}

	public ChangeQuantityOfShoppingCartItemHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		WebshopController.checkRole(request, roles);
		
		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("shoppingCart");
		ShoppingCartItem item = cart.getItemContainingProduct(productId);
		
		// if there's more than 1, change the quantity, otherwise delete it from the cart
		if (quantity > 0) {
			item.setQuantity(quantity);
		}
		else {
			cart.removeItem(item);
		}
		
		request.setAttribute("shoppingCartMessage", "Quantity changed!");
		
		request.getRequestDispatcher("Controller?action=showShoppingCart").forward(request, response);
	}
}
