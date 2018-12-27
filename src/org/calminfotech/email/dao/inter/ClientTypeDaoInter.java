package org.calminfotech.email.dao.inter;

import java.util.List;
import org.calminfotech.email.model.ClientType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Transactional(propagation= Propagation.REQUIRED, readOnly=false)

public interface ClientTypeDaoInter {
	List<ClientType>fetchType(String clientType);
}
