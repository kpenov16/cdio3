package rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		try {
			json = objectMapper.writeValueAsString(curData.getUser(Integer.parseInt(id)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return Response.status(200).entity("getUserById is called.  " + json).build();
	}
	
}