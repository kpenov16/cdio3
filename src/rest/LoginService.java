package main.java;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("login")
public class LoginService {
	
	@GET
	public String getLoginPage()
	{
		// Consider moving all html-services to one service?
		return "LOGIN DIV";
	}
}