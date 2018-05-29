package com.example.jpetstore.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.example.jpetstore.domain.TokenRequest;
import com.example.jpetstore.domain.TokenResponse;

public class KakaoLoginRestClient {
	public KakaoLoginRestClient() {}
	
	public static TokenResponse loginToken(String code) throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kauth.kakao.com/oauth/token";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		TokenRequest tokenRequest = new TokenRequest();
		tokenRequest.setCode(code);
		
		HttpEntity httpEntity = new ByteArrayEntity(tokenRequest.toString().getBytes("utf-8"));
		httpPost.setEntity(httpEntity);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		httpEntity = httpResponse.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "utf-8"));
		JSONObject object = (JSONObject)JSONValue.parse(br);
		ObjectMapper objectMapper = new ObjectMapper();
		
		return objectMapper.readValue(object.toString(), TokenResponse.class);
	}
	
	public static String getProfile(String token) throws Exception {
		
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kapi.kakao.com/v1/user/me";
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Authorization", token);
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		HttpResponse httpResponse = httpClient.execute(httpGet);
		System.out.println(httpResponse);
		
		HttpEntity httpEntity = httpResponse.getEntity();
		BufferedReader br = new BufferedReader(new InputStreamReader(httpEntity.getContent(), "utf-8"));
		JSONObject object = (JSONObject)JSONValue.parse(br);
		
		return object.toString();
	}
	
	public static void signUp(String token) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String url = "https://kapi.kakao.com/v1/user/signup";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Authorization", "Bearer "+token);
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		
		JSONObject properties = new JSONObject();
		properties.put("age", "23");
		properties.put("gender", "female");
		
		HttpEntity httpEntity01 = new StringEntity(properties.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
}