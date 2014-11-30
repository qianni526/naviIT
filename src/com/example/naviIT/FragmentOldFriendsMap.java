package com.example.naviIT;
 
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naviIT.R;

 
public class FragmentOldFriendsMap   extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      private Boolean getCurrent;
 
      public FragmentOldFriendsMap()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            View view=inflater.inflate(R.layout.activity_friends,container, false);
            /**Button button1 = (Button) view.findViewById(R.id.button1);
            button1.setPaintFlags(button1.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            button1.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "Yi Ping is at MM4.", Toast.LENGTH_SHORT).show();
				}
			});
            Button button2 = (Button) view.findViewById(R.id.button2);
            button2.setPaintFlags(button2.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            button2.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(getActivity(), "Afif is at DK1.", Toast.LENGTH_SHORT).show();
				}
			});**/
            getCurrent = false;
            
            if(getCurrent){
            	ImageView imgFrens = (ImageView) view.findViewById(R.id.imgFriends);
                final TextView labelX = (TextView) view.findViewById(R.id.x);
                final TextView labelY = (TextView) view.findViewById(R.id.y);
    			
                ImageView icon1 = (ImageView) view.findViewById(R.id.icon1);
                ImageView icon2 = (ImageView) view.findViewById(R.id.icon2);
                ImageView icon3 = (ImageView) view.findViewById(R.id.icon3);
    			
                TextView txtCount1 = (TextView) view.findViewById(R.id.txtCount1);
                TextView txtCount2 = (TextView) view.findViewById(R.id.txtCount2);
                TextView txtCount3 = (TextView) view.findViewById(R.id.txtCount3);
    			
    			
    			txtCount1.setText("3");
    			txtCount2.setText("5");
    			txtCount3.setText("1");
    			
    			
    			imgFrens.setOnTouchListener(new ImageView.OnTouchListener(){

    				

    				@Override
    				public boolean onTouch(View v, MotionEvent event) {
    					//valueX = (int)event.getX();
    				   // valueY = (int)event.getY();
    				    switch (event.getAction()) {
    				        case MotionEvent.ACTION_DOWN:
    				        case MotionEvent.ACTION_MOVE:
    				        case MotionEvent.ACTION_UP:
    				        case MotionEvent.ACTION_HOVER_MOVE:
    				        	
    				    }
    				    
    				    //labelX.setText("X: " + (Integer.toString(valueX)));
    				   // labelY.setText("Y : " + (Integer.toString(valueY)));
    				    
    				    labelX.setVisibility(View.INVISIBLE);
    				    labelY.setVisibility(View.INVISIBLE);
    					return false;
    				}
    			});
    			
    			icon1.setOnClickListener(new ImageView.OnClickListener(){

    				@Override
    				public void onClick(View v) {
    					/**Intent locFeed = new Intent();
    					locFeed.setClass(FriendsActivity.this, SpecificLocationFeed.class);
    					startActivity(locFeed);**/
    					
    					View dialogView2 = getActivity().getLayoutInflater()
    							.inflate(R.layout.activity_list, null, false);
    					
    					ListView list = (ListView) dialogView2.findViewById(R.id.list);
    					
    					 String[] check_in_friends = {"Qianni", "Sinyee", "Asma"};
    					 
    					 
    			         
    				        // Binding resources Array to ListAdapter
    					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,check_in_friends);
    					 list.setAdapter(adapter);
    		
    					
    					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getActivity());
    			  		alertDialog2.setTitle("Friends at office");
    						alertDialog2.setView(dialogView2);
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
    					
    					View dialogView2 = getActivity().getLayoutInflater()
    							.inflate(R.layout.activity_list, null, false);
    					
    					ListView list = (ListView) dialogView2.findViewById(R.id.list);
    					
    					 String[] check_in_friends = {"Qianni", "Sinyee", "Ali", "Jason", "Asma"};
    					 
    					 
    			         
    				        // Binding resources Array to ListAdapter
    					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,check_in_friends);
    					 list.setAdapter(adapter);
    		
    					
    					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getActivity());
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
    					
    					View dialogView2 = getActivity().getLayoutInflater()
    							.inflate(R.layout.activity_list, null, false);
    					
    					ListView list = (ListView) dialogView2.findViewById(R.id.list);
    					
    					 String[] check_in_friends = {"Qianni"};
    					 
    					 
    			         
    				        // Binding resources Array to ListAdapter
    					 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item,check_in_friends);
    					 list.setAdapter(adapter);
    		
    					
    					final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getActivity());
    			  		alertDialog2.setTitle("Friends at BK1");
    						alertDialog2.setView(dialogView2);
    						alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
    							


    						public void onClick(DialogInterface dialog1, int which) {
    							
    							dialog1.cancel();
    								
    						}
    						
    						}).show();	
    					
    				}
    				
    			});
            }else{
            	final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        		alertDialog.setTitle("Alert");
      			alertDialog.setMessage("Your current location is not FSKTM.");
      			alertDialog.setPositiveButton("Ok",new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
      				
      			}).show();
      				
            }
            
            
			
			setHasOptionsMenu(true);
           
            return null;
      }
      
      @Override
      public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    	  super.onCreateOptionsMenu(menu, inflater);
          inflater.inflate(R.menu.friends_action, menu);
          
          
      }
      
      @SuppressWarnings("deprecation")
	public boolean onOptionsItemSelected(MenuItem item) {
         
          switch (item.getItemId()) {
          case R.id.action_check_in:
        	  getCurrent = false;
          	if(getCurrent){
          		checkIn();
          	}else{
          		//Toast.makeText(getActivity(), "You cannot check in since you at not at FSKTM", Toast.LENGTH_SHORT).show();
          		final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        		
        		//set the alert dialog
                alertDialog.setTitle("Alert");
                alertDialog.setCancelable(false);
                alertDialog.setMessage("You cannot check in since you at not at FSKTM");
                 
                //set alert dialog's button
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int which) {
                    	alertDialog.cancel();
                    	
                    }
                });
                alertDialog.show();
          	}
          	
              return true;
          case R.id.action_feed:
          	feed();
              return true;
          /**case R.id.action_settings:
              settings();
              return true;**/
          default:
              return super.onOptionsItemSelected(item);
          }
      }



  	private void feed() {
  		/**Intent feed = new Intent();
  		feed.setClass(FriendsActivity.this, LocationFeedsActivity.class);
  		startActivity(feed);**/
  		String floor = "";
  		
  		View dialogView = getActivity().getLayoutInflater()
  				.inflate(R.layout.choose_floor_dialog, null, false);
  		
  		final CheckBox radioGround = (CheckBox) dialogView.findViewById(R.id.radioGround);
  		final CheckBox radioFirst = (CheckBox) dialogView.findViewById(R.id.radioFirst);
  		final CheckBox radioSecond = (CheckBox) dialogView.findViewById(R.id.radioSecond);
  		
  		final AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(getActivity());
    		alertDialog2.setTitle("Choose a block");
  			alertDialog2.setView(dialogView);
  			alertDialog2.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
  				


  			public void onClick(DialogInterface dialog1, int which) {
  				
  				String data = "";
  				
  				if(radioGround.isChecked()){
  					data = "1";
  				}
  				
  				if(radioFirst.isChecked()){
  					data = "2";
  				}
  				
  				if(radioSecond.isChecked()){
  					data = "3";
  				}
  				
  				Intent feed = new Intent();
  				feed.setClass(getActivity(), MockLocationFeedsActivity.class);
  				feed.putExtra("data", data);
  				startActivity(feed);
  				
  					
  			}
  			
  			}).show();	
  		
  		
  		
  	}



  	private void settings() {
  	
  		/**Intent set = new Intent();
  		set.setClass(getActivity(), FriendsSettingsActivity.class);
  		startActivity(set);**/
  	}



  	private void checkIn() {
  		Intent check = new Intent();
  		check.setClass(getActivity(), CheckInActivity2.class);
  		startActivity(check);
  		
  		/**Intent check = new Intent();
  		check.setClass(FriendsActivity.this, CheckInActivity.class);
  		startActivity(check);**/
  	}
  	
 
}