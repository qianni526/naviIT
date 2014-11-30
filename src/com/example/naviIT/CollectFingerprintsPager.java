package com.example.naviIT;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;








import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;





























import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CollectFingerprintsPager extends PagerAdapter {
 
    private Activity _activity;
    private String blockName;
    private LayoutInflater inflater;
	private ImageView radioMap1;
	
	private int valueX, valueY;
	//private TextView x;
	//private TextView y;
	private String finalLocation;
	
	private ProgressDialog progress;
	JSONParser jsonParser = new JSONParser();
	String finalRp;
	
	WifiManager mainWifiObj;
	
	
	//Receiver class
	String lname;
	String[] apName;
	String[] apNameArray;
	int apInt;
	
	String wifis[], tempW[];
	int compare[];
	String compareName[];
	String compareBssid[];
	int max, median, low;
	int[] maxCounter, medianCounter, lowCounter;
	
	String[] name,bssid;
	String[] rssi;
	
	int top31, top32, top33;
	
	StringBuilder sb,sb2;
	
	int counter;
	String strResult1, strResult2,strResult3;
	
	private String reference_point;
	
	private String finalPoint;
	
	private Button btnSelectZone;
	protected String selectedLoc;
	
	private static String rpURL = "http://navifsktm.comule.com/get_rp_id.php";
	
	private static final String KEY_SUCCESS = "success";
    private static final String KEY_RP = "rp";
    private static final String KEY_ID = "id";
    
    public CollectFingerprintsPager(Activity activity, String blockName) {
        this._activity = activity;
        this.blockName = blockName;
      
    }
 
    @Override
    public int getCount() {
    	return 1;
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
    	
       
    	
        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.fragment_fifth, container, false);
        
        
        Log.i("block name in fingerprint pager", "block name = " + blockName);
  
        radioMap1 = (ImageView)viewLayout.findViewById(R.id.radio_map_a);
        btnSelectZone = (Button)viewLayout.findViewById(R.id.btnSelectZone);
        
        radioMap1.setVisibility(View.INVISIBLE);
        btnSelectZone.setVisibility(View.INVISIBLE);
       // x = (TextView)viewLayout.findViewById(R.id.x);
		//y = (TextView)viewLayout.findViewById(R.id.y);
		
		//x.setVisibility(View.INVISIBLE);
		//y.setVisibility(View.INVISIBLE);
		
		if(blockName.equals("a")){
			//radioMap1.setImageResource(R.drawable.block_a_radio_map);
			chooseFloor(blockName);
		}else{
			Toast.makeText(_activity, "Under construction", Toast.LENGTH_SHORT).show();
		}
		
		btnSelectZone.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				loadSpinner("a_1");
				
			}
			
		});
        
        radioMap1.setOnTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				/**int valueX = (int)event.getX();
			    int valueY = (int)event.getY();
			    switch (event.getAction()) {
			        case MotionEvent.ACTION_DOWN:
			        case MotionEvent.ACTION_MOVE:
			        case MotionEvent.ACTION_UP:
			        case MotionEvent.ACTION_HOVER_MOVE:**/
			    
			   // x.setText("X: " + (Integer.toString(valueX)));
			   // y.setText("Y : " + (Integer.toString(valueY)));
			    
			    
			    //A11
			    if(valueX >= 10 && valueX <= 42 && valueY >= 227 && valueY <= 254){
			    	//Toast.makeText(_activity, "User tappe Zone A11", Toast.LENGTH_SHORT).show();
			    	//Log.i("call", "callled collect fingerprints medthod");
			    	//collectFingerprint("A1");
			    	finalRp = "A11";
			    	generateAlertDialog(finalRp);
			    	
			    	/**Intent rss = new Intent();
			    	rss.setClass(_activity, RFMainActivity.class);
			    	_activity.startActivity(rss);**/
			    	
			    	
			    }
			    
			  //A12
			    if(valueX >= 50 && valueX <= 94 && valueY >= 228 && valueY <= 253){
			    	Toast.makeText(_activity, "User tappe Zone A12", Toast.LENGTH_SHORT).show();
			    	//Log.i("call", "callled collect fingerprints medthod");
			    	//collectFingerprint("A1");
			    	
			    	/**Intent rss = new Intent();
			    	rss.setClass(_activity, RFMainActivity.class);
			    	_activity.startActivity(rss);**/
			    	
			    	
			    }
			    
			  //A13
			    if(valueX >= 102 && valueX <= 130 && valueY >= 228 && valueY <= 260){
			    	Toast.makeText(_activity, "User tappe Zone A13", Toast.LENGTH_SHORT).show();
			    	//Log.i("call", "callled collect fingerprints medthod");
			    	//collectFingerprint("A1");
			    	
			    	/**Intent rss = new Intent();
			    	rss.setClass(_activity, RFMainActivity.class);
			    	_activity.startActivity(rss);**/
			    	
			    	
			    }

			    //zone A1
			   /** if(valueX >= 11 && valueX <= 132 && valueY >= 274 && valueY <= 298){
			    	Toast.makeText(_activity, "User tappe Zone A1", Toast.LENGTH_SHORT).show();
			    	Log.i("call", "callled collect fingerprints medthod");
			    	//collectFingerprint("A1");
			    	
			    	Intent rss = new Intent();
			    	rss.setClass(_activity, RFMainActivity.class);
			    	_activity.startActivity(rss);
			    	
			    	
			    }**/
			    
			  //zone A2
			    /**if(valueX >= 294 && valueX <= 412 && valueY >= 274 && valueY <= 300){
			    	Toast.makeText(_activity, "User tappe Zone A2", Toast.LENGTH_SHORT).show();
			    	
			    	collectFingerprint("A2");
			    }**/
			    
			  //zone A3
			    /** if(valueX >= 69 && valueX <= 132 && valueY >= 449 && valueY <= 468){
			    	Toast.makeText(_activity, "User tappe Zone A3", Toast.LENGTH_SHORT).show();
			    	
			    	collectFingerprint("A3");
			    }**/
			    
			  //zone A4
			    /**if(valueX >= 131 && valueX <= 293 && valueY >= 381 && valueY <= 518){
			    	Toast.makeText(_activity, "User tappe Zone A4", Toast.LENGTH_SHORT).show();
			    	
			    	collectFingerprint("A4");
			    }**/
			    
			    
			    
			    
			    
			    
				return false;
			}
       

    });
       /** Log.i("call", "called start scan()");
      startScan ("A11");
      

		 mainWifiObj = (WifiManager) _activity.getSystemService(Context.WIFI_SERVICE);
		 wifiReciever = new WifiScanReceiver();
	     mainWifiObj.startScan();
	     Log.i("Start scan method", "Start scan method called");**/
        
        
		     
        ((ViewPager) container).addView(viewLayout);
        
        return viewLayout;
    }  
    
    private void chooseFloor(final String blockName){
		View dialogView2 = _activity.getLayoutInflater()
				.inflate(R.layout.choose_floor_dialog, null, false);
		
		final CheckBox radioGround = (CheckBox)dialogView2. findViewById(R.id.radioGround);
		final CheckBox radioFirst = (CheckBox)dialogView2. findViewById(R.id.radioFirst);
		final CheckBox radioSecond = (CheckBox)dialogView2. findViewById(R.id.radioSecond);
		
		final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(_activity);
  		alertDialog2.setTitle("Choose a floor from Block " + blockName.toUpperCase());
			alertDialog2.setView(dialogView2);
			alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
				
				private String floorNumber;
			

			public void onClick(DialogInterface dialog1, int which) {
				
					if(radioGround.isChecked() && !radioFirst.isChecked() && !radioSecond.isChecked()){
						
						floorNumber = blockName + "_1";
							
							radioMap1.setVisibility(View.VISIBLE);
							btnSelectZone.setVisibility(View.VISIBLE);
							dialog1.cancel();
							
							
							
							//intentToZone(floorNumber);
							
			
						}
						
						if(!radioGround.isChecked() && radioFirst.isChecked() && !radioSecond.isChecked()){
						
							floorNumber = blockName + "_2";
								
								
								dialog1.cancel();
								
								//intentToZone(floorNumber);
								
				
							}
						
						if(!radioGround.isChecked() && !radioFirst.isChecked() && radioSecond.isChecked()){
							
							floorNumber =  blockName + "_3";
								
								
								dialog1.cancel();
								
								//intentToZone(floorNumber);
								
				
							}
						
						if((radioGround.isChecked() && radioFirst.isChecked()) || (radioGround.isChecked() && radioSecond.isChecked())||
								(radioSecond.isChecked() && radioFirst.isChecked())){
							Toast.makeText(_activity, "Please choose only one floor.", Toast.LENGTH_LONG).show();
						}
						
						if(!radioGround.isChecked() && !radioFirst.isChecked() && !radioSecond.isChecked()){
							
							Toast.makeText(_activity, "Please choose one floor to continue.", Toast.LENGTH_LONG).show();
						}
						
						
			}


			/**private void intentToZone(String floorNumber) {
				// TODO Auto-generated method stub
				Intent collect = new Intent();
				collect.setClass(_activity, ZoneActivity.class);
				collect.putExtra("blockName", floorNumber);
				startActivity(collect);
			}**/

			
			}).show();	
	}
    	
    
    @SuppressWarnings("deprecation")
	protected void generateAlertDialog(String rp) {
    	AlertDialog alert = new AlertDialog.Builder(_activity).create();
    	alert.setTitle("Alert");
    	alert.setMessage("A reference point has been selected, start signal strength scanning?");
		alert.setButton2("Yes", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				new GetId().execute();
				
			}
			
		});
		alert.setButton3("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		});
		alert.show();
		
	}

	
		class GetId extends AsyncTask<String, String, String> {
			 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            progress = new ProgressDialog(_activity);
	            progress.setTitle("Loading");
	            progress.setMessage("Loading reference point... ...");
	            progress.setIndeterminate(false);
	            progress.setCancelable(true);
	            progress.show();
	        }
	 
	        /**
	         * Getting product details in background thread
	         * */
	        protected String doInBackground(String... args) {
	 
	          
	               
	                    // Check for success tag
	                    int success;
	                    try {
	                        // Building Parameters
	                        List<NameValuePair> params = new ArrayList<NameValuePair>();
	                        params.add(new BasicNameValuePair("reference_point", finalRp));
	 
	                        // getting product details by making HTTP request
	                        // Note that product details url will use GET request
	                        JSONObject json = jsonParser.makeHttpRequest(
	                                rpURL, "GET", params);
	 
	                        // check your log for json response
	                        Log.d("Single Product Details", json.toString());
	 
	                        // json success tag
	                        success = json.getInt(KEY_SUCCESS);
	                        if (success == 1) {
	                            // successfully received product details
	                            JSONArray rpObj = json
	                                    .getJSONArray(KEY_RP); // JSON Array
	 
	                            // get first product object from JSON Array
	                            JSONObject rp = rpObj.getJSONObject(0);
	 
	                   
	                            reference_point = rp.getString(KEY_ID);
	                            Log.i("RP in pager", "Final rp = " + reference_point);
	                            
	                           
	 
	                        }else{
	                            // product with pid not found
	                        }
	                    } catch (JSONException e) {
	                        e.printStackTrace();
	                    }
	               
	           
	 
	            return null;
	        }
	 
	        /**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog once got all details
	            progress.dismiss();
	            parseData(reference_point);
	            Log.i("Rp", "RP at post = " + reference_point);
	        }
	    }
		
	

	protected void parseData(String rp) {
    	Intent rss = new Intent();
    	rss.setClass(_activity, RFMainActivity.class);
    	rss.putExtra("referencePoint", rp);
    	Log.i("Rp in pager", "RP in pager = " + rp);
    	_activity.startActivity(rss);
		
	}

	private void collectFingerprint(String location) {
		final EditText editLocationId;
		final Spinner spinnerLocationId;
		final ProgressBar progressScanning;
		final Button btnStartScanning;
		
		
		ArrayAdapter<String> adapter;
		
		final String[] A1 = {"A11","A12","A13"};
		final String[] A2 = {"A21", "A22", "A23"};
		final String[] A3 = {"A31", "A32"};
		final String[] A4 = {"A41", "A42", "A43", "A44", "A45", "A46", "A47", "A48", "A49"};
		
		View dialogView = _activity.getLayoutInflater()
				.inflate(R.layout.activity_dialog, null, false);
		
		editLocationId = (EditText)dialogView.findViewById(R.id.editLocationId);
		spinnerLocationId = (Spinner)dialogView.findViewById(R.id.spinnerLocationId);
		progressScanning = (ProgressBar)dialogView.findViewById(R.id.progressBarScanning);
		btnStartScanning = (Button)dialogView.findViewById(R.id.btnStartScanning);
		
		progressScanning.setVisibility(View.INVISIBLE);
		btnStartScanning.setVisibility(View.INVISIBLE);
		
		if(location.equalsIgnoreCase("A1")){
			adapter = new ArrayAdapter<String>(
					_activity, android.R.layout.simple_spinner_item, A1);
					adapter.setDropDownViewResource(
					android.R.layout.simple_spinner_dropdown_item);
					spinnerLocationId.setAdapter(adapter);
					
					spinnerLocationId.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

						@Override
						public void onItemSelected(
								AdapterView<?> parent,
								View view,
								int position, long id) {
							Log.i("Spinner", "Item selected = " + position);
							if(position == 0){
								editLocationId.setText("A11");
								finalPoint = "A11";
							}
							
							if(position == 1){
								editLocationId.setText("A12");
								finalPoint = "A12";
							}
							
							if(position == 2){
								editLocationId.setText("A13");
								finalPoint = "A13";
							}
							
							
						}
						
						

						@Override
						public void onNothingSelected(
								AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}



						
						
					});
		}
		
		if(location.equalsIgnoreCase("A2")){
			adapter = new ArrayAdapter<String>(
					_activity, android.R.layout.simple_spinner_item, A2);
					adapter.setDropDownViewResource(
					android.R.layout.simple_spinner_dropdown_item);
					spinnerLocationId.setAdapter(adapter);
					
					spinnerLocationId.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

						@Override
						public void onItemSelected(
								AdapterView<?> parent,
								View view,
								int position, long id) {
							Log.i("Spinner", "Item selected = " + position);
							if(position == 0){
								editLocationId.setText("A21");
								finalPoint = "A21";
							}
							
							if(position == 1){
								editLocationId.setText("A22");
								finalPoint = "A22";
							}
							
							if(position == 2){
								editLocationId.setText("A23");
								finalPoint = "A23";
							}
							
							
						}
						
						

						@Override
						public void onNothingSelected(
								AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}
						
					});
		}
		
		if(location.equalsIgnoreCase("A3")){
			adapter = new ArrayAdapter<String>(
					_activity, android.R.layout.simple_spinner_item, A3);
					adapter.setDropDownViewResource(
					android.R.layout.simple_spinner_dropdown_item);
					spinnerLocationId.setAdapter(adapter);
					
					spinnerLocationId.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

						@Override
						public void onItemSelected(
								AdapterView<?> parent,
								View view,
								int position, long id) {
							Log.i("Spinner", "Item selected = " + position);
							if(position == 0){
								editLocationId.setText("A31");
								finalPoint = "A31";
							}
							
							if(position == 1){
								editLocationId.setText("A32");
								finalPoint = "A32";
							}
							
							
						}
						
						

						@Override
						public void onNothingSelected(
								AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}
						
					});
		}
		
		if(location.equalsIgnoreCase("A4")){
			adapter = new ArrayAdapter<String>(
					_activity, android.R.layout.simple_spinner_item, A4);
					adapter.setDropDownViewResource(
					android.R.layout.simple_spinner_dropdown_item);
					spinnerLocationId.setAdapter(adapter);
					
					spinnerLocationId.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

						@Override
						public void onItemSelected(
								AdapterView<?> parent,
								View view,
								int position, long id) {
							Log.i("Spinner", "Item selected = " + position);
							if(position == 0){
								editLocationId.setText("A41");
								finalPoint = "A41";
							}
							
							if(position == 1){
								editLocationId.setText("A42");
								finalPoint = "A42";
							}
							
							if(position == 2){
								editLocationId.setText("A43");
								finalPoint = "A43";
							}
							
							if(position == 3){
								editLocationId.setText("A44");
								finalPoint = "A44";
							}
							
							if(position == 4){
								editLocationId.setText("A45");
								finalPoint = "A45";
							}
							
							if(position == 5){
								editLocationId.setText("A46");
								finalPoint = "A46";
							}
							
							if(position == 6){
								editLocationId.setText("A47");
								finalPoint = "A47";
							}
							
							if(position == 7){
								editLocationId.setText("A48");
								finalPoint = "A48";
							}
							
							if(position == 8){
								editLocationId.setText("A49");
								finalPoint = "A49";
							}
						}
						
						

						@Override
						public void onNothingSelected(
								AdapterView<?> parent) {
							// TODO Auto-generated method stub
							
						}
						
					});
					
					finalLocation = editLocationId.getText().toString();
					
		}
		
		
		
		
				
		
	}
	
	protected void loadSpinner(final String blockName) {
		View dialogView2 = _activity.getLayoutInflater()
				.inflate(R.layout.choose_spinner_dialog, null, false);
		
		
		final Spinner locSpinner = (Spinner)dialogView2. findViewById(R.id.locSpinner);
		
		
		if(blockName.equalsIgnoreCase("a_1")){
		
			List<String> list = new ArrayList<String>();
			list.add("1_0"); 
	        list.add("1_1"); 
	        list.add("1_2"); 
	        list.add("1_3"); 
	        list.add("1_4"); 
	        list.add("1_5"); 
	        list.add("1_6"); 
	        list.add("1_7"); 
	        list.add("1_8"); 
	        list.add("1_9"); 
	        list.add("1_10"); 
	        list.add("1_11"); 
	        list.add("1_12"); 
	        list.add("1_13"); 
	        
	         
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
	                     (_activity, android.R.layout.simple_spinner_item,list);
	                      
	        dataAdapter.setDropDownViewResource
	                     (android.R.layout.simple_spinner_dropdown_item);
	                      
	        locSpinner.setAdapter(dataAdapter);
	         
		}
		
		if(blockName.equalsIgnoreCase("a_2")){
		
			List<String> list = new ArrayList<String>();
			
			list.add("2_0"); 
	        list.add("2_1"); 
	        list.add("2_2"); 
	        list.add("2_3"); 
	        list.add("2_4"); 
	        list.add("2_5"); 
	        list.add("2_6"); 
	        list.add("2_7"); 
	        list.add("2_8"); 
	        list.add("2_9"); 
	        list.add("2_10"); 
	        list.add("2_11"); 
	        list.add("2_12"); 
	        list.add("2_13"); 
	        list.add("2_14");
	        list.add("2_15");
	        list.add("2_16");
	        list.add("2_17");
	        list.add("2_18");
	        list.add("2_19");
	        list.add("2_20");
	        
	         
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
	                     (_activity, android.R.layout.simple_spinner_item,list);
	                      
	        dataAdapter.setDropDownViewResource
	                     (android.R.layout.simple_spinner_dropdown_item);
	                      
	        locSpinner.setAdapter(dataAdapter);
		}
		
		if(blockName.equalsIgnoreCase("a_3")){
			
			List<String> list = new ArrayList<String>();
			
			list.add("3_0"); 
	        list.add("3_1"); 
	        list.add("3_2"); 
	        list.add("3_3"); 
	        list.add("3_4"); 
	        list.add("3_5"); 
	        list.add("3_6"); 
	        list.add("3_7"); 
	        list.add("3_8"); 
	        list.add("3_9"); 
	        list.add("3_10"); 
	        list.add("3_11"); 
	        list.add("3_12"); 
	        list.add("3_13"); 
	        list.add("3_14");
	        list.add("3_15");
	        list.add("3_16");
	        list.add("3_17");
	        list.add("3_18");
	        list.add("3_19");
	        
	         
	        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
	                     (_activity, android.R.layout.simple_spinner_item,list);
	                      
	        dataAdapter.setDropDownViewResource
	                     (android.R.layout.simple_spinner_dropdown_item);
	                      
	        locSpinner.setAdapter(dataAdapter);
		}
		
		if(blockName.equalsIgnoreCase("b_1")){
			
			
		}
		
		if(blockName.equalsIgnoreCase("b_2")){
			
		}
		
		if(blockName.equalsIgnoreCase("b_3")){
		
		}
		
		locSpinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				Log.e("loc", "Selected loc at position = " + position);
				selectedLoc = Integer.toString(position);
				Log.e("loc", "Selected loc at position in str = " + selectedLoc);
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(_activity);
  		alertDialog2.setTitle("Choose one location to scan ");
			alertDialog2.setView(dialogView2);
			alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
				


			public void onClick(DialogInterface dialog1, int which) {
				
				Intent scan = new Intent();
				scan.setClass(_activity, ScanningActivity.class);
				scan.putExtra("blockName", blockName);
				scan.putExtra("selectedLoc", selectedLoc);
				_activity.startActivity(scan);
				_activity.finish();
				
					
			}
			
			}).show();	
		
	}
    
    
    	
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
  
    }
}