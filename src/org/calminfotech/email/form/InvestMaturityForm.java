package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class InvestMaturityForm {
	private String investMaturity;
	
	private CommonsMultipartFile attachFile2;
	
	private String subject;

	private String message;
	
	private  Date sentDate;

	public String getInvestMaturity() {
		return investMaturity;
	}

	public void setInvestMaturity(String investMaturity) {
		this.investMaturity = investMaturity;
	}

	public CommonsMultipartFile getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(CommonsMultipartFile attachFile2) {
		this.attachFile2 = attachFile2;
	}

	public String getSubject() {
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
