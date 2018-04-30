package rest;

import data.UserDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/users")
public class GetSingleUserService {
	
	@GET
	@Path("{id}")
	public Response getUserByID(@PathParam("id") String id)
	{
		UserDAO curData = UserDAO.getInstance();
		return Response.status(200).entity("getUserById is called. username:" + curData.getUser(Integer.parseInt(id)).getUserName()).build();
	}
	
}