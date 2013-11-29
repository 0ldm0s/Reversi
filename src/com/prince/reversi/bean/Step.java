package com.prince.reversi.bean;

import java.util.List;

public class Step {
	private Point p;
	private int chessType;
	private List<Point> plist;//���������ӱ仯���յ�  ��λ��������chessType��ͬ
	public Point getP() {
		return p;
	}
	public void setP(Point p) {
		this.p = p;
	}
	public int getChessType() {
		return chessType;
	}
	public void setChessType(int chessType) {
		this.chessType = chessType;
	}
	public List<Point> getPlist() {
		return plist;
	}
	public void setPlist(List<Point> plist) {
		this.plist = plist;
	}
	public Step(Point p, int chessType, List<Point> plist) {
		super();
		this.p = p;
		this.chessType = chessType;
		this.plist = plist;
	}
	
}
