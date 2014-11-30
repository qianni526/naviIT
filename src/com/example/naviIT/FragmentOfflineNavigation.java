package com.example.naviIT;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.naviIT.R;
import com.polites.android.GestureImageView;
 
public class FragmentOfflineNavigation extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      private static final int REQUEST_BARCODE = 0;
      private TextView scanResult;
  	  private String resultQR;
  	  Boolean usingQR=false;
	  GestureImageView aView;
	  ProgressDialog pd;
	  JSONArray jArray;
	  JSONObject regionItem, roomItem;
	  HashMap<String, Region> regions2 = new HashMap<String, Region>();
	  ArrayList<Region> regions = new ArrayList<Region>();
	  ArrayList<Room> rooms = new ArrayList<Room>();
	  ArrayList<Room> roomsA1 = new ArrayList<Room>();
	  ArrayList<Room> roomsA2 = new ArrayList<Room>();
	  ArrayList<Room> roomsA3 = new ArrayList<Room>();
	  ArrayList<Room> roomsB1 = new ArrayList<Room>();
	  ArrayList<Room> roomsB2 = new ArrayList<Room>();
	  ArrayList<Room> roomsB3 = new ArrayList<Room>();
	  Spinner spinnerBlock, spinnerLevel, spinnerRoom, destspinnerBlock, destspinnerLevel, destspinnerRoom;
	  ArrayAdapter<CharSequence> adapter, adapter2, adapter4, adapter5, adapter7, adapter8;
	  ArrayAdapter<String> adapter3, adapter6;
	  Button btScanQRcode, btNavigate;
	  String selectedStart, selectedGoal;
	  LinearLayout layout;
	  boolean hide = false;
	  SessionManager session;
	  
      public FragmentOfflineNavigation()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
    	  getActivity().getActionBar().setTitle("Offline Navigation");
    	  
            View view=inflater.inflate(R.layout.activity_findroute,container, false);
 
            aView = new GestureImageView(getActivity());
    		
            layout = (LinearLayout) view.findViewById(R.id.layout);
            
    		connectDB connect = new connectDB();
    		connect.execute();
            
    		spinnerBlock = (Spinner) view.findViewById(R.id.spinnerBlock);
			spinnerLevel = (Spinner) view.findViewById(R.id.spinnerLevel);
			spinnerRoom = (Spinner) view.findViewById(R.id.spinnerRoom);
			destspinnerBlock = (Spinner) view.findViewById(R.id.destspinnerBlock);
			destspinnerLevel = (Spinner) view.findViewById(R.id.destspinnerLevel);
			destspinnerRoom = (Spinner) view.findViewById(R.id.destspinnerRoom);
			btScanQRcode = (Button) view.findViewById(R.id.btScanQRcode);	
			btNavigate = (Button) view.findViewById(R.id.btNavigate);

            
            return view;
      }
      
      @Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == REQUEST_BARCODE) {
			//Toast.makeText(getActivity(), "Result back", Toast.LENGTH_SHORT).show();
			if(resultCode == Activity.RESULT_OK) {
				resultQR = data.getStringExtra("SCAN_RESULT");
				setTextViewResult(resultQR);
			} else {
				scanResult.setText("No result");				
			}
		}		
	}          
	
  //set the spinner with current location based on QR code result
  //result is set in roomId or name
		public void setTextViewResult(String result) {
			usingQR = true;
			Log.d("Message", "QR code result = "+result);
			
			for(int count=0; count<rooms.size(); count++){
				
				if(rooms.get(count).getRoomId().equals(result)){
					if(rooms.get(count).getBlock().equals("A") && rooms.get(count).getFloor().equals("1")){
						spinnerBlock.setSelection(0);//Block A
		    			spinnerLevel.setSelection(1);//Ground Floor		    			
		    			populateSpinner(roomsA1, adapter3, spinnerRoom);
		    			
		    			for(int counter=0; counter<roomsA1.size(); counter++){
							//if(roomsA1.get(counter).getRoomId().equals(result)){
							if(roomsA1.get(counter).getName().equals(result)){
								spinnerRoom.setSelection(counter);
							}
						}
					}else if(rooms.get(count).getBlock().equals("A") && rooms.get(count).getFloor().equals("2")){
						spinnerBlock.setSelection(0);//Block A
		    			spinnerLevel.setSelection(2);//First Floor		    			
		    			populateSpinner(roomsA2, adapter3, spinnerRoom);
		    			
		    			for(int counter=0; counter<roomsA2.size(); counter++){
							//if(roomsA2.get(counter).getRoomId().equals(result)){
							if(roomsA2.get(counter).getName().equals(result)){
								spinnerRoom.setSelection(counter);
							}
						}
					}else if(rooms.get(count).getBlock().equals("A") && rooms.get(count).getFloor().equals("3")){
						spinnerBlock.setSelection(0);//Block A
		    			spinnerLevel.setSelection(3);//Second Floor		    			
		    			populateSpinner(roomsA3, adapter3, spinnerRoom);
		    			
		    			for(int counter=0; counter<roomsA3.size(); counter++){
							//if(roomsA3.get(counter).getRoomId().equals(result)){
							if(roomsA3.get(counter).getName().equals(result)){
								spinnerRoom.setSelection(counter);
							}
						}
					}else if(rooms.get(count).getBlock().equals("B") && rooms.get(count).getFloor().equals("1")){
						spinnerBlock.setSelection(1);//Block B
		    			spinnerLevel.setSelection(1);//Ground Floor		    			
		    			populateSpinner(roomsB1, adapter3, spinnerRoom);
		    			
		    			for(int counter=0; counter<roomsB1.size(); counter++){
							//if(roomsA3.get(counter).getRoomId().equals(result)){
							if(roomsB1.get(counter).getName().equals(result)){
								spinnerRoom.setSelection(counter);
							}
						}
					}else if(rooms.get(count).getBlock().equals("B") && rooms.get(count).getFloor().equals("2")){
						spinnerBlock.setSelection(1);//Block B
		    			spinnerLevel.setSelection(2);//First Floor		    			
		    			populateSpinner(roomsB2, adapter3, spinnerRoom);
		    			
		    			for(int counter=0; counter<roomsB2.size(); counter++){
							//if(roomsA3.get(counter).getRoomId().equals(result)){
							if(roomsB2.get(counter).getName().equals(result)){
								spinnerRoom.setSelection(counter);
							}
						}
					}else if(rooms.get(count).getBlock().equals("B") && rooms.get(count).getFloor().equals("3")){
						spinnerBlock.setSelection(1);//Block B
		    			spinnerLevel.setSelection(3);//Ground Floor		    			
		    			populateSpinner(roomsB3, adapter3, spinnerRoom);
		    			
		    			for(int counter=0; counter<roomsB3.size(); counter++){
							//if(roomsA3.get(counter).getRoomId().equals(result)){
							if(roomsB3.get(counter).getName().equals(result)){
								spinnerRoom.setSelection(counter);
							}
						}
					}
					
				break;
				}
				
			}	   
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
  			//pd = ProgressDialog.show(MainActivity.this, "", "Routing...");	
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
  			
  			boolean finishLoop = false;
  			
  			for(int count=0; count<rooms.size(); count++){
  				
  				if(rooms.get(count).getBlock().equals("A") && rooms.get(count).getFloor().equals("1")){
  					roomsA1.add(rooms.get(count));
  				}else if(rooms.get(count).getBlock().equals("A") && rooms.get(count).getFloor().equals("2")){
  					roomsA2.add(rooms.get(count));
  				}else if(rooms.get(count).getBlock().equals("A") && rooms.get(count).getFloor().equals("3")){
  					roomsA3.add(rooms.get(count));
  				}else if(rooms.get(count).getBlock().equals("B") && rooms.get(count).getFloor().equals("1")){
  					roomsB1.add(rooms.get(count));
  				}else if(rooms.get(count).getBlock().equals("B") && rooms.get(count).getFloor().equals("2")){
  					roomsB2.add(rooms.get(count));
  				}else if(rooms.get(count).getBlock().equals("B") && rooms.get(count).getFloor().equals("3")){
  					roomsB3.add(rooms.get(count));
  				}

  				if(count==rooms.size()-1){
  					finishLoop=true;
  				}
  			}			
  			
  			if(finishLoop==true){
  				
  				if (pd != null) {
  	                pd.dismiss();
  	                layout.setVisibility(View.VISIBLE);
  				}				
  				
  				Log.d("Message","rooms: "+rooms);
  				Log.d("Message","roomsA1: "+roomsA1 );
  				Log.d("Message","roomsA2: "+roomsA2 );
  				Log.d("Message","roomsA3: "+roomsA3 );	
  				Log.d("Message","roomsB1: "+roomsB1 );
  				Log.d("Message","roomsB2: "+roomsB2 );
  				Log.d("Message","roomsB3: "+roomsB3 );	

  				//spinnerBlock
  				adapter = ArrayAdapter.createFromResource(getActivity(),
  				        R.array.block, android.R.layout.simple_spinner_item);
  				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  				spinnerBlock.setAdapter(adapter);
  				spinnerBlock.setOnItemSelectedListener(new MyOnItemSelectedListener());
  				
  				//spinnerLevel
  				adapter2 = ArrayAdapter.createFromResource(getActivity(),
  				        R.array.level, android.R.layout.simple_spinner_item);
  				adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  				spinnerLevel.setAdapter(adapter2);
  				spinnerLevel.setOnItemSelectedListener(new MyOnItemSelectedListener());
  					
  				//spinnerRoom
  				
  				spinnerRoom.setOnItemSelectedListener(new MyOnItemSelectedListener());
  				
  				//destspinnerBlock
  				adapter4 = ArrayAdapter.createFromResource(getActivity(),
  				        R.array.block, android.R.layout.simple_spinner_item);
  				adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  				destspinnerBlock.setAdapter(adapter4);
  				destspinnerBlock.setOnItemSelectedListener(new MyOnItemSelectedListener());
  				
  				//destspinnerLevel
  				
  				adapter5 = ArrayAdapter.createFromResource(getActivity(),
  				        R.array.level, android.R.layout.simple_spinner_item);
  				adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  				destspinnerLevel.setAdapter(adapter5);
  				destspinnerLevel.setOnItemSelectedListener(new MyOnItemSelectedListener());
  				
  				//destspinnerRoom
  				
  				destspinnerRoom.setOnItemSelectedListener(new MyOnItemSelectedListener());

  				
  				btScanQRcode.setPaintFlags(btScanQRcode.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
  				btScanQRcode.setOnClickListener(new View.OnClickListener() {
  					
  					@Override
  					public void onClick(View v) {
  						//Use QR code scanner
  						Intent i = new Intent("com.google.zxing.client.android.SCAN");
  		  				startActivityForResult(i, REQUEST_BARCODE);
  					}
  				});
  				
  				btNavigate.setOnClickListener(new View.OnClickListener() {
  					
  					@Override
  					public void onClick(View v) {
  						// TODO Auto-generated method stub
  						//go to another fragment
  						Intent i = new Intent(getActivity().getApplicationContext(), OfflineRouteActivity.class);
  						i.putExtra("selectedStart", selectedStart);
  						i.putExtra("selectedGoal", selectedGoal);
  						i.putExtra("spinnerBlockPosition", spinnerBlock.getSelectedItemPosition());
  						i.putExtra("spinnerLevelPosition", spinnerLevel.getSelectedItemPosition());
  						startActivity(i);

  					}
  				});
  							
  			}		
  		}	
  	}
 
      private void populateSpinner(ArrayList<Room> rooms, ArrayAdapter<String> spinnerAdapter, Spinner spinner ) {
  		List<String> roomList = new ArrayList<String>();
  	 
  	    for (int i = 0; i < rooms.size(); i++) {
  	        roomList.add(rooms.get(i).getName());
  	    }
  	 
  	    // Creating adapter for spinner
  	    spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, roomList);
  	 
  	    // Drop down layout style - list view with radio button
  	    spinnerAdapter
  	            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
  	 
  	    // attaching data adapter to spinner
  	    spinner.setAdapter(spinnerAdapter);
  	}
      
      public class MyOnItemSelectedListener implements OnItemSelectedListener{

  	    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
  	        // TODO Auto-generated method stub
      	
  	    	String blockA = "Block A", blockB = "Block B";
  		    	if(usingQR==false){
  			    	if (parent.getId()==R.id.spinnerBlock || parent.getId()==R.id.spinnerLevel){	    	
  				    	//Current Room Location is displayed based on spinnerBlock and spinnerLevel
  				    	if(spinnerBlock.getSelectedItem().equals(blockA) && spinnerLevel.getSelectedItem().equals("Ground Floor")){	
  				    		populateSpinner(roomsA1, adapter3, spinnerRoom);
  				    	}else if(spinnerBlock.getSelectedItem().equals(blockA) && spinnerLevel.getSelectedItem().equals("First Floor")){
  				    		populateSpinner(roomsA2, adapter3, spinnerRoom);
  				    	}else if(spinnerBlock.getSelectedItem().equals(blockA) && spinnerLevel.getSelectedItem().equals("Second Floor")){
  				    		populateSpinner(roomsA3, adapter3, spinnerRoom);
  				    	}else if(spinnerBlock.getSelectedItem().equals(blockB) && spinnerLevel.getSelectedItem().equals("Ground Floor")){
  				    		populateSpinner(roomsB1, adapter3, spinnerRoom); 
  				    	}else if(spinnerBlock.getSelectedItem().equals(blockB) && spinnerLevel.getSelectedItem().equals("First Floor")){
  				    		populateSpinner(roomsB2, adapter3, spinnerRoom);
  				    	}else if(spinnerBlock.getSelectedItem().equals(blockB) && spinnerLevel.getSelectedItem().equals("Second Floor")){
  				    		populateSpinner(roomsB3, adapter3, spinnerRoom);
  				    	}			    	
  			    	}
  		
  			    	if (parent.getId()==R.id.destspinnerBlock || parent.getId()==R.id.destspinnerLevel){
  			    		//Room Destination is displayed based on spinnerBlock and spinnerLevel
  			    		if(destspinnerBlock.getSelectedItem().equals(blockA) && destspinnerLevel.getSelectedItem().equals("Ground Floor")){
  			    			populateSpinner(roomsA1, adapter6, destspinnerRoom);
  				    	}else if(destspinnerBlock.getSelectedItem().equals(blockA) && destspinnerLevel.getSelectedItem().equals("First Floor")){
  				    		populateSpinner(roomsA2, adapter6, destspinnerRoom);
  				    	}else if(destspinnerBlock.getSelectedItem().equals(blockA) && destspinnerLevel.getSelectedItem().equals("Second Floor")){
  				    		populateSpinner(roomsA3, adapter6, destspinnerRoom);
  				    	}else if(destspinnerBlock.getSelectedItem().equals(blockB) && destspinnerLevel.getSelectedItem().equals("Ground Floor")){
  				    		populateSpinner(roomsB1, adapter6, destspinnerRoom);
  				    	}else if(destspinnerBlock.getSelectedItem().equals(blockB) && destspinnerLevel.getSelectedItem().equals("First Floor")){
  				    		populateSpinner(roomsB2, adapter6, destspinnerRoom);
  				    	}else if(destspinnerBlock.getSelectedItem().equals(blockB) && destspinnerLevel.getSelectedItem().equals("Second Floor")){
  				    		populateSpinner(roomsB3, adapter6, destspinnerRoom);
  				    	}	    		
  			    	}	
  			    	
  		    	}
  	    	usingQR=false;
  	    	  	
  	    	//find start and goal regionId based on spinnerRoom selection 	
  	    	if (parent.getId()==R.id.spinnerRoom || parent.getId()==R.id.destspinnerRoom){
  	    		for(int counter=0; counter<rooms.size(); counter++){
  	    			if(spinnerRoom.getSelectedItem().equals(rooms.get(counter).getName())){
  	    				selectedStart = rooms.get(counter).getRegionId();
  	    			}
  	    			if(destspinnerRoom.getSelectedItem().equals(rooms.get(counter).getName())){
  	    				selectedGoal = rooms.get(counter).getRegionId();
  	    			}
  	    		}	    						
      		}
  	    	
  	    	
  	    }

  	    public void onNothingSelected(AdapterView<?> arg0) {
  	        // TODO Auto-generated method stub
  	    }  
      
      }
}