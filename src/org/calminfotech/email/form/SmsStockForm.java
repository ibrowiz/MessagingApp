package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class SmsStockForm {
	
	private int clientId;
	
	private String stockCode;
	
	private String message;
	
	private  Date sentDate;

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
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
