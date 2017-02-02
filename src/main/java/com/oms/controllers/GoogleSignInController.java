package com.oms.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oms.pojo.User;
import com.oms.services.GoogleSignInService;

@RefreshScope
@Controller
@RequestMapping("/")
public class GoogleSignInController {

	@Autowired
	private GoogleSignInService signService;

	User user = null;
	

	@GetMapping()
	public String welcomePage() {
		return "redirect:index.html";
	}

	@GetMapping
	@RequestMapping("/OAuth2Callback")
	public String callback(@RequestParam String code) {
		String tokenResponse;
		User user1;
		if (StringUtils.isNotBlank(code)) {
			signService.testProperties();
			tokenResponse = signService.callTokenAPI(code);
			user1 = signService.callUserInfoAPI(tokenResponse);
			this.user = user1;
			return "redirect:home.html";
		}
		return "redirecting code value is not authenticated";
	}

}
