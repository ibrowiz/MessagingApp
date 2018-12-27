package org.calminfotech.email.bo.inter;

import java.util.List;

import org.calminfotech.email.model.ClientType;

public interface ClientTypeBoInter {
	List<ClientType>fetchType(String clientType);
}
