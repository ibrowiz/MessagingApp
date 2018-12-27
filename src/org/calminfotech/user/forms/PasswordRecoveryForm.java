package org.calminfotech.user.forms;

import javax.persistence.Entity;

@Entity
public class PasswordRecoveryForm {
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
