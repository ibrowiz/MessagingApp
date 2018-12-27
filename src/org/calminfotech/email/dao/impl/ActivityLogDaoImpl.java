package org.calminfotech.email.dao.impl;
import java.util.List;

import org.calminfotech.email.dao.inter.ActivityLogDaoInter;
import org.calminfotech.email.model.ActivityLogger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityLogDaoImpl implements ActivityLogDaoInter {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<ActivityLogger> fetchAllLog() {
List list = this.sessionFactory.getCurrentSession().createQuery(" from  ActivityLogger").list();
		
		return (List<ActivityLogger>) list;
	}

	@Override
	public List<ActivityLogger> fetchLogByEmail(int recipientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ActivityLogger activityLogger) {
		this.sessionFactory.getCurrentSession().save(activityLogger);
		
	}

	
}
