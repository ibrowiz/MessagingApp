package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.LoanSmsDaoInter;
import org.calminfotech.email.model.Loan;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoanSmsDaoImpl implements LoanSmsDaoInter {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Loan> fetchLoanClients() {
		@SuppressWarnings("unchecked")
		List<Loan> list = this.sessionFactory.getCurrentSession().createQuery("from Loan").list();
				
			return list;
	}

}
