package org.calminfotech.user.models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.calminfotech.email.model.SmsActivityLogger;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Calm_EmailSms_users")
public class User{
	


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="user_id", unique =true , nullable=false )
private int userId;

@Column(name= "fname" , nullable=false)
private String firstName;

@Column(name= "Lname" , nullable=false)
private String lastName;

@Column(name= "email" , nullable=false)
private String userName;

@Column(name="password", nullable=false)
private String password;

@Column(name="role", nullable=false)
private String role;

@Column(name="sms_limit", nullable=false)
private int smsLimit;

@Column(name="sms_sent", nullable=false)
private int smsmSent;

@Column(name="created_by")
private int createdBy;

@Column(name="date_created")
private Date dateCreated;

@Column(name="modified_by")
private String modifiedBy;

@Column(name="date_modified")
private String dateModified;

@Column(name="status")
private String status;

@Column(name="is_deleted")
private String isDeleted;



@OneToMany(fetch = FetchType.EAGER)
@JoinColumn(name="user_id")
private Set<SmsActivityLogger> smsAct;

@OneToMany(fetch = FetchType.EAGER)
@JoinColumn(name="user_id")
private Set<UserSession> userSession;

@Transient
public int getUserId() {
	return userId;
}

public void setId(int userId) {
	this.userId = userId;
}

@Transient
public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

@Transient
public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

@Transient
public String getUserName() {
	return userName;
}

public void setUserName(String email) {
	this.userName = email;
}


@Transient
public String getPassword() {
	return password;
}

public void setUserPassword(String password) {
	this.password = password;
}

@Transient
public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

@Transient
public Set<UserSession> getUserSession() {
	return userSession;
}

public void setUserSession(Set<UserSession> userSession) {
	this.userSession = userSession;
}

public int getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(int createdBy) {
	this.createdBy = createdBy;
}

public Date getDateCreated() {
	return dateCreated;
}

public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
}

public String getModifiedBy() {
	return modifiedBy;
}

public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}

public String getDateModified() {
	return dateModified;
}

public void setDateModified(String dateModified) {
	this.dateModified = dateModified;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public int getSmsLimit() {
	return smsLimit;
}

public void setSmsLimit(int smsLimit) {
	this.smsLimit = smsLimit;
}

public int getSmsmSent() {
	return smsmSent;
}

public void setSmsmSent(int smsmSent) {
	this.smsmSent = smsmSent;
}

public String getIsDeleted() {
	return isDeleted;
}

public void setIsDeleted(String isDeleted) {
	this.isDeleted = isDeleted;
}


}
