package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.ActivityLogBoInter;
import org.calminfotech.email.dao.inter.ActivityLogDaoInter;
import org.calminfotech.email.model.ActivityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ActivityLogBoImpl implements ActivityLogBoInter {
	
	@Autowired
	private ActivityLogDaoInter activityLogDaoInter;

	@Override
	public List<ActivityLogger> fetchAllLog() {
		return activityLogDaoInter.fetchAllLog() ;
	}

	@Override
	public List<ActivityLogger> fetchLogByEmail(int recipientId) {
		return activityLogDaoInter.fetchLogByEmail(recipientId);
	}

	@Override
	public void save(ActivityLogger activityLogger) {
	activityLogDaoInter.save(activityLogger);
		
	}

}
