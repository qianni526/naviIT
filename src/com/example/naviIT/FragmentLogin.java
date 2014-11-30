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
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.naviIT.R;
 
public class FragmentLogin extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      private static final long PERIOD = 1*60*1000;
  	  EditText username, password;
  	  Button login, signUp;
  	  ProgressDialog pd = null;
  	  JSONArray jArray;
	  JSONObject userItem;
	  ArrayList<User> user = new ArrayList<User>(); 
  	
  	  // Session Manager Class
      SessionManager session;
      
      public FragmentLogin()
      {
 
      }
 
      @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		getActivity().getActionBar().setTitle("naviIT");		
		setHasOptionsMenu(true);	
	}
      
      @Override
  	public boolean onOptionsItemSelected(MenuItem item) {
      	// Handle presses on the action bar items
  		
  		switch (item.getItemId()) {
              case R.id.action_login:
              	//openLoginPage();
              	return false;
              
              default:
                  return super.onOptionsItemSelected(item);
          }

      }
      

	@Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
    	  	
    	  
            View view=inflater.inflate(R.layout.activity_login,container, false);
 
         // Session Manager
            session = new SessionManager(getActivity().getApplicationContext());                
    		
    		username = (EditText) view.findViewById(R.id.etUsername);
    		password = (EditText) view.findViewById(R.id.etPassword);
    		signUp = (Button) view.findViewById(R.id.btSignUp);
    		signUp.setPaintFlags(signUp.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
    		signUp.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				openRegisterPage();
    			}
    		});
    		login = (Button) view.findViewById(R.id.bLogin);
    		login.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				if(username.getText().toString().equals("") || password.getText().toString().equals("")){
    					Toast.makeText(getActivity().getApplicationContext(), "Please enter username and password.", Toast.LENGTH_SHORT).show();
    				}else{
    					connectDB connect = new connectDB();
    					connect.execute();
    					//openMainActivity();
    				}
    			}
    		});
            
            return view;
      }

    @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
    	 
         menu.findItem(R.id.action_login).setVisible(false);
         menu.findItem(R.id.action_refresh).setVisible(false);
         menu.findItem(R.id.action_add_person).setVisible(false);
         
         super.onCreateOptionsMenu(menu, inflater);
 		 
	} 
    
	 public void openLoginPage(){
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentLogin(), "Login").commit();
	}  
      
	public void newRegisterIntent(){
  		Intent i = new Intent(getActivity(), RegisterActivity.class);
  		startActivity(i);

  	}
      
      public void openRegisterPage(){
    	  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentRegister(), "Register").addToBackStack(null).commit();
  	} 
  	
  	public void openMainActivity(){
  		Intent i = new Intent(getActivity(), MainActivity.class);
  		startActivity(i);
  		getActivity().finish();
  	}
  	
  	
  	class connectDB extends AsyncTask<Void, Void, Void>
  	{
  		boolean loginFlag = false;
  		
  		@Override
  		protected void onPreExecute() {
  			// TODO Auto-generated method stub
  			super.onPreExecute();
  			pd = ProgressDialog.show(getActivity(), "", "Logging in...");		
  		}
  		
  		@Override
  		protected Void doInBackground(Void... params) {
  			// TODO Auto-generated method stub
  			JSONParser jsonparser = new JSONParser();
  			List<NameValuePair> list = new ArrayList<NameValuePair>();
  			list.add(new BasicNameValuePair("username",username.getText().toString()));
  			list.add(new BasicNameValuePair("password",password.getText().toString()));
  			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/login.php", "GET", list);
  			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/login.php", "GET", list);	
  			
  			try {
  				Log.d("JSON", jObject.toString());
  				if(jObject.getString("status").equals("success"))
  				{
  					loginFlag = true;
  					jArray = jObject.getJSONArray("userItems");
					for(int i=0; i<jArray.length(); i++){
						userItem = jArray.getJSONObject(i);
						if(userItem.getString("isAdmin").equals("Admin")){
							user.add(new User(userItem.getInt("ID"), userItem.getString("username"), userItem.getString("firstname"), userItem.getString("lastname"),userItem.getString("isAdmin")));						
							
						}else{
							user.add(new User(userItem.getInt("ID"), userItem.getString("username"), userItem.getString("firstname"), userItem.getString("lastname"),"User"));						
							
						}
					}
  				}
  			} catch (JSONException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  			
  			return null;
  		}

  		@Override
  		protected void onPostExecute(Void result) {
  			
  			if (pd != null) {
                  pd.dismiss();
  			}
  			
  			if(loginFlag==true)
  			{
  				session.createLoginSession(user.get(0).getUserName(), user.get(0).getIsAdmin(), user.get(0).getId());
  				Log.d("create login session", "user.get(0).getId()" +user.get(0).getId());
  				openMainActivity();
  			}	
  			else{	
  				Toast.makeText(getActivity().getApplicationContext(), "Incorrect username or password. \nPlease try again.", Toast.LENGTH_SHORT).show();
  				username.setText("");
  				password.setText("");
  				Log.d("Message", "Fail to login");
  			}
  		}
  		
  		
  	}
 
}