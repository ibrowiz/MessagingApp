package org.calminfotech.user.dao.Impl;


import java.util.ArrayList;
import java.util.List;

import org.calminfotech.user.daoInterface.UserDao;
import org.calminfotech.user.models.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> fetchAll() {
		// TODO Auto-generated method stub
		
		List list = this.sessionFactory.getCurrentSession().createQuery("from User where role !='admin'").list();
		
		return (List<User>) list;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().save(user);
		
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().delete(user);
		
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		
		this.sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		
		List list = this.sessionFactory.getCurrentSession().createQuery("from User where userId= ?").setParameter(0, userId).list();
		
		
		if (list.size() > 0)
			return (User)list.get(0);
		return null;
	}

	@Override
	public User getUserByUserAndPassword(String email, String password) {
				// TODO Auto-generated method stub
		List obj = this.sessionFactory.getCurrentSession()
				.createQuery("from User where email= ? and password= ?")
				.setParameter(0, email).setParameter(1, password).list();
		
		if (obj.size()>0)
			return(User)obj.get(0);
		return null;		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> checkByUserNameAndPassword(String email, String password) {
		// TODO Auto-generated method stub
	
	List obj = this.sessionFactory.getCurrentSession()
			.createQuery("from User where userName = ? and password = ? and isDeleted = 1")
			.setParameter(0, email).setParameter(1, password).list();
	
	return obj;
	
	}

	@Override
	public User getUserByEmail(String username) {
		// TODO Auto-generated method stub
		
		List list = this.sessionFactory.getCurrentSession()
				.createQuery("from User where email = ?")
				.setParameter(0, username).list();
		if (list.size() > 0)
			return (User) list.get(0);
		return null;
			}

	@Override
	public User getUserRoleById(int userId) {
List list = this.sessionFactory.getCurrentSession().createQuery("from User where userId = ?").setParameter(0, userId).list();
		
		
		if (list.size() > 0)
			return (User)list.get(0);
		return null;
	}

	@Override
	public List<User> fetchUserByEmail(String username) {
		List obj = this.sessionFactory.getCurrentSession()
				.createQuery("from User where userName = ?")
				.setParameter(0, username).list();
		
		return obj;
	}

	
	

}
