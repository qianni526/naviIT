package com.example.naviIT;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

//Main service for notification
	public class MainService extends IntentService {
				
		private static final String TAG = "MainService";
				
		public MainService() {
		    super("My service");
		}


		
		@Override
		protected void onHandleIntent(Intent intent) {
		    //Do something, fire a notification or whatever you want to do here
			   //Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		       Log.d(TAG, "onStart");    
		       
		       Bundle c = intent.getExtras();	
		       Intent i = new Intent("com.example.naviIT.REFRESH");
				Log.d("Message","open refresh intent");
				//editIntent.putExtra("data", new DataWrapper(events));	
				i.putExtras(c);
				sendBroadcast(i);	

		
		}
	}
	


/*
	private static final String TAG = "MainService";

	@Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
 
	    @Override
	    public void onCreate() {
	 
	        Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG).show();
	        Log.d(TAG, "onCreate");
	    }
	 
	    @Override
	    public void onStart(Intent intent, int startId) {
	        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
	        Log.d(TAG, "onStart");    
	        
	        boolean notify=true;
			if(notify==true){
	        	Intent i = new Intent("com.example.login.REFRESH");				
	        	sendBroadcast(i);
	        }
	    }
	 
	    @Override
	    public void onDestroy() {
	        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();
	        Log.d(TAG, "onDestroy");
	      
	    }
}

 */	