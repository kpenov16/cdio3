package rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class User{
	
	private int	id = 0;                     
	private String firstName = ""; 
	private String lastName = "";
	private String initials = "";
	private String cpr = "";
	private String password = "";	
	private UserRole role = null;
	private boolean active = false;
	
	public User() {
		this(-1,"","","","","", false, new UserRole());
	}	
	public User(String firstName, String lastName) {
		this(-1, firstName, lastName, "", "", "", false, new UserRole());
	}	
	public User(int id, String firstName, String lastName, 
				   String initials, String cpr, 
				   String password,  boolean active, UserRole role) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setInitials(initials);
		setCpr(cpr);
		setPassword(password);
		setRole(role);
		setActive(active);
	}
	
	public String getPassword() {
		return password;		
	}
	public void setPassword(String password) {
		this.password = password;		
	}	
	
	public String getCpr() {
		return cpr;
	}
	public void setCpr(String cpr) {
		this.cpr = cpr;
	}
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getInitials() {
		return initials;
	}
	public void setInitials(String initials) {
		this.initials = initials;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}		
	public boolean getActive() {
		return active;
	}	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return String.format("%s %s", getFirstName(), getLastName());  
	}
	public void update(User user) {
		this.setActive(user.getActive());
		this.setCpr(user.getCpr());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setInitials(user.getInitials());
		this.setPassword(user.getPassword());
		this.setRole(user.getRole());
	}
}