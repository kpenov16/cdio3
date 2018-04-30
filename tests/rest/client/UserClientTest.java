package rest.client;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import rest.model.User;

public class UserClientTest {
	
	@Test
	public void testSearch() {
		UserSearchClient client = new UserSearchClient();
		
		String param = "123";
		List<String> searchValues = new ArrayList<>();
		searchValues.add("123");
		searchValues.add("456");
		
		List<User> users = client.search(param, searchValues);
		System.out.println(users);
		
		assertNotNull(users);		
	}
	
	@Test
	public void testDelete() {
		UserClient client = new UserClient();
		client.delete("1234");
	}
	
	@Test
	public void testPut() {
		User user = new User("Kira", "Larsen");
		user.setId(123);
		
		UserClient client = new UserClient();
		user = client.update(user);
		assertNotNull(user);
		
	}
	
	@Test
	public void testCreate() {
		UserClient client = new UserClient();
		User user = new User("Lucy","Birkelund");				
		user = client.create(user);
		
		assertNotNull(user);
	}
	
	@Test
	public void testGet() {
		UserClient client = new UserClient();
		User user = client.get("123");
		System.out.println(user);
		
		assertNotNull(user);
	}
	
	@Test
	public void testGetUserList() {
		UserClient client = new UserClient();
		 
		List<User> users =  client.get(); 

		System.out.println(users);
		
		assertNotNull(users);
	
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetWithBadRequest() {
		UserClient client = new UserClient();
		client.get("999");//no user with id 999
	}
}
