package rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import rest.model.User;

@XmlRootElement
public class LogInViewModel{
	@XmlElement public String username;
	@XmlElement public String password;
	@XmlElement public User[] users;
	@XmlElement public int idUserToDelete = -1;
	
}