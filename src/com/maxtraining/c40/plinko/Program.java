package com.maxtraining.c40.plinko;
import java.util.Scanner;

import com.maxtraining.c40.plinko.board.Board;

public class Program {

	public static void main(String[] args) {
		boolean outerActive = true;
		boolean innerActive = false;
		boolean gameActive = true;
		while(outerActive){//user login stage
			Scanner s = new Scanner(System.in);
			innerActive = Login();
			while (innerActive) {//game input instance
				Board B = new Board();
				B.GenerateBase();
				System.out.println("Enter number of balls to insert:");
				int Nballs = s.nextInt();
				System.out.println("Enter Bet Value:");
				double INPUTBET = s.nextDouble();
				//TODO this should subtract INPUTBALANCE from user balance in DB
				//System.out.println(INPUTBET);
				B.display();
				for(int Z = Nballs; Z>0;Z--) {//Inserts 1 ball then advances game one tick
					B.inPutBall(INPUTBET/Nballs);
					B.BallLogic();
					B.display();
					//B.BallLogic();// a forced second tick here can fix ball "bounce issue" not needed atm though
					//B.display();
					gameActive=true;
				}
				while(gameActive) {//finishes started game
					gameActive = B.isValidPos();
					B.BallLogic();
					B.display();
				}
			}
			
			s.close();
		}
	}

	private static boolean Login() {
		//TODO hook login method to DB
		//return true for testing purposes
		return true;
	}

}
