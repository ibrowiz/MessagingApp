package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.LoanMailBoInter;
import org.calminfotech.email.dao.inter.LoanMailDaoInter;
import org.calminfotech.email.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanMailBoImpl implements LoanMailBoInter {
	
	@Autowired
	private LoanMailDaoInter loanMailDao;

	@Override
	public List<Loan> fetchLoanClients() {
		return loanMailDao.fetchLoanClients();
	}

}
