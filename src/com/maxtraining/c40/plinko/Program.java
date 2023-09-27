package com.maxtraining.c40.plinko;
import java.util.Scanner;

import com.maxtraining.c40.plinko.board.Board;
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
		User user = null;
		System.out.println("Welcome to Plinko!");
		System.out.println("1. Login");
		System.out.println("2. New User");
		System.out.println("3. Get high score");
		System.out.println("Please select an option: ");
		String menuChoice = s.nextLine();
		switch (menuChoice){
		case("1"):
			user = cont.signIn(s);
		break;
		case("2"):
			user = cont.addNewUser(s);
		break;
		case("3"):
			System.out.println("The high score is "+cont.getHighScore());
		break;
		default:
			System.out.println("Please select a valid option!");
			break;
		}

		if (user != null) {
			innerActive = true;
		}

			
			while (innerActive) {//game input instance
				Board B = new Board();
				B.GenerateBase();
				System.out.println("Enter number of balls to insert:");
				int Nballs = s.nextInt();
				double INPUTBET = 0;
				Boolean validBet = false;
				while (validBet == false){
					System.out.println("Your balance is "+user.getScore());
					System.out.println("Enter Bet Value:");
					INPUTBET = s.nextDouble();
					if (INPUTBET <= user.getScore() && INPUTBET >= 0) {
					validBet = true;
					}
					else {
						System.out.println("Invalid bet! Bet cannot be more than your balance!");
					}
				}
				double oldScore = user.getScore();
				user.setScore(user.getScore() - INPUTBET);
				
				//main game loop
				B.display(0);
				for(int Z = Nballs; Z>0;Z--) {//Inserts 1 ball then advances game two ticks
					B.inPutBall(INPUTBET/Nballs);
					B.BallLogic(user);
					
					B.BallLogic(user);// a forced second tick here fixes ball "bounce issue"
					
					gameActive=true;
				}
				while(gameActive) {//finishes started game
					gameActive = B.isValidPos();
					B.BallLogic(user);
					
				}
				user.scoreDif(oldScore);
			}
		}
		s.close();
	}
}
