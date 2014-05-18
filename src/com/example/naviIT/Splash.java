package com.example.naviIT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;

public class Splash extends Activity {

	// for notification receiver
	private BroadcastReceiver refreshReceiver;

	ArrayList<Event> events = new ArrayList<Event>();
	private ArrayAdapter<Event> adapter;
	JSONArray jArray;
	JSONObject eventItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	

		setContentView(R.layout.splash);		

		// connectDB for retrieve events list
		connectDB connect = new connectDB();
		connect.execute();
		Log.d("Message", "connect db");
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter("com.example.naviIT.REFRESH");
		registerReceiver(refreshReceiver, filter);
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		//unregisterReceiver(refreshReceiver);	
		
	}

	public void addIntent() {
		Intent openStartActivity = new Intent(this, LoginActivity.class);
		startActivity(openStartActivity);
		finish();
	}

	class connectDB extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();

			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/event_notify.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/event_notify.php", "GET", list);
			
			try {
				if (jObject.getString("status").equals("success")) {
					jArray = jObject.getJSONArray("events");
					for (int i = 0; i < jArray.length(); i++) {
						eventItem = jArray.getJSONObject(i);
						events.add(new Event(eventItem.getString("eventId"),
								eventItem.getString("title"), eventItem
										.getString("venue"), eventItem
										.getString("date"), eventItem
										.getString("time"), eventItem
										.getString("description")));
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("Message", "status is fail");
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// alarm manager for notification one hour before event time

			int i;
				
			if(events.size()==0){
				//do nothing
			}
			else{	
			
				for(i=0; i<events.size();i++){
				
					scheduleNotification(i);
					
			}
		}
			Thread timer = new Thread() {
				public void run() {
					try {
						sleep(2500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						addIntent();
					}
				}
			};
			timer.start();

		}

		private void scheduleNotification(int i) {
			// TODO Auto-generated method stub
			Log.d("Message","Schedule notification for events "+ i);
			//AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
			Calendar cal = Calendar.getInstance();
			long now = cal.getTimeInMillis();
			Date date = null;
			Calendar eventtime = Calendar.getInstance();
			
			try {			
				String datetime = events.get(i).getDate() + " " + events.get(i).getTime();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				date = simpleDateFormat.parse(datetime);
				//Log.d("date", date.toString());
				eventtime.setTime(date);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//one hour before event time
			//eventtime.add(Calendar.HOUR, -1);

			Log.d("Message","Event time is "+eventtime.getTime().toString());
			
			long when = eventtime.getTimeInMillis();	
			
			
			Log.d("Message", "Now time : " + now + ", millis: " + when);
			if (when > now) {
				// upcoming event will be scheduled for notification
							
				
				Log.d("Message","Events that are upcoming: "+events.get(i).getTitle().toString());
				cal.set(eventtime.get(Calendar.YEAR), eventtime.get(Calendar.MONTH), eventtime.get(Calendar.DATE), eventtime.get(Calendar.HOUR_OF_DAY), eventtime.get(Calendar.MINUTE), eventtime.get(Calendar.SECOND));	
				when = cal.getTimeInMillis(); 
				Log.d("Message","Time to notify = "+cal.getTime().toString());	
				
				
				//Intent j = new Intent(getApplicationContext(), MainService.class);
				Intent j = new Intent(getApplicationContext(), MainService.class);
				Bundle c = new Bundle();
				c.putString("eventId", events.get(i).getEventId());
				c.putString("title", events.get(i).getTitle());
				c.putString("venue", events.get(i).getVenue());
				c.putString("date", events.get(i).getDate());
				c.putString("time", events.get(i).getTime());
				c.putString("description", events.get(i).getDescription());		
				j.putExtras(c);
				PendingIntent pint = PendingIntent.getService(getApplicationContext(), i, j, 0);
				// 1min after app start
				
				// mgr.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
				// SystemClock.elapsedRealtime() + PERIOD, pint);
				AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
				mgr.set(AlarmManager.RTC_WAKEUP, when, pint);
				
				// notification receiver
				refreshReceiver = new BroadcastReceiver() {
			
					@Override
					public void onReceive(Context context, Intent intent) {
						// TODO Auto-generated method stub
					}
				};
			}else{
				Log.d("Message","Past events: "+events.get(i).getTitle().toString());
				Log.d("Message","when<now, notification not sent");
			}
			
		}

	}

}
