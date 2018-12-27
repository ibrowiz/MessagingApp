package org.calminfotech.email.dao.inter;

import java.util.List;
import org.calminfotech.email.model.SmsActivityLogger;
import org.calminfotech.user.models.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface SmsActivityLogDaoInter {
	public List<SmsActivityLogger> fetchAllSmsLog();
	public void save(SmsActivityLogger sActivityLogger);
	public void delete(SmsActivityLogger sActivityLogger);
	List<SmsActivityLogger>fetchLogById(int Id);
	public void deletebyUserId(int userid);
	
	public void deleteLogs();
	
	List<SmsActivityLogger> fetchLogByUserId(User user);
	
	public SmsActivityLogger fetchLogByUserId1(User user);
	

}
