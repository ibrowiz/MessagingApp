package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.SetEmailBoInter;
import org.calminfotech.email.dao.inter.SetEmailDaoInter;
import org.calminfotech.email.model.SetEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SetEmailBoImpl implements SetEmailBoInter {
	
	@Autowired
	private SetEmailDaoInter setEmailDaoInter;
	
	@Override
	public List<SetEmail> fetch() {
		return setEmailDaoInter.fetch();
	}

	@Override
	public List<SetEmail> fetchEmailViaId(int emailId) {
		return setEmailDaoInter.fetchEmailViaId(emailId);
	}
	
	public SetEmail getEmailViaId(int emailId){
		return setEmailDaoInter.getEmailViaId(emailId);
	}

	@Override
	public void update(SetEmail setEmail) {
		setEmailDaoInter.update(setEmail);
		
	}

}
