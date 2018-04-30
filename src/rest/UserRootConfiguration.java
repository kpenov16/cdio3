package rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import rest.view.UserResource;

@ApplicationPath("rest2")
public class UserRootConfiguration extends Application {
	@Override
	public Set<Class<?>> getClasses(){
		Set<Class<?>> resources = new HashSet<>();
		resources.add(UserResource.class);
		return resources;
	}
}
