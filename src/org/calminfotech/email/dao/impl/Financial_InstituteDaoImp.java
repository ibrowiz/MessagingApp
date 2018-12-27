package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.Financial_InstituteDaoInter;
import org.calminfotech.email.model.Financial_Institute;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Financial_InstituteDaoImp  implements Financial_InstituteDaoInter {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Financial_Institute> fetchAllInst() {
@SuppressWarnings("unchecked")
List<Financial_Institute> list = this.sessionFactory.getCurrentSession().createQuery("from Financial_Institute").list();
		
		return list;
	}

}
