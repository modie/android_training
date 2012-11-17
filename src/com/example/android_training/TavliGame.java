package com.example.android_training;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.View;

public class TavliGame extends View{
	private int max_height ;
	private int max_width ;
	private int x ;
	private int y ;
	private Paint paintforlines ;
	private Paint background ; 
	private Paint paintcheckers ;
	Handler handler = new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what){
			case 0 :
				invalidate();break ;
			default :
					break ;
			}
			super.handleMessage(msg);
		}
	};
	public TavliGame(Context context,int maxh , int maxw) {
		super(context);
		// TODO Auto-generated constructor stub
		paintforlines = new Paint();
		this.paintforlines.setColor(Color.WHITE);
		this.paintforlines.setAntiAlias(true);
		this.paintforlines.setStyle(Style.STROKE);
		this.paintforlines.setStrokeWidth(1);
		max_height = maxh ;
		max_width = maxw ;
		x = (int)(max_width / 25) ;
		y = x ;
		
	}
	protected void onDraw(Canvas canvas ){
		//zwgrafisma twn plagiwn grammwn 
		//allazei to y ;
		//menei stathero to width ;
		int xm = max_height ;
		int ym = max_width ;
		int akro_panw = (int)(x/2-3);
		int akro_deksia ;
		/*TODO 
		 * TODO
		 * TODO
		 * TODO
		 * 
		 * 
		 * let s try to set background the board ;)
		*/
		Bitmap board = BitmapFactory.decodeResource(getResources(), R.drawable.tavli_resized);
		canvas.drawBitmap(board,null,new Rect(0,0,max_width,max_height), paintforlines);
		
		
		canvas.drawLine(0, (xm/2),  ym , (xm/2), paintforlines);
		
		for (int i = 0 ; i<6 ; i++ ){
			canvas.drawLine(0, (x*i)+akro_panw, max_width, (x*i)+akro_panw, paintforlines);
		}
		//zwgrafisma twn panw orizontiwn
		for (int i = 0 ; i <6 ;i++){
			canvas.drawLine(0, max_height - (x*i)-akro_panw,max_width , max_height - (x*i)-akro_panw,paintforlines);
		}
		//zwgrafisma twn katw orizontiwn
		canvas.drawLine(2.6F*x, 0, 2.6F*x, max_height, paintforlines);
		//zwgrafisma tis prwtis kathetis 
		
		for(float i=4.25F;i<=12;i+=1.5F){
			canvas.drawLine(i*x, 0, i*x, max_height, paintforlines);
			
		}
		//zwgrafisma twn allwn 5 kathetwn
		
		canvas.drawLine(max_width-(2.6F*x),0,max_width-(2.6F*x),max_height,paintforlines);
		//zwgrafisma tis prwtis apo ta deksia 
		for(float i=4.25F;i<=12;i+=1.5F){
			canvas.drawLine(max_width-(i*x), 0, max_width-(i*x), max_height, paintforlines);
			
		}
		//zwgrafisma twn allwn 5 kathetwn apo ta deksia 
		
		
		super.onDraw(canvas);
		
	}
	
	
}
