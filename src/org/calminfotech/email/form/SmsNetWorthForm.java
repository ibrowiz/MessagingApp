package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SmsNetWorthForm {
	
	private int clientId;
	
private double netWorthFrom;
	
	private double netWorthTo;
		
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

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	private  Date sentDate;

	public double getNetWorthFrom() {
		return netWorthFrom;
	}

	public void setNetWorthFrom(double netWorthFrom) {
		this.netWorthFrom = netWorthFrom;
	}

	public double getNetWorthTo() {
		return netWorthTo;
	}

	public void setNetWorthTo(double netWorthTo) {
		this.netWorthTo = netWorthTo;
	}
}
