package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserSession;

public interface UserSessionBo {
	
	public UserSession save(UserSession userSession);
	
	public List<UserSession>fetchAll();
	
public UserSession getUserSessionById(int Id);
	
	public List<UserSession> getSessionByUserId(int userId);
}
