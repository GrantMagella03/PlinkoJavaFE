package com.maxtraining.c40.plinko.board;

public class Obj {
	public int posX;
	public int posY;
	public double value;
	public int id;
	public String type;
	public boolean UpdatedThisLoop = false;
	
	public boolean isUpdatedThisLoop() {
		return UpdatedThisLoop;
	}
	public void setUpdatedThisLoop(boolean updatedThisLoop) {
		UpdatedThisLoop = updatedThisLoop;
	}
	public Obj(String T,int ID) {
		type = T;
		id = ID;
	}
	public Obj(String T, int ID,boolean UTL) {
		this(T,ID);
		UpdatedThisLoop = UTL;
	}
	public Obj(String T,int ID,boolean UTL,double V, int X, int Y) {
		this(T,ID,UTL);
		value = V;
		posX = X;
		posY = Y;
	}
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
