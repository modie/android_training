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
		maybepit= true ;
		maybewumpus = true ;
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
	public String isEmpty()
	{
		if(empty) return "YES";
		else return "NO";
	}
	public void setEmpty(boolean empty)
	{
		this.empty = empty;
	}
	public String isPit()
	{
		if(pit)
			return "YES";
		else if((!pit)&&maybepit) return "MAYBE";
		else return "NO";
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
	public String isWumpus()
	{
		if(wumpus)return "YES";
		else if((!wumpus)&&maybewumpus)return "MAYBE";
		else return "NO";
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
	public String isVisited()
	{
		if(visited)return "YES";
		else return "NO";
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
