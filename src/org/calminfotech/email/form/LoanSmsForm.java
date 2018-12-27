package org.calminfotech.email.form;

import javax.persistence.Entity;

@Entity
public class LoanSmsForm {
	
	int clientId;
	
	private String message;
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
