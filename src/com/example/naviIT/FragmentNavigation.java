package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
 
public class FragmentNavigation extends Fragment{
    
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
	
	// List view
	//List view shld be shown only when user typing
    private ListView lv;
     
    // Listview Adapter
    MyCustomAdapter adapter = null;
     
    // Search EditText
    EditText inputSearch;
    
    // Clear Text ImageButton
    ImageButton clearText;
    
    JSONArray jArray;
	JSONObject roomItem;	
	
	ProgressDialog pd;
	
	ArrayList<Room> rooms;
	
	boolean hide = false;
	SessionManager session;
	
    public FragmentNavigation(){}
      
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
  
    	getActivity().getActionBar().setTitle("Online Navigation");
    	
        View rootView = inflater.inflate(R.layout.fragment_navigation, container, false);
        
        rooms = new ArrayList<Room>();
        
        lv = (ListView) rootView.findViewById(R.id.list_view);
        inputSearch = (EditText) rootView.findViewById(R.id.inputSearch);
        clearText = (ImageButton) rootView.findViewById(R.id.clear_txt);
        
        /*
        //only show list when focus on edittext search
        inputSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    lv.setVisibility(View.VISIBLE);
                }else 
                {
                	lv.setVisibility(View.INVISIBLE);
                }
            }
        });
        */
        
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
				
				Room selectedRoom = null;
				selectedRoom = adapter.data.get(position);

				Log.d("selectedRoom", "Selected room: "+selectedRoom+" at position: "+position);			
								
				//Direct to navigation map
				Intent intent= new Intent(getActivity(), Pedometer.class);
				intent.putExtra("selectedGoal", selectedRoom.getRegionId());
				startActivity(intent);
			}
        	
        });   
        
        connectDB connect = new connectDB();
		connect.execute();
        
        return rootView;
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
    
	 public void openLoginPage(){
		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentLogin(), "Login").commit();
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
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/room.php", "GET", list);
			Log.d("JSON respond", jObject.toString());
			try {
				if (jObject.getString("status").equals("success")) {
					
						jArray = jObject.getJSONArray("rooms");
						for (int j = 0; j < jArray.length(); j++) {
							roomItem = jArray.getJSONObject(j);
							rooms.add(new Room(roomItem.getString("roomId"),
									roomItem.getString("name"), 
									roomItem.getString("block"), 
									roomItem.getString("floor"),
									(roomItem.getInt("isLecturerRoom")==0),
									roomItem.getString("regionId")));
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
				
				Log.d("Message","rooms: "+rooms);
				
				// Adding items to listview
			    
		        adapter = new MyCustomAdapter(getActivity(), rooms);
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
    
}