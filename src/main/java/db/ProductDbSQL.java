package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Product;

public class ProductDbSQL implements ProductDb {

	String url;
	Properties properties;
	
	public ProductDbSQL(Properties properties) {
		try {
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: " + e.getMessage());
		}
	}
	
	@Override
	public Product get(int id) {
		String query = "SELECT * FROM product WHERE id = ?";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			result.next();
			
			String name = result.getString("name");
			String description = result.getString("description");
			double price = result.getDouble("price");
			
			return new Product(id, name, description, price);
		} 
		catch (SQLException e) {
			throw new DbException("Er ging iets mis in de database: " + e.getMessage());
		}
	}

	@Override
	public List<Product> getAll() {
		try (	Connection connection = DriverManager.getConnection(url, properties);
				Statement statement = connection.createStatement())
		{
			ResultSet result = statement.executeQuery("SELECT * FROM product ORDER BY id");
			List<Product> products = new ArrayList<>();
			
			while (result.next()) {
				int productId = result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				double price = result.getDouble("price");
				
				Product product = new Product(productId, name, description, price);
				products.add(product);
			}
			return products;
		} 
		catch (SQLException e) {
			throw new DbException("Er ging iets mis in de database: " + e.getMessage());
		}
	}

	@Override
	public void add(Product product) {
		String query = "INSERT INTO product (name, description, price) VALUES (?, ?, ?)";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			
			statement.execute();
		} 
		catch (SQLException e) {
			throw new DbException("Er is iets misgelopen met de database:\n" + e.getMessage());
		}
	}

	@Override
	public void update(Product product) {
		String query = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getDescription());
			statement.setDouble(3, product.getPrice());
			statement.setInt(4, product.getProductId());
			
			statement.execute();
		} 
		catch (SQLException e) {
			throw new DbException("Er is iets misgelopen met de database:\n" + e.getMessage());
		}
	}

	@Override
	public void delete(int id) {
		String query = "DELETE FROM product WHERE id = ?";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setInt(1, id);
			statement.execute();
		} 
		catch (SQLException e) {
			throw new DbException("Er is iets misgelopen met de database:\n" + e.getMessage());
		}
	}

}
