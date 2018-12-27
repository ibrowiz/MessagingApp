package org.calminfotech.email.form;

import javax.persistence.Entity;


@Entity
public class CsvMailBody {

	private String to;
	
	private String subject;

	private String message;
	

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	/*public CommonsMultipartFile getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(CommonsMultipartFile attachFile) {
		this.attachFile = attachFile;
	}
*/
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
