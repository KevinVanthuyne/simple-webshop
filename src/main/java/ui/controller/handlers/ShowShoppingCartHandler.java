package ui.controller.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class ShowShoppingCartHandler extends RequestHandler {

	public ShowShoppingCartHandler() {}

	public ShowShoppingCartHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		WebshopController.checkRole(request, roles);
		
		HttpSession session = request.getSession();
		
		// If there are no products in the shopping cart yet
		if (session.getAttribute("shoppingCart") == null || (int) session.getAttribute("shoppingCartSize") == 0) {
			System.out.println("Show cart: No shoppingCart yet");
			String message = "There are no products in your shopping cart yet";
			request.setAttribute("shoppingCartMessage", message);
		}
		else {
			// get shoppingcart and items in it
			ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
			List<ShoppingCartItem> items = shoppingCart.getItems();
			
			session.setAttribute("shoppingCartItems", items);
			session.setAttribute("shoppingCartSize", shoppingCart.getSize());
			session.setAttribute("shoppingCartTotal", shoppingCart.getTotalPrice());
		}
			
		request.getRequestDispatcher("shoppingCart.jsp").forward(request, response);
	}
}
