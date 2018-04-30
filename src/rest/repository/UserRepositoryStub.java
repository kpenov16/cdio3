package rest.repository;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import rest.model.User;
import rest.model.UserRole;

public class UserRepositoryStub implements UserRepository {
	private List<User> users = new ArrayList<>();
	private static UserRepositoryStub instance;

	private UserRepositoryStub() {
		users.add( new User(1, "Pernille","Larsen", "pl", "0000-12345", "321", true, new UserRole(true,true,false,false)) );
		users.add( new User(2, "Emma", "Birkelund", "eb", "0001-12345", "123", true, new UserRole(true,true,false,false)) );
	}

	public static UserRepository getInstance(){
		if(instance == null){
			instance = new UserRepositoryStub();
		}
		return instance;
	}

	@Override
	public void delete(String userId) {
		//delete from User where userId == db.User.userId ...
	}
	
	/* (non-Javadoc)
	 * @see rest.repository.UserRepository#getAllUsers()
	 */
	@Override
	public List<User> getAllUsers(){
		//List<User> users = new ArrayList<>();
		//users.add(new User("Pernille", "Larsen"));
		//users.add(new User("Emma", "Larsen"));
		
		return users;
	}

	@Override
	public User getUser(String userId) {
		
		if(userId.equals("999")) {
			return null;
		}
		
		User user = new User("Emma", "Larsen");
		user.setId( Integer.parseInt(userId) );
		return user;
	}

	@Override
	public void create(User user) {
		//create insert statement to the database
		
	}

	@Override
	public User update(User user) {
		//search the database to see if we have the user with that ID
		//select * from User where id = ?
		//if rs size == 0
		//insert into User table
		//else
		//update the User table
		return user;
	}

	@Override
	public List<User> findById(List<String> ids) {
		// select * from users where id in (?,?,?)
		List<User> users = new ArrayList<>();
		User user = new User("Pernille","Larsen");
		user.setId(123);
		users.add(user);
		
		return users;
	}

	@Override
	public List<User> updateUsers(ArrayList<User> usersToUpdate) {		
		for(User userToUpdate : usersToUpdate) { //I know I can do it faster
			for(User user : this.users) {
				if( userToUpdate.getId() == user.getId()) {
					user.update(userToUpdate);
				}
			}
		}
		return this.users;
	}

	@Override
	public User deleteUserById(int userId) {
		User user = null;
		Iterator it = users.iterator();
		while(it.hasNext()) {
			user = (User)it.next();
			if(userId == user.getId()) {
				it.remove();
				break;
			}
		}
		return user;
	}

	@Override
	public List<User> addUsers(ArrayList<User> usersToAdd) {		
		for(User userToAdd : usersToAdd) { //I know I can do it faster
			boolean matchedId = false;
			for(User user : this.users) {
				if( userToAdd.getId() == user.getId()) {
					matchedId = true;
				}
			}
			if(!matchedId) {
				users.add(userToAdd);				
			}
		}
		return this.users;
	}

	
	
}
