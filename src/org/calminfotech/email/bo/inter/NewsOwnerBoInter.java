package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.NewsOwner;

public interface NewsOwnerBoInter {
public void save(NewsOwner newsOwner);
	
	public void update(NewsOwner newsOwner);
		
	public void delete(NewsOwner newsOwner);
	
	public List<NewsOwner> fetchAll();
	
	public NewsOwner fetchOwnerById(int Id);

}
