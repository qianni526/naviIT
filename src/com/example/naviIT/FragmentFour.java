package com.example.naviIT;
 
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.naviIT.R;
 
public class FragmentFour extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      public FragmentFour()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            View view=inflater.inflate(R.layout.fragment_layout_four,container, false);
 
           
            return view;
      }
 
}