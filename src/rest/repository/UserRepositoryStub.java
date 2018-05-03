package rest.repository;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import rest.model.User;
import rest.model.UserRole;

public class UserRepositoryStub implements UserRepository {
	private List<User> users = new ArrayList<>();
	
	private Map<String, User> usersMap = new HashMap<>();
	
	private static UserRepositoryStub instance;

	private UserRepositoryStub() {
		//secure as private can be not private sometimes :)
		if(instance != null) {
			throw new RuntimeException("User getInstance() method to create!");
		}		
		
		users.add( new User(1, "Pernille","Larsen", "pl", "0000-12345", "TheGirl", "321", true, new UserRole(true,true,false,false)) );
		users.add( new User(2, "Emma", "Birkelund", "eb", "0001-12345", "Avatar", "333", true, new UserRole(true,true,false,false)) );
	
	    User user1 = new User(1, "Pernille","Larsen", "pl", "0000-12345", "TheGirl", "321", true, new UserRole(true,true,false,false));
	    User user2 = new User(2, "Emma", "Birkelund", "eb", "0001-12345", "Avatar", "333", true, new UserRole(true,true,false,false));
	    
		usersMap.put( user1.getUserName(), user1 );
		usersMap.put( user2.getUserName(), user2 );
	}

	public static UserRepository getInstance(){
		if(instance == null){
			//secure for multiple threads 
			synchronized(UserRepositoryStub.class) {
				if(instance == null){
					instance = new UserRepositoryStub();									
				}
			}
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
		
		//return users;
		//nasty sorting in the middle of nowhere.. sorry 
		List<User> usersSortedById = new ArrayList<User>(this.usersMap.values());
		Collections.sort(usersSortedById, new Comparator<User>() {
	        public int compare(User u1, User u2) {
	            return u1.getId() - u2.getId();
	        }
	    });
		return usersSortedById;
		//return new ArrayList<User>(this.usersMap.values());
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
		List<User> usersFromMap = new ArrayList<User>(this.usersMap.values());
		
		for(User userToUpdate : usersToUpdate) { //I know I can do it faster
			for(User user : usersFromMap) {			
				if( user.hasEqualId(userToUpdate) ) {
					user.update(userToUpdate);
				}
			}
		}
		//return this.users;
		return new ArrayList<User>(this.usersMap.values());
	}
	
	
	@Override
	public List<User> addUsers(ArrayList<User> usersToAdd) {
		for(User userToAdd : usersToAdd) {
			if( usersMap.containsKey(userToAdd.getUserName()) ) {
				return null;
			}else if( userToAdd.userNameIsNullOrEmpty() ) { //on two different if's as there are two different scenarious I need to respond to, my repository will get smarter in the future 
				return null; 
			}else {
				usersMap.put(userToAdd.getUserName(), userToAdd);
			}
		}
		return new ArrayList<User>(this.usersMap.values());
		
		/*
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
		return this.users;*/
	}
	
	@Override
	public User deleteUserById(int userId) {
		User user= null;
		Iterator<Map.Entry<String,User>> it = this.usersMap.entrySet().iterator();
		while (it.hasNext()) {
		    Map.Entry<String,User> entry = it.next();
		    user = entry.getValue();
		    if(userId == user.getId()){
		        it.remove();
		    }
		}
		return user;
		/*
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
		*/
	}

	
	


	
	
}
