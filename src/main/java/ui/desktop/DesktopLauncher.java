package ui.desktop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class DesktopLauncher {
	
	public static void main(String[] args) {
		String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51617/1TX31?currentSchema=r0613906_web3";
		// String password = new TempPassword().getPassword(); 
		
		Properties properties = new Properties();
		properties.setProperty("user", "r0613906");
		// properties.setProperty("password", password);
		properties.setProperty("ssl", "true");
		properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
		
		try (Connection connection = DriverManager.getConnection(url, properties);){
			Class.forName("org.postgresql.Driver");
			// Add person
			String useridIn = JOptionPane.showInputDialog("userid:");
			String firstNameIn = JOptionPane.showInputDialog("first name:");
			String lastNameIn = JOptionPane.showInputDialog("last name:");
			String emailIn = JOptionPane.showInputDialog("email:");
			String passwordIn = JOptionPane.showInputDialog("password:");
			
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO person (userid, first_name, last_name, email, password)"
							+	String.format("VALUES ('%s', '%s', '%s', '%s', '%s')", useridIn, firstNameIn, lastNameIn, emailIn, passwordIn));
			
			// Overview of all persons
			ResultSet result = statement.executeQuery("SELECT * FROM person");
			
			while(result.next()) {
				String userid = result.getString("userid");
				String firstName = result.getString("first_name");
				String lastName = result.getString("last_name");
				String email = result.getString("email");
				
				System.out.println(userid + " | " + firstName + " | " + lastName + " | " + email);
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Database error: " + e.getMessage());
		}
		
		
	}

	
	

}
