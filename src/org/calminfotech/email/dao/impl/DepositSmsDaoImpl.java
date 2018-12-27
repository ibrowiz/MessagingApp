package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.DepositSmsDaoInter;
import org.calminfotech.email.model.Deposit;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepositSmsDaoImpl implements DepositSmsDaoInter {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Deposit> fetchDepositClients() {
		@SuppressWarnings("unchecked")
		List<Deposit> list = this.sessionFactory.getCurrentSession().createQuery("from Deposit").list();
				
			return list;
	}
	
}
