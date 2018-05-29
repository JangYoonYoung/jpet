package com.example.jpetstore.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.example.jpetstore.domain.KakaoPayRequest;
import com.example.jpetstore.domain.KakaoPayResponse;

public class KakaoPayTest {

	public static KakaoPayResponse readyPay(KakaoPayRequest sendObject) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kapi.kakao.com/v1/payment/ready";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		httpPost.setHeader("Authorization", "KakaoAK 4b28333571585dcb2ddd1265aa46c29b");
		
		HttpEntity httpEntity = new ByteArrayEntity(sendObject.toString().getBytes("utf-8"));
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		KakaoPayResponse responseDomain =new ObjectMapper().readValue(EntityUtils.toString(httpResponse.getEntity()), KakaoPayResponse.class);
		System.out.println("¿©±â : "+responseDomain);
		return responseDomain;
		
	}
	
	public void connectPay() {
		
	}
	
	public void allowPay() {
		
	}
}