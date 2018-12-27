package org.calminfotech.email.bo.impl;

import java.util.List;


import org.calminfotech.email.bo.inter.NetWorthBoInter;
import org.calminfotech.email.dao.inter.NetWorthDaoInter;
import org.calminfotech.email.model.NetWorth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NetWorthBoImpl implements NetWorthBoInter {
	
	@Autowired
	NetWorthDaoInter networthDaoInter;

	@Override
	public List<NetWorth> fetchNetWorth(double netWorthFrom, double netWorthTo) {
		return networthDaoInter.fetchNetWorth(netWorthFrom,netWorthTo);
	}

}
