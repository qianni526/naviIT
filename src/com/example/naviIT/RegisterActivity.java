package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naviIT.LoginActivity.connectDB;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {

	EditText username, firstname, lastname, email, password;
	Button signUp, cancel;
	ProgressDialog pd = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		Log.d("Message", "Go into sign up page");
		username = (EditText) findViewById(R.id.etUsername);
		firstname = (EditText) findViewById(R.id.etFirstname);
		lastname = (EditText) findViewById(R.id.etLastname);
		email = (EditText) findViewById(R.id.etEmail);
		
		password = (EditText) findViewById(R.id.etPassword);
		signUp = (Button) findViewById(R.id.btSignup);
		signUp.setOnClickListener(new View.OnClickListener() {
			


			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(username.getText().toString().equals("") || firstname.getText().toString().equals("")  || lastname.getText().toString().equals("")  || email.getText().toString().equals("")  ||password.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Please complete the sign up form.", Toast.LENGTH_SHORT).show();
				}else{
					connectDB connect = new connectDB();
					connect.execute();					
				}
			}

		});
		cancel = (Button) findViewById(R.id.btCancel);
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	public void newIntent(){
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
	
	
	class connectDB extends AsyncTask<Void, Void, Void>
	{
		boolean signUpFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(RegisterActivity.this, "", "Creating account...");
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("username",username.getText().toString()));
			list.add(new BasicNameValuePair("firstname",firstname.getText().toString()));
			list.add(new BasicNameValuePair("lastname",lastname.getText().toString()));
			list.add(new BasicNameValuePair("email",email.getText().toString()));
			list.add(new BasicNameValuePair("password",password.getText().toString()));
			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/register.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/register.php", "GET", list);
			
			try {
				Log.d("JSON", jObject.toString());
				if(jObject.getString("status").equals("success"))
				{
					signUpFlag = true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			
			if (RegisterActivity.this.pd != null) {
                RegisterActivity.this.pd.dismiss();
			}
			
			if(signUpFlag==true)
			{
				
				Toast.makeText(getApplicationContext(), "New account created.", Toast.LENGTH_SHORT).show();
				newIntent();
			}	
			else{
				Log.d("Message", "Fail to sign up");
			}
		}
		
		
	}

}

