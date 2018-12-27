package org.calminfotech.user.bo.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.daoInterface.UserDao;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserBoImpl implements UserBo{
	
	@Autowired
	private UserDao userDao;
	

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);
		return user;
		
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
		
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		User user = userDao.getUserById(userId);
		
		return user;
	}

	@Override
	public User getUserByUserNameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		User user = userDao.getUserByUserAndPassword(email, password);
		
		return user;
	}

	
	@Override
	public boolean createUser(UserForm userForm,
			HttpServletRequest httpServletRequest) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(user.getUserName());
		user.setUserPassword(user.getPassword());
		
		
		return false;
	}

	@Override
	public List<User> checkByUserNameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return userDao.checkByUserNameAndPassword(email, password);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		
		
		return userDao.getUserByEmail(email);
	}

	@Override
	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		return userDao.fetchAll();
	}

	@Override
	public User getUserRoleById(int userId) {
User user = userDao.getUserRoleById(userId);
		
		return user;
	}

	@Override
	public List<User> fetchUserByEmail(String username) {
		return this.userDao.fetchUserByEmail(username);
	}

	/*@Override
	public List<User> checkByUserNameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
