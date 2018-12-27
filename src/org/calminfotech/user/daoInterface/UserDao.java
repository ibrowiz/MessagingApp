package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface UserDao {
	
	List<User>fetchAll();
	
	void save(User user);
	
	void delete(User user);
	
	void update (User user);
	
	User getUserByEmail(String username);
	
	List<User> fetchUserByEmail(String username);
	
	User getUserById(int userId);
	
	User getUserRoleById(int userId);
	
	User getUserByUserAndPassword(String email, String password);
	
	public List<User> checkByUserNameAndPassword(String email, String password);
	
	

}
