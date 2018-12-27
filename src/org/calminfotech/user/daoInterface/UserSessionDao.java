package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserSession;

public interface UserSessionDao {

	public void save(UserSession userSession);
	
	public List<UserSession> fetchAll();
	
	public UserSession getUserSessionById(int Id);
	
	public List<UserSession> getSessionByUserId(User user);
}
