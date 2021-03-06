package org.klose.payment.server.prepare;


import org.klose.payment.bo.BillingData;

public interface PaymentIntegrationService {
	/**
	 *
	 * @param businessNumber
	 * @return
     */
	BillingData prepareBillingData(String businessNumber);
	
	void saveTransactionId(String businessNumber, Long transactionId);
}
