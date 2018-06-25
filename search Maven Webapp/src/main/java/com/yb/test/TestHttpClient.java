package com.yb.test;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class TestHttpClient {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		test();
	}
	public static void test() throws ClientProtocolException, IOException {
		String asString = Request.Get("http://192.168.0.34:8989/search/user/login/test/123/queryVip")
				.setHeader("content-type", "application/x-www-form-urlencoded")
				.execute().returnContent().asString();
		System.out.println(asString);
	}
	public static void test2() throws ClientProtocolException, IOException {
		String asString = Request.Get("http://localhost:8989/search/vipTag/queryVip/NWI5YTU5M2EwMzYwYmJmMGU2NTM3MDQ4MmJjZWU3YTkyOTkwYTY0OW09cXVlcnlWaXAmbj10ZXN0JnA9MTIzJmU9MTgwMCZ0PTE1MjU2ODc0MzkzNzU=")
				.setHeader("content-type", "application/x-www-form-urlencoded")
				.execute().returnContent().asString();
		System.out.println(asString);
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
