package rest.view;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rest.model.User;
import rest.repository.UserRepository;
import rest.repository.UserRepositoryStub;

@Path("search/users")
public class UserSearchResource {

	private UserRepository userRepository = new UserRepositoryStub();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchForUsers(@QueryParam(value = "id") List<String> ids) {
		System.out.println(ids);
		List<User> users = userRepository.findById(ids);
		if(users==null || users.size()<=0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().entity( new GenericEntity<List<User>>(users){} ).build();
	}
	
}
