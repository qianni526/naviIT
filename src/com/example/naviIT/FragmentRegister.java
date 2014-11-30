package com.example.naviIT;
 
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 
public class FragmentRegister extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      EditText username, firstname, lastname, email, password;
  	  Button signUp, cancel;
  	  ProgressDialog pd = null;
  	  boolean hide = false;
  	  SessionManager session;
      
      public FragmentRegister()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
    	  
    	   getActivity().getActionBar().setTitle("naviIT");	
    	  
            View view=inflater.inflate(R.layout.activity_register,container, false);
 
            Log.d("Message", "Go into sign up page");
    		username = (EditText) view.findViewById(R.id.etUsername);
    		firstname = (EditText) view.findViewById(R.id.etFirstname);
    		lastname = (EditText) view.findViewById(R.id.etLastname);
    		email = (EditText) view.findViewById(R.id.etEmail);
    		
    		password = (EditText) view.findViewById(R.id.etPassword);
    		signUp = (Button) view.findViewById(R.id.btSignup);
    		signUp.setOnClickListener(new View.OnClickListener() {


    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				
    				if(username.getText().toString().equals("") || firstname.getText().toString().equals("")  || lastname.getText().toString().equals("")  || email.getText().toString().equals("")  ||password.getText().toString().equals("")){
    					Toast.makeText(getActivity().getApplicationContext(), "Please complete the sign up form.", Toast.LENGTH_SHORT).show();
    				}else{
    					connectDB connect = new connectDB();
    					connect.execute();					
    				}
    			}

    		});
    		cancel = (Button) view.findViewById(R.id.btCancel);
    		cancel.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				getActivity().getSupportFragmentManager().popBackStack();
    			}
    		});
            
           
            return view;
      }

	public void openLoginPage(){
  		
    	  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentLogin(), "Login").commit();
  
  		}
  	
	@Override
	  public void onCreate(Bundle savedInstanceState)
	  {
	      super.onCreate(savedInstanceState);
	      
	      session = new SessionManager(getActivity().getApplicationContext());
	      
	      if(session.isLoggedIn()==true){
	    	  hide=true;
	    	  setHasOptionsMenu(true);
	      }else{
	    	  hide=false;
	    	  setHasOptionsMenu(true);
	      }
	      
	  }
  
  @Override
	public boolean onOptionsItemSelected(MenuItem item) {
  	// Handle presses on the action bar items
		
		switch (item.getItemId()) {
          case R.id.action_login:
          	//newLoginIntent();
          	openLoginPage();
          	return true;
          default:
              return super.onOptionsItemSelected(item);
      }
	}

  @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
  	 
	  if (hide == true)
      {
          for (int i = 0; i < menu.size(); i++)
              menu.findItem(R.id.action_login).setVisible(false);
      }else{
    	  menu.findItem(R.id.action_login).setVisible(true);
      }
	  
       menu.findItem(R.id.action_refresh).setVisible(false);
       menu.findItem(R.id.action_add_person).setVisible(false);
       
       super.onCreateOptionsMenu(menu, inflater);
		 
	} 
  
  	class connectDB extends AsyncTask<Void, Void, Void>
  	{
  		boolean signUpFlag = false;
  		
  		@Override
  		protected void onPreExecute() {
  			// TODO Auto-generated method stub
  			super.onPreExecute();
  			pd = ProgressDialog.show(getActivity(), "", "Creating account...");
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
  			list.add(new BasicNameValuePair("isAdmin","User"));
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
  			
  			if (pd != null) {
                  pd.dismiss();
  			}
  			
  			if(signUpFlag==true)
  			{
  				
  				Toast.makeText(getActivity().getApplicationContext(), "New account created.", Toast.LENGTH_SHORT).show();
  				openLoginPage();
  			}	
  			else{				
  				Log.d("Message", "Fail to sign up");
  				Toast.makeText(getActivity().getApplicationContext(), "Fail to sign up. Please try again", Toast.LENGTH_SHORT).show();
  			}
  		}
  		
  		
  	}
      
}