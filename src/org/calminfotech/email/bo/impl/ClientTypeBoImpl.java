package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.ClientTypeBoInter;
import org.calminfotech.email.dao.inter.ClientTypeDaoInter;
import org.calminfotech.email.dao.inter.StockBroadCastDaoInter;
import org.calminfotech.email.model.ClientType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientTypeBoImpl implements ClientTypeBoInter{

	@Autowired
	private ClientTypeDaoInter clientTypeDaoInter;
	
	@Override
	public List<ClientType> fetchType(String clientType) {
		return clientTypeDaoInter.fetchType(clientType);
	}

}
