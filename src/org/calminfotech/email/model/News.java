package org.calminfotech.email.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Calm_EmailSms_news")
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique =true , nullable=false )
	private int Id;
	
	@Column(name= "user_id" , nullable=false)
	private int userId;
	
	@Column(name= "subject" , nullable=false)
	private String subject;
	
	@Column(name= "value" , nullable=false)
	private String value;
	
	@Column(name= "body" , nullable=false)
	private String body;
	
	@Column(name= "date_created" , nullable=false)
	private Date dateCreated;
	
	
	@Column(name= "date_modified" , nullable=false)
	private Date dateModified;
	
	
	
	@Column(name= "status" , nullable=false)
	private String status;
	
	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

}
