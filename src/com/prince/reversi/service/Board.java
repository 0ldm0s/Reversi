package com.prince.reversi.service;

import java.util.List;

import com.prince.reversi.bean.Point;

public abstract class Board {
	/**
	 * ��ȡ������λ���б�
	 * @param nowChessLabel
	 * @return
	 */
	protected abstract List<Point> getPutableList(int nowChessLabel);
	/**
	 * ������һ������
	 */
	protected abstract void undo();
	/**
	 * �ڵ�i�� ��j�� ��ֹ chess
	 * @param i
	 * @param j
	 * @param chess
	 * @return
	 */
	protected abstract boolean putChessInPosition(int i,int j,int chess);
	
	protected abstract boolean isGameOver();
}
