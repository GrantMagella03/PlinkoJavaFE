package com.maxtraining.c40.plinko;

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
		}
		
		int i = 1;
		
	}

}
