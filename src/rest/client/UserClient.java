package rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import rest.model.User;

public class UserClient {

	private Client client;
	
	public UserClient() {
		client = ClientBuilder.newClient();
	}


	public void delete(String userId) {
		WebTarget target = client.target("http://localhost:8080/DynamicLoading/rest2/");
		
		Response response = target.path("users/" + userId).request(MediaType.APPLICATION_JSON).delete();
		
		if(response.getStatus() != 200) { //Status.OK
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}		
	}
	
	public User get(String userId) {
		//http://localhost:8080/DynamicLoading/rest2/users/123
		WebTarget target = client.target("http://localhost:8080/DynamicLoading/rest2/");
		Response response = target.path("users/" + userId).request().get(Response.class);
		
		if(response.getStatus() != 200) { //Status.OK
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		
		return response.readEntity(User.class);
	}
	
	public List<User> get(){
		WebTarget target = client.target("http://localhost:8080/DynamicLoading/rest2/");
		
		List<User> responce = target.path("users").request(MediaType.APPLICATION_JSON).get( new GenericType< List<User> >(){} );
		
		return responce;		
	}

	public User create(User user) {
		//http://localhost:8080/DynamicLoading/rest2/users/user
		WebTarget target = client.target("http://localhost:8080/DynamicLoading/rest2/");
		
		Response response = target.path("users/user")
				.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(user, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200) { //Status.OK
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		
		return response.readEntity(User.class);
	}

	public User update(User user) {
		WebTarget target = client.target("http://localhost:8080/DynamicLoading/rest2/");
		
		Response response = target.path("users/" + user.getId())
				.request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(user, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() != 200) { //Status.OK
			throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
		}
		
		return response.readEntity(User.class);
	}

}

/*public User get(String userId) {
		//http://localhost:8080/DynamicLoading/rest2/users/123
		WebTarget target = client.target("http://localhost:8080/DynamicLoading/rest2/");
		User response = target.path("users/" + userId).request().get(User.class);
		return response;
	}*/
