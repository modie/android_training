package com.example.android_training;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ThreadTimeTestie extends Activity {
//
private ProgressBar progress;
Button buttonStartProgress;
Button buttonCancel;
class ProgressTask extends AsyncTask<Integer, Integer, Void>{

	@Override
	protected void onPreExecute() {
		// initialize the progress bar
		// set maximum progress to 100.
		progress.setMax(100);

	}

	@Override
	protected void onCancelled() {
		// stop the progress
		progress.setMax(0);

	}

	@Override
	protected Void doInBackground(Integer[] params) {
		// get the initial starting value
		int start=params[0];
		// increment the progress
		for(int i=start;i<=100;i+=5){
			try {
				boolean cancelled=isCancelled();
				//if async task is not cancelled, update the progress
				if(!cancelled){
					publishProgress(i);
					SystemClock.sleep(1000);

				}

			} catch (Exception e) {
				Log.e("Error", e.toString());
			}

		}
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer[] values) {
		// increment progress bar by progress value
		progress.setProgress(values[0]);

	}

	@Override
	protected void onPostExecute(Void result) {
		// async task finished
		Log.v("Progress", "Finished");
	}

}


@Override
public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.thread1);
	//call id function from main.xml
	buttonStartProgress = (Button)findViewById(R.id.btn);
	buttonCancel = (Button)findViewById(R.id.btnCancel);
	progress = (ProgressBar)findViewById(R.id.progress);
	
	//create click method function for start progress
	buttonStartProgress.setOnClickListener(new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
			ProgressTask task=new ProgressTask();
			switch(v.getId()){
				case R.id.btn:
					//execute progress starting from 10 to 100
					task.execute(10);
					//when the progressbarr still in progress. the button cant click
					buttonStartProgress.setClickable(false);
					break;
			}
		}
	});
	
	//create click method function for cancel progress
	buttonCancel.setOnClickListener(new Button.OnClickListener(){
		@Override
		public void onClick(View v) {
		ProgressTask task=new ProgressTask();
		switch(v.getId()){
			case R.id.btnCancel:
			task.cancel(true);
			break;
		}
		
		}
	});
	
	}
	}
