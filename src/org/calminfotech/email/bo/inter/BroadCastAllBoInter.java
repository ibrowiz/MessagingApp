package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.Client;
import org.calminfotech.email.model.BroadCastAll;

public interface BroadCastAllBoInter {
	public List<BroadCastAll> fetchAll();	
	List<BroadCastAll>fetchEmailById(int clientId1, int clientId2, String name );

}
