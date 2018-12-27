package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.DepositMailBoInter;
import org.calminfotech.email.dao.inter.DepositMailDaoInter;
import org.calminfotech.email.model.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepositMailBoImpl implements DepositMailBoInter {
	
	@Autowired
	private DepositMailDaoInter depositMailDao;

	@Override
	public List<Deposit> fetchDepositClients() {
	return depositMailDao.fetchDepositClients();
	}

}
