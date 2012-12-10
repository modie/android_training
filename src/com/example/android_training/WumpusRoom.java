package com.example.android_training;

public class WumpusRoom
{
	private boolean empty ;
	private boolean pit ;
	private boolean aura ;
	private boolean blood ;
	private boolean maybepit ;
	private boolean wumpus ;
	private boolean maybewumpus ;
	private boolean visited ;
	private int numberofvisits ;
	public WumpusRoom(){
		
		numberofvisits = 0;
	}
	WumpusRoom(boolean empty , boolean pit ,boolean aura , boolean blood ,boolean wumpus,
			 boolean visited){
		empty = this.empty ;
		this.pit = pit ;
		this.aura = aura ;
		maybepit = false ;
		this.blood = blood ;
		this.wumpus = wumpus ;
		this.visited = visited ;
		if(visited){
			numberofvisits = 1 ;
			
		}else
		{
			numberofvisits = 0;
		}
		
	}
	public boolean isEmpty()
	{
		return empty ;
	}
	public void setEmpty(boolean empty)
	{
		this.empty = empty;
	}
	public boolean isPit()
	{
		return pit ;
	}
	public void setPit(boolean pit)
	{
		this.pit = pit;
		maybepit = false ;
	}
	public boolean isAura()
	{
		return aura;
	}
	public void setAura(boolean aura)
	{
		this.aura = aura;
	}
	public boolean isBlood()
	{
		return blood;
	}
	public void setBlood(boolean blood)
	{
		this.blood = blood;
	}
	public boolean isMaybepit()
	{
		return maybepit;
	}
	public void setMaybepit(boolean maybepit)
	{
		this.maybepit = maybepit;
	}
	public boolean isWumpus()
	{
		return wumpus;
	}
	public void setWumpus(boolean wumpus)
	{
		this.wumpus = wumpus;
	}
	public boolean isMaybewumpus()
	{
		return maybewumpus;
	}
	public void setMaybewumpus(boolean maybewumpus)
	{
		this.maybewumpus = maybewumpus;
	}
	public boolean isVisited()
	{
		return visited ;
	}
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
	public int getNumberofvisits()
	{
		return numberofvisits;
	}
	public void setNumberofvisits(int numberofvisits)
	{
		this.numberofvisits = numberofvisits;
	}
	public void incrNumber()
	{
		numberofvisits ++ ;
	}
	
}
