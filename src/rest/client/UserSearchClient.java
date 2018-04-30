package rest.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import rest.model.User;

public class UserSearchClient {

	private Client client = null;
	
	public UserSearchClient() {
		client = ClientBuilder.newClient();
	}

	public List<User> search(String param, List<String> searchValuers){
		
		URI uri = UriBuilder.fromUri("http://localhost:8080/DynamicLoading/rest2/")
							.path("search/users")
							.queryParam(param, searchValuers)
							.build();
		
		WebTarget target = client.target(uri);
		
		List<User> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<User>>(){}) ;
		
		System.out.println(response);
		
		return response;
	}
}
