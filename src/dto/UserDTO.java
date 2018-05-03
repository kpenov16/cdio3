package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rest.model.UserRole;

public class UserDTO implements Serializable, Cloneable, Comparable<UserDTO>{
	private static final long serialVersionUID = 4545864587995944260L;
	private int	id = 0;                     
	private String firstName = ""; 
	private String lastName = "";
	private String initials = "";
	private String cpr = "";
	private String userName = "";
	private String password = "";
	private UserRole userRole = null;
	private boolean active = false;
	
	public UserDTO() {	}
	public UserDTO(int id, String firstName, String lastName, 
				   String initials, String cpr, String userName,
				   String password, boolean active, UserRole role) {
		setId(id);
		setFirstName(firstName);
		setLastName(lastName);
		setInitials(initials);
		setCpr(cpr);
		setUserName(userName);
		setPassword(password);
		setActive(active);
		setUserRole(role);
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
	
	public String getCpr() {
		return cpr;
	}	
	public void setCpr(String cpr) {
		this.cpr = cpr;
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
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public void Print() {
		System.out.printf("%s\n", this);
	}
	
	/** 
	 * @param role is a constant representing the role type in UserRole
	 * @return true if role existed, false if not
	 */
	public boolean addRole(String role){
		return getUserRole().setRole(role, true);
	}
	/** 
	 * @param role is a constant representing the role type in UserRole
	 * @return true if role existed, false if not
	 */
	public boolean removeRole(String role){
		return getUserRole().setRole(role, false);		
	}		
	
	@Override
	public String toString() {
		return String.format("UserDTO [id= %d, name= %s, initials= %s, roles= %s]"
								, id, firstName, initials, getUserRole());
	}
	
	@Override
	public UserDTO clone() {
	   return new UserDTO(getId(), getFirstName(), getLastName(), getInitials(), cpr, getUserName(), password, isActive(), getUserRole().clone()); 
	}
	
	public void Update(UserDTO user) {
		setFirstName(user.getFirstName());
		setLastName(user.getLastName());
		setUserRole(user.getUserRole());
	}	
	
	public boolean hasId(int id) {
		return getId() == id;
	}
	
	@Override
	public int compareTo(UserDTO otherUser) {
		return ( getId() < otherUser.getId() ? -1 : (getId() == otherUser.getId() ? 0 : 1) ); 
	}
	
}
