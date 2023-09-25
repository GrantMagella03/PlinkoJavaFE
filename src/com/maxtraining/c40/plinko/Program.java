package com.maxtraining.c40.plinko;

import com.maxtraining.c40.plinko.board.Board;
import com.maxtraining.c40.plinko.board.Obj;

public class Program {

	public static void main(String[] args) {
		Board board1 = new Board();
		board1.GenerateBase();
		Obj[][] B = board1.getB();
		B[5][0] = new Obj("O", 0);
		board1.setB(B);
		board1.display();
		board1.BallUpdate();
		board1.display();
		board1.BallUpdate();
		board1.display();
		board1.BallUpdate();
	}

}
