package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.StockBroadCastBoInter;
import org.calminfotech.email.dao.inter.StockBroadCastDaoInter;
import org.calminfotech.email.model.StockBroadCast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockBroadCastBoImpl implements StockBroadCastBoInter{
	
	@Autowired
	private StockBroadCastDaoInter stockBroadCastDaoInter;

	@Override
	public List<StockBroadCast> fetchBankId(String stockCode) {
		return stockBroadCastDaoInter.fetchBankId(stockCode);
	}

	@Override
	public StockBroadCast getByBankId(String stockCode) {
		
		StockBroadCast stockBroadCast = stockBroadCastDaoInter.getByBankId(stockCode);
			return stockBroadCast;
		
	}

}
