package ui.controller.handlers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Role;
import domain.ShopService;
import domain.ShoppingCart;
import domain.ShoppingCartItem;
import ui.controller.WebshopController;

public class ShowOrderOverviewHandler extends RequestHandler {

	public ShowOrderOverviewHandler() {}

	public ShowOrderOverviewHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
		WebshopController.checkRole(request, roles);
		
		HttpSession session = request.getSession();
		
		ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
		List<ShoppingCartItem> items = shoppingCart.getItems();
		
		session.setAttribute("shoppingCartItems", items);
		session.setAttribute("shoppingCartSize", shoppingCart.getSize());
		session.setAttribute("shoppingCartTotal", shoppingCart.getTotalPrice());
		
		//response.sendRedirect("orderOverview.jsp");
		request.getRequestDispatcher("orderOverview.jsp").forward(request, response);
	}
}