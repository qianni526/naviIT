package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naviIT.FragmentNavigation.connectDB;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentFriendsMap_Friends extends Fragment {
	
	private ListView lv;
    
    MyFriendListAdapter adapter = null;
	
    // Search EditText
    EditText inputSearch;
    
    // Clear Text ImageButton
    ImageButton clearText;
    
    JSONArray jArray;
   	JSONObject userItem;
   	
   	ProgressDialog pd;
	
	ArrayList<User> users;
    
	  @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
		  
		  setHasOptionsMenu(true);
		  
	  	  View view = inflater.inflate(R.layout.fragfriendsmap_friends, container, false);
          
	  	  users = new ArrayList<User>();
        
	  	  lv = (ListView) view.findViewById(R.id.listView1);
	  	  inputSearch = (EditText) view.findViewById(R.id.inputSearch);
	  	  clearText = (ImageButton) view.findViewById(R.id.clear_txt);
	  	  
	  	  clearText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               inputSearch.setText("");
            }
	  	  });
	  	  
	  	 lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
					
					User selectedUser = null;
					selectedUser = adapter.data.get(position);

					Log.d("selectedUser", "Selected user: "+selectedUser+" at position: "+position);			
									
					//Direct to profile
					
				}
	        	
	        });   
	        
	        connectDB connect = new connectDB();
			connect.execute();
	  	  
          return view;
  	  }
  
	  @Override
	  public void onCreate(Bundle savedInstanceState)
	  {
	      super.onCreate(savedInstanceState);
	      
	      setHasOptionsMenu(true);
	  }
	
	  @Override
	  	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
	  		// TODO Auto-generated method stub
	      	 
	           menu.findItem(R.id.action_login).setVisible(false);
	           menu.findItem(R.id.action_refresh).setVisible(false);
	           menu.findItem(R.id.action_add_person).setVisible(true);
	           super.onCreateOptionsMenu(menu, inflater);
	   		 
	  	}
	  
	  @Override
		public boolean onOptionsItemSelected(MenuItem item) {
			// TODO Auto-generated method stub
	    	 
	    	 switch (item.getItemId()) {
	         case R.id.action_refresh:
	        	 
	        	 return false;
	         case R.id.action_add_person:
	        	 openAddConfirmFriendsPage();
	        	 return true;	 
	         default:
	             return super.onOptionsItemSelected(item);
	     }
		}
	  
	private void openAddConfirmFriendsPage() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), ControlFriendsActivity.class);
		startActivity(intent);
	}

	class connectDB extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pd = ProgressDialog.show(getActivity(), "", "Loading...");	
			}

			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				JSONParser jsonparser = new JSONParser();
				List<NameValuePair> list = new ArrayList<NameValuePair>();

				//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/event_notify.php", "GET", list);
				JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/user.php", "GET", list);
				Log.d("JSON respond", jObject.toString());
				try {
					if (jObject.getString("status").equals("success")) {
						
							jArray = jObject.getJSONArray("users");
							for (int j = 0; j < jArray.length(); j++) {
								userItem = jArray.getJSONObject(j);
								users.add(new User(userItem.getInt("ID"),
										userItem.getString("username"), 
										userItem.getString("firstname"), 
										userItem.getString("lastname"),
										userItem.getString("isAdmin")));
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
				
					if (pd != null) {
		                pd.dismiss();
					}				
					
					Log.d("Message","users: "+users);
					
					// Adding items to listview
				    
			        adapter = new MyFriendListAdapter(getActivity(), users);
			        lv.setAdapter(adapter);    
			        
			        inputSearch.addTextChangedListener(new TextWatcher() {
			            
			            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
			                // When user changed the Text
			            	adapter.getFilter().filter(cs);   
			            }
			             
			            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			                // TODO Auto-generated method stub                
			            }
			             
			            public void afterTextChanged(Editable arg0) {
			                // TODO Auto-generated method stub                          
			            }
			        });
					
			}	
		}
	  
	  @Override
	  public void onActivityCreated(Bundle savedInstanceState)
	  {
	      super.onActivityCreated(savedInstanceState);
	  }
	
	  @Override
	  public void onAttach(Activity activity)
	  {
	      super.onAttach(activity);
	  }
	
	  @Override
	  public void onStart()
	  {
	      super.onStart();
	  }
	
	  @Override
	  public void onResume()
	  {
	      super.onResume();
	  }
}