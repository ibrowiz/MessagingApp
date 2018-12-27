package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.SmsActivityLogger;
import org.calminfotech.user.models.User;

public interface SmsActivityLogBoInter {

	public List<SmsActivityLogger> fetchAllSmsLog();
	public void save(SmsActivityLogger sActivityLogger);
	public void delete(SmsActivityLogger sActivityLogger);
	List<SmsActivityLogger>fetchLogById(int Id);
	public SmsActivityLogger fetchLogByUserId1(int userId);
	List<SmsActivityLogger>fetchLogByUserId(int userId);
	public void deletebyUserId(int userId);
	
	public void deleteLogs();
	
}
