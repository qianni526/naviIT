package com.example.naviIT;
 
import android.app.Fragment;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.naviIT.R;
 
public class FragmentFive   extends Fragment {
 
      public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      public FragmentFive()
      {
 
      }
 
      @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            View view=inflater.inflate(R.layout.fragment_layout_five,container, false);
            Button button1 = (Button) view.findViewById(R.id.button1);
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
			});
           
            return view;
      }
 
}