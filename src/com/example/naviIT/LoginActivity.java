package com.example.naviIT;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private static final long PERIOD = 1*60*1000;
	EditText username, password;
	Button login, signUp;
	ProgressDialog pd = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		 
		
		//Intent i = new Intent(this, MainService.class);
		//startService(i);

		username = (EditText) findViewById(R.id.etUsername);
		password = (EditText) findViewById(R.id.etPassword);
		signUp = (Button) findViewById(R.id.btSignUp);
		signUp.setPaintFlags(signUp.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
		signUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newRegisterIntent();
			}
		});
		login = (Button) findViewById(R.id.bLogin);
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(username.getText().toString().equals("") || password.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Please enter username and password.", Toast.LENGTH_SHORT).show();
				}else{
					connectDB connect = new connectDB();
					connect.execute();
					
				}
			}
		});
		
	}
	
	public void newRegisterIntent(){
		Intent i = new Intent(this, RegisterActivity.class);
		startActivity(i);

	}
	
	public void newLoginIntent(){
		Intent i = new Intent(this, LoginActivity.class);
		startActivity(i);

	}
	
	public void newIntent(){
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
	
	
	class connectDB extends AsyncTask<Void, Void, Void>
	{
		boolean loginFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(LoginActivity.this, "", "Logging in...");
			
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("username",username.getText().toString()));
			list.add(new BasicNameValuePair("password",password.getText().toString()));
			JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/login.php", "GET", list);
					
			try {
				Log.d("JSON", jObject.toString());
				if(jObject.getString("status").equals("success"))
				{
					loginFlag = true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			if (LoginActivity.this.pd != null) {
                LoginActivity.this.pd.dismiss();
			}
			
			if(loginFlag==true)
			{
				newIntent();
			}	
			else{	
				Toast.makeText(getApplicationContext(), "Incorrect username or password. \nPlease try again.", Toast.LENGTH_SHORT).show();
				username.setText("");
				password.setText("");
				Log.d("Message", "Fail to login");
			}
		}
		
		
	}

}
