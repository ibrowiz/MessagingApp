package org.calminfotech.email.dao.impl;


import java.util.List;


import org.calminfotech.email.dao.inter.SmsActivityLogDaoInter;
import org.calminfotech.email.model.ActivityLogger;
import org.calminfotech.email.model.SmsActivityLogger;
import org.calminfotech.user.models.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SmsActivityLogDaoImpl implements SmsActivityLogDaoInter {
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<SmsActivityLogger> fetchAllSmsLog() {
List list = this.sessionFactory.getCurrentSession().createQuery("from SmsActivityLogger").list();
		
		return (List<SmsActivityLogger>) list;
	}

	@Override
	public void save(SmsActivityLogger sActivityLogger) {
		this.sessionFactory.getCurrentSession().save(sActivityLogger);
	}

	@Override
	public List<SmsActivityLogger> fetchLogById(int Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SmsActivityLogger> fetchLogByUserId(User user) {
		List list = this.sessionFactory.getCurrentSession().createQuery(" from  SmsActivityLogger where user = ? and is_deleted != 1 ").setParameter(0, user).list();
		
		return (List<SmsActivityLogger>) list;
	}

	@Override
	public void delete(SmsActivityLogger sActivityLogger) {
		this.sessionFactory.getCurrentSession().delete(sActivityLogger.getUser());
	}

	@Override
	public SmsActivityLogger fetchLogByUserId1(User user) {
		List list = sessionFactory.getCurrentSession()
				.createQuery("from SmsActivityLogger where user = ? ")
				.setParameter(0, user).list();
		if (list.size() > 0)
			return (SmsActivityLogger)list.get(0);
		
		return null;
	}

/*	@Override
	public List<SmsActivityLogger> deletebyUserId(int userId) {
List list = this.sessionFactory.getCurrentSession().createQuery(" delete from  SmsActivityLogger where userId = ?").setParameter(0, userId).list();
		
		return (List<SmsActivityLogger>) list;
	}*/

	
	public void deletebyUserId(int userid) {
Query query = this.sessionFactory.getCurrentSession().createQuery("UPDATE SmsActivityLogger SET is_deleted = '1' WHERE user.userId = ?").setParameter(0, userid);
		System.out.println("the id is" + userid );
		//return (List<SmsActivityLogger>) list;
		query.executeUpdate();
	}

	@Override
	public void deleteLogs() {
		Query query = this.sessionFactory.getCurrentSession().createQuery("UPDATE SmsActivityLogger SET is_deleted = '1'");
		
		query.executeUpdate();
	}

}
