package org.calminfotech.user.bo.impl;

import org.calminfotech.user.boInterface.UserDetailBo;
import org.calminfotech.user.daoInterface.UserDetailDao;
import org.calminfotech.user.models.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailBoImpl implements UserDetailBo{

	@Autowired
	private UserDetailDao userDetailDao;
	
	@Override
	public void save(UserDetail userDetail) {
		// TODO Auto-generated method stub
		
		userDetailDao.save(userDetail);	
	}

	@Override
	public void update(UserDetail userDetail) {
		// TODO Auto-generated method stub
		
		userDetailDao.update(userDetail);
		
	}

	@Override
	public UserDetail getUserDetailById(int userId) {
		// TODO Auto-generated method stub
		return userDetailDao.getUserDetailById(userId);
	}

}
