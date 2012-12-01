package com.example.android_training;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvX= (TextView)findViewById(R.id.tvX);
        TextView tvY = (TextView)findViewById(R.id.tvY);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        
        tvX.setText("Screen width = "+dm.widthPixels);
        tvY.setText("Screen height = "+dm.heightPixels);
        Button bt1 =(Button)findViewById(R.id.button1);
        Button bt2 =(Button)findViewById(R.id.button2);
        Button bt11 = (Button)findViewById(R.id.button11);
        Button bt3 = (Button)findViewById(R.id.button3);
        Button bt7 = (Button)findViewById(R.id.button7);
        Button bt5 = (Button)findViewById(R.id.button5);
        Button bt4 = (Button)findViewById(R.id.button4);
        Button bt6 = (Button)findViewById(R.id.button6);
        bt6.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent openWumpus = new Intent("android.intent.action.WUMPUS");
				startActivity(openWumpus);
				
			}
		});
        bt1.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openTestSurface = new Intent("android.intent.action.TESTSURFACEVIEW");
				startActivity(openTestSurface);
			}
        	
        });
        bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openSurfaceViewTouch = new Intent("android.intent.action.SURFACEVIEWTOUCH");
				startActivity(openSurfaceViewTouch);
			}
		});
        bt11.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openTextTestie = new Intent("android.intent.action.TEXTTESTIE");
				startActivity(openTextTestie);
				
			}
		});
        bt3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent openThreadTime = new Intent("android.intent.action.THREADTIME");
				startActivity(openThreadTime);
			}
		});
        bt7.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent openFullScreen = new Intent("android.intent.action.FULLSCREEN");
				startActivity(openFullScreen);
				
			}
		});
        bt5.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent startTicTacToe = new Intent("android.intent.action.TICTACTOE");
				startActivity(startTicTacToe);
			}
		});
        
        bt4.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent startTavli = new Intent("android.intent.action.Tavli");
				startActivity(startTavli);
				
				
			}
		});
        		
    }
}
