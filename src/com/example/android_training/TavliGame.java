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
import android.view.MotionEvent;
import android.view.View;

/*
 * … //create rects for each position
[8:02:39 PM] moody rails: TODO
[8:02:41 PM] moody rails: for(int j =0;j<13;j++)
			{
[8:02:45 PM] moody rails: -_
[8:02:47 PM] moody rails: :P
[8:03:15 PM] moody rails: na valw allo ena comment ? :P
[8:03:40 PM] moody rails: // check rect for line vert [1],hor[1] - [vert2][hor2]
[8:03:44 PM] moody rails: ok :P
[8:03:47 PM] athena7x: :P
[8:06:03 PM] moody rails: kai ena teleutaio comment
[8:06:19 PM] moody rails: /////if it works with rects then i create a rec array with 6 items,for each position
[8:06:22 PM] moody rails: :P

 */
public class TavliGame extends View{
	private Checker[][] singlesquare = null ;
	private int max_height ;
	private int max_width ;
	private int x ;
	int linesHorizontal [] ;
	int linesVertical [];
	boolean whoplays = true;
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
		paintforlines = new Paint();
		this.paintforlines.setColor(Color.WHITE);
		this.paintforlines.setAntiAlias(true);
		this.paintforlines.setStyle(Style.STROKE);
		this.paintforlines.setStrokeWidth(1);
		max_height = maxh ;
		max_width = maxw ;
		x = (int)(max_width / 25) ;
		y = x ;
		int xm = max_height ;
		int akro_panw = (int)(x/2-3);
		//single square orisma,me tis times twn draw ;)
		//x = 15 
		//y = 12 
		
		
		
		
		linesHorizontal = new int[13];
		linesVertical = new int[14];
		
		//TODO Setting lines
		//canvas.drawLine(0, (xm/2),  ym , (xm/2), paintforlines);
		//zwgrafisma tis orizontias grammis stin mesi
		linesHorizontal[7]=(int)(xm/2);
		
		for (int i = 0 ; i<6 ; i++ ){
			//canvas.drawLine(0, (x*i)+akro_panw, max_width, (x*i)+akro_panw, paintforlines);
			linesHorizontal[i]=(int)((x*i)+akro_panw);
		}
		//zwgrafisma twn panw orizontiwn
		for (int i = 0 ; i <6 ;i++){
			//canvas.drawLine(0, max_height - (x*i)-akro_panw,max_width , max_height - (x*i)-akro_panw,paintforlines);
			linesHorizontal[12-i]= (int)( max_height - (x*i)-akro_panw);
		}
		//zwgrafisma twn katw orizontiwn
		
		//canvas.drawLine(2.6F*x, 0, 2.6F*x, max_height, paintforlines);
		linesVertical[0]= (int)(2.6F*x);
		
		//zwgrafisma tis prwtis kathetis 
		int y = 1;
		for(float i=4.25F;i<=12;i+=1.5F){
			//canvas.drawLine(i*x, 0, i*x, max_height, paintforlines);
			linesVertical[y]=(int)(i*x) ;
			y++ ;
		}
		//zwgrafisma twn allwn 5 kathetwn
		
		//canvas.drawLine(max_width-(2.6F*x),0,max_width-(2.6F*x),max_height,paintforlines);
		//zwgrafisma tis prwtis apo ta deksia 
		linesVertical[13]=(int)(max_width-(2.6F*x/2));
		y= 1 ;
		for(float i=4.25F;i<=12;i+=1.5F){
			//canvas.drawLine(max_width-(i*x), 0, max_width-(i*x), max_height, paintforlines);
			linesVertical[13-y]=(int)(max_width-(i*x));
			y++;
		}
		//TODO End of setting lines
		singlesquare = new Checker[14][13];
		for(int j =0;j<13;j++)
		{
		for(int i = 0 ; i<14 ; i ++)
		{
			
				singlesquare[i][j]= new CheckerEmpty(linesHorizontal[j],linesVertical[i]);
			}
		}
		
		
	}
	protected void onDraw(Canvas canvas ){
		//zwgrafisma twn plagiwn grammwn 
		//allazei to y ;
		//menei stathero to width ;
		int xm = max_height ;
		int ym = max_width ;
		int akro_panw = (int)(x/2-3);
		int akro_deksia ;
		
		Bitmap board = BitmapFactory.decodeResource(getResources(), R.drawable.tavli_resized);
		canvas.drawBitmap(board,null,new Rect(0,0,max_width,max_height), paintforlines);
		
		
		canvas.drawLine(0, (xm/2),  ym , (xm/2), paintforlines);
		//zwgrafisma tis orizontias grammis stin mesi
		//linesHorizontal[7]=(int)(xm/2);
		
		for (int i = 0 ; i<6 ; i++ ){
			canvas.drawLine(0, (x*i)+akro_panw, max_width, (x*i)+akro_panw, paintforlines);
			//linesHorizontal[i]=(int)((x*i)+akro_panw);
		}
		//zwgrafisma twn panw orizontiwn
		for (int i = 0 ; i <6 ;i++){
			canvas.drawLine(0, max_height - (x*i)-akro_panw,max_width , max_height - (x*i)-akro_panw,paintforlines);
			//linesHorizontal[12-i]= (int)( max_height - (x*i)-akro_panw);
		}
		//zwgrafisma twn katw orizontiwn
		
		canvas.drawLine(2.6F*x, 0, 2.6F*x, max_height, paintforlines);
		linesVertical[0]= (int)(2.6F*x);
		
		//zwgrafisma tis prwtis kathetis 
		//int y = 0;
		for(float i=4.25F;i<=12;i+=1.5F){
			canvas.drawLine(i*x, 0, i*x, max_height, paintforlines);
			//linesVertical[y]=(int)(i*x) ;
			//y++ ;
		}
		//zwgrafisma twn allwn 5 kathetwn
		
		canvas.drawLine(max_width-(2.6F*x),0,max_width-(2.6F*x),max_height,paintforlines);
		//zwgrafisma tis prwtis apo ta deksia 
		linesVertical[13]=(int)(max_width-(2.6F*x));
		//y= 0 ;
		for(float i=4.25F;i<=12;i+=1.5F){
			canvas.drawLine(max_width-(i*x), 0, max_width-(i*x), max_height, paintforlines);
			//linesVertical[13-y]=(int)(max_width-(i*x));
			//y++;
		}
		//zwgrafisma twn allwn 5 kathetwn apo ta deksia 
		
		
		
		//TODO zwgrafisma olwn twn singlesquares 
		//TODO
		//singlesquare = new Checker[14][13];
		/*TODO working atm
		for(int i = 1 ; i<13 ; i ++)
		{
			for(int j =1;j<12;j++)
			{
				singlesquare[i][j].draw(canvas, getResources(),i,j,linesVertical[j+1]-linesVertical[j],linesHorizontal[j+1]-linesHorizontal[j]);
			}
		}
		*/
		Bitmap im = BitmapFactory.decodeResource(getResources(), R.drawable.pouliportok);
		canvas.drawBitmap(im, null,new Rect(linesVertical[0],linesHorizontal[0],linesVertical[1],linesHorizontal[1]),new Paint());
		Bitmap yaw = BitmapFactory.decodeResource(getResources(), R.drawable.pouliroz);
		canvas.drawBitmap(yaw,null,new Rect(linesVertical[1],linesHorizontal[1],linesVertical[2],linesHorizontal[2]),new Paint());
		canvas.drawBitmap(yaw,null,new Rect(linesVertical[0],linesHorizontal[1],linesVertical[1],linesHorizontal[2]),new Paint());
		canvas.drawBitmap(yaw,null,new Rect(linesVertical[12],linesHorizontal[0],linesVertical[13],linesHorizontal[1]),new Paint());
		canvas.drawBitmap(yaw,null,new Rect(linesVertical[0],linesHorizontal[11],linesVertical[1],linesHorizontal[12]),new Paint());
		canvas.drawBitmap(yaw,null,new Rect(linesVertical[12],linesHorizontal[11],linesVertical[13],linesHorizontal[12]),new Paint());
		/*
		Bitmap im = BitmapFactory.decodeResource(getResources(), R.drawable.pouliportok);
		canvas.drawBitmap(im, null,new Rect(100,100,400,400),new Paint());
		*/
		//TODO change checker so he can hold a cube 
		
		//TODO
		
		
		
		
		
		super.onDraw(canvas);
		
	}
	public boolean onTouchEvent(MotionEvent event)
	{
		int x_touch = (int)(event.getX()/(this.getWidth()/x));		
		int y_touch = (int)(event.getY()/(this.getHeight()/y));
		int x = 0 ;
		int a ;
		for ( a = 0 ; a< linesVertical.length-1;a++)
		{
			if ( x_touch>=linesVertical[a] && x_touch <= linesVertical[a+1])
			{
				x = linesVertical[a];
			}
		}
		int y = 0 ;
		int b ;
		for(b = 0 ; b < linesHorizontal.length-1 ;b++)
		{
			if( y_touch>=linesHorizontal[b] && y_touch <=linesHorizontal[b+1])
			{
				y = linesHorizontal[b];
			}
		}
		drawImage(x,y,a,b);
		return super.onTouchEvent(event);
		
	}
	public void drawImage(int x_t,int y_t,int a,int b)
	{
		Canvas g = new Canvas();
		
		
		if(whoplays)
		{
			CheckerOrange chec = new CheckerOrange(x_t,y_t);
			singlesquare[a][b]=chec ;
			whoplays = false ;
		}
		else
		{
			CheckerPink chec = new CheckerPink(x_t, y_t);
			singlesquare[a][b]=chec ;
			whoplays = true ;
		}
		
		handler.sendMessage(Message.obtain(handler,0));
		
		
		
	}
	
	
}
