package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Product;

public class ProductDbInMemory implements ProductDb {
	private Map<Integer, Product> records = new HashMap<Integer, Product>();
	
	public ProductDbInMemory () {
		Product p1 = new Product("Pioneer CDJ-2000NXS2", "Multimedia speler", 2299);
		Product p2 = new Product("Pioneer DJM-900NXS2", "4-kanaals mixer", 2299);
		Product p3 = new Product("Pioneer CDJ-850-K", "Multimedia speler", 950);
		Product p4 = new Product("Pioneer DJM-450", "2-kanaals mixer", 695);
		Product p5 = new Product("Sennheiser HD-25", "Studio hoofdtelefoon", 159);
		Product p6 = new Product("Sennheiser HD8", "DJ hoofdtelefoon", 329);
		add(p1);
		add(p2);
		add(p3);
		add(p4);
		add(p5);
		add(p6);
	}
	
	@Override
	public Product get(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		return records.get(id);
	}
	
	@Override
	public List<Product> getAll(){
		return new ArrayList<Product>(records.values());	
	}

	@Override
	public void add(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		int id = records.size() + 1;
		product.setProductId(id);
		if (records.containsKey(product.getProductId())) {
			throw new DbException("Product already exists");
		}
		records.put(product.getProductId(), product);
	}
	
	@Override
	public void update(Product product){
		if(product == null){
			throw new DbException("No product given");
		}
		if(!records.containsKey(product.getProductId())){
			throw new DbException("No product found");
		}
		records.put(product.getProductId(), product);
	}
	
	@Override
	public void delete(int id){
		if(id < 0){
			throw new DbException("No valid id given");
		}
		records.remove(id);
	}

}
