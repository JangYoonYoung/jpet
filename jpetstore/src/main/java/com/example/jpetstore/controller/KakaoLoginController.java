package com.example.jpetstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpetstore.domain.TokenResponse;

@Controller
@RequestMapping("/oauth/*")
public class KakaoLoginController {
	
	@RequestMapping("rest")
	public TokenResponse getUser(@ModelAttribute("tokenResponse") TokenResponse tokenResponse,
								@RequestParam String code) throws Exception {
		tokenResponse = KakaoLoginRestClient.loginToken(code);
		KakaoLoginRestClient.signUp(tokenResponse.getAccess_token());
		return tokenResponse;
	}

}