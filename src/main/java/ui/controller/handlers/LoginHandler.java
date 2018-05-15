package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Person;
import domain.ShopService;

public class LoginHandler extends RequestHandler {

	public LoginHandler() {}
	
	public LoginHandler(ShopService shop) {
		setService(shop);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		
		if (type.equals("Login")) {
			String userid = request.getParameter("userid");
			String password = request.getParameter("password");
			
			Person user = shop.getUserIfAuthenticated(userid, password);
			
			if (user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userid", userid);
				session.setAttribute("firstName", user.getFirstName());
				session.setAttribute("role", user.getRole());
				System.out.println("Logged in");
				response.sendRedirect("Controller?action=index");
			}
			else {
				request.setAttribute("error", "Incorrect userid/password");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		
		if (type.equals("Log out")) {
			request.getSession().invalidate();
			System.out.println("Logged out");
			response.sendRedirect("Controller?action=index");
		}
	}
}
