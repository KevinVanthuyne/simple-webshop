package ui.controller.handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Product;
import domain.Role;
import domain.ShopService;
import domain.ShoppingCart;
import domain.ShoppingCartItem;
import ui.controller.WebshopController;

public class AddProductToCartHandler extends RequestHandler {

	public AddProductToCartHandler() {}
	
	public AddProductToCartHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		WebshopController.checkRole(request, roles);
		
		// get the product that should be added to the cart
		int productId = 0;
		int amount = 0;
		
		try {
			productId = Integer.parseInt(request.getParameter("productId"));
			amount = Integer.parseInt(request.getParameter("amount"));
		}
		catch (NumberFormatException exc) {
			// TODO
		}
		// error check
		if (amount < 0) {
			// TODO
		}
		
		HttpSession session = request.getSession();
		ShoppingCart cart;
		
		// create shopping cart if there is none yet
		if (session.getAttribute("shoppingCart") == null) {
			System.out.println("No shoppingcart");
			cart = new ShoppingCart();
		}
		else {
			cart = (ShoppingCart) session.getAttribute("shoppingCart");
		}

		// add product to shopping cart
		Product product = shop.getProductDb().get(productId);
		ShoppingCartItem item = new ShoppingCartItem(product, amount);
		cart.addItem(item);
		
		session.setAttribute("shoppingCart", cart);
		session.setAttribute("shoppingCartSize", cart.getSize());
		
		response.sendRedirect("Controller?action=productOverview");
	}
}
