package rest.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import rest.LogInViewModel;
import rest.model.User;
import rest.repository.UserRepository;
import rest.repository.UserRepositoryStub;

@Path("users")
public class UserResource {
	private UserRepository userRepository = UserRepositoryStub.getInstance();
	
	@DELETE
	@Path("{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser( @PathParam("userId") String userId) {
		System.out.format("User id: %s", userId);
		userRepository.delete(userId);
		return Response.ok().build();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers(){
		
		return userRepository.getAllUsers();
	}
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		if(userId == null || userId.length() > 45) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
		User user = userRepository.getUser(userId);
		if(user == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
				
		return Response.status(Status.OK).entity(user).build();
	}
	
	@POST
	@Path("user")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User user) {
		System.out.format("%s %s", user.getFirstName(), user.getLastName());
		
		userRepository.create(user);
		
		return user;
	}

	@POST
	@Path("all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers(final LogInViewModel vm) {
		
		System.out.format("%s %s", vm.username, vm.password);
		
		List<User> users = userRepository.getAllUsers();
	
		if(users==null || users.size()<=0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().entity( new GenericEntity<List<User>>(users){} ).build();		
	}
	
	
	@DELETE
	@Path("Id")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUserById( final LogInViewModel vm ) {
		System.out.format("User id: %s", vm.idUserToDelete);
		User user = userRepository.deleteUserById(vm.idUserToDelete);
		if(user == null) {
			System.out.format("User id: %s was not deleted!", vm.idUserToDelete);
		}		
		//return Response.ok().build();
		return Response.status(Status.OK).entity(user).build();
	}
	
	@PUT
	@Path("all")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsers(final LogInViewModel vm) {
		System.out.format("%s %s %b", vm.users[0].getFirstName(), vm.users[0].getLastName(), vm.users[0].getRole().isAdmin());
		
		List<User> users = userRepository.updateUsers( new ArrayList<User>(Arrays.asList(vm.users)) );
		//I need to check if the update was ok or not and report that
		return Response.ok().entity(new GenericEntity<List<User>>(users){}).build();		
	}
	
	@PUT
	@Path("{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) {
		System.out.format("%s %s %s", user.getId(), user.getFirstName(), user.getLastName());
		
		user = userRepository.update(user);
		
		return Response.ok().entity(user).build();
	}
}

/*
@GET
@Produces(MediaType.APPLICATION_JSON)
@Path("{userId}")
public User getUser(@PathParam("userId") String userId) {
	return userRepository.getUser(userId);
}*/
