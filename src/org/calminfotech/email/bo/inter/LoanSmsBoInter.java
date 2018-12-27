package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.Loan;

public interface LoanSmsBoInter {
	
	List<Loan> fetchLoanClients();

}
