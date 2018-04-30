package rest.repository;

import java.util.ArrayList;
import java.util.List;

import rest.model.User;

public interface UserRepository {

	List<User> getAllUsers();

	User getUser(String userId);

	void create(User user);

	User update(User user);

	void delete(String userId);

	List<User> findById(List<String> ids);

	List<User> updateUsers(ArrayList<User> users);

	User deleteUserById(int userId);

}