package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.Loan;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface LoanSmsDaoInter {
	List<Loan> fetchLoanClients();
}
