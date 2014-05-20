package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.naviIT.R;
 
public class FragmentOne extends Fragment {
 
      ImageView ivFloorplan;
      TextView tvItemName;
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
      private Spinner spinner1;
      //private Button btnSubmit;
 
      public FragmentOne() {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {   	  	
    	  
            View view = inflater.inflate(R.layout.fragment_layout_one, container,
                        false);
            
            ivFloorplan = (ImageView) view.findViewById(R.id.iVfloorplan);
                    
            spinner1 = (Spinner) view.findViewById(R.id.spinner1);
            //btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
            List<String> list = new ArrayList<String>();
            list.add("Foyer");
            list.add("Office");
            list.add("Lecture Room 1 (BK1)");
            list.add("Lecture Room 3 (BK3)");
            list.add("SERL");
             
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                         (getActivity(), android.R.layout.simple_spinner_item,list);
                          
            dataAdapter.setDropDownViewResource
                         (android.R.layout.simple_spinner_dropdown_item);
                          
            spinner1.setAdapter(dataAdapter);
             
            // Spinner item selection Listener  
            addListenerOnSpinnerItemSelection();
             
            // Button click Listener 
            //addListenerOnButton();
             
            return view;
     
        }
     
        // Add spinner data
         
        public void addListenerOnSpinnerItemSelection(){
             
                    spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        }
         
        //get the selected dropdown list value
        /* 
        public void addListenerOnButton() {
     
        	btnSubmit.setOnClickListener(new OnClickListener() {
        	 
     
                @Override
                public void onClick(View v) {
     
                    Toast.makeText(getActivity(),
                            "On Button Click : " + 
                            "\n" + String.valueOf(spinner1.getSelectedItem()) ,
                            Toast.LENGTH_LONG).show();
                }
     
            });

      }
      */
 
}