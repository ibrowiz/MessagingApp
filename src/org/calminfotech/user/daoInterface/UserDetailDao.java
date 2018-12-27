package org.calminfotech.user.daoInterface;

import org.calminfotech.user.models.UserDetail;

public interface UserDetailDao {
	
	public void save(UserDetail userDetail);
	
	public void update(UserDetail userDetail);
	
	public UserDetail getUserDetailById(int userId);

}
