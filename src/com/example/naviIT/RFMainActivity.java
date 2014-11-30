package com.example.naviIT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class RFMainActivity extends Activity {
	
	TextView result1, result2, result3, count;
	int counter;
	String strResult1, strResult2,strResult3;
	
	Button btnSave, btnShowList, btnExcel;
	
	WifiManager mainWifiObj;
	WifiScanReceiver wifiReciever;

	private ProgressDialog scanProgress;
	
	int defaultSize= 15;
	int numScan = 1;
	int counterScan;
	
	String referencePoint;
	String[] ap_name, ap_rssi, ap_bssid, ap_speed;
	String name, rssi, bssid, speed;
	
	private JSONArray location;
	JSONParser jsonParser = new JSONParser();
	
	private static String rootURL = "http://navifsktm.comule.com/new_root_rssi.php";
	
	private static final String KEY_SUCCESS = "success";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rfmain);
		
		Intent data = getIntent();
		referencePoint = data.getStringExtra("referencePoint");
		Log.i("rp in rf", "rp = " + referencePoint);
		
		
		result1 = (TextView) findViewById(R.id.result1);
		result2 = (TextView) findViewById(R.id.result2);
		result3 = (TextView) findViewById(R.id.result3);
		count = (TextView) findViewById(R.id.count);
		
		result1.setVisibility(View.INVISIBLE);
		result2.setVisibility(View.INVISIBLE);
		result3.setVisibility(View.INVISIBLE);
		count.setVisibility(View.INVISIBLE); 
		
		
		scanProgress = new ProgressDialog(RFMainActivity.this);
		scanProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		scanProgress.setTitle("Scanning");
		scanProgress.setMessage("Scanning signal strength, please wait for a moment...");
		scanProgress.setCancelable(false);
		//scanProgress.setIndeterminate(false);
		//scanProgress.setMax(100);
		//scanProgress.setProgress(0);
		scanProgress.show();
		
		ap_name = new String[defaultSize];
		ap_bssid = new String[defaultSize];
		ap_speed = new String[defaultSize];
		ap_rssi = new String[defaultSize];
		
		counterScan = 0;
		
		 mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	     wifiReciever = new WifiScanReceiver();
	     mainWifiObj.startScan();
	     counter++;
	     //counterScan++;
	    // Log.i("Counter Scan", "counter scan = " + counterScan);
	     
	     /**if(counterScan < numScan){
			 mainWifiObj.startScan();
			 counterScan++;
			 Log.i("Counter Scan in cs", "counter scan = " + counterScan);
			 scanProgress.setProgress(counterScan * 20);
		}else{
			unregisterReceiver(wifiReciever); 
			 scanProgress.dismiss();
			 RFMainActivity.this.finish();
		}*/
	     
	    /** for(int i = 0; i < numScan; i++){
	    	 mainWifiObj.startScan();
	    	 Log.i("Counter Scan in cs", "counter scan = " + counterScan);
			 scanProgress.setProgress(counterScan * 20);
	     }
	     
	     if(counterScan + 1 > numScan){
	    	 unregisterReceiver(wifiReciever); 
			 scanProgress.dismiss();
			 RFMainActivity.this.finish();
	     }**/
	     
	    

	}

	protected void onPause() {
	      //unregisterReceiver(wifiReciever);
	      super.onPause();
	   }

	   protected void onResume() {
	      registerReceiver(wifiReciever, new IntentFilter(
	      WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	      super.onResume();
	   }
	   

	   class WifiScanReceiver extends BroadcastReceiver {
	      private int[] top3;
	      private String[] top3Bssid;
	      private String[] top3Name;
	      private List<ScanResult> results;
	      private int size;

	      /**@TargetApi(Build.VERSION_CODES.GINGERBREAD)
		@SuppressLint("UseValueOf")
	      public void onReceive(Context c, Intent intent) {
	         List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
	         
	        
	        
	         if (mainWifiObj.isWifiEnabled() == false)
	         {
	        	 mainWifiObj.setWifiEnabled(true);
	         }  
	         results = mainWifiObj.getScanResults();
	         size = results.size();
	         Log.i("Size of result", "Size of result = " + size);
	         

	         if (counter < numScan) { //because i want the wifi scan to be repeated 15 times in a row
	                mainWifiObj.startScan();
	                Log.i("Counter Scan in cs", "counter scan = " + counterScan);
	                scanProgress.setProgress(counterScan * 20);
	            } else {
	                unregisterReceiver(wifiReciever);//stops the continuous scan 
	                scanProgress.dismiss();
	   			 	RFMainActivity.this.finish();
			
		 }
	        	
	         
	        for(int a = 0; a < numScan; a++)
	        if (size > defaultSize) {
	        	
	        	
	         for(int i = 0; i < defaultSize; i++){
	        	String temp = ((wifiScanList.get(i)).toString());
	        	ScanResult scanResult = wifiScanList.get(i);
	        	//medianCounter = new int[(wifiScanList.size())];
	        	String name = scanResult.SSID;
	        	Log.i("Name", "Name " + i + " = " + name);
	        	/**speed = String.valueOf(info.getLinkSpeed() + " " + WifiInfo.LINK_SPEED_UNITS);
				rssi = String.valueOf(String.valueOf(info.getRssi()));*/
	        	/**String speed = String.valueOf(scanResult.frequency);
	        	Log.i("Speed", "Speed " + i + " = " + speed);
	        	String rssi = String.valueOf(scanResult.level);
	        	Log.i("RSSI", "RSSI " + i + " = " + rssi);
	        	String bssid = scanResult.BSSID;
	        	Log.i("BSSID", "BSSID " + i + " = " + bssid);
	        	counter++;
	        	
	        }  
	        
	        	
		} else {
			 unregisterReceiver(wifiReciever); 
	            Toast.makeText(RFMainActivity.this, "FAIL", Toast.LENGTH_LONG).show();
			
      }
	        
	        
		}**/
		   public void onReceive(Context c, Intent intent) {
		   List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
		   
	        
	         if (mainWifiObj.isWifiEnabled() == false)
	         {
	        	 mainWifiObj.setWifiEnabled(true);
	         }  
	         results = mainWifiObj.getScanResults();
	         size = results.size();
	         Log.e("Size of scan", "Size = " + size);

	         if (size > defaultSize) {
	         for(int i = 0; i < defaultSize; i++){
	        	 ScanResult scanResult = wifiScanList.get(i);
		        	//medianCounter = new int[(wifiScanList.size())];
		        	name = scanResult.SSID;
		        	Log.i("Name", "Name " + i + " = " + name);
		        	speed = String.valueOf(scanResult.frequency);
		        	Log.i("Speed", "Speed " + i + " = " + speed);
		        	rssi = String.valueOf(scanResult.level);
		        	Log.i("RSSI", "RSSI " + i + " = " + rssi);
		        	bssid = scanResult.BSSID;
		        	Log.i("BSSID", "BSSID " + i + " = " + bssid);
	   
		        	ap_name[i] = name;
		        	ap_bssid[i] = bssid;
		        	ap_rssi[i] = rssi;
		        	ap_speed[i] = speed;
		        	
		        	
		      
		        	
  
	         }
	        

	         counter++;
	         Log.e("Counter", "Counter = " + counter);

	         fillData();
	     
          /** if (counter  < numScan + 1) { //because i want the wifi scan to be repeated 15 times in a row
               mainWifiObj.startScan();
               new PopulateData().execute();
               scanProgress.setProgress(counter * 20);
           } else {
               unregisterReceiver(wifiReciever);//stops the continuous scan 
               scanProgress.dismiss();
               RFMainActivity.this.finish();
              
           }**/
		} else {
          unregisterReceiver(wifiReciever); 
          Toast.makeText(RFMainActivity.this, "Failed to scan signal strength. This activity will be terminated.", 
        		  Toast.LENGTH_LONG).show();
          
          scanProgress.dismiss(); 
          RFMainActivity.this.finish();
      }
       
	      }
	   
	   }
	  
	  
	   class PopulateData extends AsyncTask<String, String, String> {
  		 
          
		/**
            * Before starting background thread Show Progress Dialog
            * */
           @Override
           protected void onPreExecute() {
               super.onPreExecute();
               
           }
    
           /**
            * Saving product
            * */
           protected String doInBackground(String... args) {
    
               String ap_name = name;
               String ap_rssi = rssi;
               String ap_bssid = bssid;
               String ap_speed = speed;
               
               Log.i("Data", "name = " + ap_name + " rssi = " + ap_rssi + " bssid = " + ap_bssid + " speed = " + ap_speed);
               
    
               // Building Parameters
               List<NameValuePair> params = new ArrayList<NameValuePair>();
               params.add(new BasicNameValuePair("ap_name", ap_name));
               params.add(new BasicNameValuePair("ap_rssi", ap_rssi));
               params.add(new BasicNameValuePair("ap_bssid", ap_bssid));
               params.add(new BasicNameValuePair("ap_speed", ap_speed));
               params.add(new BasicNameValuePair("reference_point", referencePoint));
    
               // getting JSON Object
               // Note that create product url accepts POST method
               JSONObject json = jsonParser.makeHttpRequest(rootURL,
                       "POST", params);
    
               // check log cat fro response
               Log.d("Create Response", json.toString());
    
               // check for success tag
               try {
                   int success = json.getInt(KEY_SUCCESS);
    
                   if (success == 1) {
                       // success
                	   Log.i("Success", "Success add one row of root rssi");
                   } else {
                       // fail
                	   Log.i("Failed", "Failed to add one row of root rssi");
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
	            // dismiss the dialog after getting all products
	        	Log.i("add new", "add new done");
	        	
	 
	        }
   	}


	public void fillData() {
		for(int i = 0; i < defaultSize; i++){
			name = ap_name[i];
			rssi = ap_rssi[i];
			bssid = ap_bssid[i];
			speed = ap_speed[i];
			
			 Log.i("Fill Data", "name = " + ap_name[i] + " rssi = " + ap_rssi[i] + " bssid = " + ap_bssid[i] + " speed = " + ap_speed[i]+ " reference point = " + referencePoint);
			
			new PopulateData().execute();
             int progress = 0;
             progress++;
             
             //scanProgress.setProgress(i*10);
             
             try{
 				synchronized(this){
 					int counter = 0;
 					
 					//startScan(finalPoint);
 					Log.i("Start scan method in dialog", "Start scan method in dialog called");
 					
 					while(counter <= 10){
 						this.wait(100);
 						counter++;
 						//scanProgress.setProgress(50);
 						
 					}
 				}
 			}
 			catch(InterruptedException e){
 				e.printStackTrace();
 			}
 
            // scanProgress.setProgress(i*10);
             
		}
		scanProgress.setProgress(100);
		unregisterReceiver(wifiReciever);//stops the continuous scan 
        scanProgress.dismiss();
        RFMainActivity.this.finish();
	}
}

