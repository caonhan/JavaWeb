package serp.dao;

import java.util.List;

import serp.model.Users;

public interface UserDao {
	List<Users> findAllUsers();
}
