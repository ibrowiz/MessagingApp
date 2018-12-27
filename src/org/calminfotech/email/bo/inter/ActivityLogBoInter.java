package org.calminfotech.email.bo.inter;
import java.util.List;

import org.calminfotech.email.model.ActivityLogger;

public interface ActivityLogBoInter {
	public List<ActivityLogger> fetchAllLog();	
	public void save(ActivityLogger activityLogger);
	List<ActivityLogger>fetchLogByEmail(int recipientId);

}
