package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SmsActivityLogForm {
	
	private int Id;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getAliasFrom() {
		return aliasFrom;
	}

	public void setAliasFrom(String aliasFrom) {
		this.aliasFrom = aliasFrom;
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

	private String to;
	
	private String from;

	private String aliasFrom;
	
	private String message;
	
	private  Date sentDate;

}
