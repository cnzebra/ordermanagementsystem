package com.oms.services;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.oms.pojo.User;

@Service
public class GoogleSignInServiceImpl implements GoogleSignInService {

	@Value("${google.token.api}")
	private String tokenAPI;

	@Value("${google.user.profile.api}")
	private String userProfileAPI;

	@Value("${google.console.project.clientid}")
	private String clientId;

	@Value("${google.console.project.clientsecreatkey}")
	private String clientSecreatKey;

	@Value("${google.redirect.uri}")
	private String googleRedirectURI;

	public User user;

	public void testProperties() {
		System.out.println("Calling the testproperties method");
		System.out.println("google token api::" + tokenAPI);
		System.out.println("google user profile  api::" + userProfileAPI);
		System.out.println("client id and and client secreat key::" + clientId);
		System.out.println("client secreat key::" + clientSecreatKey);
		System.out.println("google redirect uri::" + googleRedirectURI);
	}

	@Override
	public String callTokenAPI(String code) {
		String httpResponse = "";
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(tokenAPI);
		post.addRequestHeader("Host", "accounts.google.com");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		NameValuePair[] data = { new NameValuePair("code", code), new NameValuePair("client_id", clientId),
				new NameValuePair("client_secret", clientSecreatKey),
				new NameValuePair("redirect_uri", googleRedirectURI),
				new NameValuePair("grant_type", "authorization_code") };

		post.setRequestBody(data);
		int httpStatus = 0;
		try {
			httpStatus = client.executeMethod(post);
			httpResponse = post.getResponseBodyAsString();
			JSONObject access_token = new JSONObject(httpResponse);
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return httpResponse;
	}

	@Override
	public User callUserInfoAPI(String httpResponse) {
		JSONObject access_token = new JSONObject(httpResponse);
		JSONObject user = null;
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(userProfileAPI + "?access_token=" + access_token.getString("access_token"));
		User data = null;
		try {
			client.executeMethod(get);
			user = new JSONObject(get.getResponseBodyAsString());
			data = (User) new Gson().fromJson(user.toString(), User.class);
			this.user = data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
