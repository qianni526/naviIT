package com.example.naviIT;
 
import android.app.AlertDialog;

import android.support.v4.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.naviIT.R;

 
public class FragmentBuildingOverview   extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      
      private ViewPager viewPager;
 
      public FragmentBuildingOverview()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
           // View view=inflater.inflate(R.layout.fragment_layout_two,container, false);
 
           
           // return view;
    	  
    	 View rootView = inflater.inflate(R.layout.fullscreen_pager, container, false);
		 	
		 	boolean ori = true;
		 	
  		viewPager = (ViewPager)rootView. findViewById(R.id.pager);
  		
  		View dialogView = getActivity().getLayoutInflater()
					.inflate(R.layout.choose_box_dialog, null, false);
  		
  		final CheckBox checkA = (CheckBox)dialogView.findViewById(R.id.checkA);
  		final CheckBox checkB = (CheckBox)dialogView.findViewById(R.id.checkB);
  		
  		new AlertDialog.Builder(getActivity())
			.setTitle("Choose a Block")
			.setView(dialogView)
			.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				
						if(checkA.isChecked() && !checkB.isChecked()){
							//StaticScreenImagePager adapter = new StaticScreenImagePager(getActivity());
							InfoPlanScreenImagePager adapter = new InfoPlanScreenImagePager(getActivity());
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

			
			})
			//.setNegativeButton("Cancel",null)
			.show();
  		
  			
  			
  		// displaying selected image first
  		//viewPager.setCurrentItem(position);
  		
  		return rootView;
      }
 
}