package rest;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import common.MainController;
import common.MainControllerImpl;
import dto.UserDTO;
import rest.model.LogInResponceViewModel;
import rest.model.User;
import rest.model.UserRole;


@Path("login")
public class LoginService {
	
	//@Context ServletContext servletContext;
	
	//$postParams = @{"@type"="LogInViewModel"; "username"="root"; "password" = "321";}
	//$postParams = @{"username"="root"; "password" = "321";}
	//Invoke-WebRequest -Uri http://localhost:8080/DynamicLoading/rest/login -Method POST -Body ($postParams|ConvertTo-Json) -ContentType "application/json"
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LogInResponceViewModel logIn(final LogInViewModel vm) {
				
		File file = new File(new File("/files"), "data.bin");
		String path = file.getAbsolutePath();
				
		LogInResponceViewModel rvm = null;
		
		MainController controller = new MainControllerImpl(vm.username, vm.password, path); 
		
		rvm = getLogInResponceViewModel(controller.getUsersAsDTO());
		
		/*
		if(vm.username.equals("root") && vm.password.equals("321")) {
			// ( CommonContext.IsAdmin(vm.username, vm.password) ) {
			rvm = new LogInResponceViewModel(
					true, 
					new String[]{"admin", "leader"}, 
					new User[]{ new User("Pernille", "Larsen"), new User("Emma", "Larsen")}
					); 
		}else {
			rvm = new LogInResponceViewModel(
					false, new String[]{}, new User[]{}
					);
		}*/
		return rvm;
	}
	
	private LogInResponceViewModel getLogInResponceViewModel(List<UserDTO> usersAsDTO) {
		
		List<User> parsedUsers = new ArrayList<>();		 
		for(UserDTO user : usersAsDTO) {			
			parsedUsers.add(
					new User(user.getId(), user.getFirstName(), user.getLastName(), 
							 user.getInitials(), user.getCpr(), user.getUserName(), user.getPassword(), 
							 user.isActive(), new UserRole(
									 			user.getUserRole().isAdmin(), user.getUserRole().isLeader(), 
									 			user.getUserRole().isPharmacist(), user.getUserRole().isTechnician()
									 			)//the UserRole could be just passed as user.getUserRole() but as UserDTO is from another domain there should be UserRoleDTO as well, I use here the same class to speed up a bit 
							 )
					);
		}
		User[] users = parsedUsers.toArray(new User[parsedUsers.size()]);
		return new LogInResponceViewModel(
					true, 
					new String[]{"admin", "leader"}, 
					users
		);		
	}

}

