package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.SetEmail;

public interface SetEmailBoInter {
	public List<SetEmail> fetch();	
	List<SetEmail>fetchEmailViaId(int emailId);
	public SetEmail getEmailViaId(int emailId);
	public void update(SetEmail setEmail);
}
