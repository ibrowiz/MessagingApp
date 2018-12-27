package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.NewsBoInter;
import org.calminfotech.email.dao.inter.NewsDaoInter;
import org.calminfotech.email.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsBoImpl implements NewsBoInter {
	
	@Autowired
	private NewsDaoInter newsDao;

	@Override
	public News save(News news) {
		return this.newsDao.save(news);
		
		
	}

	@Override
	public void update(News news) {
		this.newsDao.update(news);		
	}

	@Override
	public void delete(News news) {
	this.newsDao.delete(news);
	}

	@Override
	public List<News> fetchAll() {
		return this.newsDao.fetchAll();
	}

	@Override
	public News fetchNewsById(int Id) {
		return this.newsDao.fetchNewsById(Id);
	}

}
