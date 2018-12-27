package com.yb.test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.yb.util.AESUtils;
import com.yb.util.RSAUtils;
import org.apache.http.client.fluent.Request;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class TestHttpClient {
	public static void main(String[] args) throws Exception {
		test2();
	}
	public static void test() throws Exception {
		String asString = null;
		try {
			asString = Request.Get("http://localhost:8080/search/user/login/test/123/queryVip")
					.setHeader("content-type", "application/x-www-form-urlencoded")
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(asString);

	}
	public static void test2() throws Exception {
		String asString = null;
		try {
			asString = Request.Get("http://192.168.0.34:8989/search/vipTag/queryVip/N2MyOWNjOTQxOTYxNTg2ZGU2ZDZiNTM3YjJjYmNhM2Q0MjVmYmIwYm09cXVlcnlWaXAmbj10ZXN0JnA9MTIzJmU9MTgwMCZ0PTE1MzA1MjY2Nzk4MTE=/test")
					.setHeader("content-type", "application/x-www-form-urlencoded")
					.execute().returnContent().asString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(asString);
		Object data = JSON.parseObject(asString).get("data");
		System.out.println("data========"+data.toString());
        byte[] bytes = AESUtils.parseHexStr2Byte(data.toString());
        byte[] bjshells = AESUtils.decrypt(bytes, "bjshell");
        System.out.println(new String(bjshells));
    }
	
	/*@Autowired
	private WeatherDao weatherDao;
	
	@org.junit.Test
	public  void test() throws Exception {
			HttpEntity httpEntity = new StringEntity("time="+"2016-11-06"+"","UTF-8");
			String asString = Request.Post("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c")
					.body(httpEntity).setHeader("content-type", "application/x-www-form-urlencoded")
					.execute().returnContent().asString();
			Double avgPre = JsoupUtil.getAvgPre(asString);
			System.out.println(asString);
	}
	@org.junit.Test
	public  void test2() throws Exception {
		HashedMap<String,Object> map = new HashedMap<String,Object>();
		map.put("name",new String(Base64Utils.encode("test" .getBytes())));
		map.put("password",new String(Base64Utils.encode("test" .getBytes())));
		String jsonString = JSON.toJSONString(map);
		System.out.println(jsonString);
		HttpEntity httpEntity = new StringEntity("name="+new String(Base64Utils.encode("test" .getBytes()))+
				"&password="+new String(Base64Utils.encode("123" .getBytes()))+"&station[]='50001','50002'","UTF-8");
		String asString = Request.Post("http://localhost:8989/search/vipTag/queryVip")
				.body(httpEntity).setHeader("content-type", "application/x-www-form-urlencoded")
				.execute().returnContent().asString();
		
		System.out.println("asString============================"+asString);
	}
	public static void main(String[] args) {
		byte[] encode = Base64Utils.encode("test".getBytes());
		String string = new String(encode);
		System.out.println(string);
		String jsonString = JSON.toJSONString(string);
		System.out.println(jsonString);
		byte[] decode = Base64Utils.decode(encode);
		String string2 = new String(decode);
		System.out.println(string2);
		System.out.println(new String(Base64Utils.encode("test".getBytes())));
		String digest = MD5Utils.getDigest("d3NhdP"+123);
		System.out.println(digest);
		
	}*/
}
