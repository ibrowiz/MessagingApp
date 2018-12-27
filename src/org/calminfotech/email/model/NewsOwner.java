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


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "Calm_EmailSms_news_owner")
public class NewsOwner {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique =true , nullable=false )
	private int Id;
	
	
	/*@Column(name= "news_id" , nullable=false)
	private int newsId;*/
	
	@ManyToOne
	@JoinColumn(name ="news_id")
	private News news;
	
	@Column(name= "user_id" , nullable=false)
	private int userId;
	
	@Column(name= "Client_ID" , nullable=false)
	private int clientId ;
	
	@Column(name= "date_created" , nullable=false)
	private Date dateCreated;
	
	@Column(name= "status" , nullable=false)
	private String status;
	
	@Column(name= "view_status" , nullable=false)
	private String viewStatus;

	public int getId() {
		return Id;
	}

	public void setId(int Id) {
		this.Id = Id;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

}
