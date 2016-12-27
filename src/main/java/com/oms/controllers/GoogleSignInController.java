package com.oms.controllers;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oms.pojo.User;
import com.oms.services.GoogleSignInService;



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
                    String tokenResponse = "";
                    User user = null;
                    System.out.println("inside GoogleSignController:callback method");
                    if (StringUtils.isNotBlank(code)) {
                    	            signService.testProperties();
                                    tokenResponse = signService.callTokenAPI(code);
                                    System.out.println("tokenResponse......."+tokenResponse);
                                    user = signService.callUserInfoAPI(tokenResponse);
                                    System.out.println("user......."+user);
                                    this.user = user;
                                    return "redirect:product.html";
                    }
                    return "redirecting code value is not authenticated";
    }

   
    
    

}
