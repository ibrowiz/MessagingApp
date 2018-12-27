package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.ClientBoInter;
import org.calminfotech.email.dao.inter.ClientDaoInter;
import org.calminfotech.email.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientBoImpl implements ClientBoInter {

	@Autowired
	private ClientDaoInter clientDaoInter;
	
	@Override
	public List<Client> fetchAll() {
		return clientDaoInter.fetchAll();
	}


	@Override
	public List<Client> fetchEmailById(int clientId) {
		// TODO Auto-generated method stub
		return null;
	}

}
