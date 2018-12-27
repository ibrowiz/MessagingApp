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
@Table(name = "Calm_EmailSms_SMS_Settings_Audit")
public class SettingsAuditSMS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique =true , nullable=false )
	private int Id;
	
	@Column(name= "vendor" , nullable=false)
	private String vendor;
	
	@Column(name= "url" , nullable=false)
	private String url;
	
	@Column(name= "username" , nullable=false)
	private String userName ;

	@Column(name= "password" , nullable=false)
	private String password;
	
	@Column(name= "source" , nullable=false)
	private String source;
	
	@Column(name= "dlr" , nullable=false)
	private String dlr;
	
	@Column(name= "type" , nullable=false)
	private String type;
	
	@Column(name= "port" , nullable=false)
	private int port;
	
	@Column(name= "status" , nullable=false)
	private String status;
	
	@Column(name="modified_by")
	private int modifiedBy;
	
	@Column(name="modified_date")
	private Date ModifiedDate;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDlr() {
		return dlr;
	}

	public void setDlr(String dlr) {
		this.dlr = dlr;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return ModifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		ModifiedDate = modifiedDate;
	}

}
