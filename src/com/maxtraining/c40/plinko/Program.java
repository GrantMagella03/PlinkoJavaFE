package com.maxtraining.c40.plinko;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.maxtraining.c40.plinko.board.Board;
import com.maxtraining.c40.plinko.board.Obj;
import com.maxtraining.c40.plinko.http.dbController;
import com.maxtraining.c40.plinko.user.User;

public class Program {

	public static void main(String[] args) {
		User user = new User();
		dbController cont = new dbController();
		
		cont.getHighScore();
		try {
			user = cont.signIn();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}/*
		User user2 = new User();
		try {
			user2 = cont.addNewUser();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		user.setScore(42069);
		cont.updateDb(user);
		int i = 1;
		
	}

}
