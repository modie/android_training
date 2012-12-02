package com.example.android_training;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
 
public class WumpusMonster extends Cell {
 
    public WumpusMonster(int x, int y) {
        super(x, y);
    }
 
    public void draw(Canvas g, Resources res, int x, int y, int w, int h) {
    Bitmap im = BitmapFactory.decodeResource(res, R.drawable.roombase);
    g.drawBitmap(im, null, new Rect(x*w, y*h, (x*w)+w, (y*h)+h), new Paint());
    Bitmap mon = BitmapFactory.decodeResource(res, R.drawable.wumpus);
    Paint paint = new Paint();
    g.drawBitmap(mon, null, new Rect(x*w, y*h, (x*w)+w, (y*h)+h), paint);
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
		return 0;
	}
}