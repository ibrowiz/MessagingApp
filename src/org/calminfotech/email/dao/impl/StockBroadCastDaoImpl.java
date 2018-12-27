package org.calminfotech.email.dao.impl;

import java.util.List;

import org.calminfotech.email.dao.inter.StockBroadCastDaoInter;
import org.calminfotech.email.model.StockBroadCast;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StockBroadCastDaoImpl implements StockBroadCastDaoInter {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<StockBroadCast> fetchBankId(String stockCode) {
		List<StockBroadCast> list = this.sessionFactory.getCurrentSession().createQuery("from StockBroadCast where stockCode = ?").setParameter(0, stockCode).list();
		
			return (List<StockBroadCast>) list;
		
	}

	@Override
	public StockBroadCast getByBankId(String  stockCode) {
		List list = this.sessionFactory.getCurrentSession().createQuery("from StockBroadCast where  stockCode= ?").setParameter(0,  stockCode).list();
		
		if (list.size() > 0)
			return (StockBroadCast)list.get(0);
		return null;
	}
}
