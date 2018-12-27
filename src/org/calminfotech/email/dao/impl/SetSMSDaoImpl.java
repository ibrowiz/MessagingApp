package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.SetSMSDaoInter;
import org.calminfotech.email.model.SetSMS;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SetSMSDaoImpl implements SetSMSDaoInter {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SetSMS> fetchactive() {
		List list = this.sessionFactory.getCurrentSession().createQuery("from SetSMS where status = 'active'").list();
		return (List<SetSMS>) list;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SetSMS> fetch() {
		List list = this.sessionFactory.getCurrentSession().createQuery("from SetSMS").list();
		
		return (List<SetSMS>) list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SetSMS> fetchSMSViaId(int Id) {
		List list = this.sessionFactory.getCurrentSession().createQuery("from SetSMS where Id = ?").setParameter(0, Id).list();
		return  (List<SetSMS>)list;
	}

	@Override
	public SetSMS getSMSViaId(int Id) {
List list = this.sessionFactory.getCurrentSession().createQuery("from SetSMS where Id = ?").setParameter(0, Id).list();
		
		if (list.size() > 0)
			return (SetSMS)list.get(0);
		
		return null;
	}

	@Override
	public void update(SetSMS setSMS) {
		this.sessionFactory.getCurrentSession().update(setSMS);
		
	}

	@Override
	public void add(SetSMS setSMS) {
		this.sessionFactory.getCurrentSession().save(setSMS);
	}

	@Override
	public void delete(SetSMS setSMS) {
		this.sessionFactory.getCurrentSession().delete(setSMS);
		
	}

	

}
