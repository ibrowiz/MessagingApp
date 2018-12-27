package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.NewsOwnerDaoInter;
import org.calminfotech.email.model.News;
import org.calminfotech.email.model.NewsOwner;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NewsOwnerDaoImpl implements NewsOwnerDaoInter  {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(NewsOwner newsOwner) {
		this.sessionFactory.getCurrentSession().save(newsOwner);
	}

	@Override
	public void update(NewsOwner newsOwner) {
		this.sessionFactory.getCurrentSession().update(newsOwner);
	}

	@Override
	public void delete(NewsOwner newsOwner) {
		this.sessionFactory.getCurrentSession().delete(newsOwner);
	}

	@Override
	public List<NewsOwner> fetchAll() {
		List list = this.sessionFactory.getCurrentSession().createQuery("from NewsOwner").list();
		return (List<NewsOwner>)list;
	}

	@Override
	public NewsOwner fetchOwnerById(int Id) {
List list = this.sessionFactory.getCurrentSession().createQuery("from NewsOwner Id=?").setParameter(0, Id).list();
		
		if (list.size() > 0)
			return (NewsOwner)list.get(0);
		
		return null;
	}

}
