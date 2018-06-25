package com.yb.test;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

import com.yb.util.RSAUtils;

public class TestRSA {
		public static void main(String[] args) throws Exception {
			RSAPrivateKey priKey = RSAUtils.getPrivateKey("148053205630784517442737484901701939132910334492394866021217487717819824915268579166845235063689090799954681149410884820440441912371043895939782920660197855436133054480335324511943293781165170523787935418251169736908165354930964863977673992728291652826396818514051359864800458232984889434627838864600502519869", 
					"88011426189324107222662155336457357625143868677071144320316952316989525899475846861484711122667550978461547261850537280002733974152974642445000576864986912764113830432409937215287263054813746937400158961139347869461560485039659470997212603042032504003694495402832725206450600693409138948273652995306852466245");  
			String string = RSAUtils.decryptByPrivateKey("9200358C07D919462E1015BD6CEEE8DDC61DCC89D3021E9B5C4B1914BDECCE99A0EC8E4167E02431CE36CDE8BFCD58B8A76B51D49A5FB16F9D5F3D4E1F3E78982DA09A57799945E4DA573FAC58BD323AE86D537B8630B86BBB09C960B6BD80A4E48A63795A9BAC00397E7BCA6085AE7331DA9D149D70B0E12F9D114003137E72", priKey);  
		}
	public static void tets(String[] args) throws Exception {
		 // TODO Auto-generated method stub  
        HashMap<String, Object> map = RSAUtils.getKeys();  
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
	
}
