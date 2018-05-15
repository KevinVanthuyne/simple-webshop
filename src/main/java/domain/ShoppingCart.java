package domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
	private List<ShoppingCartItem> items;
	
	public ShoppingCart() {
		items = new ArrayList<>();
	}
	
	public void addItem(ShoppingCartItem item) {
		if (item == null) {
			throw new DomainException("Invalid item");
		}
		// if the shoppingcart already contains the product
		Product product = item.getProduct();
		
		if (getProductIds().contains(product.getProductId())) {
			System.out.println("Item already in cart");
			ShoppingCartItem itemContainingProduct = this.getItemContainingProduct(product);
			
			itemContainingProduct.addQuantity(item.getQuantity());
		}
		// if the product is not yet in the shoppingcart
		else {
			items.add(item);
		}
	}
	
	public void removeItem(ShoppingCartItem item) {
		items.remove(item);
	}
	

	
	public int getSize() {
		int size = 0;
		
		for (ShoppingCartItem item: items) {
			size += item.getQuantity();
		}
		return size;
	}
	
	public List<ShoppingCartItem> getItems() {
		return this.items;
	}
	
	private List<Integer> getProductIds() {
		List<Integer> productIds = new ArrayList<>();
		
		for (ShoppingCartItem item: items) {
			productIds.add(item.getProduct().getProductId());
		}
		return productIds;
	}
	
	private ShoppingCartItem getItemContainingProduct(Product product) {
		for (ShoppingCartItem item: items) {
			if (item.getProduct().getProductId() == product.getProductId()) {
				return item;
			}
		}
		return null;
	}
	
	public ShoppingCartItem getItemContainingProduct(int productId) {
		for (ShoppingCartItem item: items) {
			if (item.getProduct().getProductId() == productId) {
				return item;
			}
		}
		return null;
	}
	
	public double getTotalPrice() {
		double total = 0;
		
		for (ShoppingCartItem item: items) {
			total += item.getQuantity() * item.getProduct().getPrice();
		}
		
		return total;
	}
}
