package com.example.naviIT;

import java.util.HashMap;

import com.example.naviIT.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AbstractNavDrawerActivity {
	
	 // Session Manager Class
    SessionManager session;
    MenuItem menuItemLogin;
	boolean hide=false;
	LinearLayout drawerChild;
	ImageView profileImage;
	TextView profileName;
	TextView profileRole;
     
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
        
		 drawerChild = (LinearLayout) findViewById(R.id.drawerChild);
         profileImage = (ImageView) findViewById(R.id.profileImage);
         profileName = (TextView) findViewById(R.id.profileName);
         profileRole = (TextView) findViewById(R.id.profileRole);
		 
	    	if(session.isLoggedIn()){

	    		//here got probleammmm!!!!!null
		        drawerChild.setVisibility(View.VISIBLE);
	    		profileImage.setImageResource(R.drawable.ic_action_person);
	    		
	    		// get user data from session
	            HashMap<String, String> user = session.getUserDetails();
	             
	            // name
	            String name = user.get(SessionManager.KEY_NAME);
	             
	            profileName.setText(name);
	            
	            // password
	            String isAdmin = user.get(SessionManager.IS_ADMIN);
	            
		            if(isAdmin.equals("Admin")){
		            	
		            	profileRole.setVisibility(View.VISIBLE);
		            	
		            }
		            
	    	}
		 
        if ( savedInstanceState == null ) {
        	getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentNavigation(), "Navigation").commit();
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
	public boolean onCreateOptionsMenu(Menu menu) {   	
    	// Inflate the menu items for use in the action bar
		
		MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        
        if (hide == true)
        {
            for (int i = 0; i < menu.size(); i++)
                menu.findItem(R.id.action_login).setVisible(false);
        }
        
		return super.onCreateOptionsMenu(menu);
	}
	
    @Override
    protected NavDrawerActivityConfiguration getNavDrawerConfiguration() {
    	
    	NavDrawerItem[] menu=null;
    	
    	// Session class instance
        session = new SessionManager(getApplicationContext());
        
    	if(session.isLoggedIn()){

    		hide = true; // setting state
    		invalidateOptionsMenu(); // now onCreateOptionsMenu(...) is called again
    		
    		// get user data from session
            HashMap<String, String> user = session.getUserDetails();
             
            // name
            String name = user.get(SessionManager.KEY_NAME);
             
            // password
            String isAdmin = user.get(SessionManager.IS_ADMIN);
            
	            if(isAdmin.equals("Admin")){
	            	
	            	menu = new NavDrawerItem[] {
	            			 NavMenuItem.create(101,"Online Navigation", R.drawable.ic_action_location_found, true, this),
	 	                    NavMenuItem.create(102, "Offline Navigation", R.drawable.ic_action_directions, true, this), 
	 	                    NavMenuItem.create(103, "Building Overview", R.drawable.ic_action_search_white, true, this), 
	 	                    NavMenuItem.create(104, "Floor plan", R.drawable.ic_action_map, true, this), 
	 	                    NavMenuItem.create(105, "Events", R.drawable.ic_action_event, true, this), 
	 	                    NavMenuItem.create(106, "Friends Map", R.drawable.ic_action_group, true, this), 
	                        NavMenuSection.create(200, "Admin"),
	                        NavMenuItem.create(201, "Fingerprint collector", R.drawable.ic_action_labels, true, this),
	                        NavMenuItem.create(202, "QR code generator", R.drawable.ic_action_import_export, true, this), 
	                        NavMenuItem.create(203, "Building Overview Setting", R.drawable.ic_action_settings, true, this),
	            			NavMenuItem.create(204, "Log out", R.drawable.ic_action_logout, false, this)};       
	            	
	            }else{
	            	menu = new NavDrawerItem[] {
	                    NavMenuItem.create(101,"Online Navigation", R.drawable.ic_action_location_found, true, this),
	                    NavMenuItem.create(102, "Offline Navigation", R.drawable.ic_action_directions, true, this), 
	                    NavMenuItem.create(103, "Building Overview", R.drawable.ic_action_search_white, true, this), 
	                    NavMenuItem.create(104, "Floor plan", R.drawable.ic_action_map, true, this), 
	                    NavMenuItem.create(105, "Events", R.drawable.ic_action_event, true, this), 
	                    NavMenuItem.create(106, "Friends Map", R.drawable.ic_action_group, true, this), 
	                    NavMenuItem.create(204, "Log out", R.drawable.ic_action_logout, false, this)}; 
	            }
	            
    	}else if(session.isLoggedIn()==false){
    		hide = false; // setting state
    		invalidateOptionsMenu(); // now onCreateOptionsMenu(...) is called again	
    		
    		menu = new NavDrawerItem[] {
    				 NavMenuItem.create(101,"Online Navigation", R.drawable.ic_action_location_found, true, this),
	                    NavMenuItem.create(102, "Offline Navigation", R.drawable.ic_action_directions, true, this), 
	                    NavMenuItem.create(103, "Building Overview", R.drawable.ic_action_search_white, true, this), 
	                    NavMenuItem.create(104, "Floor plan", R.drawable.ic_action_map, true, this), 
	                    NavMenuItem.create(105, "Events", R.drawable.ic_action_event, true, this), 
	                    NavMenuItem.create(106, "Friends Map", R.drawable.ic_action_group, true, this)}; 
    		}
   			
        NavDrawerActivityConfiguration navDrawerActivityConfiguration = new NavDrawerActivityConfiguration();
        navDrawerActivityConfiguration.setMainLayout(R.layout.activity_main);
        navDrawerActivityConfiguration.setDrawerLayoutId(R.id.drawer_layout);
        navDrawerActivityConfiguration.setLinearLayoutId(R.id.drawer);
        navDrawerActivityConfiguration.setLeftDrawerId(R.id.left_drawer);
        navDrawerActivityConfiguration.setNavItems(menu);
        navDrawerActivityConfiguration.setDrawerShadow(R.drawable.drawer_shadow);       
        navDrawerActivityConfiguration.setDrawerOpenDesc(R.string.drawer_open);
        navDrawerActivityConfiguration.setDrawerCloseDesc(R.string.drawer_close);
        navDrawerActivityConfiguration.setBaseAdapter(
            new CustomDrawerAdapter(this, R.layout.custom_drawer_item, menu ));

        return navDrawerActivityConfiguration;
    }
    
	    public void newMainActivity(){
	  		Intent intent = new Intent(this, MainActivity.class);
	  		startActivity(intent);
	  	}
	      
        public void openLoginPage(){
    		getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentLogin(), "Login").commit();
    	}
/*
        @Override
        public void onBackPressed() {
        	
            int count = getSupportFragmentManager().getBackStackEntryCount();

            if (count == 0) {
                super.onBackPressed();
                //additional code
            } else {
                getSupportFragmentManager().popBackStack();
                
                if(getSupportFragmentManager().findFragmentByTag("Navigation").getTag().equals("Navigation")){                	
                	getActionBar().setTitle("Navigation");
                }else if(getSupportFragmentManager().findFragmentByTag("Building Overview").getTag().equals("Building Overview")){                	
                	getActionBar().setTitle("Building Overview");
                }else if(getSupportFragmentManager().findFragmentByTag("Floor Plan").getTag().equals("Floor Plan")){                	
                	getActionBar().setTitle("Floor Plan");
                }else if(getSupportFragmentManager().findFragmentByTag("Events").getTag().equals("Events")){                	
                	getActionBar().setTitle("Events");
                }else if(getSupportFragmentManager().findFragmentByTag("Friends Map").getTag().equals("Friends Map")){                	
                	getActionBar().setTitle("Friends Map");
                }else if(getSupportFragmentManager().findFragmentByTag("Fingerprint Collector").getTag().equals("Fingerprint Collector")){                	
                	getActionBar().setTitle("Fingerprint Collector");
                }else if(getSupportFragmentManager().findFragmentByTag("QR Code Generator").getTag().equals("QR Code Generator")){                	
                	getActionBar().setTitle("QR Code Generator");
                }else if(getSupportFragmentManager().findFragmentByTag("Building Overview Setting").getTag().equals("Building Overview Setting")){                	
                	getActionBar().setTitle("Building Overview Setting");
                }else{
                	getActionBar().setTitle("naviIT");
                }
            }

        }
        
*/
		@Override
		protected void onNavItemSelected(int id) {
			// TODO Auto-generated method stub

		        switch ((int) id) {
			        case 101:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentNavigation(), "Navigation").commit();
			            break;
			        case 102:
			           getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentOfflineNavigation(), "OfflineNavigation").commit();
			            break;
			        case 103:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentBuildingOverview(), "Building Overview").commit();
			            break;
			        case 104:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentFloorPlan(), "Floor Plan").commit();
			            break;
			        case 105:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentEvent(), "Events").commit();
			            break;
			        case 106:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentFriendsMap(), "Friends Map").commit();
			            break;
			        case 201:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentFingerprint(), "Fingerprint Collector").commit();
			            break;
			        case 202:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentQRgenerator(), "QR Code Generator").commit();
			            break;
			        case 203:
			            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentSetting(), "Building Overview Setting").commit();
			            break;	            
			        case 204:
			        	session.logoutUser();
			        	newMainActivity();
			        	break;	            
		        }
		}

		@Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
	            fragment.onActivityResult(requestCode, resultCode, data);
	        }
	    }
		
}
