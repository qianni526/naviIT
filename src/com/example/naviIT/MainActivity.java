package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends Activity{
		 
      private DrawerLayout mDrawerLayout;
      private ListView mDrawerList;
      private ActionBarDrawerToggle mDrawerToggle;
 
      private CharSequence mDrawerTitle;
      private CharSequence mTitle;
      CustomDrawerAdapter adapter;
 
      List<DrawerItem> dataList;
      
      @Override
      protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
 
            Log.d("Message", "go into main activity");
            
            // Initializing
            dataList = new ArrayList<DrawerItem>();
            mTitle = mDrawerTitle = getTitle();
            mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            mDrawerList = (ListView) findViewById(R.id.left_drawer);
 
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
                        GravityCompat.START);
            
         // Add Drawer Item to dataList
        dataList.add(new DrawerItem("Map", R.drawable.ic_action_directions));
        dataList.add(new DrawerItem("Building Overview", R.drawable.ic_action_map));
        dataList.add(new DrawerItem("Events", R.drawable.ic_action_event));
        dataList.add(new DrawerItem("Scan QR code", R.drawable.ic_action_labels));
        dataList.add(new DrawerItem("Friends Map", R.drawable.ic_action_group));
        dataList.add(new DrawerItem("Log Out", R.drawable.ic_action_logout));
       
	            adapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item,
                        dataList);
 
	            mDrawerList.setAdapter(adapter);
	            mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
	            
	            getActionBar().setDisplayHomeAsUpEnabled(true);
	            getActionBar().setHomeButtonEnabled(true);
	             
	            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                        R.drawable.ic_drawer, R.string.drawer_open,
	                        R.string.drawer_close) {
	                  public void onDrawerClosed(View view) {
	                        getActionBar().setTitle(mTitle);
	                        invalidateOptionsMenu(); // creates call to
                                                              // onPrepareOptionsMenu()
              }
         
              public void onDrawerOpened(View drawerView) {
                    getActionBar().setTitle(mDrawerTitle);
                    invalidateOptionsMenu(); // creates call to
                                                              // onPrepareOptionsMenu()
              }
        };
         
        mDrawerLayout.setDrawerListener(mDrawerToggle);
         
        if (savedInstanceState == null) {
              SelectItem(0);
        }
        
      }
  
	  @Override
	  public void setTitle(CharSequence title) {
	        mTitle = title;
	        getActionBar().setTitle(mTitle);
	  }
	   
	  @Override
	  protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        // Sync the toggle state after onRestoreInstanceState has occurred.
	        mDrawerToggle.syncState();
	  }
	   
	  @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		  int id = item.getItemId();
	        if (id == R.id.action_login) {
	        	newLoginIntent();
	        }

		  
		  // The action bar home/up action should open or close the drawer.
	        // ActionBarDrawerToggle will take care of this.
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	              return true;
	        }
	   
	        return false;
	  }
	   
	  @Override
	  public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        // Pass any configuration change to the drawer toggles
	            mDrawerToggle.onConfigurationChanged(newConfig);
	      }
	      
      private class DrawerItemClickListener implements
      		ListView.OnItemClickListener {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          SelectItem(position);
      }
  }
      
      @Override
      public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
	            getMenuInflater().inflate(R.menu.main, menu);
	            
	            //set button in action bar to invisible
	            menu.getItem(0).setVisible(false);
	            return true;
	      }
	      
	      public void SelectItem(int possition) {
         	 
              Fragment fragment = null;
              Bundle args = new Bundle();
              switch (possition) {
              case 0:
                    fragment = new FragmentOne();
                    args.putString(FragmentOne.ITEM_NAME, dataList.get(possition)
                                .getItemName());
                    args.putInt(FragmentOne.IMAGE_RESOURCE_ID, dataList.get(possition)
                                .getImgResID());
                    break;
              case 1:
                    fragment = new FragmentTwo();
                    args.putString(FragmentTwo.ITEM_NAME, dataList.get(possition)
                                .getItemName());
                    args.putInt(FragmentTwo.IMAGE_RESOURCE_ID, dataList.get(possition)
                                .getImgResID());
                    break;
              case 2:
                    fragment = new FragmentThree();
                    args.putString(FragmentThree.ITEM_NAME, dataList.get(possition)
                                .getItemName());
                    args.putInt(FragmentThree.IMAGE_RESOURCE_ID, dataList.get(possition)
                                .getImgResID());
                    break;
              case 3:
            	  //Scan QR code
            	  fragment = new FragmentFour();
            	  args.putString(FragmentFour.ITEM_NAME, dataList.get(possition)
                          .getItemName());
            	  args.putInt(FragmentFour.IMAGE_RESOURCE_ID, dataList.get(possition)
                          .getImgResID());
                  break;
              case 4:
            	  //Friends Map
            	  fragment = new FragmentFive();
            	  args.putString(FragmentFive.ITEM_NAME, dataList.get(possition)
                          .getItemName());
              args.putInt(FragmentFive.IMAGE_RESOURCE_ID, dataList.get(possition)
                          .getImgResID());
                  break;   
              case 5:
                    newLoginIntent();
                    finish();
                    return;
			default:
                    break;
              }
   
          fragment.setArguments(args);
          FragmentManager frgManager = getFragmentManager();
          frgManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
   
          mDrawerList.setItemChecked(possition, true);
          setTitle(dataList.get(possition).getItemName());
          mDrawerLayout.closeDrawer(mDrawerList);
	      
	  }
	      
	      public void newLoginIntent(){
	  		Intent intent = new Intent(this, LoginActivity.class);
	  		startActivity(intent);
	  	}
	      
	      
}
