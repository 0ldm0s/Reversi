package com.prince.reversi.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.prince.reversi.bean.Point;
import com.prince.reversi.bean.ScorePoint;

public abstract class AI {
	/*public static final int INFINITE = 999999;
	*//**
	 * ��ǰ���̾��ƶ� evalLabel��������
	 * @param board
	 * @param evalLabel
	 * @return
	 *//*
	protected abstract int calcularScore(Board board,int evalLabel);
	*//**
	 * ��p����nowChessLabel �Ӻ� ���̾��ƶ�evalLabel�ŵ�����
	 * Ҫ�������̵Ļ���
	 * @param board
	 * @param evalLabel
	 * @param nowChessLabel
	 * @param p
	 * @return
	 *//*
	protected abstract int getScoreIf(Board board,int evalLabel,int nowChessLabel,Point p);
	
	*//**
	 * �ݹ������㷨 min max �㷨
	 * ���輺���Ͷ��ֶ��㹻����  ���ǿ��Խ��Է������ƽ������
	 * ���Լ�����ӶԼ��������ľ�����ѡ��õĽ��
	 * ���Է�����ѡ����ÿ����֧����õĽ���� ѡ�����Ľ��
	 * @param board ������
	 * @param evalLabel	��ֵ�������÷� ���������ߵ���һ��  ����˵��  �����������ľ�����
	 * @param nowChessLabel  �������е�ǰ�����ߵ�һ��  һ���Ǽ����Ͷ�����������
	 * @param deepLength	�ݹ���� �� �������㼶
	 * �������������� ������֦����  ���ڵݹ����������ʱ���õ�����ȱ�����ʽ
	 * @param minMaxScore   ��������ʱ ������ֵ�ǰ��֧�����ֵ ����ֵ�Ѿ����ϲ����ڵ���Сֵ�������ڶ����㹻���� ���Ѿ�����ѡ��ǰ��֧���ʿ��Բ����е�ǰ��֧�ļ������� 
	 * @param maxMinScore   �Է�����ʱ ������ֵ�ǰ��֧����Сֵ ����ֵ�Ѿ����ϲ����ڵ����ֵС    �����ڼ����㹻�������Ѿ�����ѡ��ǰ��֧���ʿ��Բ����е�ǰ��֧�ļ�������
	 * @return  ������һ�����ߵ�  ��װһ�����ŵ� ����ѡ��ǰ��ĶԵ�ǰ�����ߵ���ѵ÷�
	 *//*
	protected ScorePoint deepSearch(Board board,int evalLabel,int nowChessLabel,int deepLength,int minMaxScore,int maxMinScore){
		if (deepLength == 0) {//�������Ϊ0 ֱ�ӷ���
			return new ScorePoint(calcularScore(board, evalLabel), null);
		}
		final boolean isRobot = (nowChessLabel==evalLabel);	//�ж��Ƿ��ǻ��������
		int score = isRobot ? ( - INFINITE - 1) : (INFINITE + 1);	//ƽ�����½�
		List<Point> plist= board.getPutableList(nowChessLabel);		//��ȡ��ǰ���̿������б�
		Point returnPoint = new Point(-1,-1);	//���շ��صľ�������λ��
		int psize = plist.size();
		if (psize>0){
			final Map<Point,Integer> scoreMap = new HashMap<Point,Integer>();
			for (int i=0; i<psize;i++){
				Point pTemp=plist.get(i);
				scoreMap.put(pTemp,getScoreIf(board, evalLabel,nowChessLabel, pTemp));	//��ȡ������ӵĵ÷� 
			}
			Collections.sort(plist,new Comparator<Point>(){
				@Override
				public int compare(Point lhs,Point rhs) {
					int lhscore = scoreMap.get(lhs);
					int rhscore = scoreMap.get(rhs);
					return isRobot?rhscore-lhscore:lhscore-rhscore;
				}
			});
			if (deepLength == 1) {//���˼�����Ϊ1 ����u�߷�
				returnPoint =plist.get(0);
				score = scoreMap.get(returnPoint);
			} else {//���˼����Ȳ�Ϊ1 ��ݹ�˷���
				for (int i=0;i<plist.size();i++) {
					Point p = plist.get(i);
					board.putChessInPosition(p.getY(), p.getX(),nowChessType);
					Log.e("deepSearch","��ͨ�����������ȣ�"+(deepLength-1));
					ScorePoint scoreN_1=deepSearch(board, evalLabel, (nowChessType==ReversiBoard.WHITE)?ReversiBoard.BLACK:ReversiBoard.WHITE, deepLength-1, minMaxScore,maxMinScore);
					board.undo();
					Log.e("deepSearch","scoreN_1.getScore��"+scoreN_1.getScore());
					if (isRobot) {
						if (scoreN_1.getScore()>score) {
							score = scoreN_1.getScore();
							returnPoint = p;
						}
						minMaxScore = (minMaxScore > score ? minMaxScore: score);
						if (minMaxScore >= maxMinScore) {
							break;
						}
					} else {
						if (scoreN_1.getScore()<score) {
							score = scoreN_1.getScore();
							returnPoint = p;
						}
						maxMinScore = (maxMinScore < score ? maxMinScore: score);
						if (minMaxScore >= maxMinScore) {
							break;
						}
					}
				}
			}
		} else {
			if (!board.isGameOver()) {
				ScorePoint scoreN=deepSearch(board, evalLabel,(nowChessType==ReversiBoard.WHITE)?ReversiBoard.BLACK:ReversiBoard.WHITE, deepLength, meScore, youScore);
				score = scoreN.getScore();
				returnPoint = null;
			} else {
				score = getScoreWhenCanPass(board, evalLabel);
				returnPoint = null;
			}
		}
		return new ScorePoint(score, returnPoint);
	}*/
}
