package com.yb.test;



import com.yb.util.Base64Utils;
import com.yb.util.RSAUtils;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.HashMap;
import java.util.Map;


public class TestRSA {
		public static void main(String[] args) throws Exception {
            Map<String, Object> keyMap = RSAUtils.getKeyMap();
            String privateKey = RSAUtils.getPrivateKey(keyMap);
            String publicKey = RSAUtils.getPublicKey(keyMap);
            String s=" 张海松";
            byte[] bytes = RSAUtils.encryptByPublicKey(s.getBytes(),publicKey);
            byte[] bytes1 = RSAUtils.decryptByPrivateKey(bytes, privateKey);
            System.out.println(new String(bytes1));
        }
	/*public static void tets() throws Exception {
		 // TODO Auto-generated method stub  
        //生成公钥和私钥
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");  
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");  
          
        //模  
        String modulus = publicKey.getModulus().toString();  
        System.out.println("模"+modulus);
        //公钥指数  
        String public_exponent = publicKey.getPublicExponent().toString();  
        System.out.println("公式植树"+public_exponent);
        //私钥指数  
        String private_exponent = privateKey.getPrivateExponent().toString();  
        System.out.println("四十"+private_exponent);
        //明文
        String ming = "123456789";
        //使用模和指数生成公钥和私钥  
        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);  
        
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);  
        System.out.println("prikey"+priKey.toString());
        //加密后的密文  
        String mi = RSAUtils.encryptByPublicKey(ming, pubKey);  
        System.err.println(mi);  
        //解密后的明文  
        ming = RSAUtils.decryptByPrivateKey(mi, priKey);  
        System.err.println(ming);  
	}
	*/
}
