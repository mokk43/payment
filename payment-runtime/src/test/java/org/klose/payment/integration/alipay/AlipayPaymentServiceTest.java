package org.klose.payment.integration.alipay;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.klose.payment.AbstractPaymentTest;
import org.klose.payment.bo.BillingData;
import org.klose.payment.bo.PaymentForm;
import org.klose.payment.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.UUID;


public class AlipayPaymentServiceTest extends AbstractPaymentTest {
    @Resource(name = "aliGateWayPaymentService")
    private PaymentService service;
    private BillingData data;
    private final static Logger logger = LoggerFactory
            .getLogger(AlipayPaymentServiceTest.class);


    @Before
    public void prepare() throws Exception {
        data = new BillingData();
        data.setAccountNo("ALIPAY01");
        data.setBizNo(UUID.randomUUID().toString());
        data.setBizType("1");
        data.setCurrency("CNY");
        data.setDescription("test policy issue for alipay");
        data.setSubject("test product");
        data.setContextPath("http://axatp.localhost:10080/pa");
        data.setPrice(new BigDecimal(0.01));
        data.setQuantity(1);
        data.setReturnURL("");
    }

    @Test
    @Rollback(true)
    public void test() throws Exception {
        Assert.assertNotNull(service);
        Assert.assertNotNull(data);
        PaymentForm view = service.generatePaymentData(data);
        Assert.assertNotNull(view);
        Assert.assertNotNull(view.getTransactionId());
        logger.info("view url " + view.getForwardURL() + " view forward Type "
                + view.getForwardType());
        for (String key : view.getParams().keySet())
            logger.info("key = " + key + " value = " + view.getParams().get(key));
        logger.info("transactionId = " + view.getTransactionId());
    }
}
