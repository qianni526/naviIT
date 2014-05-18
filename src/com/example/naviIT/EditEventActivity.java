package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditEventActivity extends Activity{

	EditText title, venue, date, time, description;
	Button save, cancel;
	ImageButton discard;
	ProgressDialog pd = null;
	Bundle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editevent);
		
		b = getIntent().getExtras();
		
		title = (EditText) findViewById(R.id.etTitle);
		venue = (EditText) findViewById(R.id.etVenue);
		date = (EditText) findViewById(R.id.etDate);
		time = (EditText) findViewById(R.id.etTime);
		description = (EditText) findViewById(R.id.etDescription);
		
		//set editText with existing info
		title.setText(b.getString("title"));
		venue.setText(b.getString("venue"));
		date.setText(b.getString("date"));
		time.setText(b.getString("time"));
		description.setText(b.getString("description"));
		
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
			JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/deleteEvent.php", "GET", list);
					
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
			list.add(new BasicNameValuePair("time",time.getText().toString()));
			list.add(new BasicNameValuePair("description",description.getText().toString()));
			Log.d("Message", "The list passed to php is :"+list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/editEvent.php", "GET", list);
					
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
				Log.d("Message", "Fail to edit event");
			}
		}
		
		
	}


}
