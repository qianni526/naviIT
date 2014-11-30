package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class EditCheckInActivity extends Activity{

	ArrayList<Post> posts = new ArrayList<Post>();
	MyPostCustomAdapter adapter;
	JSONArray jArray;
	JSONObject postItem;
	
	EditText etContent;
	Button btRoom;
	Button save, cancel;
	ImageButton discard;
	ProgressDialog pd = null;
	Bundle b, bundle;
	String postId, regionId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editcheckin);
		
		b = getIntent().getExtras();
		
		etContent = (EditText) findViewById(R.id.etContent);
		btRoom = (Button) findViewById(R.id.btRoom);
		save = (Button) findViewById(R.id.btSave);
		cancel = (Button) findViewById(R.id.btCancel);
		
		Log.d("etContent!=null", "etContent: "+ b.getString("etContent"));
		
		//set editText with existing info
			postId = b.getString("postId");
			etContent.setText(b.getString("etContent"));
			btRoom.setText(b.getString("btRoom"));
			regionId = b.getString("regionId");
		
		btRoom.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent= new Intent(EditCheckInActivity.this, EditPlacesInCheckIn.class);
				startActivityForResult(intent, 0);
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
		
		save = (Button) findViewById(R.id.btSave);
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
	}
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
   		super.onActivityResult(requestCode, resultCode, data);
   		if(requestCode == 0){
   			if(resultCode == Activity.RESULT_OK){
   				bundle = data.getExtras();
   				btRoom.setText(bundle.getString("roomName")); 
   				regionId = bundle.getString("selectedGoal");
   			}
   		}
   	}
	
	class connectDatabase extends AsyncTask<Void, Void, Void>
	{
		boolean deleteEventFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(EditCheckInActivity.this, "", "Deleting...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("postId", postId));
			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/deleteEvent.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/deletePost.php", "GET", list);
			
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
			
			if (EditCheckInActivity.this.pd != null) {
                EditCheckInActivity.this.pd.dismiss();
			}
			
			if(deleteEventFlag==true)
			{
				Toast.makeText(getApplicationContext(), "Post deleted.", Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}	
			else{
				Log.d("Message", "Fail to delete post");
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
			pd = ProgressDialog.show(EditCheckInActivity.this, "", "Updating...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("postId", postId));
			list.add(new BasicNameValuePair("content",etContent.getText().toString()));
			list.add(new BasicNameValuePair("room",btRoom.getText().toString()));
			list.add(new BasicNameValuePair("regionId",regionId));
			Log.d("Message", "The list passed to php is :"+list);
			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/editEvent.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/editPost.php", "GET", list);
			
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
			
			if (EditCheckInActivity.this.pd != null) {
                EditCheckInActivity.this.pd.dismiss();
			}			
			if(editEventFlag==true)
			{
				Toast.makeText(getApplicationContext(), "Changes saved.", Toast.LENGTH_SHORT).show();
				setResult(RESULT_OK);
				finish();
			}	
			else{
				Toast.makeText(getApplicationContext(), "Fail to edit post. Please try again.", Toast.LENGTH_SHORT).show();
				Log.d("Message", "Fail to edit post");
			}
			
			
		}	
	}

	

	
}
	



