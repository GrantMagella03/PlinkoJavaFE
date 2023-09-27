package com.maxtraining.c40.plinko;
import java.util.Scanner;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.maxtraining.c40.plinko.board.Board;
import com.maxtraining.c40.plinko.board.Obj;
import com.maxtraining.c40.plinko.http.dbController;
import com.maxtraining.c40.plinko.user.User;

public class Program {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		dbController cont = new dbController();
		boolean outerActive = true;
		boolean innerActive = false;
		boolean gameActive = true;
		while(outerActive){//user login stage
		User user = new User();
			user = cont.signIn(s);

		if (user != null) {
			innerActive = true;
		}

			//innerActive = Login();
			while (innerActive) {//game input instance
				Board B = new Board();
				B.GenerateBase();
				System.out.println("Enter number of balls to insert:");
				int Nballs = s.nextInt();
				System.out.println("Enter Bet Value:");
				double INPUTBET = s.nextDouble();
				user.setScore(user.getScore() - INPUTBET);
				//System.out.println(INPUTBET);
				B.display();
				for(int Z = Nballs; Z>0;Z--) {//Inserts 1 ball then advances game one tick
					B.inPutBall(INPUTBET/Nballs);
					B.BallLogic(user);
					B.display();
					//B.BallLogic();// a forced second tick here can fix ball "bounce issue" not needed atm though
					//B.display();
					gameActive=true;
				}
				while(gameActive) {//finishes started game
					gameActive = B.isValidPos();
					B.BallLogic(user);
					B.display();
				}
			}
		}
		s.close();
	}
}
