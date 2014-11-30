package com.example.naviIT;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class FullScreenImagePager extends PagerAdapter {
 
    private Activity _activity;
    private ArrayList<String> _imagePaths;
    private ArrayList<Integer> testImage;
    private LayoutInflater inflater;
    
    
 
    // constructor
    /**public FullScreenImagePager(Activity activity,
            ArrayList<Integer> testImage) {
        this._activity = activity;
        //this._imagePaths = imagePaths;
        this.testImage = testImage;
    }*/
 
    public FullScreenImagePager(Activity activity) {
        this._activity = activity;
        testImage = new ArrayList();
        testImage.add(R.drawable.plain_a_1);
        testImage.add(R.drawable.plain_a_2);
        testImage.add(R.drawable.plain_a_3);
        testImage.add(R.drawable.b_1);
        testImage.add(R.drawable.b_2);
        testImage.add(R.drawable.b_3);
        
    }
 
    @Override
    public int getCount() {
        //return this._imagePaths.size();
    	return this.testImage.size();
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TouchImageView imgDisplay;
        Button btnClose;
        //WebView webImage;
        //LargerImageView imgDisplay;
        
       
       
  
        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      View viewLayout = inflater.inflate(R.layout.full_image, container, false);
        //View viewLayout = inflater.inflate(R.layout.test_image, container, false);
  
        imgDisplay = (TouchImageView) viewLayout.findViewById(R.id.imgDisplay);
        //imgDisplay.setBackgroundResource(R.drawable.background_a);
        //imgDisplay = (LargerImageView) viewLayout.findViewById(R.id.imgDisplay);
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);
       // webImage = (WebView) viewLayout.findViewById(R.id.webImage);
        
        btnClose.setVisibility(View.INVISIBLE);
         
        /**BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(_imagePaths.get(position), options);
        imgDisplay.setImageBitmap(bitmap);*/
        
       // int x = _activity.getResourceId(testImage.get(position));
        imgDisplay.setImageResource(testImage.get(position));
        
         
        // close button click event
        btnClose.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
                _activity.finish();
            }
        });
  
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
    }
     
    class Tester extends Activity{
    	
    	public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
  
            }
    	
    	public int getImage(int position){
    		
    		int x = this.getResources().getIdentifier("ic_tree", "drawable", this.getPackageName());
			return position;
    		
    	}
    	
    }
    
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
  
    }
}