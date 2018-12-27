package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.StockBroadCast;

public interface StockBroadCastBoInter {
	List<StockBroadCast> fetchBankId(String stockCode);
	public StockBroadCast getByBankId(String stockCode);
}
