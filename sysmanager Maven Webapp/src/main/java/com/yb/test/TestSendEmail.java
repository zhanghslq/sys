package com.yb.test;

import com.yb.mail.SendJavaMail;
import com.yb.service.AutoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:spring-basic.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSendEmail {
    @Autowired
    private AutoService autoService;
    @Test
    public  void test() {
        autoService.autoSendCoupon();
    }
}
