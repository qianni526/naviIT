package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
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
import android.widget.Button;
import android.widget.Toast;

public class ScanningActivity extends Activity {
	int counter;
	String strResult1, strResult2,strResult3;
	
	Button btnSave, btnShowList, btnExcel;
	
	WifiManager mainWifiObj;
	WifiScanReceiver wifiReciever;

	private ProgressDialog scanProgress;
	
	private int size;
	
	//int defaultSize= 10;
	int numScan = 1;
	int counterScan;
	
	//String referenceNumber;
	String blockName, selectedLoc;
	String[] ap_name, ap_rssi, ap_bssid, ap_speed;
	String name, rssi, bssid, speed;
	
	private JSONArray location;
	JSONParser jsonParser = new JSONParser();
	
	private static String rootURL = "http://navifsktm.comule.com/admin_new_signal.php";
	
	private static final String KEY_SUCCESS = "success";
	
	private int noOfScan;
	private String userFromData;
	
	private String blockId, floorId;
	public String userId;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scanning);
		
		Intent data = getIntent();
		//referenceNumber = data.getStringExtra("referenceNumber");
		//Log.i("rp in rf", "rp = " + referenceNumber);
		blockName = data.getStringExtra("blockName");
		selectedLoc = data.getStringExtra("selectedLoc");
		Log.i("Data", "Block Name in scanning = " + blockName);
		Log.i("Data", "Selected Loc in scanning = " + selectedLoc);
		
		DatabaseHandler db = new DatabaseHandler(getApplicationContext());
    	userFromData = db.getUsername();
    	Log.i("Data", "Username = " + userFromData);
    	
    	getBlockId(blockName);
    	
    	getFloorId(blockId,selectedLoc);
    	
    	userId = "1";
		
		
		scanProgress = new ProgressDialog(ScanningActivity.this);
		scanProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		scanProgress.setTitle("Scanning");
		scanProgress.setMessage("Scanning signal strength, please wait for a moment...");
		scanProgress.setCancelable(false);
		//scanProgress.setIndeterminate(false);
		//scanProgress.setMax(100);
		//scanProgress.setProgress(0);
		scanProgress.show();
		
		ap_name = new String[100];
		ap_bssid = new String[100];
		ap_speed = new String[100];
		ap_rssi = new String[100];
		
		counterScan = 0;
		
		 mainWifiObj = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	     wifiReciever = new WifiScanReceiver();
	     mainWifiObj.startScan();
	     counter++;
	     
	    

	}

	
	private void getFloorId(String blockId,String selectedLoc) {
		if(blockId.equalsIgnoreCase("1")){
			if(selectedLoc.equalsIgnoreCase("0")){
				floorId = "1";
			}
			if(selectedLoc.equalsIgnoreCase("1")){
				floorId = "2";
			}
			if(selectedLoc.equalsIgnoreCase("2")){
				floorId = "3";
			}
			if(selectedLoc.equalsIgnoreCase("3")){
				floorId = "4";
			}
			if(selectedLoc.equalsIgnoreCase("4")){
				floorId = "5";
			}
			if(selectedLoc.equalsIgnoreCase("5")){
				floorId = "6";
			}
			if(selectedLoc.equalsIgnoreCase("6")){
				floorId = "7";
			}
			if(selectedLoc.equalsIgnoreCase("7")){
				floorId = "8";
			}
			if(selectedLoc.equalsIgnoreCase("8")){
				floorId = "9";
			}
			if(selectedLoc.equalsIgnoreCase("9")){
				floorId = "10";
			}
			if(selectedLoc.equalsIgnoreCase("10")){
				floorId = "11";
			}
			if(selectedLoc.equalsIgnoreCase("11")){
				floorId = "12";
			}
			if(selectedLoc.equalsIgnoreCase("12")){
				floorId = "13";
			}
			if(selectedLoc.equalsIgnoreCase("13")){
				floorId = "14";
			}
		}
		
		if(blockId.equalsIgnoreCase("2")){
			if(selectedLoc.equalsIgnoreCase("0")){
				floorId = "15";
			}
			if(selectedLoc.equalsIgnoreCase("1")){
				floorId = "16";
			}
			if(selectedLoc.equalsIgnoreCase("2")){
				floorId = "17";
			}
			if(selectedLoc.equalsIgnoreCase("3")){
				floorId = "18";
			}
			if(selectedLoc.equalsIgnoreCase("4")){
				floorId = "19";
			}
			if(selectedLoc.equalsIgnoreCase("5")){
				floorId = "20";
			}
			if(selectedLoc.equalsIgnoreCase("6")){
				floorId = "21";
			}
			if(selectedLoc.equalsIgnoreCase("7")){
				floorId = "22";
			}
			if(selectedLoc.equalsIgnoreCase("8")){
				floorId = "23";
			}
			if(selectedLoc.equalsIgnoreCase("9")){
				floorId = "24";
			}
			if(selectedLoc.equalsIgnoreCase("10")){
				floorId = "25";
			}
			if(selectedLoc.equalsIgnoreCase("11")){
				floorId = "26";
			}
			if(selectedLoc.equalsIgnoreCase("12")){
				floorId = "27";
			}
			if(selectedLoc.equalsIgnoreCase("13")){
				floorId = "28";
			}
			if(selectedLoc.equalsIgnoreCase("14")){
				floorId = "29";
			}
			if(selectedLoc.equalsIgnoreCase("15")){
				floorId = "30";
			}
			if(selectedLoc.equalsIgnoreCase("16")){
				floorId = "31";
			}
			if(selectedLoc.equalsIgnoreCase("17")){
				floorId = "32";
			}
			if(selectedLoc.equalsIgnoreCase("18")){
				floorId = "33";
			}
			if(selectedLoc.equalsIgnoreCase("19")){
				floorId = "34";
			}
			if(selectedLoc.equalsIgnoreCase("20")){
				floorId = "35";
			}
		}
		
		if(blockId.equalsIgnoreCase("3")){
			if(selectedLoc.equalsIgnoreCase("0")){
				floorId = "36";
			}
			if(selectedLoc.equalsIgnoreCase("1")){
				floorId = "37";
			}
			if(selectedLoc.equalsIgnoreCase("2")){
				floorId = "38";
			}
			if(selectedLoc.equalsIgnoreCase("3")){
				floorId = "39";
			}
			if(selectedLoc.equalsIgnoreCase("4")){
				floorId = "40";
			}
			if(selectedLoc.equalsIgnoreCase("5")){
				floorId = "41";
			}
			if(selectedLoc.equalsIgnoreCase("6")){
				floorId = "42";
			}
			if(selectedLoc.equalsIgnoreCase("7")){
				floorId = "43";
			}
			if(selectedLoc.equalsIgnoreCase("8")){
				floorId = "44";
			}
			if(selectedLoc.equalsIgnoreCase("9")){
				floorId = "45";
			}
			if(selectedLoc.equalsIgnoreCase("10")){
				floorId = "46";
			}
			if(selectedLoc.equalsIgnoreCase("11")){
				floorId = "47";
			}
			if(selectedLoc.equalsIgnoreCase("12")){
				floorId = "48";
			}
			if(selectedLoc.equalsIgnoreCase("13")){
				floorId = "49";
			}
			if(selectedLoc.equalsIgnoreCase("14")){
				floorId = "50";
			}
			if(selectedLoc.equalsIgnoreCase("15")){
				floorId = "51";
			}
			if(selectedLoc.equalsIgnoreCase("16")){
				floorId = "52";
			}
			if(selectedLoc.equalsIgnoreCase("17")){
				floorId = "53";
			}
			if(selectedLoc.equalsIgnoreCase("18")){
				floorId = "54";
			}
			if(selectedLoc.equalsIgnoreCase("19")){
				floorId = "55";
			}
			
		}
		
		Log.e("final", "final floor id = " + floorId);
	}


	private void getBlockId(String blockName) {
		if(blockName.equalsIgnoreCase("a_1")){
			blockId = "1";
		}
		
		if(blockName.equalsIgnoreCase("a_2")){
			blockId = "2";
		}
		
		if(blockName.equalsIgnoreCase("a_3")){
			blockId = "3";
		}
		
		if(blockName.equalsIgnoreCase("b_1")){
			blockId = "4";
		}
		
		if(blockName.equalsIgnoreCase("b_2")){
			blockId = "2";
		}
		
		if(blockName.equalsIgnoreCase("b_3")){
			blockId = "6";
		}
		
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
	      

	   
		   public void onReceive(Context c, Intent intent) {
		   List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
		   
	        
	         if (mainWifiObj.isWifiEnabled() == false)
	         {
	        	 mainWifiObj.setWifiEnabled(true);
	         }  
	         results = mainWifiObj.getScanResults();
	         size = results.size();
	         Log.e("Size of scan", "Size = " + size);

	         if (size == size) {
	         for(int i = 0; i < size; i++){
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

	        // fillData();
	     
         
		} else {
          unregisterReceiver(wifiReciever); 
          Toast.makeText(ScanningActivity.this, "Failed to scan signal strength. This activity will be terminated.", 
        		  Toast.LENGTH_LONG).show();
          
          scanProgress.dismiss(); 
          ScanningActivity.this.finish();
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
               userId = "1";
               
               Log.i("Data", "name = " + ap_name + " rssi = " + ap_rssi + " bssid = " + ap_bssid + " speed = " + ap_speed);
               
    
               // Building Parameters
               List<NameValuePair> params = new ArrayList<NameValuePair>();
               params.add(new BasicNameValuePair("ap_name", ap_name));
               params.add(new BasicNameValuePair("ap_rssi", ap_rssi));
               params.add(new BasicNameValuePair("ap_bssid", ap_bssid));
               params.add(new BasicNameValuePair("ap_speed", ap_speed));
               params.add(new BasicNameValuePair("blockId", blockId));
               params.add(new BasicNameValuePair("floorId",floorId));
               params.add(new BasicNameValuePair("userId",userId));
               
               Log.e("data", "Block id at params = " + blockId);
               Log.e("data", "Floor id at params = " + floorId);
    
               // getting JSON Object
               // Note that create product url accepts POST method
               JSONObject json = jsonParser.makeHttpRequest(rootURL,
                       "POST", params);
    
               // check log cat for response
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
		for(int i = 0; i < size; i++){
			name = ap_name[i];
			rssi = ap_rssi[i];
			bssid = ap_bssid[i];
			speed = ap_speed[i];
			
			 Log.i("Fill Data", "name = " + ap_name[i] + " rssi = " + ap_rssi[i] + " bssid = " + ap_bssid[i] + " speed = " + ap_speed[i]);
			
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
 
             
		}
		scanProgress.setProgress(100);
		unregisterReceiver(wifiReciever);//stops the continuous scan 
        scanProgress.dismiss();
        ScanningActivity.this.finish();
        
	}
}

