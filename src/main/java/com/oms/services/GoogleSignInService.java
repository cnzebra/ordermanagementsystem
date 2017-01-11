package com.oms.services;
import com.oms.pojo.User;

public interface GoogleSignInService {
	
	public String callTokenAPI(String code);

	public User callUserInfoAPI(String httpResponse);

	public void testProperties();

}
