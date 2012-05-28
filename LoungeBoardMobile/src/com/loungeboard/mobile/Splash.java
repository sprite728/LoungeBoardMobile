package com.loungeboard.mobile;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {
	MediaPlayer mySong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		mySong = MediaPlayer.create(Splash.this, R.raw.testsound); // Splash.this = the context of this class
		mySong.start();
		
		
		Thread timer = new Thread(){
			// A thread Look for a run method
			public void run(){
				try{
					sleep(5000);
					
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally{
					// Define an Intent
					Intent openLoungeBoardMobileActivity = new Intent("com.loungeboard.mobile.MENU");
					
					startActivity(openLoungeBoardMobileActivity);
					
				}
				
			}
			
		}; //Thread end
		timer.start();
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mySong.release();
		finish();
	}

	
}
