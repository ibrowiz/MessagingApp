package org.calminfotech.email.bo.impl;

import java.util.List;


import org.calminfotech.email.bo.inter.NewsOwnerBoInter;
import org.calminfotech.email.dao.inter.NewsOwnerDaoInter;
import org.calminfotech.email.model.NewsOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NewsOwnerBoImpl implements NewsOwnerBoInter {
	
	@Autowired
	private NewsOwnerDaoInter newsOwnerDao;

	@Override
	public void save(NewsOwner newsOwner) {
		this.newsOwnerDao.save(newsOwner);
	}

	@Override
	public void update(NewsOwner newsOwner) {
		this.newsOwnerDao.update(newsOwner);
	}

	@Override
	public void delete(NewsOwner newsOwner) {
		this.newsOwnerDao.delete(newsOwner);
	}

	@Override
	public List<NewsOwner> fetchAll() {
		return this.newsOwnerDao.fetchAll();
	}

	@Override
	public NewsOwner fetchOwnerById(int Id) {
		return this.newsOwnerDao.fetchOwnerById(Id);
	}

}
