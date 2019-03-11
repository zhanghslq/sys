package com.yb.test;

import com.yb.mail.SendJavaMail;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;

public class TestJavaMail {

    public static void main(String[] args) throws AddressException {
        Address[] addresses = new Address[1];

        addresses[0]=new InternetAddress("962203169@qq.com");
        SendJavaMail.send("F:\\excel\\20-27.xls","测试",addresses,"测试标题");
    }
}
