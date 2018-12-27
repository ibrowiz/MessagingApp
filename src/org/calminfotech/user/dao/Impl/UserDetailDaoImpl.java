package org.calminfotech.user.dao.Impl;

import java.util.List;
import org.calminfotech.user.daoInterface.UserDetailDao;
import org.calminfotech.user.models.UserDetail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailDaoImpl implements UserDetailDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UserDetail userDetail) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(userDetail);
		
	}

	@Override
	public void update(UserDetail userDetail) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(userDetail);
		
	}

	@Override
	public UserDetail getUserDetailById(int id) {
		// TODO Auto-generated method stub
		
		List list = sessionFactory.getCurrentSession().createQuery("from UserDetail where userId= ?").setParameter(0, id).list();
		if(list.size()>0)
		return (UserDetail) list;
		
	return null;
	}

	
}
