package org.calminfotech.user.boInterface;

import org.calminfotech.user.models.UserDetail;

public interface UserDetailBo {
	
	public void save(UserDetail userDetail);
	
	public void update(UserDetail userDetail);
	
	public UserDetail getUserDetailById(int userId);

}
