package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.SettingsAuditSMSBoInter;
import org.calminfotech.email.dao.inter.SettingsAuditSMSDaoInter;
import org.calminfotech.email.model.SettingsAuditSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SettingsAuditSMSBoImpl implements SettingsAuditSMSBoInter {
	
	@Autowired
	private SettingsAuditSMSDaoInter settingsAuditSMSDao;

	@Override
	public List<SettingsAuditSMS> fetch() {
		return settingsAuditSMSDao.fetch();
	}

	@Override
	public List<SettingsAuditSMS> fetchViaId(int Id) {
		return settingsAuditSMSDao.fetchViaId(Id);
	}

	@Override
	public SettingsAuditSMS getViaId(int Id) {
		return settingsAuditSMSDao.getViaId(Id);
	}

	@Override
	public void add(SettingsAuditSMS audit) {
		settingsAuditSMSDao.add(audit);
	}

	@Override
	public void update(SettingsAuditSMS audit) {
		settingsAuditSMSDao.update(audit);
	}

	@Override
	public void delete(SettingsAuditSMS audit) {
		settingsAuditSMSDao.delete(audit);
	}

}
