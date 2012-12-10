package com.example.android_training;
//TODO working at arrayWumpus :D
//TODO remove Strings from wumpus room and here,then debug :D
import android.graphics.Point;
import android.util.Log;

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
	public void incrNumberOfVisits(int x,int y)
	{
		r[x][y].incrNumber();
		
	}
	public void setVisited(int x,int y)
	{
		r[x][y].setVisited(true);
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
	private WumpusRoom getUpLeftRoom(Point p)
	{
		if(p.x-1 >= 0 && p.y-1>=0)
		{
			return r[p.x-1][p.y-1];
		}
		else 
		{
			return null ;
		}
	}
	private WumpusRoom getUpRightRoom(Point p)
	{
		if(p.x-1>=0 && p.y+1<size)
		{
			return r[p.x-1][p.y+1];
		}
		else 
		{
			return null ;
		}
	}
	private WumpusRoom getUpRoom(Point p)
	{
		if(p.x-1 >=0)
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
	private WumpusRoom getDownLeftRoom(Point p)
	{
		if(p.x+1 < size && p.y-1>=0)
		{
			return r[p.x+1][p.y-1];
		}
		else
		{
			return null ;
		}
	}
	private WumpusRoom getDownRightRoom(Point p)
	{
		if(p.x+1 < size && p.y+1 < size)
		{
			return r[p.x+1][p.y+1];
		}
		else 
		{
			return null ;
		}
	}
	//looking for pit at 400 
	protected void updateField()
	{
		
		WumpusRoom left ,right , up , down,current,upleft,upright,downright,downleft ;
		left = getLeftRoom(location);
		right = getRightRoom(location);
		up = getUpRoom(location);
		down = getDownRoom(location);
		upleft = getUpLeftRoom(location);
		upright = getUpRightRoom(location);
		downright = getDownRightRoom(location);
		downleft = getDownLeftRoom(location);
		current = r[location.x][location.y];
		
		//checking for wumpus 
		if(wumpusalive)
		{
			if(current.isBlood())
			{
				if(!up.isWumpus() && !down.isWumpus()
						&& !left.isWumpus())
				{
					right.setWumpus(true);
				}
				else if( !up.isWumpus() && !down.isWumpus()
						&& !right.isWumpus())
				{
					left.setWumpus(true);
				}
				else if( !up.isWumpus() && !right.isWumpus() 
						&& !left.isWumpus())
				{
					down.setWumpus(true);
				}
				else if( !down.isWumpus() && !right.isWumpus()
						&& !left.isWumpus())
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
			if(!up.isPit() && !down.isPit() 
					&& !right.isPit())
			{
				left.setPit(true);
			}
			else if( !up.isPit() && !down.isPit()
					&& !left.isPit())
			{
				right.setPit(true);
			}
			else if( !up.isPit() && !right.isPit()
					&& !left.isPit())
			{
				down.setPit(true);
			}
			else if( !down.isPit() && !right.isPit()
					&& !left.isPit())
			{
				up.setPit(true);
			}
			if(upleft.isAura() )
			{
				left.setMaybepit(true);
				up.setMaybepit(true);
			}
			if(downleft.isAura())
			{
				left.setMaybepit(true);
				down.setMaybepit(true);
				
			}
			if(downright.isAura())
			{
				down.setMaybepit(true);
				right.setMaybepit(true);
			}
			if(upright.isAura())
			{
				up.setMaybepit(true);
				right.setMaybepit(true);
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
			if(!right.isWumpus() && !right.isPit()
					&& !right.isVisited())
			{
				couldright = 20 ;
			}
			else if(!right.isWumpus() && !right.isPit()
					&& right.isVisited())
			{
				couldright = 20-right.getNumberofvisits() ;
			}
			else if(right.isMaybepit() && !right.isVisited())
			{
				couldright = 10 ;
			}
			else if(right.isMaybepit() && right.isVisited())
			{
				couldright = 10 - right.getNumberofvisits() ;
			}
			else if (right.isWumpus() || right.isPit())
			{
				couldright = -1 ;
			}
		}
		else {
			couldright = -100;
		}
		//TODO do the same for up left down line 613
		if(left!=null){
			if(!left.isWumpus() && !left.isPit()
					&& !left.isVisited())
			{
				couldleft = 20 ;
			}
			else if(!left.isWumpus() && !left.isPit()
					&& left.isVisited())
			{
				couldleft = 20-left.getNumberofvisits() ;
			}
			else if(left.isMaybepit() && !left.isVisited())
			{
				couldleft = 10 ;
			}
			else if(left.isMaybepit() && left.isVisited())
			{
				couldleft = 10 - left.getNumberofvisits() ;
			}
			else if (left.isWumpus() || left.isPit())
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
			if(!up.isWumpus() && !up.isPit()
					&& !up.isVisited())
			{
				couldup = 20 ;
			}
			else if(!up.isWumpus() && !up.isPit()
					&& up.isVisited())
			{
				couldup = 20-up.getNumberofvisits() ;
			}
			else if(up.isMaybepit() && !up.isVisited())
			{
				couldup = 10 ;
			}
			else if(up.isMaybepit() && up.isVisited())
			{
				couldup = 10 - up.getNumberofvisits() ;
			}
			else if (up.isWumpus() || up.isPit())
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
			if(!down.isWumpus() && !down.isPit()
					&& !down.isVisited())
			{
				coulddown = 20 ;
			}
			else if(!down.isWumpus()&& !down.isPit()
					&& down.isVisited())			
			{
				coulddown = 20-down.getNumberofvisits() ;
			}
			else if(up.isMaybepit() && !up.isVisited())
			{
				couldup = 10 ;
			}
			else if(up.isMaybepit() && up.isVisited())
			{
				couldup = 10 - up.getNumberofvisits() ;
			}
			else if (down.isWumpus() || down.isPit())
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
			
		}else if( right == highest) {
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
