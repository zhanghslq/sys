package com.yb.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.test.MD5Utils;
import com.yb.dao.WeatherDao;
import com.yb.entity.Weather;
import com.yb.util.DateUtil;
import com.yb.util.JsoupUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-basic.xml")
public class TestHttpClient {
	@Autowired
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
		/*byte[] encode = Base64Utils.encode("test".getBytes());
		String string = new String(encode);
		System.out.println(string);
		String jsonString = JSON.toJSONString(string);
		System.out.println(jsonString);
		byte[] decode = Base64Utils.decode(encode);
		String string2 = new String(decode);
		System.out.println(string2);*/
		System.out.println(new String(Base64Utils.encode("test".getBytes())));
		String digest = MD5Utils.getDigest("d3NhdP"+123);
		System.out.println(digest);

	}
}
