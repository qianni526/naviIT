package com.example.naviIT;
 
import java.util.List;


import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naviIT.R;
import com.example.naviIT.CollectFingerprintsPager.GetId;


 
public class FragmentFingerprint   extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      private ViewPager viewPager;
      private String blockName;
      
      private String curr;
      
      WifiManager mainWifiObj;
  	 
  	private ProgressDialog scanProgress, loadProgress;
  	
  	private boolean checkOne, checkTwo, checkThree, checkFour, checkFive, checkSix, checkSeven, checkEight, checkNine, checkTen;
  	
  	private double highest60, highest61;
  	
  	int defaultSize= 10;
  	int numScan = 1;
  	int counterScan;
  	
  	String referenceNumber;
  	String[] ap_name, ap_rssi, ap_bssid, ap_speed;
  	String name, rssi, bssid, speed;
  	
  	private int groundOne, groundTwo, groundThree, secondOne, secondTwo, secondThree, thirdOne, thirdTwo, extraOne, extraTwo;
  	private String bssidOne, bssidTwo, bssidThree, bssidFour, bssidFive, bssidSix, bssidSeven, bssidEight, bssidNine, bssidTen;
  	
  	
  	private int noOfScan;
  	
  	private int size;
  	
  	private boolean getCurrent;
  	
 
      public FragmentFingerprint()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            //View view=inflater.inflate(R.layout.fragment_layout_two,container, false);
 
           
           // return view;
    	  
    	 // Bundle data = getArguments();
    	 // curr = data.getString("curr");
    	  
    	  
    	  Bundle args = getArguments();
    	  if (args  != null && args.containsKey("curr")){
 
    		  curr = args.getString("curr");
    		  Log.i("curr", curr);
    		  
    		  curr = "0";
    		  
    		  if(curr.equalsIgnoreCase("1")){
    			  View rootView = inflater.inflate(R.layout.fullscreen_pager, container, false);
    	  		 	
     	   		 
    	  		 	
      	  		viewPager = (ViewPager)rootView. findViewById(R.id.pager);
      	  		
      	  		View dialogView = getActivity().getLayoutInflater()
      						.inflate(R.layout.choose_box_dialog, null, false);
      	  		
      	  		
      	  		
      	  		final CheckBox checkA = (CheckBox)dialogView.findViewById(R.id.checkA);
      	  		final CheckBox checkB = (CheckBox)dialogView.findViewById(R.id.checkB);
      	  		
      	  		
      	  		final AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(getActivity());
      	  		alertDialog1.setTitle("Choose a Block");
      				alertDialog1.setView(dialogView);
      				alertDialog1.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
      				
      				public void onClick(DialogInterface dialog, int which) {
      					
      						if(checkA.isChecked() && !checkB.isChecked()){
      								
      							
      								blockName = "a";
      								
      								dialog.cancel();
      								
      								CollectFingerprintsPager adapter = new CollectFingerprintsPager(getActivity(), blockName);
      								Log.i("Collect fingerprint", "Go to collect fingerprints");
      			        			viewPager.setAdapter(adapter);
      				
      							}
      							
      							if(checkB.isChecked() && !checkA.isChecked()){
      								Toast.makeText(getActivity(), "Under Construction", Toast.LENGTH_LONG).show();
      							
      							}
      							
      							if(checkA.isChecked() && checkB.isChecked()){
      								Toast.makeText(getActivity(), "Please choose only one.", Toast.LENGTH_LONG).show();
      							}
      							
      							if(!checkA.isChecked() && !checkB.isChecked()){
      								Toast.makeText(getActivity(), "Please choose one block to access.", Toast.LENGTH_LONG).show();
      							}
      							
      				}
      				}).show();	
    		  }else{
    			 
        		  final AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(getActivity());
    		  		alertDialog1.setTitle("Alert");
    					//alertDialog1.setView(dialogView);
    		  		alertDialog1.setMessage("Your current location is not FSKTM. So, you cannot collect fingerprints.");
    					alertDialog1.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
    					
    					public void onClick(DialogInterface dialog, int which) {
    						
    							
    					}
    					}).show();
    		  }
    		 
    		  
    	  				
    	  				
    	  		  		/**View dialogView = getActivity().getLayoutInflater()
    	  							.inflate(R.layout.choose_box_dialog, null, false);
    	  		  		
    	  		  		
    	  		  		
    	  		  		final CheckBox checkA = (CheckBox)dialogView.findViewById(R.id.checkA);
    	  		  		final CheckBox checkB = (CheckBox)dialogView.findViewById(R.id.checkB);*/
    	  		  		
    	  		  		
    	  		  		
    				
    		  
    	  }else{
    		  
    		  Log.e("curr", "curr = null");
    		  	
    	  }
    	 
    	  
    	 return null;
    	  
    	
      }
      
      
	@SuppressWarnings("deprecation")
	protected void generateAlertDialog() {
    	AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
    	alert.setTitle("Alert");
    	alert.setMessage("Your current location is not FSKTM. You cannot collect fingerprint.");
		alert.setButton2("Ok", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				getActivity().dismissDialog(which);
				
			}
			
		});
		
		alert.show();
		
	}


 
}