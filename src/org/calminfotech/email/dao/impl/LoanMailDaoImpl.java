package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.LoanMailDaoInter;
import org.calminfotech.email.model.Loan;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class LoanMailDaoImpl implements LoanMailDaoInter {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	/*public List<Loan> fetchLoanClients() {
@SuppressWarnings("unchecked")
List list = this.sessionFactory.getCurrentSession().createQuery("from Loan").list();
		
	return (List<Loan>)list;
	}*/
	public List<Loan> fetchLoanClients() {
		List list = this.sessionFactory.getCurrentSession().createQuery("from Loan").list();
		return list;
	}

}
