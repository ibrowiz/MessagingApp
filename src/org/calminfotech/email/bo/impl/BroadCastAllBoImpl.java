package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.BroadCastAllBoInter;
import org.calminfotech.email.dao.inter.BroadCastAllDaoInter;
import org.calminfotech.email.model.BroadCastAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BroadCastAllBoImpl implements BroadCastAllBoInter {
	
	@Autowired
	private BroadCastAllDaoInter clientNbDaoInter;

	@Override
	public List<BroadCastAll> fetchAll() {
		return clientNbDaoInter.fetchAll();
	}

	@Override
	public List<BroadCastAll> fetchEmailById(int clientId1, int clientId2, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
