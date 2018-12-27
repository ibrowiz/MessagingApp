package org.calminfotech.email.form;

import javax.persistence.Entity;

@Entity
public class EmailLogForm {
	
	private String from;

	private String to;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

}
