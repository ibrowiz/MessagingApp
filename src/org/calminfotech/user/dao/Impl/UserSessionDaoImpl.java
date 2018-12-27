package org.calminfotech.user.dao.Impl;

import java.util.List;

import org.calminfotech.user.daoInterface.UserSessionDao;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserSession;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserSessionDaoImpl implements UserSessionDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(UserSession userSession) {
		
	this.sessionFactory.getCurrentSession().save(userSession);
	}

	@Override
	public List<UserSession> fetchAll() {
		// TODO Auto-generated method stub
		
		List<UserSession> list = this.sessionFactory.getCurrentSession().createQuery("from UserSession").list();
		return list;
		
		
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<UserSession> getSessionByUsername(String username) {
		// TODO Auto-generated method stub
		
		List<UserSession> list = this.sessionFactory.getCurrentSession()
				.createQuery("from UserSession where username=?").setParameter(0, username).list();
		return list;
	}*/

	@Override
	public List<UserSession> getSessionByUserId(User user) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from UserSession where user = ? ")
				.setParameter(0, user).list();
		return list;
	}

	@Override
	public UserSession getUserSessionById(int Id) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(UserSession.class).add(Restrictions.eq("id", Id));

		List list = criteria.list();

		if (list.size() > 0)
			return (UserSession) list.get(0);

		return null;

	}
	

	
}
