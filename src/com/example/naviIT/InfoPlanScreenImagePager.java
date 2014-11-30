package com.example.naviIT;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;








import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;























import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InfoPlanScreenImagePager extends PagerAdapter {
 
    private Activity _activity;
    
    private LayoutInflater inflater;
    
    int valueX, valueY;
    int finalPosition;
   
    private static String KEY_SUCCESS = "success";
	private static String KEY_ID = "lid";
	private static String KEY_NAME = "location_name";
	private static String KEY_DISPLAY_NAME = "display_location_name";
	private static String KEY_DESCRIPTION = "location_description";
	private static String KEY_LOCATION ="location";
	private static String KEY_IMAGES ="images";
	private static String KEY_EID = "eid";
	private static String KEY_PATH = "img_path";
	
	private String location_name, location_display_name, location_description;
	private String id, name, description; //final 
	
	private JSONArray location;
	private JSONArray images;
	
	private String eid, path;
	
	JSONParser jparser = new JSONParser();
	private ProgressDialog progress;
	
	private static String locationURL = "http://navifsktm.comule.com/get_location_info.php";
	private static String imageURL = "http://navifsktm.comule.com/get_location_image.php";
	
	private String finalLocation;
	
	private Runnable myRunner;
	private Handler myHandler;
	
	private int intDuration, currentSize, imageSize;
	
	private TextView labelLocationName; 
	private TextView labelLocationDescription; 
	private ImageView labelLocationImage; 
	private ImageButton btnStaticPrev; 
	private ImageButton btnStaticNext; 
	
	private ImageView bigImage;
	
	private int imageLocation;
	
	private LruCache<String, Bitmap> mMemoryCache;

	private ArrayList<Integer> testImage;
	
	private Bitmap img_bitmap;
    
    public InfoPlanScreenImagePager(Activity activity) {
        this._activity = activity;
        
        testImage = new ArrayList(5);
        testImage.add(R.drawable.background_a);
        //testImage.add(R.drawable.radio_map1);
        
    }
 
    @Override
    public int getCount() {
    	return this.testImage.size();
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        Button btnClose;
        LargerImageView imgDisplay;
        final TextView staticX;
		final TextView staticY;
  
        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.test_image, container, false);
  
        imgDisplay = (LargerImageView) viewLayout.findViewById(R.id.imgDisplay1);
        btnClose = (Button) viewLayout.findViewById(R.id.btnClose);
        staticX = (TextView) viewLayout.findViewById(R.id.staticX);
        staticY = (TextView) viewLayout.findViewById(R.id.staticY);
        
        staticX.setVisibility(View.INVISIBLE);
        staticY.setVisibility(View.INVISIBLE);
         
       
        imgDisplay.setImageResource(testImage.get(position));
        Log.i("Position", "Position = " + position);
        finalPosition = position;
         
      
        btnClose.setOnClickListener(new View.OnClickListener() {            
            @Override
            public void onClick(View v) {
               // _activity.finish();
            	Toast.makeText(_activity, "Under Construction", Toast.LENGTH_SHORT).show();
            }
        });
        
        imgDisplay.setOnTouchListener(new LargerImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				valueX = (int)event.getX();
			    valueY = (int)event.getY();
			    
			    switch (event.getAction()) {
		        case MotionEvent.ACTION_DOWN:
		        case MotionEvent.ACTION_MOVE:
		        case MotionEvent.ACTION_UP:
		        case MotionEvent.ACTION_HOVER_MOVE:
		    }
			    
			    staticX.setText("X: " + (Integer.toString(valueX)));
			    staticY.setText("Y : " + (Integer.toString(valueY)));
			    
			    
			    switch (position){
			    
			    case 0: 
			    	//office
			    	 if(valueX >= 14 && valueX <= 147 && valueY >= 121 && valueY <= 292){
					    	//Toast.makeText(_activity, "User tapped Office", Toast.LENGTH_SHORT).show();
					    	finalLocation = "office";					    
					    	new LoadLocation().execute();
					    	
					    }
					    
					    //office
					    if(valueX >= 148 && valueX <= 328 && valueY >= 122 && valueY <= 259){
					    	//Toast.makeText(_activity, "User tapped Office", Toast.LENGTH_SHORT).show();
					    	finalLocation = "office";
					    	new LoadLocation().execute();
					    	
					    	
					    }
					    
					    //office
					    if(valueX >= 329 && valueX <= 458 && valueY >= 121 && valueY <= 293){
					    	//Toast.makeText(_activity, "User tapped Office", Toast.LENGTH_SHORT).show();
					    	finalLocation = "office";
					    	new LoadLocation().execute();
					    	
					    	
					    }
					    
					    //foyer
					    if(valueX >= 149 && valueX <= 329 && valueY >= 259 && valueY <= 413){
					    	//Toast.makeText(_activity, "User tapped foyer", Toast.LENGTH_SHORT).show();
					    	finalLocation = "foyer";
					    	new LoadLocation().execute();
					    	
					    	
					    }
					    
					    //bk3
					    if(valueX >= 8 && valueX <= 148 && valueY >= 320 && valueY <= 488){
					    	//Toast.makeText(_activity, "User tapped BK3", Toast.LENGTH_SHORT).show();
					    	finalLocation = "lectureRoom3";
					    	new LoadLocation().execute();
					    
					    }
					    
					    //bk1
					    if(valueX >= 328 && valueX <= 465 && valueY >= 325 && valueY <= 486){
					    	//Toast.makeText(_activity, "User tapped BK1", Toast.LENGTH_SHORT).show();
					    	finalLocation = "lectureRoom1";
					    	new LoadLocation().execute();
					    	
					    	
					    }
					    
					    //toilet
					    if(valueX >= 9 && valueX <= 76 && valueY >= 489 && valueY <= 565){
					    	//Toast.makeText(_activity, "User tapped toilet", Toast.LENGTH_SHORT).show();
					    	Toast.makeText(_activity, "No info available", Toast.LENGTH_SHORT).show();
					    	
					    }
					    
					    //toilet
					    if(valueX >= 75 && valueX <= 144 && valueY >= 507 && valueY <= 564){
					    	//Toast.makeText(_activity, "User tapped toilet", Toast.LENGTH_SHORT).show();
					    	Toast.makeText(_activity, "No info available", Toast.LENGTH_SHORT).show();
					    	
					    }
					    
					    //main entrance
					    if(valueX >= 212 && valueX <= 264 && valueY >= 539 && valueY <= 552){
					    	//Toast.makeText(_activity, "User tapped main entrance", Toast.LENGTH_SHORT).show();
					    	Toast.makeText(_activity, "No info available", Toast.LENGTH_SHORT).show();
					    	
					    }
					    
					    //sel
					    if(valueX >= 327 && valueX <= 465 && valueY >= 489 && valueY <= 564){
					    	//Toast.makeText(_activity, "User tapped Software Engineering Lab", Toast.LENGTH_SHORT).show();
					    	finalLocation = "softwareEngineeringLab";
					    	new LoadLocation().execute();
					    	
					    	
					    }
					    break;
					    
			    case 1:
			    	Toast.makeText(_activity, "Under Construction", Toast.LENGTH_SHORT).show();
			        break;
			        
			        default:
			        	Toast.makeText(_activity, "Not yet done", Toast.LENGTH_SHORT).show();
			    }
			    	
			    
			  
			    
				return false;
			   
			    

			};
        });
        
        
	    
        
  
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
    }
     
   
	@SuppressWarnings("deprecation")
	protected void generateDialog(String name, String description) {
		
		View dialogView = _activity.getLayoutInflater()
				.inflate(R.layout.activity_dialog_location, null, false);
		
		labelLocationName = (TextView) dialogView.findViewById(R.id.labelLocationName);
		labelLocationDescription = (TextView) dialogView.findViewById(R.id.labelLocationDescription);
		labelLocationImage = (ImageView) dialogView.findViewById(R.id.labelLocationImage);
		btnStaticPrev = (ImageButton) dialogView.findViewById(R.id.btnStaticPrev);
		btnStaticNext = (ImageButton) dialogView.findViewById(R.id.btnStaticNext);
		//progress = (ProgressBar) dialogView.findViewById(R.id.progressBar1);
		
		labelLocationName.setText(name);
		labelLocationDescription.setText(description);
		
		if(img_bitmap != null){
			labelLocationImage.setImageBitmap(img_bitmap);
		}else{
			Toast.makeText(_activity, "Error decode bitmap", Toast.LENGTH_SHORT).show();
		}

		
		btnStaticPrev.setVisibility(View.INVISIBLE);
		btnStaticNext.setVisibility(View.INVISIBLE);
		
		btnStaticNext.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				nextShow(finalLocation);
				
			}
			
		});
		
		btnStaticPrev.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				currentSize -= 1;
				
				/**switch(finalLocation){
				case "office":
					
					labelLocationImage.setImageResource(imageAOffice.get(currentSize));
					slideTimer(finalLocation);
					
				case "lectureRoom1":
					
					labelLocationImage.setImageResource(imageABK1.get(currentSize));
					slideTimer(finalLocation);
					
				case "lectureRoom3":
					
					labelLocationImage.setImageResource(imageABK3.get(currentSize));
					slideTimer(finalLocation);
					
				case "foyer":
					
					labelLocationImage.setImageResource(imageAFoyer.get(currentSize));
					slideTimer(finalLocation);
					
				case "softwareEngineeringLab":
					
					labelLocationImage.setImageResource(imageASLE.get(currentSize));
					slideTimer(finalLocation);
					
					default:
						Toast.makeText(_activity, "No image available", Toast.LENGTH_LONG).show();
				}**/
				//slideTimer(finalLocation);
			}
			
		});
		
		
		
		
		final AlertDialog alertInfo = new AlertDialog.Builder(_activity).create();
		alertInfo.setTitle("Info of " + finalLocation);
		alertInfo.setView(dialogView);
		alertInfo.setButton("Ok", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			 		
			
					
					
		}

		
		});
		alertInfo.show();
		
		
		labelLocationImage.setOnTouchListener(new ImageView.OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				alertInfo.dismiss();
				generateImageDialog();
				return false;
			}
			
		});
		
		
		
		

	}
	
	
@SuppressWarnings("deprecation")
protected void generateImageDialog() {

	View dialogView = _activity.getLayoutInflater()
			.inflate(R.layout.activity_dialog_image, null, false);
	
	
	bigImage = (ImageView) dialogView.findViewById(R.id.bigImage);
	
	//progress = (ProgressBar) dialogView.findViewById(R.id.progressBar1);
	
	
	if(img_bitmap != null){
		bigImage.setImageBitmap(img_bitmap);
	}else{
		Toast.makeText(_activity, "Error decode bitmap", Toast.LENGTH_SHORT).show();
	}

	
	final AlertDialog alertImage = new AlertDialog.Builder(_activity).create();
	alertImage.setTitle("Info of " + finalLocation);
	alertImage.setView(dialogView);
	alertImage.setButton("Ok", new DialogInterface.OnClickListener() {
	public void onClick(DialogInterface dialog, int which) {
		 		alertImage.dismiss();
		 		
		 		generateDialog(name, description);
		
				
				
	}

	
	});
	alertImage.show();
	
	

}


private void slideTimer(final String finalLocation){
		
		if(intDuration == 0){
			intDuration = 10;
		}
		
		myHandler = new Handler();
		myRunner = new Runnable() {
	            @Override
	            public void run() {
	          	 
	            	nextShow(finalLocation);
	          	 
	            }
	        };
		try {
	            myHandler.postDelayed(myRunner, intDuration * 1000);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		

	}

		protected void nextShow(String finalLocation) {
			// TODO Auto-generated method stub
			
			
			if(currentSize == imageSize - 1){
				//loadImageForLocation(finalLocation);
			}else{
				sizeController(currentSize);
				Log.i("Current Size", "Current Size = " + currentSize);
				//labelLocationImage.setImageResource(imageAOffice.get(currentSize));
				slideTimer(finalLocation);
				
			}
			
			
			
		}


	private void sizeController(int lastSize) {
		lastSize++;
		currentSize = lastSize;
		
		}

	
	
	
	 class LoadLocation extends AsyncTask<String, String, String> {
		 
	        /**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            progress = new ProgressDialog(_activity);
	            progress.setMessage("Loading ... ....");
	            progress.setIndeterminate(false);
	            progress.setCancelable(false);
	            progress.show();
	        }
	 
	        /**
	         * getting All products from url
	         * */
	        protected String doInBackground(String... args) {
	            // Building Parameters
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("location_name", finalLocation));
	            // getting JSON string from URL
	            JSONObject json = jparser.makeHttpRequest(locationURL, "POST", params);
	 
	            // Check your log cat for JSON reponse
	            Log.d("All location: ", json.toString());
	 
	            try {
	                // Checking for SUCCESS TAG
	                int success = json.getInt(KEY_SUCCESS);
	 
	                if (success == 1) {
	                    // products found
	                    // Getting Array of Products
	                    location = json.getJSONArray(KEY_LOCATION);
	 
	                    // looping through All Products
	                    for (int i = 0; i < location.length(); i++) {
	                        JSONObject c = location.getJSONObject(i);
	 
	                        // Storing each json item in variable
	                         id = c.getString(KEY_ID);
	                         name = c.getString(KEY_DISPLAY_NAME);
	                         description = c.getString(KEY_DESCRIPTION);
	                        
	                        Log.i("id", id);
	                        Log.i("name", name);
	                        Log.i("desc", description);
	 
	                        
	                    }
	                } else {
	                   
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	 
	            return null;
	        }
	 
	        /**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog after getting all products
	            progress.dismiss();
	            // updating UI from Background Thread
	            _activity.runOnUiThread(new Runnable() {
	                public void run() {
	                   //generateDialog(name, description);
	                	new LoadImage().execute();
	                }
	            });
	 
	        }
	 
	 }
	 
	 class LoadImage extends AsyncTask<String, String, String> {
		 
	       

			/**
	         * Before starting background thread Show Progress Dialog
	         * */
	        @Override
	        protected void onPreExecute() {
	            super.onPreExecute();
	            progress = new ProgressDialog(_activity);
	            progress.setMessage("Loading image ... ....");
	            progress.setIndeterminate(false);
	            progress.setCancelable(false);
	            progress.show();
	        }
	 
	        /**
	         * getting All products from url
	         * */
	        protected String doInBackground(String... args) {
	            // Building Parameters
	            List<NameValuePair> params = new ArrayList<NameValuePair>();
	            params.add(new BasicNameValuePair("lid", id));
	            // getting JSON string from URL
	            JSONObject json = jparser.makeHttpRequest(imageURL, "POST", params);
	 
	            // Check your log cat for JSON reponse
	            Log.d("All location: ", json.toString());
	 
	            try {
	                // Checking for SUCCESS TAG
	                int success = json.getInt(KEY_SUCCESS);
	 
	                if (success == 1) {
	                    // products found
	                    // Getting Array of Products
	                    images = json.getJSONArray(KEY_IMAGES);
	 
	                    // looping through All Products
	                    for (int i = 0; i < images.length(); i++) {
	                        JSONObject c = images.getJSONObject(i);
	 
	                        // Storing each json item in variable
	                         eid = c.getString(KEY_EID);
	                         path = c.getString(KEY_PATH);
	                         
	                        
	                        Log.i("eid", eid);
	                        Log.i("path", path);
	                       
	 
	                        
	                    }
	                } else {
	                   
	                }
	            } catch (JSONException e) {
	                e.printStackTrace();
	            }
	            
	            try{
	                byte[] imageAsBytes = Base64.decode(path, Base64.DEFAULT);
	                img_bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);

	                /**FileOutputStream fos = openFileOutput(name, Context.MODE_WORLD_READABLE);

	                img_bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
	                fos.flush();
	                fos.close();*/
	                
	                //labelLocationImage.setImageBitmap(img_bitmap);
	      

	            }
	            catch(Exception e){
	                e.printStackTrace();
	            }
	 
	            return null;
	        }
	 
	        /**
	         * After completing background task Dismiss the progress dialog
	         * **/
	        protected void onPostExecute(String file_url) {
	            // dismiss the dialog after getting all products
	            progress.dismiss();
	            // updating UI from Background Thread
	            _activity.runOnUiThread(new Runnable() {
	                public void run() {
	                   generateDialog(name, description);
	                }
	            });
	 
	        }
	 
	 }
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
  
        img_bitmap.recycle();
		img_bitmap = null;
    }
}