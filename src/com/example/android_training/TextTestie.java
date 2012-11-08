package com.example.android_training;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TextTestie extends Activity{
	TextView min = (TextView) findViewById(R.id.textView1);
	TextView max = (TextView) findViewById(R.id.textView2);
	TextView Text = (TextView)findViewById(R.id.textView3);
	int maximum = Integer.MIN_VALUE ;
	int minimum = Integer.MAX_VALUE ;
	String myText = "";
	@Override
	
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.main);
	         
	        TextView myTextView = new TextView(this);
	        setContentView(myTextView);
	         
	 
	        InputStream inputStream = getResources().openRawResource(R.raw.yaw);
	         
	        ByteArrayOutputStream byteArrayOutputStream 
	         = new ByteArrayOutputStream();
	         
	        String myText = "";
	        int in;
	  try {
	   in = inputStream.read();
	   while (in != -1)
	         {
	    byteArrayOutputStream.write(in);
	          in = inputStream.read();
	         }
	   inputStream.close();
	   myText = byteArrayOutputStream.toString();
	  }catch (IOException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	 
	        myTextView.setText(myText);
	    }
	}

