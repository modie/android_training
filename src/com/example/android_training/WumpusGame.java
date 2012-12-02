package com.example.android_training;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
 
public class WumpusGame extends View {
    private Cell[][] singlesquare = null;
    int x = 10;
    int y = 10;
    private int l;
    private int a;
    int xss ;
    int yss ;
    int looking_up = 2 ;
	int looking_down = 0 ;
	int looking_aristera = -1 ;
	int looking_deksia = 1 ;
    private boolean whatdrawn = false;
    private int playerwin = 3;
    private Paint paint;
    World w ;
    Handler handler = new Handler() {
        // @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case 0:
            	//invalidate comes there ..
            	invalidate();
               
                break;
            case 1:
                Toast.makeText(getContext(), "Wumpus dead", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getContext(), "Wumpus dead NOT", Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(getContext(), "Treasure Found-Win", Toast.LENGTH_LONG).show();
                break;
            case 4:
            	Toast.makeText(getContext(), "Yaw since u are not columbus", Toast.LENGTH_SHORT).show();
            	Toast.makeText(getContext(), "there is nothing there", Toast.LENGTH_SHORT).show();
            	//there is nothing there
            	break;
            case 5:
            	Toast.makeText(getContext(), "Haha fell into pit ", Toast.LENGTH_LONG).show();
            	newWorld();
            	break;
            case 6:
            	Toast.makeText(getContext(), "Haha wumpus made love with ya", Toast.LENGTH_LONG).show();
            	break;
            default:
                break;
            }
 
            super.handleMessage(msg);
        }
    };
 
    public int getGameSize() {
        return x;
    }
 
    public WumpusGame(Context context) {
        super(context);
 
        paint = new Paint();
        this.paint.setARGB(255, 0, 0, 0);
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Style.STROKE);
        this.paint.setStrokeWidth(5);
        
        
        l = this.getWidth();
        a = (this.getHeight()-250);
        newWorld();
        /*int yaw[] = {1,2};
		int yaw1[]= {2,4};
        w = new World(4,5,yaw1,yaw,2,7);
        x = w.getSize();
        y = w.getSize();
        singlesquare = new Cell[x][y];
 
        xss = (int)(l / x);
        yss = (int)(a / y);
        
        for (int z = 0; z < y; z++) {
            for (int i = 0; i < x; i++) {
                singlesquare[z][i] = new WumpusEmpty(xss * i, z * yss);
            }
        }
		setWorld(w) ;
		*/
		
    }

	
	public void newWorld()
	{
		int yaw[] = {1,2};
		int yaw1[]= {2,4};
        w = new World(4,5,yaw1,yaw,2,7);
        x = w.getSize();
        y = w.getSize();
        singlesquare = new Cell[x][y];
 
        xss = (int)(l / x);
        yss = (int)(a / y);
        
        for (int z = 0; z < y; z++) {
            for (int i = 0; i < x; i++) {
                singlesquare[z][i] = new WumpusEmpty(xss * i, z * yss);
            }
        }
		setWorld(w) ;
	}
	@Override
    protected void onDraw(Canvas canvas) {
		
		int max_height = this.getHeight()- 250 ;
        for (int i = 0; i < singlesquare.length; i++) {
            for (int j = 0; j < singlesquare[0].length; j++) {
                singlesquare[i][j].draw(canvas, getResources(), j, i, (this
                        .getWidth() + 3)
                        / singlesquare.length, (max_height)
                        / singlesquare[0].length);
            }
        }
        //button to rotate right
        Bitmap rotate_right = BitmapFactory.decodeResource(getResources(), R.drawable.redo);
        Bitmap new_rotate_right = Bitmap.createScaledBitmap(rotate_right, 120, 120, true);
        canvas.drawBitmap(new_rotate_right, this.getWidth()-120, max_height, paint);
        //button to rotate left
        Bitmap rotate_left = BitmapFactory.decodeResource(getResources(), R.drawable.redo1);
        Bitmap new_rotate_left = Bitmap.createScaledBitmap(rotate_left, 120, 120, true);
        canvas.drawBitmap(new_rotate_left,0,max_height,paint);
        //button to move
        Bitmap BtnMove = BitmapFactory.decodeResource(getResources(), R.drawable.move);
        Bitmap newBtnMove = Bitmap.createScaledBitmap(BtnMove, this.getWidth()-240, 120, true);
        canvas.drawBitmap(newBtnMove,120 , max_height , paint);
        
        super.onDraw(canvas);
    }
	public void setWorld(World w){
		int size = w.getSize();
		int map[][] = w.getMap();
		int player_pos_x = w.getPlayerX();
		int player_pos_y = w.getPlayerY();
		int looking = w.getLooking() ;
		WumpusPit we = null ;
		//singlesquare[2][2]= new WumpusPit(2*xss , 2 *yss);
		
		for (int i = 0 ; i<size ; i++)
		{
			
			
			System.out.println();
			for(int y = 0 ; y < size ; y ++)
			{
				
				
				if (map[i][y]== 1)
				{
					singlesquare[i][y] = new WumpusBlood(i*xss , yss * y);
				}
				else if(map[i][y]==2)
				{
					singlesquare[i][y]= new WumpusAura(i*xss , yss*y);
				}
				else if(map[i][y]==3)
				{
					singlesquare[i][y]= new WumpusMonster(i*xss , yss*y);
				}
				else if(map[i][y]==4)
				{
					singlesquare[i][y] =  new WumpusPit(i* xss , yss*y);
				}
				else if(i == player_pos_x && y == player_pos_y )
				{
					if(map[player_pos_x][player_pos_y]==5) 
					{
						singlesquare[i][y] = new WumpusHuman(i*xss , yss*y,w.getLooking());
					}
				}
				
			}
	}
	
	}
 
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x_aux = (int) (event.getX());
        int y_aux = (int) (event.getY());
        if(x_aux >=this.getWidth()-120 && event.getAction() == event.ACTION_UP){
        rotateRight();
        }
        else if(x_aux <=120 && event.getAction() == event.ACTION_UP)
        {
        	rotateLeft();
        }
        else if(x_aux >120 && x_aux < this.getWidth()-120 && event.getAction() == event.ACTION_UP)
        {
        	move();
        }
        
        
        
        return true;
    }
    public void rotateRight()
    {
    	int l = w.getLooking();
    	int lnew =-1;
    	if(l == 0 )
    	{
    		lnew = -1 ;
    	}
    	else if(l == 2)
    	{
    		lnew = 1 ;
    	}
    	else if(l == 1)
    	{
    		lnew= 0 ;
    	}
    	else if(l == -1)
    	{
    		lnew = 2 ;
    	}
    	w.setLooking(lnew);
    	singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),lnew,whatWasBeforeLanding(w.getPlayerX(),w.getPlayerY()));
    	
    	
    
    handler.sendMessage(Message.obtain(handler, 0));
    }
    public void rotateLeft()
    {
    	
    	int l = w.getLooking();
    	int lnew =-1;
    	
    	if(l == 0 )
    	{
    		lnew = 1 ;
    	}
    	else if(l == 2)
    	{
    		lnew = -1 ;
    	}
    	else if(l == 1)
    	{
    		lnew= 2 ;
    	}
    	else if(l == -1)
    	{
    		lnew = 0 ;
    	}
    	w.setLooking(lnew);
    	singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),lnew,whatWasBeforeLanding(w.getPlayerX(), w.getPlayerY()));
    	
    	
    
    handler.sendMessage(Message.obtain(handler, 0));
    }
    public void move()
    {
    	int oldmap[][] = w.getMap();
    	WumpusPit pit = null;
    	WumpusMonster  mon = null ;
    	//TODO add here for treasure WumpusTreasure tres = null ;
    	int l = w.getLooking();
    	int xprev = w.getPlayerX();
    	int yprev = w.getPlayerY();
    	int xnew = xprev ;
    	int ynew = yprev ;
    	int whatwasBeforeLanding = R.drawable.roombase;
    	
    	if (l == 2)
    	{
    		if(xprev -1 >=0 ){
    			if(w.getId(xprev-1, yprev) ==4){
    				handler.sendMessage(Message.obtain(handler,5));
    				
    			}
    			else if(w.getId(xprev-1, yprev) ==3)
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else{
		    		xnew = xprev -1 ;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
		    	}
    		}
    		else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    		ynew = yprev ;
    		
    		
    	}
    	else if( l == 1)
    	{
    		xnew = xprev ;
    		if (yprev +1 <w.getSize()){
    			if( w.getId(xnew, yprev+1) ==4 ){
    				handler.sendMessage(Message.obtain(handler,5));
    				
    			}
    			else if( w.getId(xnew, yprev+1) ==3)
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else{
		    		ynew = yprev + 1 ;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
    			}
    		}
    		else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    	}
    	else if( l == 0)
    	{
    		if(xprev+1 < w.getSize()){
    			if(w.getId(xprev+1, yprev) ==4 ){
    				handler.sendMessage(Message.obtain(handler,5));
    				
    			}
    			else if(w.getId(xprev+1, yprev) ==3 )
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else{
		    		xnew = xprev +1 ;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
	    		}
    		}
    		else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    		ynew = yprev ;
    	}
    	else if(l==-1)
    	{
    		xnew = xprev ;
    		if(yprev - 1 >=0){
    			if(w.getId(xprev, yprev-1) ==4 )
    			{
    				handler.sendMessage(Message.obtain(handler,5));
    			}
    			else if(w.getId(xprev, yprev-1) ==3 )
    			{
    				handler.sendMessage(Message.obtain(handler,6));
    			}
    			else
    			{
		    		ynew = yprev -1;
		    		whatwasBeforeLanding =whatWasBeforeLanding(xnew, ynew);
    			}
    		}else{
    			handler.sendMessage(Message.obtain(handler, 4));
    		}
    	}
    	
    	w.Movement(xnew, ynew);
    	if (oldmap[xprev][yprev]== 1)
		{
			singlesquare[xprev][yprev] = new WumpusBlood(xprev*xss , yss * yprev);
		}
		else if(oldmap[xprev][yprev]==2)
		{
			singlesquare[xprev][yprev]= new WumpusAura(xprev*xss , yss*yprev);
		}
		else if(oldmap[xprev][yprev]==3)
		{
			singlesquare[xprev][yprev]= new WumpusMonster(xprev*xss , yss*yprev);
		}
		else if(oldmap[xprev][yprev]==4)
		{
			singlesquare[xprev][yprev] =  new WumpusPit(xprev* xss , yss*yprev);
		}
		else if(oldmap[xprev][yprev]==0)
		{
			singlesquare[xprev][yprev] = new WumpusEmpty(xprev*xss, yprev*yss);
		}
		
    	singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),l,whatwasBeforeLanding);
    	
    	handler.sendMessage(Message.obtain(handler, 0));
    }
    public int whatWasBeforeLanding(int x,int y){
    	return  singlesquare[x][y].Drawable();
    }
 
    
 
   
 
    //TODO
    //TODO
    //TODO
    //TODO
    //fixe aura/blood when rotating :D
   
 
    
}