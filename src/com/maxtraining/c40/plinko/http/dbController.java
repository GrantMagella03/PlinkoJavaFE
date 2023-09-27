package com.maxtraining.c40.plinko.http;
import java.awt.image.RescaleOp;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.net.http.HttpRequest.BodyPublishers;
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
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(res.statusCode());
		String scoreStr = res.body().toString();
		System.out.println(scoreStr);
		double score = Double.parseDouble(scoreStr);
		return score;
	}

	public User signIn(Scanner scan)  {
		// handles usersignin
		System.out.println("Please enter your username:");
		String username = scan.nextLine();
		System.out.println("Please enter your password");
		String password = scan.nextLine();

		HttpRequest req = HttpRequest.newBuilder().uri(URI.create(baseURL+"/api/users/"+username+"/"+password)).GET().build();
		//Makes calls to the API database
		HttpResponse<String> res = null;
		try {
			res = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		User user = new User();
		// if the statuscode is not OK 200 logon has failed and user will need to try again.
		if (res.statusCode() != 200) {
			user = null;
			System.out.println("Logon Failed! SC: " + res.statusCode());
			return user;
		}
		String jsonData = res.body().toString();
		// code that converts the JSON data from the db call and converts it into an instance of a user object
		try {
			user = mapper.readValue(jsonData, User.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Logged in!");
		return user;
	}
	public User addNewUser(Scanner scan) throws IOException, InterruptedException {
		// Will create a new user and set their balance to 1000 to start.
		System.out.println("Please enter a new username:");
		String username = scan.nextLine();
		System.out.println("Please enter a new password:");
		String password = scan.nextLine();
		User user = new User(0, username, password, 1000);
	
		String jsondata ="";
			try {
				jsondata = mapper.writeValueAsString(user);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		System.out.print(jsondata);
		//Makes the calls to the db to create a new user, converting the properties of the instance user
		// and converting it to JSON DATA that is readable to the db.
		HttpRequest req = HttpRequest.newBuilder(URI.create(baseURL+"/api/users")).header("Content-Type", "application/json").POST(BodyPublishers.ofString(jsondata)).build();
			HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
			System.out.println(res.statusCode());
		if (res.statusCode() != 201) {
			System.out.println("Usercreation failed!");
			return user;
		}
		req = HttpRequest.newBuilder().uri(URI.create(baseURL+"/api/users/"+username+"/"+password)).GET().build();
		//Makes calls to the API database
		try {
			res = client.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String jsonData = res.body().toString();
		// code that converts the JSON data from the db call and converts it into an instance of a user object
		try {
			user = mapper.readValue(jsonData, User.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public void updateDb(User user) {
		// General method to be called when the db needs to be updated with highscore etc
		String jsondata = "";
		try {
			jsondata = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		HttpRequest req = HttpRequest.newBuilder(URI.create(baseURL+"/api/users/"+user.id)).header("Content-Type", "application/json").PUT(BodyPublishers.ofString(jsondata)).build();
			HttpResponse<String> res = null;
			try {
				res = client.send(req, HttpResponse.BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}

		if (res.statusCode() != 200) {
			System.out.println("update failed SC: " + res.statusCode());
			return;
		}
		//System.out.println("Updated sucess SC: " + res.statusCode());
		return;
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

