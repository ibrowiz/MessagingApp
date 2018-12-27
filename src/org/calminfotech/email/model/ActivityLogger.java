package org.calminfotech.email.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Calm_EmailSms_AcctivityLogger")
public class ActivityLogger implements Serializable {
	
	/**
	 * 
	 */
	 private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="recipient_id", unique =true , nullable=false )
	private int recipientId;
	

	@Column(name= "choice" , nullable=false)
	private String choice;
	
	@Column(name= "attach_File" , nullable=false)
	private CommonsMultipartFile attachFile;

	@Column(name= "attach_File2" , nullable=false)
	private CommonsMultipartFile attachFile2;
	
	@Column(name= "mail_to" , nullable=false)
	private String to;
	
	@Column(name= "subject" , nullable=false)
	private String subject;
	
	@Column(name= "message" , nullable=false)
	private String message;
	
	@Column(name= "date_sent" , nullable=false)
	private Date dateSent;
	
	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	
	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	
	public CommonsMultipartFile getAttachFile() {
		return attachFile;
	}

	public void setAttachFile(CommonsMultipartFile attachFile) {
		this.attachFile = attachFile;
	}

	
	public CommonsMultipartFile getAttachFile2() {
		return attachFile2;
	}

	public void setAttachFile2(CommonsMultipartFile attachFile2) {
		this.attachFile2 = attachFile2;
	}

	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMesssage(String message) {
		this.message = message;
	}

	public Date getDateSent() {
		return dateSent;
	}

	public  void setDateSent(Date dateSent) {
		this.dateSent = dateSent;
	}
	
}
