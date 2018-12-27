package org.calminfotech.user.utils;

import java.util.Date;

import javax.persistence.Column;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserIdentity {

	private int userId; 
	
	private String userName;
	
	private String firstName;

	private String lastName;
	
	private String password;

	private String role;
	
	private int smsLimit;
	
	private int smsmSent;
	
	private int createdBy;

	private Date dateCreated;

	private String modifiedBy;

	private String dateModified;
	
	private String status;
	
	private String isDeleted;
	
	
	/*private String userName;
	private String password;
	*/
	//Assign User to Point
	/*private Integer sectionId;
	private String sectionName;
	private Integer billId;
	private String billName;
	private Integer currentPointId;
	private String currentPointName;
	private Integer visitId;
	//global error handler
	private String errormessage;
	Exception exception;
	private Date timestamp;
	private StringBuffer url;
	//role assignment permission list
	private List<Integer> pList = new ArrayList<Integer>();
	//user level assignment permission list
	private List<Integer> uList = new ArrayList<Integer>();*/
	//declare link
	private boolean identity = false;

	public UserIdentity() {

	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/*public String getUsername() {
		return userName;
	}

	public void setUsername(String userName) {
		this.userName = userName;
	}

	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}*/

	public boolean hasIdentity() {
		return identity;
	}

	public void setIdentity(boolean identity) {
		this.identity = identity;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public boolean isIdentity() {
		return identity;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	

	/*public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}*/

	/*public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/
}
