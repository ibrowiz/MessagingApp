package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.SetEmail;


public interface SetEmailDaoInter {
	
	public List<SetEmail> fetch();	
	
	List<SetEmail>fetchEmailViaId(int emailId);
	
	public SetEmail getEmailViaId(int emailId);
	
	public void update(SetEmail setEmail);
}
