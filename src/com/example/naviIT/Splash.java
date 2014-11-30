package com.example.naviIT;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.splash);		
        
		Thread background = new Thread() {
			public void run() {
				try {
					sleep(3*1000);
					
					Intent openStartActivity = new Intent(getBaseContext(), MainActivity.class);
					startActivity(openStartActivity);
					
					finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
		};
		
		// start thread
        background.start();
	}
	
	
	
	 @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
