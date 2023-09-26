package com.maxtraining.c40.plinko.http;
import java.awt.image.RescaleOp;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.*;



import com.maxtraining.c40.plinko.user.User;


public class dbController {
	public String baseURL = "http://localhost:8080";
	public HttpClient client = HttpClient.newHttpClient();
	public ObjectMapper mapper = new ObjectMapper();
	public dbController() {
		
	}
	
	public dbController(String baseURL, HttpClient client) {
		super();
		this.baseURL = baseURL;
		this.client = client;
	}


	public double getHighScore()  {
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(baseURL+"/api/users/highscore")).GET().build();
		HttpResponse<String> res = null;
		try {
			res = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res.statusCode());
		String scoreStr = res.body().toString();
		System.out.println(scoreStr);
		double score = Double.parseDouble(scoreStr);
		return score;
	}

	public User signIn() throws JsonMappingException, JsonProcessingException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter your username:");
		String username = scan.nextLine();
		System.out.println("Please enter your password");
		String password = scan.nextLine();
		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(baseURL+"/api/users/"+username+"/"+password)).GET().build();
		HttpResponse<String> res = null;
		try {
			res = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (res.statusCode() != 200) {
			User user = new User();
			user = null;
			System.out.println("Logon Failed!");
			return user;
		}
		String jsonData = res.body().toString();
		User user = mapper.readValue(jsonData, User.class);
		System.out.println("Logged in!");
		return user;
	}


	public String getBaseURL() {
		return baseURL;
	}


	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}


	public HttpClient getClient() {
		return client;
	}


	public void setClient(HttpClient client) {
		this.client = client;
	}
}

