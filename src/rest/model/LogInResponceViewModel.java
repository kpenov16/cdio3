package rest.model;

public class LogInResponceViewModel{
	public boolean success = false;
	public String[] roles;
	public User[] users;
	
	public LogInResponceViewModel(boolean success, String[] roles, User[] users) {
		setSuccess(success);
		setRoles(roles);
		setUsers(users);			
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}
}