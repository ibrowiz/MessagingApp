package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.NetWorthDaoInter;
import org.calminfotech.email.model.ClientType;
import org.calminfotech.email.model.NetWorth;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class NetWorthDaoImpl implements NetWorthDaoInter{
	

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<NetWorth> fetchNetWorth(double netWorthFrom, double netWorthTo) {
@SuppressWarnings("unchecked")
List<NetWorth> list = this.sessionFactory.getCurrentSession().createQuery("from NetWorth where netWorth BETWEEN ? AND ?")
.setParameter(0, netWorthFrom)
.setParameter(1, netWorthTo).list();
		
		return (List<NetWorth>) list;
	}

}
