package org.calminfotech.email.form;

import javax.persistence.Entity;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
public class BroadCastCsvForm {
	
	private CommonsMultipartFile attachFile;
	
	private String subject;

	private String message;

	public CommonsMultipartFile getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(CommonsMultipartFile attachFile) {
		this.attachFile = attachFile;
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

}
