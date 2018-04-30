package common;

import java.util.HashMap;
import java.util.Map;

public class CommonContext {
 
	public static boolean IsAdmin(String username, String password) {
		String request = DataBase.admins.get(username);		
		return request == null ? false : (request.equals(username) ? true : false);
	}
	
	public static boolean IsProductionLeder(String username, String password) {
		String request = DataBase.productionLeaders.get(username);		
		return request == null ? false : (request.equals(username) ? true : false);
	}

}

class DataBase{
	static Map<String, String> admins = null; 
	static {
		admins = new HashMap<>();
		admins.put("root", "321");
	}	
	
	static Map<String, String> productionLeaders = null; 
	static {
		productionLeaders = new HashMap<>();
		productionLeaders.put("root", "321");
		productionLeaders.put("Pernille", "123");
	}	
}