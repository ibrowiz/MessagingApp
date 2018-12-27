package org.calminfotech.email.bo.inter;

import java.util.List;


import org.calminfotech.email.model.NetWorth;

public interface NetWorthBoInter {
	List<NetWorth> fetchNetWorth(double netWorthFrom, double netWorthTo);
}
