package org.calminfotech.email.bo.impl;

import java.util.List;

import org.calminfotech.email.bo.inter.Financial_InstituteBoInter;
import org.calminfotech.email.dao.inter.Financial_InstituteDaoInter;
import org.calminfotech.email.model.Financial_Institute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class Financial_InstituteBoImpl implements Financial_InstituteBoInter {

	@Autowired
	private Financial_InstituteDaoInter financial_InstituteDaoInter;

	@Override
	public List<Financial_Institute> fetchAllInst() {
		return financial_InstituteDaoInter.fetchAllInst();
	}
}
