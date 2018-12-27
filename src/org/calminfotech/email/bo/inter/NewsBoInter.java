package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.News;

public interface NewsBoInter {

public News save(News news);
	
	public void update(News news);
		
	public void delete(News news);
	
	public List<News> fetchAll();
	
	public News fetchNewsById(int Id);
	
}
