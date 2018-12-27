package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.SettingsAuditEmail;


public interface SettingsAuditEmailDaoInter {
		
		public List<SettingsAuditEmail> fetch();	
			
		List<SettingsAuditEmail>fetchViaId(int Id);
			
		public SettingsAuditEmail getViaId(int Id);
			
		public void add(SettingsAuditEmail audit);
			
		public void update(SettingsAuditEmail audit);
			
		public void delete(SettingsAuditEmail audit);


}
