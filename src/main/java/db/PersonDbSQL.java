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

import domain.Person;
import domain.Role;

public class PersonDbSQL implements PersonDb {
	
	String url;
	Properties properties;
	
	public PersonDbSQL(Properties properties) {
		try {
			Class.forName("org.postgresql.Driver");
			this.properties = properties;
			this.url = properties.getProperty("url");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: " + e.getMessage());
		}
	}
	
	@Override
	public Person get(String personId) {
		String query = "SELECT * FROM person WHERE userid = ?";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setString(1, personId);
			ResultSet result = statement.executeQuery();
			
			//if (!result.isBeforeFirst()) { // if pointer is not before first row -> or if resultset is empty
				while (result.next()) {
					String userid = result.getString("userid");
					String firstName = result.getString("first_name");
					String lastName = result.getString("last_name");
					String email = result.getString("email");
					String password = result.getString("password");
					String salt = result.getString("salt");
					Role role = Role.valueOf(result.getString("role"));
					
					return new Person(userid, email, password, firstName, lastName, salt, role);
				}
			//}
				return null;
		} 
		catch (SQLException e) {
			throw new DbException("Er ging iets mis met de database: " + e.getMessage());
		}
	}

	@Override
	public List<Person> getAll() {
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			Statement statement = connection.createStatement()
		) {
			List<Person> persons = new ArrayList<>();
			ResultSet result = statement.executeQuery("SELECT * FROM person");
			
			while (result.next()) {
				String userid = result.getString("userid");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String email = result.getString("email");
				String password = result.getString("password");
				String salt = result.getString("salt");
				Role role = Role.valueOf(result.getString("role"));
				
				Person person = new Person(userid, email, password, firstName, lastName, salt, role);
				persons.add(person);
			}
			return persons;
		} 
		catch (SQLException e) {
			throw new DbException("Er ging iets mis met de database: " + e.getMessage());
		}
	}

	@Override
	public void add(Person person) {
		String query = "INSERT INTO person (userid, first_name, last_name, email, password, salt, role) VALUES (?, ?, ?, ?, ?, ?, ?)"; 
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setString(1, person.getUserid().toUpperCase());
			statement.setString(2, person.getFirstName());
			statement.setString(3, person.getLastName());
			statement.setString(4, person.getEmail());
			statement.setString(5, person.getPassword());
			statement.setString(6, person.getSalt());
			statement.setString(7, Role.CUSTOMER.toString()); // TODO juist?
			
			statement.execute();
		} 
		catch (SQLException e) {
			throw new DbException("Er ging iets mis met de database: " + e.getMessage());
		}
	}

	@Override
	public void update(Person person) {
		String query = "UPDATE person SET first_name = ?, last_name = ?, email = ?, password = ?, salt = ?, role = ? WHERE userid = ?";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setString(1, person.getFirstName());
			statement.setString(2, person.getLastName());
			statement.setString(3, person.getEmail());
			statement.setString(4, person.getPassword());
			statement.setString(5, person.getSalt());
			statement.setString(6, person.getRole().toString());
			statement.setString(7, person.getUserid());
			
			statement.execute();
		}
		catch (SQLException e) {
			throw new DbException("Er is iets misgelopen met de database:\n" + e.getMessage());
		}
	}

	@Override
	public void delete(String personId) {
		String query = "DELETE FROM person WHERE userid = ?";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setString(1, personId);
			statement.execute();
		} 
		catch (SQLException e) {
			throw new DbException("Er ging iets mis met de database: " + e.getMessage());
		}
	}

	@Override
	public Person getPersonWithEmail(String email) {
		String query = "SELECT * FROM person WHERE email = ?";
		
		try (	
			Connection connection = DriverManager.getConnection(url, properties);
			PreparedStatement statement = connection.prepareStatement(query);
		) {
			statement.setString(1, email);
			ResultSet result = statement.executeQuery();
			
			while (result.next()) {
				String userid = result.getString("userid");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String emailGet = result.getString("email");
				String password = result.getString("password");
				String salt = result.getString("salt");
				Role role = Role.valueOf(result.getString("role"));
				
				return new Person(userid, emailGet, password, firstName, lastName, salt, role);
			}
			return null;
		} 
		catch (SQLException e) {
			throw new DbException("Er ging iets mis met de database: " + e.getMessage());
		}
	}

}
