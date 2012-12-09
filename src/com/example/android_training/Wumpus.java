package com.example.android_training;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class Wumpus extends Activity{
	WumpusGame wg;
	WumpusAgent wa ;
	
	int map[][];
	Thread ourThread = null ;
	Point p ;
	Thread t ;
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
		wg = new WumpusGame(this);
		setContentView(wg);
		wg.rotateRight();
		wg.move();
		p = new Point();
		map =wg.getMap() ;
		wa = new WumpusAgent(12);
		t= new Thread(){
			public void run(){
				while(true){
					try{
						//TODO
						//this is where i call ai
						//maybe something like WumpusAgent(wg); :D
						//wg.makeMove();
						
						
						p = wg.makeMove();  // p wg.makeMove(wa.decide(p.x,p.y));
						wa.setRoom(p.x, p.y, map[p.x][p.y]);
						wa.setLocation(p);
						//update and getting moves 
						//then doing moves 
						if(wa.getRoom(p.x, p.y).isBlood() )
						{
							break;
						}
						Thread.sleep(500);
					}catch(InterruptedException e){
						
					}
				}
			}
		};
		//t.start();
		
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
