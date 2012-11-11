package com.example.android_training;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class TicTacToe extends Activity {
    private Game game1;
	int x = 3 ;
	int y = 3 ;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        game1 = new Game(this);
        setContentView(game1);
    }
}
