package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ActivityLogForm {
	
	private int recipientId;
	
	private String to;
	
	private String emailFrom;

	private String aliasFrom;

	private String subject;
	
	private String message;
	
	private  Date sentDate;

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getAliasFrom() {
		return aliasFrom;
	}

	public  void setAliasFrom(String aliasFrom) {
		this.aliasFrom = aliasFrom;
	}

	public  String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
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
