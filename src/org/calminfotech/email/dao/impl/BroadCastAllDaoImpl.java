package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.BroadCastAllDaoInter;
import org.calminfotech.email.model.BroadCastAll;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BroadCastAllDaoImpl implements BroadCastAllDaoInter {

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<BroadCastAll> fetchAll() {
List<BroadCastAll> list = this.sessionFactory.getCurrentSession().createQuery("from BroadCastAll").list();
		
		return list;
	}


	@Override
	public List<BroadCastAll> fetchEmailById(int clientId1, int clientId2, String name) {
		 Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(BroadCastAll.class);
	        Criterion A = Restrictions.ge("clientId",clientId1);
	        Criterion B = Restrictions.le("clientId",clientId2);
	        Criterion C = Restrictions.like("name", name);
	        LogicalExpression orExp = Restrictions.or(Restrictions.and(A,B),C);
	        crit.add(orExp);
	        
	        return crit.list();
		
	}

}
