package ui.controller.handlers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DbException;
import domain.DomainException;
import domain.Product;
import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public class AddProductHandler extends RequestHandler {

	public AddProductHandler() {}
	
	public AddProductHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Role[] roles = {Role.ADMINISTRATOR};
		WebshopController.checkRole(request, roles);
		
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String priceString = request.getParameter("price");
		
		Product product = new Product();
		ArrayList<String> errors = new ArrayList<>();
		
		try {
			product.setName(name);
			request.setAttribute("namePreviousValue", name);
		}
		catch(DomainException exc) {
			errors.add(exc.getMessage());
		}
		
		try {
			product.setDescription(description);
			request.setAttribute("descriptionPreviousValue", description);
		}
		catch(DomainException exc) {
			errors.add(exc.getMessage());
		}
		
		try {
			double price = Double.parseDouble(priceString);
			product.setPrice(price);
			request.setAttribute("namePreviousValue", name);
		}
		catch(NumberFormatException exc) {
			errors.add("Price has to be a valid number");
		}
		catch(DomainException exc) {
			errors.add(exc.getMessage());
		}
		
		
		if (!errors.isEmpty()) {
			for(String error: errors) {
				System.out.println(error);
			}
			
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("addProduct.jsp").forward(request, response);
		}
		else {
			try {
				shop.addProduct(product);
			}
			catch (DbException exc) {
				errors.add(exc.getMessage());
				request.setAttribute("errors", errors);
				request.getRequestDispatcher("addProduct.jsp").forward(request, response);
			}
			response.sendRedirect("Controller?action=productOverview");
		}
	}
}
