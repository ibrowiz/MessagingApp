package org.calminfotech.email.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import org.calminfotech.user.models.User;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Calm_EmailSms_SMS_ActivityLogger")
/*@SQLDelete(sql = "UPDATE sActivityLogger SET is_deleted = 1 WHERE user_id = ?")
//@SQLInsert(sql="")
@Where(clause = "is_deleted <> 1")*/
public class SmsActivityLogger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", unique =true , nullable=false )
	private int Id;
	
	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user;
	
	@Column(name= "choice" , nullable=false)
	private String choice;
	
	@Column(name= "message_to" , nullable=false)
	private String to;
	
	@Column(name= "message" , nullable=false)
	private String message;
	
	@Column(name= "date_sent" , nullable=false)
	private Date dateSent;
	
	@Column(name= "is_deleted" , nullable=false)
	private int is_deleted;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}

	/*public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/
}
