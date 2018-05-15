package domain;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String salt;
	private Role role;

	public Person(String userid, String email, String password, String firstName, String lastName, String salt, Role role) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setSalt(salt);
		setRole(role);
	}
	
	public Person(String userid, String email, String password, String firstName, String lastName, Role role) {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setRole(role);
	}
	
	public Person() {
	}

	private void setRole(Role role) {
		if (role == null) {
			throw new IllegalArgumentException("Invalid role");
		}
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
			throw new IllegalArgumentException("No email given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.email = email.toLowerCase();
	}
	
	public String getEmail() {
		return email;
	}
	
	// kan niet private zijn? TODO
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}
	
	public void setHashedPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = hashPassword(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}
	
	public String getSalt() {
		return salt;
	}
	
	public byte[] getSaltByteArray() {
		try {
			return salt.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new DomainException("Unsupported encoding");
		}
	}
	
	public void setSalt(String salt) {
		if (salt == null || salt.trim().isEmpty() || salt.length() != 40) {
			throw new IllegalArgumentException("Invalid salt");
		}
		this.salt = salt;
	}
	
	private void setSalt(byte[] salt) { // TODO is dit juist?
		BigInteger saltBigInt = new BigInteger(1, salt);
		setSalt(saltBigInt.toString(16));
	}
	
	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}	
	
	private String hashPassword(String password) {
		try {
			// convert String to byte array
			byte[] passwordBytes;
			passwordBytes = password.getBytes("UTF-8");
			// add password to hash
			MessageDigest crypt = MessageDigest.getInstance("SHA-512");
			crypt.reset();
			crypt.update(passwordBytes);
			// add salt to hash
			if (getSalt() == null) {
				// generate & store salt
				SecureRandom random = new SecureRandom();
				byte[] salt = random.generateSeed(20); // TODO salt .getBytes("UTF-8")
				setSalt(salt);
				crypt.update(getSaltByteArray());
			}
			else {
				crypt.update(getSaltByteArray());
			}
			// encrypt	
			byte[] digest = crypt.digest();
			// convert back to String
			BigInteger digestAsBigInteger = new BigInteger(1, digest);
			return digestAsBigInteger.toString(16); // hexadecimal
		} 
		catch (UnsupportedEncodingException e) {
			throw new DomainException("Unsupported encoding");
		} 
		catch (NoSuchAlgorithmException e) {
			throw new DomainException("Invalid encryption algorithm");
		}
	}
	
	public boolean isPasswordCorrect(String password) {
		return hashPassword(password).equals(getPassword());
	}
}
