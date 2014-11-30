package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CheckInActivity2 extends Activity {
	
	private Spinner spinnerCheckIn;
	private EditText editCheckIn;
	private Button btnDone;
	
	private String item;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_in_2);
		
		spinnerCheckIn = (Spinner) findViewById(R.id.spinnerCheckIn);
		editCheckIn = (EditText) findViewById(R.id.editCheckIn);
		btnDone = (Button) findViewById(R.id.btnDone);
		
		// get action bar   
        ActionBar actionBar = getActionBar();
 
        // Enabling Up / Back navigation
        actionBar.setDisplayHomeAsUpEnabled(true);
        
     // Spinner Drop down elements
        List categories = new ArrayList();
        categories.add("Office");
        categories.add("BK1");
        categories.add("BK3");
        categories.add("SERL");
 
        // Creating adapter for spinner
        ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
 
        // attaching data adapter to spinner
        spinnerCheckIn.setAdapter(dataAdapter);
		
	
		
        
        // Spinner click listener
        spinnerCheckIn.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				 item = parent.getItemAtPosition(position).toString();
				 
			    editCheckIn.setText(item);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.i("Item", "Nothing selected from spinner");
				
			}
        	
        });
        
        btnDone.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				String result = editCheckIn.getText().toString();
				
				if(result.isEmpty() || result.equalsIgnoreCase(null)){
					Toast.makeText(CheckInActivity2.this, "Please choose one place", Toast.LENGTH_SHORT).show();
				}else{
					/**Log.i("Check in", "Finish Check in");
					Intent back = new Intent();
					back.setClass(CheckInActivity2.this, FriendsActivity.class);
					finish();**/
				}
				
			}
        	
        });
		
		
	}

}
