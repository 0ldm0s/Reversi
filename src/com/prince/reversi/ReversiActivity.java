package com.prince.reversi;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.prince.reversi.bean.Point;
import com.prince.reversi.service.ReversiAi;
import com.prince.reversi.service.ReversiBoard;
import com.prince.reversi.view.ReversiView;
import com.prince.reversi.view.ReversiView.ViewStateListenner;

public class ReversiActivity extends Activity {
	private ReversiView reversiView;
	private ReversiBoard board;
	private ReversiAi ai;
	private Handler handler;
	private Button xian;
	
	private int selfchess=ReversiBoard.BLACK;
	private int robotChess = ReversiBoard.WHITE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initBoard();
        initView();
        initHandler();
    }
    private void initHandler(){
    	handler = new Handler() {  
	        public void handleMessage(Message message) {
	        	switch (message.arg1){
	        		case 1:
	        			reversiView.drawReversiViewByBoard(board,selfchess);
	        			break;
	        		case 2:
	        			Long time = (Long)message.obj;
	        			Toast.makeText(ReversiActivity.this, "���㻨��"+time+"ms", Toast.LENGTH_SHORT).show();
	        			break;
	        		case 3:
	        			String msg = (String)message.obj;
	        			Toast.makeText(ReversiActivity.this, msg, Toast.LENGTH_SHORT).show();
	        			break;
	        	}
	        }  
	    };
    }
    public void initView(){
    	reversiView = (ReversiView)findViewById(R.id.reversiView);
    	xian = (Button)findViewById(R.id.xian);
    	reversiView.addViewStateListenner(new ViewStateListenner() {
			@Override
			public void onPrepared() {
				reversiView.drawReversiViewByBoard(board,ReversiBoard.BLACK);
			}
			@Override
			public void onBoardClick(int x, int y) {
				if(x>=ReversiBoard.BOARD_LENGTH||y>=ReversiBoard.BOARD_LENGTH){
					reversiView.canOnclick = true;
					return ;
				}
				Log.e("onBoardClick", "�������x_y��"+x+"_"+y);
				Log.e("onBoardClick", "��ǰ���ӣ�"+selfchess);
				boolean flag = board.putChessInPosition(y, x, selfchess);
				Log.e("onBoardClick", "�������ӳɹ�"+flag);
				if(flag){
					reversiView.drawReversiViewByBoard(board,robotChess);
				}else{
					reversiView.canOnclick = true;
				}
			}
			@Override
			public void onLastChessDraw(int deschess) {
				if(board.isGameOver()){
					sendMessage(3, "��Ϸ����");
					return;
				}
				if(deschess==selfchess){
					if(board.getPutableList(robotChess).size()!=0){
						aiCalcular();
					}
				}else{
					if(board.getPutableList(selfchess).size()==0){
						aiCalcular();
					}
				}
			}
		});
    	xian.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				selfchess=ReversiBoard.WHITE;
				robotChess = ReversiBoard.BLACK;
				aiCalcular();
			}
		});
    }
    private void aiCalcular(){
    	int chessType =robotChess;
    	if(board.getPutableList(chessType).size()>0){
    		long timestart = new Date().getTime();
			Point p = ai.findBestStep(board, chessType);
			long timeend = new Date().getTime();
			sendMessage(2, timeend-timestart);
			int y=p.getY();
			int x=p.getX();
			Log.e("onBoardClick", "����ѡ������x_y��"+x+"_"+y);
			Log.e("onBoardClick", "��ǰ���ӣ�"+chessType);
			boolean flag = board.putChessInPosition(y, x, chessType);
			Log.e("onBoardClick", "�����������ӳɹ�"+flag);
			if(flag){
				sendMessage(1);
			}
			if(board.getPutableList(selfchess).size()!=0){
				reversiView.canOnclick = true;
			}
		}
    }
    private void sendMessage(int caseindex){
    	Message message = new Message();
       	message.arg1=caseindex;
        handler.sendMessage(message);
    }
    private void sendMessage(int caseindex,Object obj){
    	Message message = handler.obtainMessage(0, obj);
       	message.arg1=caseindex;
        handler.sendMessage(message);
    }
    public void initBoard(){
    	board = new ReversiBoard();
    	board.resetGame();
    	ai = new ReversiAi();
    }
}