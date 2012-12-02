package com.example.android_training;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

/*
int looking_up = 2 ;
int looking_down = 0 ;
int looking_aristera = -1 ;
int looking_deksia = 1 ;
 */
public class WumpusHuman extends Cell {
	int looking ;
	int baseDrawing ;
	int drawBefore = R.drawable.roombase;
    public WumpusHuman(int x, int y,int looking) {
        super(x, y);
        this.looking = looking ;
    }
    public WumpusHuman(int x,int y,int looking,int drawBefore)
    {
    	super(x,y);
    	this.looking = looking ;
    	this.drawBefore = drawBefore;
    }
 
    public void draw(Canvas g, Resources res, int x, int y, int w, int h) {
    Bitmap im = BitmapFactory.decodeResource(res, drawBefore);
    g.drawBitmap(im, null, new Rect(x*w, y*h, (x*w)+w, (y*h)+h), new Paint());
    Bitmap human = BitmapFactory.decodeResource(res, R.drawable.playern);
    Matrix matrix = new Matrix();
    if(looking == 2)
    {
    	matrix.postRotate(0);
    }
    else if(looking == 1)
    {
    	matrix.postRotate(90);
    }
    else if(looking == -1 )
    {
    	matrix.postRotate(270);
    }
    else if(looking == 0 )
    {
    	matrix.postRotate(180);
    }
    
    Bitmap rotatedHuman = Bitmap.createBitmap(human,0,0,human.getWidth(),human.getHeight(),matrix,true);
    g.drawBitmap(rotatedHuman, null, new Rect(x*w, y*h, (x*w)+w, (y*h)+h), new Paint());
    }
 
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Empty) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return " ";
    }
	@Override
	public int Drawable() {
		// TODO Auto-generated method stub
		return drawBefore ;
	}
    
}