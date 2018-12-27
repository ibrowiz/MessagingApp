package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class SmsForm {
	
	private int Id;
	
	private String choice;
	
	private CommonsMultipartFile attachFile;
	
	private String to;

	private String message;
	
	private  Date sentDate;

	

	public int getId() {
		return Id;
	}

	public void setRecipientId(int Id) {
		this.Id = Id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public CommonsMultipartFile getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(CommonsMultipartFile attachFile) {
		this.attachFile = attachFile;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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
