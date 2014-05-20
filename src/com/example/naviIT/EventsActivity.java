package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EventsActivity extends Activity implements OnItemClickListener {

	ListView listView1;

	ArrayList<Event> events = new ArrayList<Event>();
	private ArrayAdapter<Event> adapter;
	JSONArray jArray;
	JSONObject eventItem;
	ProgressDialog pd = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_event);

		connectDB connect = new connectDB();
		connect.execute();
		Log.d("Message", "connect db");

		Button addEvent = (Button) findViewById(R.id.btAddEvent);
		addEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				addIntent();
			}
		});
		
		listView1 = (ListView) findViewById(R.id.listView1);
		listView1.setOnItemClickListener(EventsActivity.this);
		
	}

	public void onItemClick(AdapterView<?> p, View v, final int position, long id) {
		String dialogTitle = events.get(position).getTitle().toString();
		String venue = events.get(position).getVenue().toString();
		String date = events.get(position).getDate().toString();
		String time = events.get(position).getTime().toString();
		String description = events.get(position).getDescription().toString();
		
		View dialogView = getLayoutInflater().inflate(R.layout.dialog_event, null);
		
		ImageButton editButton1 = (ImageButton) dialogView.findViewById(R.id.editButton1);
		
		TextView tvVenue = (TextView) dialogView
				.findViewById(R.id.tvVenue);
		tvVenue.setText("Venue: \n"+venue);
		TextView tvDate = (TextView) dialogView
				.findViewById(R.id.tvDate);
		tvDate.setText("Date: \n"+date);
		TextView tvTime = (TextView) dialogView
				.findViewById(R.id.tvTime);
		tvTime.setText("Time: \n"+time);
		TextView tvDescription = (TextView) dialogView
				.findViewById(R.id.tvDescription);		
		tvDescription.setText("Details: \n" +description);
		
		editButton1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditIntent(events.get(position));
			}
		});
		
		AlertDialog.Builder builder = new AlertDialog.Builder(EventsActivity.this);
		builder
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
						}
					})
			.show();

		
		
		
			
		//.setMessage("Venue: " + venue + "\nDate: "+ datetime + "\nDescription: "+ description)
			
	}


	class CustomAdapter extends ArrayAdapter<Event> {

		public CustomAdapter() {
			super(EventsActivity.this, android.R.layout.simple_list_item_1,
					events);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = getLayoutInflater().inflate(R.layout.row, null,
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
			textView4.setText(events.get(position).getTime());
			return rowView;
		}

	}

	public void addIntent(){
		Intent addIntent = new Intent(this, AddEventActivity.class);
		startActivity(addIntent);
	}
	
	public void EditIntent(Event e){
		Intent editIntent = new Intent(this, EditEventActivity.class);
		//editIntent.putExtra("data", new DataWrapper(events));
		Bundle b = new Bundle();
		b.putString("eventId", e.getEventId());
		b.putString("title", e.getTitle());
		b.putString("venue", e.getVenue());
		b.putString("date", e.getDate());
		b.putString("time", e.getTime());
		b.putString("description", e.getDescription());		
		editIntent.putExtras(b);		
		startActivity(editIntent);
	}
	
	class connectDB extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pd = ProgressDialog.show(EventsActivity.this, "", "Loading...");
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			JSONParser jsonparser = new JSONParser();
			List<NameValuePair> list = new ArrayList<NameValuePair>();

			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/event.php", "GET", list);
			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/event.php", "GET", list);
			
			try {
				if (jObject.getString("status").equals("success")) {
					jArray = jObject.getJSONArray("events");
					for(int i=0; i<jArray.length(); i++){
						eventItem = jArray.getJSONObject(i);
						events.add(new Event(eventItem.getString("eventId"),eventItem.getString("title"),eventItem.getString("venue"),eventItem.getString("date"),eventItem.getString("time"),eventItem.getString("description")));					
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

			if (EventsActivity.this.pd != null) {
                EventsActivity.this.pd.dismiss();
			}
			
			adapter = new CustomAdapter();
			listView1.setAdapter(adapter);
		}
	}
}
