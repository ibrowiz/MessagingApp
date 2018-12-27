package org.calminfotech.email.dao.inter;

import java.util.List;
import org.calminfotech.email.model.Client;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ClientDaoInter {
	public List<Client> fetchAll();	
	List<Client>fetchEmailById(int clientId);
	
}
