package org.calminfotech.email.bo.impl;
import java.util.List;

import org.calminfotech.email.bo.inter.SetSMSBoInter;
import org.calminfotech.email.dao.inter.SetSMSDaoInter;
import org.calminfotech.email.model.SetSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetSMSBoImpl implements SetSMSBoInter {
	
	@Autowired
	private SetSMSDaoInter setSMSDao;
	
	@Override
	public List<SetSMS> fetchactive() {
		return setSMSDao.fetchactive();
	}

	@Override
	public List<SetSMS> fetch() {
		return setSMSDao.fetch();
	}

	@Override
	public List<SetSMS> fetchSMSViaId(int Id) {
		return setSMSDao.fetchSMSViaId(Id);
	}

	@Override
	public SetSMS getSMSViaId(int Id) {
		return setSMSDao.getSMSViaId(Id);
	}

	@Override
	public void update(SetSMS setSMS) {
		 setSMSDao.update(setSMS);
		
	}

	@Override
	public void add(SetSMS setSMS) {
		setSMSDao.add(setSMS);
	}

	@Override
	public void delete(SetSMS setSMS) {
		setSMSDao.delete(setSMS);
	}



}
