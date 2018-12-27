package com.yb.mail;


import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * @author: 孤傲苍狼
 * @date: 2015-1-12 下午9:42:56
 *
 */
public class SendJavaMail {


    /**
     * @param fileName
     * @param content
     * @param addresses
     */
    public static void send(String fileName,String content,Address[] addresses){

        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.partner.outlook.cn");
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.port", "587");
        prop.setProperty("mail.smtp.auth", "true");
        prop.setProperty("mail.smtp.starttls.enable", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = null;
        try {
            ts = session.getTransport();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        //3、连上邮件服务器
        try {
            ts.connect("smtp.partner.outlook.cn", "ITPost@bjshell.com", "itpost.123");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        //4、创建邮件
        Message message = null;
        try {
            message = createAttachMail(session,fileName,content,addresses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //5、发送邮件
        try {
            ts.sendMessage(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        finally {
            try {
                ts.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @Method: createAttachMail
     * @Description: 创建一封带附件的邮件

     *
     * @param session
     * @return
     * @throws Exception
     */
    public static MimeMessage createAttachMail(Session session,String fileName,String content,Address[] addresses) throws Exception{
        MimeMessage message = new MimeMessage(session);
        //设置邮件的基本信息
        //发件人
        message.setFrom(new InternetAddress("ITPost@bjshell.com"));

        //收件人
        message.setRecipients(Message.RecipientType.TO, addresses);

        //邮件标题
        message.setSubject("优惠券数据");

        //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(content, "text/html;charset=UTF-8");
        //text.setContent("9月20号到27号的优惠券数据，请查收", "text/html;charset=UTF-8");

        //创建邮件附件
        MimeBodyPart attach = new MimeBodyPart();
        File file = new File(fileName);
        DataHandler dh = new DataHandler(new FileDataSource(file));
        attach.setDataHandler(dh);
        attach.setFileName(dh.getName());

        //创建容器描述数据关系
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(text);
        mp.addBodyPart(attach);
        mp.setSubType("mixed");
        message.setContent(mp);
        message.saveChanges();
        //将创建的Email写入到E盘存储
        //message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
        return message;
    }
}
