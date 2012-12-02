package com.example.android_training;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
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
                Toast.makeText(getContext(), "You Win!", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "Computer Win!", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "Loose!", Toast.LENGTH_SHORT).show();
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
        Bitmap rotate_left = BitmapFactory.decodeResource(getResources(), R.drawable.rotate_left);
        Bitmap new_rotate_left = Bitmap.createScaledBitmap(rotate_left, 120, 120, true);
        canvas.drawBitmap(new_rotate_left, this.getWidth()-120, max_height, paint);
        
        
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
        int x_aux = (int) (event.getX() / (this.getWidth() / x));
        int y_aux = (int) event.getY();
        if(event.getAction() == event.ACTION_UP){
        dosomeshit();
        }
        	//last try ;
        //after that try i m gonna go to surfaceview = gg :)
        
        return true;
    }
    public void dosomeshit(){
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
    	singlesquare[w.getPlayerX()][w.getPlayerY()]= new WumpusHuman(w.getPlayerX()*xss , yss*w.getPlayerY(),lnew);
    	
    	
    
    handler.sendMessage(Message.obtain(handler, 0));
    }
 
    public String getPiece(int player) {
        switch (player) {
        case 1:
            return "x";
        case -1:
            return "o";
        }
        return null;
    }
 
    public void drawimage(int x_aux, int y_aux) {
        Cell cel = null;
        if (whatdrawn) 
        {
            cel = new WumpusPit(singlesquare[x_aux][y_aux].x,singlesquare[x_aux][y_aux].y);
            whatdrawn = false;
        } 
        else
        {
            cel = new WumpusBlood(singlesquare[x_aux][y_aux].x, singlesquare[x_aux][y_aux].y);
            whatdrawn = true;
        }
 
        singlesquare[y_aux][x_aux] = cel;
 
        handler.sendMessage(Message.obtain(handler, 0));
 
    }
 
   
 
    
 
    public void resizegame(int s) {
        x = s;
        y = s;
 
        singlesquare = new Cell[x][y];
 
        int xss = l / x;
        int yss = a / y;
 
        for (int z = 0; z < y; z++) {
            for (int i = 0; i < x; i++) {
                singlesquare[z][i] = new Empty(xss * i, z * yss);
            }
        }
        handler.sendMessage(Message.obtain(handler, 0));
    }
     
    public int getPlayerwin() {
        return playerwin;
    }
}