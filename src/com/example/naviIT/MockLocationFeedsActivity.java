package com.example.naviIT;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MockLocationFeedsActivity extends Activity {
	
	private String data;
	private boolean one, two, three;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent pass = getIntent();
		data = pass.getStringExtra("data");
		
		one = false;
		two = false;
		three = false;
		
		Log.i("data in mock", "data = " + data);
		
		if(data.equalsIgnoreCase("1")){
			setContentView(R.layout.activity_friends);
			one = true;
		}
		
		if(data.equalsIgnoreCase("2")){
			setContentView(R.layout.activity_mock);
			two = true;
		}
		
		if(data.equalsIgnoreCase("3")){
			setContentView(R.layout.activity_mock2);
			three = true;
		}
		
		 ActionBar actionBar = getActionBar();
		 
	     actionBar.setDisplayHomeAsUpEnabled(true);
	     
	     
	     if(one){
	    	 TextView txtCount1 = (TextView) findViewById(R.id.txtCount1);
	    	 TextView txtCount2 = (TextView) findViewById(R.id.txtCount2);
	    	 TextView txtCount3 = (TextView) findViewById(R.id.txtCount3);
	    	 
	    	 ImageView icon1 = (ImageView) findViewById(R.id.icon1);
	    	 ImageView icon2 = (ImageView) findViewById(R.id.icon2);
	    	 ImageView icon3 = (ImageView) findViewById(R.id.icon3);
	    	 
	    	 txtCount1.setText("3");
	 		 txtCount2.setText("5");
	 		 txtCount3.setText("1");
	 		 
	 		icon1.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					/**Intent locFeed = new Intent();
					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
					startActivity(locFeed);**/
					
					View dialogView = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_list, null, false);
					
					ListView list = (ListView) dialogView.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Qianni", "Sinyee", "Asma"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at office");
						alertDialog2.setView(dialogView);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});
	 		icon2.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					/**Intent locFeed = new Intent();
					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
					startActivity(locFeed);**/
					
					View dialogView2 = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_list, null, false);
					
					ListView list = (ListView) dialogView2.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Qianni", "Sinyee", "Ali", "Jason", "Asma"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at BK3");
						alertDialog2.setView(dialogView2);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});
			
			icon3.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					/**Intent locFeed = new Intent();
					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
					startActivity(locFeed);**/
					
					View dialogView3 = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_list, null, false);
					
					ListView list = (ListView) dialogView3.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Qianni"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at BK1");
						alertDialog2.setView(dialogView3);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});
	    	 
	    	 
	     }
	     
	     if(two){
	    	 TextView txtCount1 = (TextView) findViewById(R.id.txtCount1);
	    	 TextView txtCount2 = (TextView) findViewById(R.id.txtCount2);
	    	 TextView txtCount3 = (TextView) findViewById(R.id.txtCount3);
	    	 
	    	 ImageView icon1 = (ImageView) findViewById(R.id.icon1);
	    	 ImageView icon2 = (ImageView) findViewById(R.id.icon2);
	    	 ImageView icon3 = (ImageView) findViewById(R.id.icon3);
	    	 
	    	 txtCount1.setText("2");
	 		 txtCount2.setText("3");
	 		 txtCount3.setText("1");
	 		 
	 		icon1.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					/**Intent locFeed = new Intent();
					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
					startActivity(locFeed);**/
					
					View dialogView = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_main, null, false);
					
					ListView list = (ListView) dialogView.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Qianni", "Sinyee"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at CCNA Lab");
						alertDialog2.setView(dialogView);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});
	 		icon2.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					/**Intent locFeed = new Intent();
					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
					startActivity(locFeed);**/
					
					View dialogView2 = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_main, null, false);
					
					ListView list = (ListView) dialogView2.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Qianni", "Sinyee", "Asma"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at library");
						alertDialog2.setView(dialogView2);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});
			
			icon3.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					/**Intent locFeed = new Intent();
					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
					startActivity(locFeed);**/
					
					View dialogView3 = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_main, null, false);
					
					ListView list = (ListView) dialogView3.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Qianni"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at Micro Lab 1");
						alertDialog2.setView(dialogView3);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});
	    	 
	     }
	     
	     if(three){
	    	 TextView txtCount2 = (TextView) findViewById(R.id.txtCount2);
	    	 TextView txtCount3 = (TextView) findViewById(R.id.txtCount3);
	    	 
	    	 ImageView icon2 = (ImageView) findViewById(R.id.icon2);
	    	 ImageView icon3 = (ImageView) findViewById(R.id.icon3);
	    	 
	    	 txtCount2.setText("1");
	 		 txtCount3.setText("1");
	 		 
	 		 txtCount3.setVisibility(View.INVISIBLE);
	 		 icon3.setVisibility(View.INVISIBLE);
	 		 
	 		icon2.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					/**Intent locFeed = new Intent();
					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
					startActivity(locFeed);**/
					
					View dialogView2 = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_main, null, false);
					
					ListView list = (ListView) dialogView2.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Sinyee"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at Neural Network Research Lab");
						alertDialog2.setView(dialogView2);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});
			
			/**icon3.setOnClickListener(new ImageView.OnClickListener(){

				@Override
				public void onClick(View v) {
					
					View dialogView3 = MockLocationFeedsActivity.this.getLayoutInflater()
							.inflate(R.layout.activity_main, null, false);
					
					ListView list = (ListView) dialogView3.findViewById(R.id.list);
					
					 String[] check_in_friends = {"Qianni"};
					 
					 
			         
				        // Binding resources Array to ListAdapter
					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(MockLocationFeedsActivity.this,R.layout.list_item,check_in_friends);
					 list.setAdapter(adapter);
		
					
					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(MockLocationFeedsActivity.this);
			  		alertDialog2.setTitle("Friends at Micro Lab 1");
						alertDialog2.setView(dialogView3);
						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
							


						public void onClick(DialogInterface dialog1, int which) {
							
							dialog1.cancel();
								
						}
						
						}).show();	
					
				}
				
			});**/
	     }
	     
		
	}

}
