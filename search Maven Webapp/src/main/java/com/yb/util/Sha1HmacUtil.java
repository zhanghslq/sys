package com.yb.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Sha1HmacUtil {
	
    private static final String MAC_NAME = "HmacSHA1";  
    private static final String ENCODING = "UTF-8";  
	   public static void main(String[] args) throws Exception {
		   String hmacSHA1Encrypt = HmacSHA1Encrypt("123","123");
		   System.out.println(hmacSHA1Encrypt);
		}
    /** 
     * 使用 HMAC-SHA1 签名方法对对encryptText进行签名 
     * @param encryptText 被签名的字符串 
     * @param encryptKey  密钥 
     * @return 
     * @throws Exception 
     */  
    public static String HmacSHA1Encrypt(String encryptText, String encryptKey) throws Exception   
    {         
        byte[] data=encryptKey.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME); 
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME); 
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);  
        byte[] text = encryptText.getBytes(ENCODING);  
        byte[] b = mac.doFinal(text);
        //完成 Mac 操作 
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString();
    }  
}