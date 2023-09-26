package com.maxtraining.c40.plinko.board;

public class Board {
public int BY = 7;
public int BX = 13;
public Obj[][] B = new Obj[BX][BY];///x,y
	
public Obj[][] getB() {
return B;
}

public void setB(Obj[][] b) {
	B = b;
}

	//prints current board status to console
	public void display() {
		for(int y=0; y<BY;y++) {	
			for(int x=0; x<BX; x++) {
				if(B[x][y] != null) {
					System.out.print(B[x][y].getType());
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}   
	//Generates base board
	public void GenerateBase() {
		for(int y=0; y<BY;y++) {	
			for(int x=0; x<BX; x++) {
				if(((y%2==0&&x%2==1)||(y%2==1&&x%2==0))&&y!=0){//if BY is odd ((y%2==0&&x%2==1)||(y%2==1&&x%2==0)) if its even the x%2's need to be reversed
					B[x][y] = new Obj("^",-1);
				}
				if(y==BY-1&&x%2==0) {
					B[x][y] = new Obj("U",-1);
				}
			}
		}
	}
	//moves the ball through the board dependent on the object at y+1 of the ball
	public void BallLogic() {
		for(int y=0; y<BY;y++) {
			// first loop  of x, moves ball left or right if the object at y+1 is a peg(^)
			for(int x=0; x<BX; x++) {
				if(B[x][y] != null) {
					if(B[x][y].getType() == "O"&&B[x][y].isUpdatedThisLoop()==false) {
						double r = BallDir(x);
						
						if(B[x][y+1]!=null&&B[x][y+1].getType()=="^") {
							if (r<0.5) {
								int ID = B[x][y].getId();
								double V = B[x][y].getValue();
								B[x][y]=null;
								B[x-1][y] = new Obj("O",ID,false,V,x-1,y);
							} else {
								int ID = B[x][y].getId();
								double V = B[x][y].getValue();
								B[x][y]=null;
								B[x+1][y] = new Obj("O",ID,false,V,x+1,y);
							}
						}
						
					}
				}
			}
			//loops through x a second time, moves the ball y+1 if the object in y+1 is null or a pocket(U)
			for(int x=0; x<BX; x++) {
				if(B[x][y]!=null&&B[x][y].getType()=="O") {
					if(B[x][y+1]==null) {
						int ID = B[x][y].getId();
						double V = B[x][y].getValue();
						B[x][y]=new Obj("^", -1);
						B[x][y+1] = new Obj("O",ID,true,V,x,y+1);
					} else if(B[x][y+1].getType()=="U") {
						double V = B[x][y].getValue();
						B[x][y]=new Obj("^", -1);
						Cashout(V,x);
						//TODO add visual clarity to what pocket a ball enters
					}
				}
				//fixes issue where top empty row has pegs generated into it, probably could be done better
				if(y==0&&B[x][y]!=null&&B[x][y].getType()=="^") {
	  				B[x][y] = null;
				}
			}
		}
		//set reset the update status of all active balls on board before loop closes
		for(int y=0; y<BY;y++) {	
			for(int x=0; x<BX; x++) {
				if(B[x][y]!=null) {
					if(B[x][y].getType()=="O") {
						B[x][y].setUpdatedThisLoop(false);
					}
				}
			}
		}
		try {Thread.sleep(150);} catch (InterruptedException e) {}
	}
	//chooses the direction the ball will go, if at the edge of the board the ball will always go inward, elsewise its random
	private double BallDir(int x) {
		if(x==0) {
			return 1;
		}
		if(x==BX-1) {
			return 0;
		}
		return Math.random();
	}

	//updates user balance based on x pos and value of the ball
	private void Cashout(double v, int x) {
		//TODO Implement api calls from here to update user balance
		
	}
	//spawns a ball at the top of the board
	public void inPutBall(double V) {
		int R = (int) (Math.random()*BX);
		B[R][0]= new Obj("O", (int)Math.random()*100, false, V, R, 0);
	}
	//returns true if there are any balls still on the board, else returns false
	public boolean isValidPos() {
		for(int y=0; y<BY;y++) {	
			for(int x=0; x<BX; x++) {
				if(B[x][y]!=null) {
					if(B[x][y].getType()=="O") {
						return true;
					}
				}
			}
		}
		return false;
	}
}
