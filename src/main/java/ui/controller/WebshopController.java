package ui.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.DbException;
import domain.DomainException;
import domain.NotAuthorizedException;
import domain.Person;
import domain.Product;
import domain.Role;
import domain.ShopService;
import ui.controller.handlers.*;

/**
 * Servlet implementation class WebshopController
 */
@WebServlet("/Controller")
public class WebshopController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ShopService shop;
	private HandlerFactory handlerFactory;
       
	@Override
	public void init() throws ServletException {
		super.init();
		
		ServletContext context = getServletContext();
		
		Properties properties = new Properties();
		properties.setProperty("user", context.getInitParameter("user"));
		properties.setProperty("password", context.getInitParameter("password"));
		properties.setProperty("ssl", context.getInitParameter("ssl"));
		properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
		properties.setProperty("url", context.getInitParameter("url"));
		
		shop = new ShopService(properties);
		handlerFactory = new HandlerFactory();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WebshopController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}
	
	private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		// handle color cookie
		Cookie stylesheetCookie = getCookie("stylesheet", request, response);
		String stylesheet;
		
		if (stylesheetCookie == null) {
			System.out.println("No cookie found");
			// if there is no cookie yet, set default stylesheet
			stylesheetCookie = new Cookie("stylesheet", "css/dark.css");
			response.addCookie(stylesheetCookie);
			stylesheet = "css/dark.css";
		}
		else {
			stylesheet = stylesheetCookie.getValue();
		}
		
		request.setAttribute("stylesheet", stylesheet);
		
		if (action.equals("switchColor")) {
			action = switchColor(request, response);
		}
		// execute action

		// convert action to handler name
		// ex.: productOverview -> ProductOverviewHandler
		String handlerName = action.substring(0, 1).toUpperCase() + action.substring(1) + "Handler";
		
		RequestHandler handler = handlerFactory.getHandler(handlerName, shop);
		handler.handle(request, response);
	}

	public static void checkRole(HttpServletRequest request, Role[] roles) {
		if (request.getSession().getAttribute("role") != null) {
			boolean containsRole = false;
			Role role = (Role) request.getSession().getAttribute("role");
			
			for (Role r: roles) {
				
				if (r.equals(role)) {
					containsRole = true;
				}
			}
			if (!containsRole) {
				throw new NotAuthorizedException("You do not have access to this page. Please login below.");
			}
		}
		else {
			throw new NotAuthorizedException("You do not have access to this page. Please login below.");
		}
	}
	
	private String switchColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie cookie = getCookie("stylesheet", request, response);
		String stylesheet = cookie.getValue();
		
		if (stylesheet.equals("css/dark.css")) {
			stylesheet = "css/bright.css";
		}
		else if (stylesheet.equals("css/bright.css") ){
			stylesheet = "css/dark.css";
		}
		
		cookie.setValue(stylesheet);
		response.addCookie(cookie);
		request.setAttribute("stylesheet", stylesheet);
		
		return request.getParameter("page");
	}
	
	private Cookie getCookie(String cookie, HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		
		for (Cookie c: cookies) {
			if (c.getName().equals(cookie)) {
				return c;
			}
		}
		return null;
	}
	
}
