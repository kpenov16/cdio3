package rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class User{
	
	private int id = 0;                     
	private String firstName = ""; 
	private String lastName = "";
	private String initials = "";
	private String cpr = "";
	private String userName = "";	
	private String password = "";	
	private UserRole role = null;
	private boolean active = false;
	
	public User() {
		this(-1,"","","","","","", false, new UserRole());
	}	
	public User(String firstName, String lastName) {
		this(-1, firstName, lastName, "", "", "", "", false, new UserRole());
	}	
	public User(int id, String firstName, String lastName, 
				   String initials, String cpr, 
				   String userName, String password,  
				   boolean active, UserRole role) {
		setId(id);
		//this.id++; //I don't have the time to change all the project right now, but do that to remind myself that is important to do
		setFirstName(firstName);
		setLastName(lastName);
		setInitials(initials);
		setCpr(cpr);
		setUserName(userName);
		setPassword(password);
		setRole(role);
		setActive(active);		
	}
	
	
	public String getUserName() {
		return userName;		
	}
	public void setUserName(String userName) {
		this.userName = userName;		
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
		this.setUserName(user.getUserName());
		this.setPassword(user.getPassword());
		this.setRole(user.getRole());
	}
	
	public void updateSkipUserName(User user) {
		this.setActive(user.getActive());
		this.setCpr(user.getCpr());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setInitials(user.getInitials());
		//this.setUserName(user.getUserName());
		this.setPassword(user.getPassword());
		this.setRole(user.getRole());
	}
	
	public boolean userNameIsNullOrEmpty() {
		return getUserName() == null || getUserName().trim().isEmpty(); 
	}
	
	public boolean hasEqualUserName(User user) {
		return this.getUserName().trim().toLowerCase()
				   .equals( user.getUserName().trim().toLowerCase() );
	}
	
	public boolean hasEqualId(User user) {
		return this.getId() == user.getId();
	}
	
}