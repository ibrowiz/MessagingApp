package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.SettingsAuditEmailBoInter;
import org.calminfotech.email.dao.inter.SettingsAuditEmailDaoInter;
import org.calminfotech.email.model.SettingsAuditEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class SettingsAuditEmailBoImpl implements SettingsAuditEmailBoInter {
	
	@Autowired
	private SettingsAuditEmailDaoInter settingsAuditEmailDao;

	@Override
	public List<SettingsAuditEmail> fetch() {
		return settingsAuditEmailDao.fetch();
	}

	@Override
	public List<SettingsAuditEmail> fetchViaId(int Id) {
		return settingsAuditEmailDao.fetchViaId(Id);
	}

	@Override
	public SettingsAuditEmail getViaId(int Id) {
		return settingsAuditEmailDao.getViaId(Id);
	}

	@Override
	public void add(SettingsAuditEmail audit) {
		settingsAuditEmailDao.add(audit);
	}

	@Override
	public void update(SettingsAuditEmail audit) {
		settingsAuditEmailDao.update(audit);
	}

	@Override
	public void delete(SettingsAuditEmail audit) {
		settingsAuditEmailDao.update(audit);
	}

}
