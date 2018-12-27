package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.SetEmailDaoInter;
import org.calminfotech.email.model.SetEmail;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SetEmailDaoImpl implements SetEmailDaoInter {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SetEmail> fetch() {
List list = this.sessionFactory.getCurrentSession().createQuery("from SetEmail").list();
		
		return /*(List<SetEmail>)*/list;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public List<SetEmail> fetchEmailViaId(int emailId) {
		List list = this.sessionFactory.getCurrentSession().createQuery("from SetEmail where emailId = ?").setParameter(0, emailId).list();
		return  (List<SetEmail>)list;
	}

	@Override
	public SetEmail getEmailViaId(int emailId) {
List list = this.sessionFactory.getCurrentSession().createQuery("from SetEmail where emailId = ?").setParameter(0, emailId).list();
		
		if (list.size() > 0)
			return (SetEmail)list.get(0);
		
		return null;
	}

	@Override
	public void update(SetEmail setEmail) {
		this.sessionFactory.getCurrentSession().update(setEmail);
	}
	
	

}
