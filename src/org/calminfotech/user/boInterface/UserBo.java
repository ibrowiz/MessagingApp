package org.calminfotech.user.boInterface;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.User;

public interface UserBo {
	
	List<User>fetchAll();
	
	User save(User user);
	
	void delete(User user);
	
	void update (User user);
	
	User getUserById(int userId);
	
	public User getUserRoleById(int userId);
	
	User getUserByEmail(String username);
	
	User getUserByUserNameAndPassword(String email, String password);
	
	List <User> checkByUserNameAndPassword(String email, String password);
		
	boolean createUser(UserForm userForm, HttpServletRequest httpServletRequest);
	
	public List<User> fetchUserByEmail(String username);

	//User checkByUserNameAndPassword(User user);
	
	}
