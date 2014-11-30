package com.example.naviIT;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FragmentFriendsMap_Map extends Fragment {
	
	TextView textview;
	Button button;
	 
	  @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
          
	  	  View view = inflater.inflate(R.layout.fragfriendsmap_map, container, false);
          textview = (TextView) view.findViewById(R.id.textView);
          textview.setText("Map");
          Button button = (Button) view.findViewById(R.id.button1);
          button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textview.setText("Hi");
			}
		});
          return view;
  	  }
  
	  @Override
	  public void onCreate(Bundle savedInstanceState)
	  {
	      super.onCreate(savedInstanceState);
	  }

	
	  @Override
	  public void onActivityCreated(Bundle savedInstanceState)
	  {
	      super.onActivityCreated(savedInstanceState);
	  }
	
	  @Override
	  public void onAttach(Activity activity)
	  {
	      super.onAttach(activity);
	  }
	
	  @Override
	  public void onStart()
	  {
	      super.onStart();
	  }
	
	  @Override
	  public void onResume()
	  {
	      super.onResume();
	  }
}