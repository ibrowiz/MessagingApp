package org.calminfotech.email.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class NewsForm {
	
private int Id;

private int userId;
			
private String subject;
	
private String body;

private String value1;

private String value2;

private Date dateModified;
	
private Date dateCreated;

private String status;
	
public int getId() {
	return Id;
}

public void setId(int id) {
	Id = id;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getSubject() {
	return subject;
}

public void setSubject(String subject) {
	this.subject = subject;
}

public String getBody() {
	return body;
}

public void setBody(String body) {
	this.body = body;
}

public Date getDateCreated() {
	return dateCreated;
}

public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
}



public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Date getDateModified() {
	return dateModified;
}

public void setDateModified(Date dateModified) {
	this.dateModified = dateModified;
}

public String getValue1() {
	return value1;
}

public void setValue1(String value1) {
	this.value1 = value1;
}

public String getValue2() {
	return value2;
}

public void setValue2(String value2) {
	this.value2 = value2;
}

}
