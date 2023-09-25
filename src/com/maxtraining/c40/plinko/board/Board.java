package com.maxtraining.c40.plinko.board;

public class Board {
public Obj[][] B = new Obj[13][7];///x,y
	
public Obj[][] getB() {
return B;
}

public void setB(Obj[][] b) {
	B = b;
}

	public void TestGen() {
		for(int y=0; y<7;y++) {	
			for(int x=0; x<13; x++) {
				B[x][y] = new Obj("-",1);
			}
		}
	}
	
	public void display() {
		for(int y=0; y<7;y++) {	
			for(int x=0; x<13; x++) {
				if(B[x][y] != null) {
					System.out.print(B[x][y].getType());
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}
	}   
	
	public void GenerateBase() {
		for(int y=0; y<7;y++) {	
			for(int x=0; x<13; x++) {
				if(((y%2==0&&x%2==0)||(y%2==1&&x%2==1))&&y!=0){
					B[x][y] = new Obj("^",-1);
				}
				if(y==6&&x%2==1) {
					B[x][y] = new Obj("U",-1);
				}
			}
		}
	}
	
	public void BallUpdate() {
		for(int y=0; y<7;y++) {	
			for(int x=0; x<13; x++) {
				if(B[x][y] != null) {
					if(B[x][y].getType() == "O"&&B[x][y].isUpdatedThisLoop()==false) {
						double r = Math.random();
						if(B[x][y+1].getType()=="^") {
							if (r<0.5) {
								int ID = B[x][y].getId();
								B[x][y]=null;
								B[x-1][y+1] = new Obj("O",ID,true);
							} else {
								int ID = B[x][y].getId();
								B[x][y]=null;
								B[x+1][y+1] = new Obj("O",ID,true);
							}
						} else if(B[x][y+1].getType()==null) {
							int ID = B[x][y].getId();
							B[x][y]=null;
							B[x][y+1] = new Obj("O",ID,true);
						}
					}
				}
			}
		}
		
		for(int y=0; y<7;y++) {	
			for(int x=0; x<13; x++) {
				if(B[x][y]!=null) {
					if(B[x][y].getType()=="O") {
						B[x][y].setUpdatedThisLoop(false);
					}
				}
			}
		}
	}
	public void testplay() {
		
	}
}
