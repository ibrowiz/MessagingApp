package org.calminfotech.email.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "calm_email_clientType_view")
public class ClientType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Client_ID", unique =true , nullable=false )
	private int clientId;
		
	@Column(name= "SN" , nullable=false)
	private String surName; 
	
	@Column(name= "FN" , nullable=false)
	private String firstName;
	
	@Column(name= "EM" , nullable=false)
	private String emailAddress;
	
	@Column(name= "Invt_Mandate" , nullable=false)
	private String invstMandate;
	
	@Column(name= "tel" , nullable=false)
	private String phoneNumber;
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getInvstMandate() {
		return invstMandate;
	}

	public void setInvstMandate(String invstMandate) {
		this.invstMandate = invstMandate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	

}
