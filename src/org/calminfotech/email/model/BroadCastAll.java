package org.calminfotech.email.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "calm_email_broadcast")
public class BroadCastAll {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Client_ID", unique =true , nullable=false )
	private int clientId;
	

	@Column(name= "fn" , nullable=false)
	private String name;
	
	@Column(name= "em" , nullable=false)
	private String emailAddress;
	
	@Column(name= "Row_Id" , nullable=false)
	private String rowId;
	
	@Column(name= "tel" , nullable=false)
	private String tel;

	
	
	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	@Transient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	
}
