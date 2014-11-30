package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchPlacesInCheckIn extends Activity {

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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_navigation);
		
		getActionBar().setTitle("Check In");
		
		rooms = new ArrayList<Room>();
        
        lv = (ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        clearText = (ImageButton) findViewById(R.id.clear_txt);
		
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
								
				//Direct to post check in
				Intent intent= new Intent(SearchPlacesInCheckIn.this, Checkin.class);
				intent.putExtra("roomName", selectedRoom.getName());
				intent.putExtra("selectedGoal", selectedRoom.getRegionId());
				//startActivity(intent);
				startActivityForResult(intent, 0);
			}
        	
        });   
        
        connectDB connect = new connectDB();
		connect.execute();
		
		
	}

	 public void onActivityResult(int requestCode, int resultCode, Intent data){
	   		super.onActivityResult(requestCode, resultCode, data);
	   		if(requestCode == 0){
	   			if(resultCode == Activity.RESULT_OK){
	   				setResult(RESULT_OK);
	   				finish();
	   			}
	   		}
	   	}
	
	class connectDB extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(SearchPlacesInCheckIn.this, "", "Loading...");	
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
			    
		        adapter = new MyCustomAdapter(SearchPlacesInCheckIn.this, rooms);
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
