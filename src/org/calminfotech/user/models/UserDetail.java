package org.calminfotech.user.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert=true)
@Table(name="user_details")
public class UserDetail {

	private int userId;
	private User user;
	private String surName;
	private String firstName;
	private String otherName;
	private String email;
	private String address;
	private String createdBy;
	private Date createdDate;
	private Date modifiiedDate;
	
	@Id
	@Column(name="userid" ,unique=true , nullable=false)
	@GeneratedValue(generator= "generator")
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name="property" , value= "user"))
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@OneToOne(mappedBy = "userDetail", cascade = CascadeType.ALL)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="surname" , nullable=false)
	public String getSurName() {
		return surName;
	}
	public void setSurName(String surName) {
		this.surName = surName;
	}
	
	@Column(name="first_name" , nullable=false)
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="other_name" , nullable=false)
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	@Column(name="email" , nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="address" , nullable=false)
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="created_by" , nullable=false)
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="date_created" , nullable=false)
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name="date_modified" , nullable=false)
	public Date getModifiiedDate() {
		return modifiiedDate;
	}
	public void setModifiiedDate(Date modifiiedDate) {
		this.modifiiedDate = modifiiedDate;
	}
	
	
	
}
