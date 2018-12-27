package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class BroadCastForm {
	
	private int recipientId;
	
	private String choice;
	
	private CommonsMultipartFile attachFile;
	
	private CommonsMultipartFile attachFile2;
	
	private String to;
	
	private String subject;

	private String message;
	
	private  Date sentDate;

	

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
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

	public CommonsMultipartFile getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(CommonsMultipartFile attachFile2) {
		this.attachFile2 = attachFile2;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
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
