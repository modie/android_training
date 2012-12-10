package com.example.android_training;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Wumpus extends Activity{
	WumpusGame wg;
	WumpusAgent wa ;
	World w ;
	int map[][];
	Thread ourThread = null ;
	Point p ,pnew;
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
		
		pnew = new Point();
		p = new Point();
		map =wg.getMap() ;
		w = wg.getWorld();
		wa = new WumpusAgent(7);
		t= new Thread(){
			public void run(){
				while(true){
					try{
						//TODO
						//this is where i call ai
						//maybe something like WumpusAgent(wg); :D
						//wg.makeMove();
						
						
						//p = wg.makeMove();  // p wg.makeMove(wa.decide(p.x,p.y));
						p = wa.getLocation() ;
						if(p.x!=w.getPlayerX()){
							p.x = w.getPlayerX();
							
						}
						if(p.y!= w.getPlayerY()){
							p.y = w.getPlayerY();
						}
						wa.setRoom(p.x, p.y, map[p.x][p.y]);
						wa.setVisited(p.x, p.y);
						wa.incrNumberOfVisits(p.x, p.y);
						
						Log.e("yaw", "bstrds number of visits is "+wa.getNumberOfVisits(p.x, p.y));
						wa.setLocation(p);
						wa.updateField();
						//then doing moves 
						if(wa.possibleMoves()==2)
						{
							wg.moveUp();
							pnew = new Point(p.x-1,p.y);
							
						}
						else if(wa.possibleMoves()==1)
						{
							wg.moveRight();
							pnew = new Point(p.x,p.y+1);
						}
						else if(wa.possibleMoves()==0)
						{
							wg.moveDown();
							pnew = new Point(p.x+1,p.y);
						}
						else if(wa.possibleMoves()==-1)
						{
							
							wg.moveLeft();
							pnew = new Point(p.x,p.y-1);
						}
						else if(wa.possibleMoves()==2)
						{
							wg.moveUp();
						}
						wa.setLocation(pnew);
						
						
						
						
						
						Thread.sleep(1000);
					}catch(InterruptedException e){
						
					}
				}
			}
		};
		t.start();
		
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
}
