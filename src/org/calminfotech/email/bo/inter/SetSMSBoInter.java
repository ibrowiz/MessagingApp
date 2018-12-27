package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.SetSMS;

public interface SetSMSBoInter {
	
	public List<SetSMS> fetchactive();
	
	public List<SetSMS> fetch();	
	
	List<SetSMS>fetchSMSViaId(int Id);
	
	public SetSMS getSMSViaId(int Id);
	
	public void add(SetSMS setSMS);
	
	public void update(SetSMS setSMS);
	
	public void delete(SetSMS setSMS);
}

