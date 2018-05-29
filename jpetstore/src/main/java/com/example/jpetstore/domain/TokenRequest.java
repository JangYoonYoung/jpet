package com.example.jpetstore.domain;

public class TokenRequest {
	private final static String grant_type = "authorization_code";
	private final static String client_id="287704ee46a1efda52da413f01967801";
	private final static String redirect_uri = "http://127.0.0.1:8080/oauth/rest";
	private String code;
	private final static String client_secret="287704ee46a1efda52da413f01967801";
	
	public String getClient_id() {
		return client_id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public static String getGrantType() {
		return grant_type;
	}
	public static String getRedirectUri() {
		return redirect_uri;
	}
	public static String getClientSecret() {
		return client_secret;
	}
	@Override
	public String toString() {
		return "code=" + code + "&grant_type="+grant_type+"&client_id="+client_id+"&redirect_uri="+redirect_uri+"&client_secret="+client_secret;
	}

}