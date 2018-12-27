package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.DepositSmsBoInter;
import org.calminfotech.email.dao.inter.DepositSmsDaoInter;
import org.calminfotech.email.model.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class DepositSmsBoImpl implements DepositSmsBoInter{
	
	@Autowired
	private DepositSmsDaoInter depositSmsDao;

	@Override
	public List<Deposit> fetchDepositClients() {
		return depositSmsDao.fetchDepositClients();
	}

}
