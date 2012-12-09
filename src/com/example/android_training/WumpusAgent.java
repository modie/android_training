package com.example.android_training;
//TODO working at arrayWumpus :D
import android.graphics.Point;

public class WumpusAgent
{
	protected boolean wumpusalive ;
	protected Point location ;
	protected int size ;
	WumpusRoom [][]r ;
	int couldright , couldleft,couldup,coulddown ;
	int choice ;
	// 2 for up , 1 for right , -1 for left, 0 for down 
	public Point getLocation()
	{
		return location;
	}
	public void setLocation(Point location)
	{
		this.location = location;
	}
	public int getCouldright()
	{
		return couldright;
	}
	public void setCouldright(int couldright)
	{
		this.couldright = couldright;
	}
	public int getCouldleft()
	{
		return couldleft;
	}
	public void setCouldleft(int couldleft)
	{
		this.couldleft = couldleft;
	}
	public int getCouldup()
	{
		return couldup;
	}
	public void setCouldup(int couldup)
	{
		this.couldup = couldup;
	}
	public int getCoulddown()
	{
		return coulddown;
	}
	public void setCoulddown(int coulddown)
	{
		this.coulddown = coulddown;
	}
	public WumpusAgent(int size)
	{
		wumpusalive = true ;
		this.size = size ;
		r = new WumpusRoom[size][size];
		for(int i=0 ; i<size  ;i ++)
		{
			for(int y = 0 ; y < size ; y++)
			{
				r[i][y] = new WumpusRoom();
			}
		}
		location = new Point(6,0);
		r[location.x][location.y].setVisited(true);
		
	}
	public void setRoom(int x,int y, int yaw)
	{
		if(yaw==1)
		{
			r[x][y].setBlood(true);
			r[x][y].incrNumber();
		}
		else if(yaw==2)
		{
			r[x][y].setAura(true);
			r[x][y].incrNumber();
		}
		else if(yaw==3)
		{
			r[x][y].setWumpus(true);
			r[x][y].incrNumber();
		}
		else if(yaw==4)
		{
			r[x][y].setPit(true);
			r[x][y].incrNumber();
		}
		else if(yaw==5)
		{
			r[x][y].setAura(true);
			r[x][y].setBlood(true);
			r[x][y].incrNumber();
		}
		
	}
	public WumpusRoom getRoom(int x,int y)
	{
		
		return r[x][y];
		
		
	}
	public void setStartingField(int x ,int y,boolean hasAura,boolean hasBlood)
	{
		r[x][y].setVisited(true);
		r[x][y].incrNumber();
		r[x][y].setAura(hasAura);
		r[x][y].setBlood(hasBlood);
	}
	//lines 115
	private WumpusRoom getLeftRoom(Point p)
	{
		if(p.y-1 >=0){
			return r[p.x][p.y-1];
		}
		else
		{
			return null ;
		}
	}
	private WumpusRoom getRightRoom(Point p)
	{
		if(p.y +1 < size)
		{
			return r[p.x][p.y+1];
		}
		else
		{
			return null ;
		}
	}
	private WumpusRoom getUpRoom(Point p)
	{
		if(p.x-1 >0)
		{
			return r[p.x-1][p.y];
			
		}
		else 
		{
			return null ;
		}
		
	}
	private WumpusRoom getDownRoom(Point p)
	{
		if(p.x +1 < size)
		{
			return r[p.x+1][p.y];
		}
		else
		{
			return null ;
		}
	}
	//looking for pit at 400 
	protected void updateField()
	{
		
		WumpusRoom left ,right , up , down,current ;
		left = getLeftRoom(location);
		right = getRightRoom(location);
		up = getUpRoom(location);
		down = getDownRoom(location);
		current = r[location.x][location.y];
		
		//checking for wumpus 
		if(wumpusalive)
		{
			if(current.isBlood())
			{
				if(up.isWumpus().equals("NO") && down.isWumpus().equals("NO") 
						&& left.isWumpus().equals("NO"))
				{
					right.setWumpus(true);
				}
				else if( up.isWumpus().equals("NO") && down.isWumpus().equals("NO") 
						&& right.isWumpus().equals("NO"))
				{
					left.setWumpus(true);
				}
				else if( up.isWumpus().equals("NO") && right.isWumpus().equals("NO") 
						&& left.isWumpus().equals("NO"))
				{
					down.setWumpus(true);
				}
				else if( down.isWumpus().equals("NO") && right.isWumpus().equals("NO")
						&& left.isWumpus().equals("NO"))
				{
					up.setWumpus(true);
				}
			}
			else //if no blood
			{
				if(up!=null)
					up.setWumpus(false);
				if(down!=null)
				down.setWumpus(false);
				if(left!=null)
				left.setWumpus(false);
				if(right!=null)
				right.setWumpus(false);
			}
		}
		if(current.isAura())//checkin for pit 
		{
			if(up.isPit().equals("NO") && down.isPit().equals("NO") 
					&& right.isPit().equals("NO"))
			{
				left.setPit(true);
			}
			else if( up.isPit().equals("NO") && down.isPit().equals("NO")
					&& left.isPit().equals("NO"))
			{
				right.setPit(true);
			}
			else if( up.isPit().equals("NO") && right.isPit().equals("NO")
					&& left.isPit().equals("NO"))
			{
				down.setPit(true);
			}
			else if( down.isPit().equals("NO") && right.isPit().equals("NO")
					&& left.isPit().equals("NO"))
			{
				up.setPit(true);
			}
		}
		else//if no aura,then no pits near
		{
			if(up!=null)
			up.setPit(false);
			if(down!=null)
			down.setPit(false);
			if(left!=null)
			left.setPit(false);
			if(right!=null)
			right.setPit(false);
		}
		
	}
	protected int possibleMoves()// = reason ,line 454 
	{
		WumpusRoom left, right ,up,down ;
		left = getLeftRoom(location);
		right = getRightRoom(location);
		up = getUpRoom(location);
		down = getDownRoom(location);
		
		//checking for moving right ;
		//line 577
		if(right!=null){
			if(right.isWumpus().equals("NO") && right.isPit().equals("NO")
					&& right.isVisited().equals("NO"))
			{
				couldright = 10 ;
			}
			else if(right.isWumpus().equals("NO") && right.isPit().equals("NO")
					&& right.isVisited().equals("YES"))
			{
				couldright = 10-right.getNumberofvisits() ;
			}
			else if (right.isWumpus().equals("YES") || right.isPit().equals("YES"))
			{
				couldright = -1 ;
			}
		}
		else {
			couldright = -100;
		}
		//TODO do the same for up left down line 613
		if(left!=null){
			if(left.isWumpus().equals("NO") && left.isPit().equals("NO")
					&& left.isVisited().equals("NO"))
			{
				couldleft = 10 ;
			}
			else if(left.isWumpus().equals("NO") && left.isPit().equals("NO")
					&& left.isVisited().equals("YES"))
			{
				couldleft = 10-left.getNumberofvisits() ;
			}
			else if (left.isWumpus().equals("YES") || left.isPit().equals("YES"))
			{
				couldleft = -1 ;
			}
		}
		else 
		{
			couldleft = -100 ;
		}
		//doing the same for up
		if(up!=null){
			if(up.isWumpus().equals("NO") && up.isPit().equals("NO")
					&& up.isVisited().equals("NO"))
			{
				couldup = 10 ;
			}
			else if(up.isWumpus().equals("NO") && up.isPit().equals("NO")
					&& up.isVisited().equals("YES"))
			{
				couldup = 10-up.getNumberofvisits() ;
			}
			else if (up.isWumpus().equals("YES") && up.isPit().equals("YES"))
			{
				couldup = -1 ;
			}
		}
		else 
		{
			couldup = -100 ;
		}
		//doing the same for down 
		if(down!=null){
			if(down.isWumpus().equals("NO") && down.isPit().equals("NO")
					&& down.isVisited().equals("NO"))
			{
				coulddown = 10 ;
			}
			else if(down.isWumpus().equals("NO") && down.isPit().equals("NO")
					&& down.isVisited().equals("YES"))
			{
				coulddown = 10-down.getNumberofvisits() ;
			}
			else if (down.isWumpus().equals("YES") && down.isPit().equals("YES"))
			{
				coulddown = -1 ;
			}
		}
		else
		{
			coulddown = -100;
		}
		return getMove(couldright,couldup,couldleft,coulddown);
		
		//return getMove(z) ;
		
	}
	private int getMove( int right, int up, int left, int down )
	{
		
		int highest = Integer.MIN_VALUE;
		if(right == up && up == left && down == left)
		{
			return up;
		}
		
		if( ( highest < right && right > 0 ) )
		{
			if(right!=-100)
			{
				highest = right;
			}
		}
		if( ( highest < up && up > 0 ) )
		{
			if(up!=-100)
			{
				highest = up;
			}
		}
		if( highest < left && left > 0  )
		{
			if(left!=-100)
			{
			
			highest = left;
			}
		}
		if( highest < down && down > 0  )
		{
			if(down!=-100)
			{
				highest = down;
			}
		}
		int choice = 10 ;
		if (highest == down){
			choice = 0;
		}else if(highest == up){
			choice = 2;
		}else if(highest == left){
			choice = -1 ;
		}else if( right == 0) {
			choice = 1 ;
		}
		return choice;
	}
	public int getMove(int choice)
	{
		int i = 10 ;
		if(choice == couldright)
		{
			i =1 ;
		}
		else if(choice == couldleft)
		{
			i= -1 ;
		}
		else if(choice == couldup)
		{
			i=  2 ;
		}
		else if(choice == coulddown)
		{
			i= 0 ;
		}
		return i ;
	}
	
	
}
