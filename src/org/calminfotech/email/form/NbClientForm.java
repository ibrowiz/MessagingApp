package org.calminfotech.email.form;

import javax.persistence.Entity;

@Entity
public class NbClientForm {
	
	private int clientId1;
	
	private int clientId2;
	
	private String name;
	
	private String subject;

	private String message;

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

	public int getClientId1() {
		return clientId1;
	}

	public void setClientId1(int clientId1) {
		this.clientId1 = clientId1;
	}

	public int getClientId2() {
		return clientId2;
	}

	public void setClientId2(int clientId2) {
		this.clientId2 = clientId2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
