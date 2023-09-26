package com.maxtraining.c40.plinko.user;

public class User {
	public int id;
	public String username;
	public String password;
	public double score = 1000.00;
	public User() {}
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(int id, String username, String password, double score) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.score = score;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}
