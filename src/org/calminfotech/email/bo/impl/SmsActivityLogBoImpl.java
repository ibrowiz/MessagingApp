package org.calminfotech.email.bo.impl;

import java.util.List;
import org.calminfotech.email.bo.inter.SmsActivityLogBoInter;
import org.calminfotech.email.dao.inter.SmsActivityLogDaoInter;
import org.calminfotech.email.model.SmsActivityLogger;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class SmsActivityLogBoImpl implements SmsActivityLogBoInter {
	@Autowired
	private UserBo userBo;
	
	@Autowired
	private SmsActivityLogDaoInter smsActivityLogDao;

	@Override
	public List<SmsActivityLogger> fetchAllSmsLog() {
		return smsActivityLogDao.fetchAllSmsLog();
	}

	@Override
	public void save(SmsActivityLogger sActivityLogger) {
		smsActivityLogDao.save(sActivityLogger);
	}

	@Override
	public List<SmsActivityLogger> fetchLogById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmsActivityLogger> fetchLogByUserId(int userId) {
		User user = this.userBo.getUserById(userId);
		return this.smsActivityLogDao.fetchLogByUserId(user);
	}

	@Override
	public void delete(SmsActivityLogger sActivityLogger) {
		this.smsActivityLogDao.delete(sActivityLogger);
	}

	@Override
	public SmsActivityLogger fetchLogByUserId1(int userId) {
		User user = this.userBo.getUserById(userId);
		return this.smsActivityLogDao.fetchLogByUserId1(user);
	}

	@Override
	public void deletebyUserId(int userId) {
		//User user = this.userBo.getUserById(userId);
		this.smsActivityLogDao.deletebyUserId(userId);
	}

	@Override
	public void deleteLogs() {
		this.smsActivityLogDao.deleteLogs();
	}

	

}
