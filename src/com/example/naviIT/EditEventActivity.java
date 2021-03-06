package com.example.naviIT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naviIT.AddEventActivity.connectDBtonotify;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EditEventActivity extends Activity{

	// for notification receiver
	private BroadcastReceiver refreshReceiver;

	ArrayList<Event> events = new ArrayList<Event>();
	private ArrayAdapter<Event> adapter;
	JSONArray jArray;
	JSONObject eventItem;
	
	EditText title, venue, description;
	TextView date;
	TimePicker time;
	Button save, cancel;
	ImageButton chgDate, discard;
	ProgressDialog pd = null;
	Bundle b;
	private int mYear, mMonth, mDay;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editevent);
		
		b = getIntent().getExtras();
		
		title = (EditText) findViewById(R.id.etTitle);
		venue = (EditText) findViewById(R.id.etVenue);
		date = (TextView) findViewById(R.id.textDate);
		time = (TimePicker) findViewById(R.id.timePicker1);
		description = (EditText) findViewById(R.id.etDescription);
		
		//set editText with existing info
		title.setText(b.getString("title"));
		venue.setText(b.getString("venue"));
		date.setText(b.getString("date"));
		time.setCurrentHour(Integer.parseInt(b.getString("hour")));
		time.setCurrentMinute(Integer.parseInt(b.getString("minute")));
;		description.setText(b.getString("description"));
		
		chgDate = (ImageButton) findViewById(R.id.btChgDate);
		chgDate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String input = b.getString("date");
				String[] out = input.split("-");			
				
				mYear = Integer.parseInt(out[0]);
				mMonth = Integer.parseInt(out[1])-1;
				mDay = Integer.parseInt(out[2]);
				 
				//launch datepicker dialog
				DatePickerDialog dpd = new DatePickerDialog(EditEventActivity.this, new DatePickerDialog.OnDateSetListener() {
				 
				            @Override
				            public void onDateSet(DatePicker view, int year,
				                    int monthOfYear, int dayOfMonth) {
				            	date.setText(year + "-"
				                        + (monthOfYear + 1) + "-" + dayOfMonth);
				 
				            }

				        }, mYear, mMonth, mDay);
				dpd.show();
			}
		});

		discard = (ImageButton) findViewById(R.id.btDiscard);
		discard.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				connectDatabase connect_discard = new connectDatabase();
				connect_discard.execute();
				Log.d("Message", "connect db");
			}
		});
		
		save = (Button) findViewById(R.id.btSaveEdit);
		save.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				connectDB connect = new connectDB();
				connect.execute();
				Log.d("Message", "connect db");
			}
		});
		
		cancel = (Button) findViewById(R.id.btCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// cancel
				finish();
			}
		});
			
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
	
	class connectDatabase extends AsyncTask<Void, Void, Void>
	{
		boolean deleteEventFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(EditEventActivity.this, "", "Deleting...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("eventId", b.getString("eventId")));
			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/deleteEvent.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/deleteEvent.php", "GET", list);
			
			try {
				Log.d("JSON", jObject.toString());
				if(jObject.getString("status").equals("success"))
				{
					deleteEventFlag=true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			if (EditEventActivity.this.pd != null) {
                EditEventActivity.this.pd.dismiss();
			}
			
			if(deleteEventFlag==true)
			{
				Toast.makeText(getApplicationContext(), "Event deleted.", Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}	
			else{
				Log.d("Message", "Fail to delete event");
			}
			
			connectDBtonotify connect = new connectDBtonotify();
			connect.execute();
			
		}	
	}
	
	class connectDB extends AsyncTask<Void, Void, Void>
	{
		boolean editEventFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(EditEventActivity.this, "", "Updating...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("eventId", b.getString("eventId")));
			list.add(new BasicNameValuePair("title",title.getText().toString()));
			list.add(new BasicNameValuePair("venue",venue.getText().toString()));
			list.add(new BasicNameValuePair("date",date.getText().toString()));
			list.add(new BasicNameValuePair("hour",time.getCurrentHour().toString()));
			list.add(new BasicNameValuePair("minute",time.getCurrentMinute().toString()));
			list.add(new BasicNameValuePair("description",description.getText().toString()));
			Log.d("Message", "The list passed to php is :"+list);
			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/editEvent.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/editEvent.php", "GET", list);
			
			try {
				Log.d("JSON", jObject.toString());
				if(jObject.getString("status").equals("success"))
				{
					editEventFlag=true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			if (EditEventActivity.this.pd != null) {
                EditEventActivity.this.pd.dismiss();
			}			
			if(editEventFlag==true)
			{
				Toast.makeText(getApplicationContext(), "Changes saved.", Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}	
			else{
				Toast.makeText(getApplicationContext(), "Fail to edit event. Please try again.", Toast.LENGTH_SHORT).show();
				Log.d("Message", "Fail to edit event");
			}
			
			connectDBtonotify connect = new connectDBtonotify();
			connect.execute();
		}	
	}

	class connectDBtonotify extends AsyncTask<Void, Void, Void> {

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
										.getString("hour"), eventItem
										.getString("minute"), eventItem
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
				String datetime = events.get(i).getDate() + " " + events.get(i).getHour()+":"+events.get(i).getMinute();
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
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
				c.putString("hour", events.get(i).getHour());
				c.putString("minute", events.get(i).getMinute());
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
	



