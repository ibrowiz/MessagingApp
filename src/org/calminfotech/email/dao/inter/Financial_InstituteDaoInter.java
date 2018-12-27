package org.calminfotech.email.dao.inter;

import java.util.List;

import org.calminfotech.email.model.BroadCastAll;
import org.calminfotech.email.model.Financial_Institute;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.REQUIRED, readOnly=false)
public interface Financial_InstituteDaoInter {
	public List<Financial_Institute> fetchAllInst();
}
