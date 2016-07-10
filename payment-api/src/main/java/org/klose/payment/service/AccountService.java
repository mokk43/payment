package org.klose.payment.service;


import org.klose.payment.bo.AccountInfo;
import org.klose.payment.constant.PaymentType;

import java.util.List;
import java.util.Map;

public interface AccountService {
	
	List<AccountInfo> getAccountsByPaymentType(PaymentType type);
	
	AccountInfo getAccountbyNo(String accountNo);
	
	String getProcessBeanByNo(String accountNo);
	
	boolean isAccountUnderTesting(String accountNo);

	Map<String, Object> parseConfigData(String accountNo);
	
}
