package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.Deposit;

public interface DepositMailBoInter {

	List<Deposit> fetchDepositClients();
}
