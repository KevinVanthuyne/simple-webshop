package ui.controller.handlers;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import domain.ShopService;

public class HandlerFactory {
	
	public HandlerFactory() {}
	
	public RequestHandler getHandler(String handlerName, ShopService shop) {
		RequestHandler handler = null;
		
		try {
			Class handlerClass = Class.forName("ui.controller.handlers." + handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (RequestHandler) handlerObject;
			handler.setService(shop);
		} 
		catch (ClassNotFoundException e) {
			throw new HandlerException("Handler not found");
		} 
		catch (InstantiationException e) {
			throw new HandlerException("Handler instantiation failed.");
		} 
		catch (IllegalAccessException e) {
			throw new HandlerException("Handler access failed.");
		}
		return handler;
	}
	
	public RequestHandler test(String handlerName, ShopService shop) {
		
		Class handlerClass;
		try {
			handlerClass = Class.forName(handlerName);
			Object handlerObject = handlerClass.newInstance();
			RequestHandler handler = (RequestHandler) handlerObject;
			return handler;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
