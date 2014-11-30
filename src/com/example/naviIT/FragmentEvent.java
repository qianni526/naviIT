package com.example.naviIT;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.naviIT.R;
 
//This is actually the Event Activity Class 
public class FragmentEvent extends Fragment implements OnItemClickListener{

      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      ListView listView1;

  	  ArrayList<Event> events = new ArrayList<Event>();
  	  private ArrayAdapter<Event> adapter;
  	  JSONArray jArray;
  	  JSONObject eventItem;
  	  AlertDialog.Builder builder;
  	  AlertDialog alert;
  	  boolean closeEventDialogFlag = false;
  	// Session Manager Class
      SessionManager session;
      String name;
      String isAdmin;
      ProgressDialog pd;
      boolean hide=false;
  	  
      public FragmentEvent() {
 
      }
      
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            View view = inflater.inflate(R.layout.activity_event, container,
                        false);
            
            connectDB connect = new connectDB();
    		connect.execute();
    		Log.d("Message", "connect db");
            
    		// Session class instance
            session = new SessionManager(getActivity().getApplicationContext());
            
         // get user data from session
            HashMap<String, String> user = session.getUserDetails();
             
            // name
            name = user.get(SessionManager.KEY_NAME);
             
            // password
            isAdmin = user.get(SessionManager.IS_ADMIN);
            
            Button addEvent = (Button) view.findViewById(R.id.btAddEvent);
            
        	if(session.isLoggedIn()){
        		 if(isAdmin.equals("Admin")){
        			 addEvent.setVisibility(View.VISIBLE);
        		 }
        	}
    		
           
    		addEvent.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				AddIntent();
    			}
    		});
          
    		listView1 = (ListView) view.findViewById(R.id.listView1);
    		listView1.setOnItemClickListener(this);
    		
            return view;
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

			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/event.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/event.php", "GET", list);
			Log.d("JSON respond", jObject.toString());
			
			try {
				if (jObject.getString("status").equals("success")) {
					jArray = jObject.getJSONArray("events");
					for(int i=0; i<jArray.length(); i++){
						eventItem = jArray.getJSONObject(i);
						events.add(new Event(eventItem.getString("eventId"),eventItem.getString("title"),eventItem.getString("venue"),eventItem.getString("date"),eventItem.getString("hour"),eventItem.getString("minute"),eventItem.getString("description")));					
					}
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.d("Message","status is fail");
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			if (pd != null) {
	                pd.dismiss();
				}	
			adapter = new CustomAdapter();
			listView1.setAdapter(adapter);
		}
	}
  	
  	class CustomAdapter extends ArrayAdapter<Event> {

		public CustomAdapter() {
			super(getActivity(), android.R.layout.simple_list_item_1,
					events);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = getActivity().getLayoutInflater().inflate(R.layout.row, null,
					false);
			
			TextView textView1 = (TextView) rowView
					.findViewById(R.id.textView1);
			textView1.setText(events.get(position).getTitle());
			TextView textView2 = (TextView) rowView
					.findViewById(R.id.textView2);
			textView2.setText(events.get(position).getVenue());
			TextView textView3 = (TextView) rowView
					.findViewById(R.id.textView3);
			textView3.setText(events.get(position).getDate());
			TextView textView4 = (TextView) rowView
					.findViewById(R.id.textView4);


			int setTime=(Integer.parseInt(events.get(position).getMinute()) * 60 + (Integer.parseInt(events.get(position).getHour())) * 60 * 60) * 1000;
			
			SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			
			   String formatted = format.format(setTime);
			
			textView4.setText(formatted);
			
			return rowView;
		}

	}

  	public void AddIntent(){
		Intent addIntent= new Intent(getActivity(), AddEventActivity.class);
		startActivityForResult(new Intent(getActivity(), AddEventActivity.class), 0);
		//startActivity(addIntent);
		//getActivity().finish();
	}
  	
  	public void onActivityResult(int requestCode, int resultCode, Intent data){
  		super.onActivityResult(requestCode, resultCode, data);
  		if(requestCode == 0){
  			if(resultCode == Activity.RESULT_OK){
  				
  				if(closeEventDialogFlag == true){
  					closeEventDialog();
  				}
  				refresh();
  			}
  		}
  	}

  	public void closeEventDialog() {
		alert.cancel();		
	}
  	
	public void refresh() {
		events.clear();
		
		connectDB connect = new connectDB();
		connect.execute();
		
	}

	public void EditIntent(Event e){
		Intent editIntent = new Intent(getActivity(), EditEventActivity.class);
		//editIntent.putExtra("data", new DataWrapper(events));
		Bundle b = new Bundle();
		b.putString("eventId", e.getEventId());
		b.putString("title", e.getTitle());
		b.putString("venue", e.getVenue());
		b.putString("date", e.getDate());
		b.putString("hour", e.getHour());
		b.putString("minute", e.getMinute());
		b.putString("description", e.getDescription());		
		editIntent.putExtras(b);
		//startActivity(editIntent);
		startActivityForResult(editIntent, 0);
		//getActivity().finish();
	}
	
	public void onItemClick(AdapterView<?> p, View v, final int position, long id) {
		String dialogTitle = events.get(position).getTitle().toString();
		String venue = events.get(position).getVenue().toString();
		String date = events.get(position).getDate().toString();
		String hour = events.get(position).getHour();
		String minute = events.get(position).getMinute();
		String description = events.get(position).getDescription().toString();
		
		View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_event, null);
		ImageButton editButton1 = (ImageButton) dialogView.findViewById(R.id.editButton1);
		
		TextView tvVenue = (TextView) dialogView
				.findViewById(R.id.tvVenue);
		tvVenue.setText("Venue: \n"+venue);
		TextView tvDate = (TextView) dialogView
				.findViewById(R.id.tvDate);
		tvDate.setText("Date: \n"+date);
		TextView tvTime = (TextView) dialogView
				.findViewById(R.id.tvTime);
		 
		int setTime=(Integer.parseInt(minute) * 60 + (Integer.parseInt(hour)) * 60 * 60) * 1000;
		  SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
		  format.setTimeZone(TimeZone.getTimeZone("UTC"));
		   String formatted = format.format(setTime);
		
		tvTime.setText("Time: \n"+formatted);
		
		//tvTime.setText("Time: \n"+hour+":"+minute);
		TextView tvDescription = (TextView) dialogView
				.findViewById(R.id.tvDescription);		
		tvDescription.setText("Details: \n" +description);
		
		
        
    	if(session.isLoggedIn()){
    		 if(isAdmin.equals("Admin")){
    			 editButton1.setVisibility(View.VISIBLE);
    		 }
    	}
		
		editButton1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditIntent(events.get(position));
			}
		});
		
		builder = new AlertDialog.Builder(getActivity());
		alert = builder
			.setTitle(dialogTitle)
			.setView(dialogView)
			.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User cancelled the dialog
						}
					})
			.setNeutralButton("Locate",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int which) {
							// go to the map location of event
							//select navigation mode
				            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

				            builder.setMessage("Please select a navigation mode.");
				            
				            builder.setPositiveButton("Online", new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface dialog, int id) {
				                	getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentNavigation(), "Navigation").commit();
				                }
				            });
				            builder.setNegativeButton("Offline", new DialogInterface.OnClickListener() {
				                public void onClick(DialogInterface dialog, int id) {
				                	getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentOfflineNavigation(), "OfflineNavigation").commit();
				                }
				            });
				            
				            
				            AlertDialog dialogNavigate = builder.create();
				            
				            dialogNavigate.show();
						}
					}).create();
			alert.show();
			closeEventDialogFlag = true;
	}
	
	
}