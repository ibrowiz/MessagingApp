package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.LoanSmsBoInter;
import org.calminfotech.email.dao.inter.LoanSmsDaoInter;
import org.calminfotech.email.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoanSmsBoImpl implements LoanSmsBoInter {
	
	@Autowired
	private LoanSmsDaoInter loanSmsDao;

	@Override
	public List<Loan> fetchLoanClients() {
		return loanSmsDao.fetchLoanClients();
	}

}
