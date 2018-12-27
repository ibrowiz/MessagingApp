package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SmsClientTypeForm {
	
	private String clientType;
	
	private String message;
	
	private  Date sentDate;

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
}
