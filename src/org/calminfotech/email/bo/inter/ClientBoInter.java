package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.Client;

public interface ClientBoInter {
	
	public List<Client> fetchAll();	
	List<Client>fetchEmailById(int clientId);

}
