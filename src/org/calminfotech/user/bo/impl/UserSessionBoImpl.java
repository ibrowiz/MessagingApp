package org.calminfotech.user.bo.impl;

import java.util.List;

import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserSessionBo;
import org.calminfotech.user.daoInterface.UserSessionDao;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserSessionBoImpl implements UserSessionBo{

	@Autowired
	private UserSessionDao userSessionDao;
	
	@Autowired
	private UserBo userBo;
	
	@Override
	public UserSession save(UserSession userSession) {
		// TODO Auto-generated method stub
		
		userSessionDao.save(userSession);
		return userSession;
		
		
	}

	@Override
	public List<UserSession> fetchAll() {
		// TODO Auto-generated method stub
		
		List <UserSession> list = userSessionDao.fetchAll();
		return list;
	}

	@Override
	public UserSession getUserSessionById(int Id) {
		return userSessionDao.getUserSessionById(Id);
	}

	@Override
	public List<UserSession> getSessionByUserId(int userId) {
		User user = userBo.getUserById(userId);
		return userSessionDao.getSessionByUserId(user);
	}

	
	

}
