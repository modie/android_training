package com.example.android_training;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Point;
 
public abstract class WumpusNull extends Point {
 
    public WumpusNull(int x, int y) {
        super(x, y);
    }
 
    abstract public void draw(Canvas g,Resources res, int x, int y, int w, int h);
}