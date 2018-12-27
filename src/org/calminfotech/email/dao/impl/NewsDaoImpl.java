package org.calminfotech.email.dao.impl;

import java.util.List;


import org.calminfotech.email.dao.inter.NewsDaoInter;
import org.calminfotech.email.model.News;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NewsDaoImpl implements NewsDaoInter{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public News save(News news) {
		this.sessionFactory.getCurrentSession().save(news);
		return news;	
	}

	@Override
	public void update(News news) {
		this.sessionFactory.getCurrentSession().update(news);
	}

	@Override
	public void delete(News news) {
		this.sessionFactory.getCurrentSession().delete(news);
	}

	@Override
	public List<News> fetchAll() {
		List list = this.sessionFactory.getCurrentSession().createQuery("from News").list();
		return (List<News>)list;
	}

	@Override
	public News fetchNewsById(int Id) {
List list = this.sessionFactory.getCurrentSession().createQuery("from News where Id=?").setParameter(0, Id).list();
		
		if (list.size() > 0)
			return (News)list.get(0);
		
		return null;
	}

}
