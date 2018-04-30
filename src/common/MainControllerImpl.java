package common;

import java.util.ArrayList;
import java.util.List;

import dataaccess.IUserDAO;
import dataaccess.IUserDAO.DALException;
import dataaccess.UserDAO;
import dto.UserDTO;
import rest.model.UserRole;

public class MainControllerImpl implements MainController {

	private String username = null;
	private String password = null;
	private String path = null;
	private List<UserDTO> users = null;
	
	private IUserDAO dao = null;	
	
	private void loadData(String path){			
		try {
			dao = new UserDAO(path);//"data.bin");
			dao.createUser(new UserDTO( 1, "Pernille","Larsen", "pl", "0000-12345", "321", true, new UserRole(true,true,false,false) ));
			dao.createUser(new UserDTO( 2, "Emma", "Birkelund", "eb", "0001-12345", "123", true, new UserRole(true,true,false,false) ));		
		} catch (DALException e) {
			e.printStackTrace();			
		}finally {

		}		
	}
	
	public MainControllerImpl(String username, String password, String path) {
		this.username = username;
		this.password = password;		
		this.path = path;

		loadData(path);
		
		try {
			users = dao.getUserList();
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
	
	public List<UserDTO> getUsersAsDTO(){
		return users;
	}

}
