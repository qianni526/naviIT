package com.example.naviIT;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.Toast;
 
public class CustomOnItemSelectedListener implements OnItemSelectedListener {
 
    public void onItemSelected(AdapterView<?> parent, View view, int pos,
            long id) {
         
        /*Toast.makeText(parent.getContext(), 
                "On Item Select : \n" + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_LONG).show();
        */
    	//parent.getRootView().findViewById(R.id.imageView1);
    	
    	//ImageView iv1 = (ImageView) view.findViewById(R.id.imageView1);
    	ImageView iv1 = (ImageView) parent.getRootView().findViewById(R.id.imageView1);
    	ImageView iv2 = (ImageView) parent.getRootView().findViewById(R.id.imageView2);
    	ImageView iv3 = (ImageView) parent.getRootView().findViewById(R.id.imageView3);
    	ImageView iv4 = (ImageView) parent.getRootView().findViewById(R.id.imageView4);
    	if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Office")){
    		iv1.setVisibility(View.VISIBLE);
    		iv2.setVisibility(View.VISIBLE);
    		iv3.setVisibility(View.INVISIBLE);
    		iv4.setVisibility(View.INVISIBLE);
    	}else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Lecture Room 3 (BK3)")){
    		iv1.setVisibility(View.VISIBLE);
    		iv2.setVisibility(View.INVISIBLE);
    		iv3.setVisibility(View.VISIBLE);
    		iv4.setVisibility(View.INVISIBLE);
    	}else if(parent.getItemAtPosition(pos).toString().equalsIgnoreCase("Lecture Room 1 (BK1)")){
    		iv1.setVisibility(View.VISIBLE);
    		iv2.setVisibility(View.INVISIBLE);
    		iv3.setVisibility(View.INVISIBLE);	
    		iv4.setVisibility(View.VISIBLE);	
    	}else{
    		iv1.setVisibility(View.VISIBLE);
    		iv2.setVisibility(View.INVISIBLE);
    		iv3.setVisibility(View.INVISIBLE);	
    		iv4.setVisibility(View.INVISIBLE);
    	}
    	
    	
    	
    	
    }
 
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
 
    }
 
}