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
@Table(name = "calm_email_stock_sec")
public class Financial_Institute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Row_Id", unique =true , nullable=false )
	private int Row_Id;
	
	@Column(name= "Stock_Code" , nullable=false)
	private String stockCode;
	
	@Column(name= "Stock_Name" , nullable=false)
	private String stockName;
	
	
	@Transient
	public int getRow_Id() {
		return Row_Id;
	}

	public void setRow_Id(int row_Id) {
		Row_Id = row_Id;
	}

	@Transient
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Transient
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	

	

}
