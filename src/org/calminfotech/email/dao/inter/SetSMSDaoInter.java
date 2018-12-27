package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.SetSMS;

public interface SetSMSDaoInter {
	public List<SetSMS> fetch();	
	
	public List<SetSMS> fetchactive();

	List<SetSMS>fetchSMSViaId(int Id);
	
	public SetSMS getSMSViaId(int Id);
	
	public void add(SetSMS setSMS);
	
	public void update(SetSMS setSMS);
	
	public void delete(SetSMS setSMS);
}
