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
@Table(name = "Calm_EmailSms_Set_Sms")
public class SetSMS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="s_id", unique =true , nullable=false )
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
	
	@Column(name= "created_by" , nullable=false)
	private int createdBy;
	
	@Column(name= "created_date" , nullable=false)
	private Date createdDate;
	
	@Column(name="modifiedby")
	private int modifiedBy;
	
	@Column(name="last_modified_date")
	private Date lastModifiedDate;
	
	@Column(name="status")
	private String status;
	
	
	@Transient
	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
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

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Transient
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Transient
	public String getDlr() {
		return dlr;
	}

	public void setDlr(String dlr) {
		this.dlr = dlr;
	}

	@Transient
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Transient
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getCreatedBy() {
	return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	
	@Transient
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
