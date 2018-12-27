package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.News;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface NewsDaoInter {
	
	public News save(News news);
	
	public void update(News news);
		
	public void delete(News news);
	
	public List<News> fetchAll();
	
	public News fetchNewsById(int Id);
}
