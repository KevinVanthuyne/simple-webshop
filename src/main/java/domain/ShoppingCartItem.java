package domain;

public class ShoppingCartItem {
	private Product product;
	private int quantity;
	
	public ShoppingCartItem(Product product, int quantity) {
		setProduct(product);
		setQuantity(quantity);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		if (product == null) {
			throw new DomainException("Invalid product.");
		}
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		if (quantity < 1) {
			throw new DomainException("Invalid quantity");
		}
		this.quantity = quantity;
	}
	
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ShoppingCartItem) {
			ShoppingCartItem item = (ShoppingCartItem) obj;
			
			if (item.getProduct().getProductId() == this.getProduct().getProductId()) {
				if (item.getQuantity() == this.getQuantity()) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	
}
