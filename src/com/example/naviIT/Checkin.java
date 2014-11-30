package com.example.naviIT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

public class Checkin extends Activity{
	
	SessionManager session;
	EditText etContent;
	TextView tvRoom;
	Button btPost, btCancel;
	ProgressDialog pd;
	String username, selectedGoal, roomName;
	int userId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_checkin);
		
		Intent intent = getIntent();
		roomName = intent.getStringExtra("roomName");
		selectedGoal = intent.getStringExtra("selectedGoal");
		
		session = new SessionManager(getApplicationContext());
		
		if(session.isLoggedIn()){

    		// get user data from session
            HashMap<String, String> user = session.getUserDetails();
             
            // username
            username = user.get(SessionManager.KEY_NAME);
            userId = session.getUserId();
		} 
		
		etContent = (EditText) findViewById(R.id.etContent);
		/*
		etContent.setOnTouchListener(new OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {
                // TODO Auto-generated method stub
                if (view.getId() == R.id.etContent) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction()&MotionEvent.ACTION_MASK){
                    case MotionEvent.ACTION_UP:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                    }
                }
                return false;
            }

        });
        */
		
		etContent.setScroller(new Scroller(Checkin.this.getApplicationContext())); 
		etContent.setMinLines(5); 
		etContent.setMaxLines(5); 
		etContent.setVerticalScrollBarEnabled(true); 
		etContent.setMovementMethod(new ScrollingMovementMethod()); 
		
		
		tvRoom = (TextView) findViewById(R.id.tvRoom);
		
		tvRoom.setText(roomName);
		
		btPost = (Button) findViewById(R.id.btPost);
		btPost.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				connectDB connect = new connectDB();
				connect.execute();
				setResult(RESULT_OK);
				finish();
				Log.d("Message", "connect db");
			}
		});
		
		btCancel = (Button) findViewById(R.id.btCancel);
		btCancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// cancel
				finish();
			}
		});		
	}
		
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	
		if ((pd != null) && pd.isShowing()){
	        pd.dismiss();
		}
	    pd = null;
	}

	class connectDB extends AsyncTask<Void, Void, Void>
	{
		boolean addEventFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(Checkin.this, "", "Posting your check in...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("ID",Integer.toString(userId)));
			list.add(new BasicNameValuePair("username",username));
			list.add(new BasicNameValuePair("room",roomName));
			list.add(new BasicNameValuePair("content",etContent.getText().toString()));
			list.add(new BasicNameValuePair("regionId",selectedGoal));
			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/addEvent.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/addPost.php", "GET", list);
			
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
			
			 if ((Checkin.this.pd != null) && Checkin.this.pd.isShowing()) { 
				 Checkin.this.pd.dismiss();
			   }
			
			if(addEventFlag==true)
			{
				Toast.makeText(getApplicationContext(), "New check in is created.", Toast.LENGTH_SHORT).show();	
				setResult(RESULT_OK);
				finish();
			}	
			else{
				Toast.makeText(getApplicationContext(), "Fail to create new check in. Please try again.", Toast.LENGTH_SHORT).show();	
				Log.d("Message", "Fail to create new check in.");
			}
		}
		
	}
	
}


