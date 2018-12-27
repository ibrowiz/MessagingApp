package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.SettingsAuditEmailDaoInter;
import org.calminfotech.email.model.SettingsAuditEmail;
import org.calminfotech.email.model.SettingsAuditSMS;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SettingsAuditEmailDaoImpl implements SettingsAuditEmailDaoInter{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SettingsAuditEmail> fetch() {
List list = this.sessionFactory.getCurrentSession().createQuery("from SettingsAuditEmail").list();
		
		return (List<SettingsAuditEmail>) list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SettingsAuditEmail> fetchViaId(int Id) {
		List list = this.sessionFactory.getCurrentSession().createQuery("from SettingsAuditEmail where Id = ?").setParameter(0, Id).list();
		return  (List<SettingsAuditEmail>)list;
	}

	@Override
	public SettingsAuditEmail getViaId(int Id) {
List list = this.sessionFactory.getCurrentSession().createQuery("from SettingsAuditEmail where Id = ?").setParameter(0, Id).list();
		
		if (list.size() > 0)
			return (SettingsAuditEmail)list.get(0);
		
		return null;
	}

	@Override
	public void add(SettingsAuditEmail audit) {
		this.sessionFactory.getCurrentSession().save(audit);
	}

	@Override
	public void update(SettingsAuditEmail audit) {
		this.sessionFactory.getCurrentSession().update(audit);
	}

	@Override
	public void delete(SettingsAuditEmail audit) {
		this.sessionFactory.getCurrentSession().delete(audit);
	}

}
