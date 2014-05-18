package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naviIT.R;
import com.example.naviIT.EditEventActivity.connectDB;
import com.example.naviIT.EditEventActivity.connectDatabase;
 
public class EditEvent extends Fragment {

      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
  	EditText title, venue, date, time, description;
  	Button save;
  	ImageButton discard;
  	Bundle b;
      
      public EditEvent() {
 
      }
      
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            View view = inflater.inflate(R.layout.activity_addevent, container,
                        false);
            
            b = getActivity().getIntent().getExtras();
    		
    		title = (EditText) view.findViewById(R.id.etTitle);
    		venue = (EditText) view.findViewById(R.id.etVenue);
    		date = (EditText) view.findViewById(R.id.etDate);
    		time = (EditText) view.findViewById(R.id.etTime);
    		description = (EditText) view.findViewById(R.id.etDescription);
    		
    		//set editText with existing info
    		title.setText(b.getString("title"));
    		venue.setText(b.getString("venue"));
    		date.setText(b.getString("date"));
    		time.setText(b.getString("time"));
    		description.setText(b.getString("description"));
    		
    		discard = (ImageButton) view.findViewById(R.id.btDiscard);
    		discard.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				connectDatabase connect_discard = new connectDatabase();
    				connect_discard.execute();
    				Log.d("Message", "connect db");
    			}
    		});
    		
    		save = (Button) view.findViewById(R.id.btSaveEdit);
    		save.setOnClickListener(new View.OnClickListener() {
    			
    			@Override
    			public void onClick(View v) {
    				// TODO Auto-generated method stub
    				connectDB connect = new connectDB();
    				connect.execute();
    				Log.d("Message", "connect db");
    			}
    		});
          
            return view;
      }
      
      public void FragmentThree(){
		  // Create new fragment and transaction
		  Fragment newFragment = new FragmentThree();
		  FragmentTransaction transaction = getFragmentManager().beginTransaction();

		  // Replace whatever is in the fragment_container view with this fragment,
		  // and add the transaction to the back stack
		  transaction.replace(R.id.content_frame, newFragment);
		  transaction.addToBackStack(null);

		  // Commit the transaction
		  transaction.commit();
	  }
	
	class connectDatabase extends AsyncTask<Void, Void, Void>
	{
		boolean deleteEventFlag = false;
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("eventId", b.getString("eventId")));
			JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/deleteEvent.php", "GET", list);
					
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
			if(deleteEventFlag==true)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Event deleted.", Toast.LENGTH_SHORT).show();
				FragmentThree();
			}	
			else{
				Log.d("Message", "Fail to delete event");
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
		}
		
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("eventId", b.getString("eventId")));
			list.add(new BasicNameValuePair("title",title.getText().toString()));
			list.add(new BasicNameValuePair("venue",venue.getText().toString()));
			list.add(new BasicNameValuePair("date",date.getText().toString()));
			list.add(new BasicNameValuePair("time",time.getText().toString()));
			list.add(new BasicNameValuePair("description",description.getText().toString()));
			Log.d("Message", "The list passed to php is :"+list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/editEvent.php", "GET", list);
					
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
			if(editEventFlag==true)
			{
				Toast.makeText(getActivity().getApplicationContext(), "Changes saved.", Toast.LENGTH_SHORT).show();
				FragmentThree();
			}	
			else{
				Log.d("Message", "Fail to edit event");
			}
		}
		
		
	}
}
     