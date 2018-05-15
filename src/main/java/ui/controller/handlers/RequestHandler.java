package ui.controller.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Role;
import domain.ShopService;
import ui.controller.WebshopController;

public abstract class RequestHandler {
	protected ShopService shop;
	
	public void setService(ShopService shop) {
		if (shop == null) {
			throw new IllegalArgumentException("Invalid ShopService");
		}
		this.shop = shop;
	}
	
	public ShopService getService() {
		return shop;
	}
	
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	/*
	TEMPLATE FOR EXTENDING HANDLERS

	import java.io.IOException;

	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	
	import domain.Role;
	import domain.ShopService;
	import ui.controller.WebshopController;

	public class Handler extends RequestHandler {

		public Handler() {}

		public Handler(ShopService shop) {
			setService(shop);
		}
		
		@Override
		public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Role[] roles = {Role.ADMINISTRATOR, Role.CUSTOMER};
			WebshopController.checkRole(request, roles);
		}
	}

	 */
}
