package org.calminfotech.email.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Calm_EmailSms_Set_Email")
public class SetEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emailId", unique =true , nullable=false )
	private int emailId;
	
	@Column(name= "emailAddress" , nullable=false)
	private String emailAddress;

	@Column(name= "name" , nullable=false)
	private String name;
	
	@Column(name="modifiedby")
	private int modifiedBy;
	
	@Column(name="last_modified_date")
	private Date lastModifiedDate;
	
	@Transient
	public int getEmailId() {
		return emailId;
	}

	public void setEmailId(int emailId) {
		this.emailId = emailId;
	}
	
	@Transient
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		
	@Transient
	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Transient
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	

}
