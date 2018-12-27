package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.ClientTypeDaoInter;
import org.calminfotech.email.model.ClientType;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientTypeDaoImpl implements ClientTypeDaoInter{
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientType> fetchType(String clientType) {
		List<ClientType> list = this.sessionFactory.getCurrentSession().createQuery("from ClientType where invstMandate = ?").setParameter(0, clientType).list();
		
		return (List<ClientType>) list;
	}

}
