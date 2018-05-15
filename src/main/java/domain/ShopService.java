package domain;

import java.util.List;
import java.util.Properties;

import db.*;

public class ShopService {
	private PersonDb personDb;
	private ProductDb productDb;

	public ShopService(Properties properties){
		personDb = new PersonDbSQL(properties);
		productDb = new ProductDbSQL(properties);
	}
	
	// Personen
	
	public Person getPerson(String personId) {
		return getPersonDb().get(personId.toUpperCase());
	}

	public List<Person> getPersons() {
		return getPersonDb().getAll();
	}

	public void addPerson(Person person) {
		getPersonDb().add(person);
	}

	public void updatePerson(Person person) {
		getPersonDb().update(person);
	}

	public void deletePerson(String id) {
		getPersonDb().delete(id.toUpperCase());
	}

	public PersonDb getPersonDb() {
		return personDb;
	}
	
	public Person getUserIfAuthenticated(String userid, String password) {
		Person person = getPerson(userid);
		
		if (person != null && person.isPasswordCorrect(password)) {
			return person;
		}
		return null;
	}
	
	public Person getPersonWithEmail(String email) {
		return getPersonDb().getPersonWithEmail(email);
	}
	
	// Producten
	
	public ProductDb getProductDb() {
		return productDb;
	}
	
	public List<Product> getProducts() {
		return getProductDb().getAll();
	}
	
	public void addProduct(Product product) {
		getProductDb().add(product);
	}
	
	public void updateProduct(Product product) {
		getProductDb().update(product);
	}


	
}
