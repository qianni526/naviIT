package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEventActivity extends Activity{

	EditText title, venue, date, time, description;
	Button createNewEvent, cancel;
	ProgressDialog pd = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addevent);
		
		title = (EditText) findViewById(R.id.etTitle);
		venue = (EditText) findViewById(R.id.etVenue);
		date = (EditText) findViewById(R.id.etDate);
		time = (EditText) findViewById(R.id.etTime);
		description = (EditText) findViewById(R.id.etDescription);
		
		createNewEvent = (Button) findViewById(R.id.btCreateNewEvent);
		createNewEvent.setOnClickListener(new View.OnClickListener() {
			
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
	
	class connectDB extends AsyncTask<Void, Void, Void>
	{
		boolean addEventFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(AddEventActivity.this, "", "Creating new event...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("title",title.getText().toString()));
			list.add(new BasicNameValuePair("venue",venue.getText().toString()));
			list.add(new BasicNameValuePair("date",date.getText().toString()));
			list.add(new BasicNameValuePair("time",time.getText().toString()));
			list.add(new BasicNameValuePair("description",description.getText().toString()));
			JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/addEvent.php", "GET", list);
					
			try {
				Log.d("JSON", jObject.toString());
				if(jObject.getString("status").equals("success"))
				{
					addEventFlag=true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			if (AddEventActivity.this.pd != null) {
                AddEventActivity.this.pd.dismiss();
			}
			
			if(addEventFlag==true)
			{
				Toast.makeText(getApplicationContext(), "New event is created.", Toast.LENGTH_SHORT).show();	
				setResult(RESULT_OK);
				finish();
			}	
			else{
				Log.d("Message", "Fail to create new event");
			}
		}
		
		
	}


}


