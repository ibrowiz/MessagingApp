package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.Client;
import org.calminfotech.email.model.BroadCastAll;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface BroadCastAllDaoInter {
	public List<BroadCastAll> fetchAll();	
	List<BroadCastAll>fetchEmailById(int clientId1, int clientId2, String name);
}
