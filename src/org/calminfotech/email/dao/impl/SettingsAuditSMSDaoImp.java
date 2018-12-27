package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.SettingsAuditSMSDaoInter;
import org.calminfotech.email.model.SetSMS;
import org.calminfotech.email.model.SettingsAuditSMS;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SettingsAuditSMSDaoImp implements SettingsAuditSMSDaoInter {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SettingsAuditSMS> fetch() {
List list = this.sessionFactory.getCurrentSession().createQuery("from SettingsAuditSMS").list();
		
		return (List<SettingsAuditSMS>) list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SettingsAuditSMS> fetchViaId(int Id) {
		List list = this.sessionFactory.getCurrentSession().createQuery("from SettingsAuditSMS where Id = ?").setParameter(0, Id).list();
		return  (List<SettingsAuditSMS>)list;
	}

	@Override
	public SettingsAuditSMS getViaId(int Id) {
List list = this.sessionFactory.getCurrentSession().createQuery("from SettingsAuditSMS where Id = ?").setParameter(0, Id).list();
		
		if (list.size() > 0)
			return (SettingsAuditSMS)list.get(0);
		
		return null;
	}

	@Override
	public void add(SettingsAuditSMS audit) {
		this.sessionFactory.getCurrentSession().save(audit);
	}

	@Override
	public void update(SettingsAuditSMS audit) {
		this.sessionFactory.getCurrentSession().update(audit);
	}

	@Override
	public void delete(SettingsAuditSMS audit) {
		this.sessionFactory.getCurrentSession().delete(audit);
	}
}
