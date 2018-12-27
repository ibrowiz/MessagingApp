package org.calminfotech.email.dao.inter;
import java.util.List;

//import org.calminfotech.client.model.Address;
import org.calminfotech.email.model.ActivityLogger;
import org.calminfotech.email.model.Client;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface ActivityLogDaoInter {
	public List<ActivityLogger> fetchAllLog();
	public void save(ActivityLogger activityLogger);
	List<ActivityLogger>fetchLogByEmail(int recipientId);
}
