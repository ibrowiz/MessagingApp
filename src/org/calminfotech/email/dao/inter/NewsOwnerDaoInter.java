package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.NewsOwner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface NewsOwnerDaoInter {

public void save(NewsOwner newsOwner);
	
	public void update(NewsOwner newsOwner);
		
	public void delete(NewsOwner newsOwner);
	
	public List<NewsOwner> fetchAll();
	
	public NewsOwner fetchOwnerById(int Id);
	
}
