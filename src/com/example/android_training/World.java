package com.example.android_training;

public class World {
	int monster_x ;
	int monster_y ;
	int lakouves_x[] ;
	int lakouves_y[] ;
	int player_pos_x =0;
	int player_pos_y =0;
	int looking_up = 2 ;
	int looking_down = 0 ;
	int looking_aristera = -1 ;
	int looking_deksia = 1 ;
	int size ;
	int map[][] ;
	int looking ; // 2 gia panw , 1 gia deksia , -1 gia aristera , 0 gia katw .
	World(int mx , int my , int lkx[],int lky[],int looking,int size)
	{
		this.looking = looking ;
		monster_x = mx ;
		monster_y = my ;
		lakouves_x = lkx ;
		lakouves_y = lky ;
		this.size = size ;
		player_pos_x = size- 1;
		map = new int[size][size];
		
		EnterValues();
	}
	public void Movement(int x , int y)
	{
		player_pos_x = x ;
		player_pos_y = y ;
	}
	void EnterValues(){
		//enter monster 
		map[monster_x][monster_y]= 3 ;
		//enter values around monster S=1 
		if(!((monster_x+1)>=size)){
			map[monster_x+1][monster_y]=1 ;
		}
		map[monster_x-1][monster_y]=1 ;
		map[monster_x][monster_y-1]=1 ;
		if(!((monster_y+1)>=size))
		{
			map[monster_x][monster_y+1]=1 ;
		}
		//enter pits , L == 4;
		for(int i =0 ;i<lakouves_x.length ; i++)
		{
			
				int lx = lakouves_x[i];
				int ly = lakouves_y[i];
				
				map[lx][ly]=4 ;
			
		}
		for(int i = 0 ; i < lakouves_x.length ; i++)
		{
			int lx = lakouves_x[i];
			int ly = lakouves_y[i];
			if(!(lx+1>=size))
			{
				map[lx+1][ly]=2 ;
			}
			map[lx-1][ly]=2 ;
			if(!(ly+1>=size))
			{
				map[lx][ly+1]=2 ;
			}
			map[lx][ly-1]=2 ;
		}
		map[player_pos_x][player_pos_y]= 5 ;//our playa 
		
		
		
		
	}
	public int getSize(){
		return size ;
	}
	public int[][] getMap(){
		return map;
	}
	public int getPlayerX(){
		return player_pos_x;
	}
	public int getPlayerY(){
		return player_pos_y;
	}
	public int getLooking(){
		return looking ;
	}
	public void setLooking(int i)
	{
		looking = i ;
	}
	public void printWorld()
	{
		System.out.println("Welcome to wumpus world ");
		System.out.println("S = smell , A = aura , X = monster, L = pit");
		System.out.println("deks = agent looking right , ar = agent looking left");
		System.out.println("up = agent looking up , down = agent looking down ");
		
		for (int i = 0 ; i<size ; i++)
		{
			
			System.out.println();
			for(int y = 0 ; y < size ; y ++)
			{
				String s = "|";
				if (map[i][y]== 1)
				{
					s += "  S  ";
				}
				else if(map[i][y]==2)
				{
					s+= "  A  ";
				}
				else if(map[i][y]==3)
				{
					s+= "  M  ";
				}
				else if(map[i][y]==4)
				{
					s+="  L  ";
				}
				else if(i == player_pos_x && y == player_pos_y ){
					
				
					if(map[player_pos_x][player_pos_y]==5 && looking == looking_aristera) {
						s+="  ar ";
					}
					else if(map[player_pos_x][player_pos_y]==5 && looking == looking_up)
					{
						s+= "  up ";
					}
					else if(map[player_pos_x][player_pos_y]==5 && looking == looking_down )
					{
						s+= " down";
					}
					else if(map[player_pos_x][player_pos_y]==5 && looking == looking_deksia)
					{
						s+= " deks";
					}
				}
				else
				{
					s+="     ";
				}
				s += "|";
				System.out.print(s);//5 spaces 
			}
			
			
			
			
			
			
			
			
			
		}
		
	}
	
}
