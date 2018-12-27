package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.SettingsAuditSMS;

public interface SettingsAuditSMSDaoInter {
	
public List<SettingsAuditSMS> fetch();	
	
	List<SettingsAuditSMS>fetchViaId(int Id);
	
	public SettingsAuditSMS getViaId(int Id);
	
	public void add(SettingsAuditSMS audit);
	
	public void update(SettingsAuditSMS audit);
	
	public void delete(SettingsAuditSMS audit);

}
