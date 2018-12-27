package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.ClientDaoInter;
import org.calminfotech.email.model.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDaoImpl implements ClientDaoInter {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> fetchAll() {
List list = this.sessionFactory.getCurrentSession().createQuery("select C.emailAddress from Client C").list();
		
		return (List<Client>) list;
	}

	@Override
	public List<Client> fetchEmailById(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
