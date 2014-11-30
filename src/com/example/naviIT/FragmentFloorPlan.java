package com.example.naviIT;
 
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.naviIT.R;

 
public class FragmentFloorPlan   extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      private ViewPager viewPager;
 
      public FragmentFloorPlan()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
    	 View rootView = inflater.inflate(R.layout.fullscreen_pager, container, false);
		 	
		 	boolean ori = true;
		 	
  		viewPager = (ViewPager)rootView. findViewById(R.id.pager);
  		
  		//Intent i = getActivity().getIntent();
  		//int position = i.getIntExtra("position", 0);

  		
  			//LargerFullScreenImagePager adapter = new LargerFullScreenImagePager(getActivity(), getFilePaths());
  			//viewPager.setAdapter(adapter);
  		
  			FullScreenImagePager adapter = new FullScreenImagePager(getActivity());
  			viewPager.setAdapter(adapter);
  
  			
  		// displaying selected image first
  		//viewPager.setCurrentItem(position);
  			
  			return rootView;
      }
 
}