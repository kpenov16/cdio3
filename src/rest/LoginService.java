package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("login")
public class LoginService {
	
	@GET
	@Produces("text/plain")
	public String getLoginPage()
	{
		// Consider moving all html-services to one service?
		return    "<div id=\"loginbox\">\r\n"
				+ "		<h1>Administrator login</h1>\r\n"
				+ "		<h2>Group 14</h2>\r\n"
				+ "		Username <input type=\"text\" name=\"username\"><br>\r\n"
				+ "		Password <input type=\"text\" name=\"password\"><br>\r\n"
				+ "		<button id=\"loginbutton\">Log in</button>\r\n"
				+ "		<br>\r\n"
				+ "</div>";
	}
	
}