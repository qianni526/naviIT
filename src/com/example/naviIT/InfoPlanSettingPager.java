package com.example.naviIT;


import java.io.BufferedReader;

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
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class InfoPlanSettingPager extends PagerAdapter {
 
    private Activity _activity;
    private ArrayList<Integer> imageLocationA;
    private LayoutInflater inflater;
    private String locationName;
    
    int valueX, valueY;
    int finalPosition;
   
    private static String KEY_SUCCESS = "success";
	private static String KEY_ID = "lid";
	private static String KEY_NAME = "location_name";
	private static String KEY_DISPLAY_NAME = "display_location_name";
	private static String KEY_DESCRIPTION = "location_description";
	private static String KEY_LOCATION ="location";
	
	private String location_name, location_display_name, location_description;
	private String id, name, description; //final 
	
	private JSONArray location;
	
	JSONParser jparser = new JSONParser();
	private ProgressDialog progress;
	
	private static String locationURL = "http://navifsktm.comule.com/get_location_details.php";
	private static String updateURL = "http://navifsktm.comule.com/update_location_info.php";
	
	EditText editLName, editLDesc;
    ImageView editLImage;
    Button btnSaveInfo, btnUploadNewImage;
    ImageButton btnNext, btnPrev;
    
    int imageSize;
    int currentSize;
	
    Handler myHandler;
	Runnable myRunner;
	int intDuration;
	
	JSONParser jsonParser = new JSONParser();
	
	private ProgressDialog pDialog;
    
    public InfoPlanSettingPager(Activity activity, String locationName) {
        this._activity = activity;
        this.location_name = locationName;
        imageLocationA = new ArrayList(100);
        imageLocationA.add(R.drawable.a_office_re);
        imageLocationA.add(R.drawable.a_office1_re);
    }
 
    @Override
    public int getCount() {
    	return 1;
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ScrollView) object);
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
    	
       
    	
        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.fragment_eighth, container, false);
        
        
        Log.i("location name in info", "location name = " + location_name);
  
        editLName = (EditText) viewLayout.findViewById(R.id.editLName);
        editLDesc = (EditText) viewLayout.findViewById(R.id.editLDesc);
        editLImage = (ImageView) viewLayout.findViewById(R.id.editLImage);
        btnSaveInfo = (Button) viewLayout.findViewById(R.id.btnSaveLInfo);
        btnNext = (ImageButton) viewLayout.findViewById(R.id.btnNext);
        btnPrev = (ImageButton) viewLayout.findViewById(R.id.btnPrev);
        //btnUploadNewImage = (Button) viewLayout.findViewById(R.id.btnUploadNewImage);
        
        new LoadLocation().execute();
        
        loadImageForLocation();
        
        
        btnNext.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				nextShow();
				
			}
        	
        });
        
        btnPrev.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				currentSize -= 1;
				editLImage.setImageResource(imageLocationA.get(currentSize));
				slideTimer();
			}
        	
        });
        
        btnSaveInfo.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				new UpdateLocationDetails().execute();
				//new LoadLocation().execute();
			}
        	
        });
         
       
        
  
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
    }
    

    
    	
    	class UpdateLocationDetails extends AsyncTask<String, String, String> {
    		 
            /**
             * Before starting background thread Show Progress Dialog
             * */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pDialog = new ProgressDialog(_activity);
                pDialog.setMessage("Saving location... ...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
            }
     
            /**
             * Saving product
             * */
            protected String doInBackground(String... args) {
     
            	String name = editLName.getText().toString();
        		String desc = editLDesc.getText().toString();
        		
        		List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("display_location_name", name));
                params.add(new BasicNameValuePair("location_description", desc));
                params.add(new BasicNameValuePair("lid", id));

                // sending modified data through http request
                // Notice that update product url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(updateURL, "POST", params);

                // check json success tag
                try {
                    int success = json.getInt(KEY_SUCCESS);

                    if (success == 1) {
                       //Toast.makeText(_activity, "Update successfully.", Toast.LENGTH_SHORT).show();
                    	//progress.dismiss();
                    } else {
                       //Toast.makeText(_activity, "Failed to update.", Toast.LENGTH_SHORT).show();
                    	Log.e("Failed to update", "Failed to update");
                    	//progress.dismiss();
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
	        	pDialog.dismiss();
	            // updating UI from Background Thread
	            _activity.runOnUiThread(new Runnable() {
	                public void run() {
	                   //generateDialog(name, description);
	                	//new LoadLocation().execute();
	                }
	            });
	 
	        }
    	}
	

	private void alertUser() {
		// TODO Auto-generated method stub
    	new AlertDialog.Builder(_activity)
		.setTitle("Exit without saving?")
		.setPositiveButton("Exit", new DialogInterface.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				
				_activity.finish();
			}
			
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				
			}
		}).show();

	}

     
   
	private void loadImageForLocation() {
		imageSize = imageLocationA.size();
		
		currentSize = 0;
		
		editLImage.setImageResource(imageLocationA.get(currentSize));
		
		//sizeController(currentSize);
		slideTimer();
		
			
			
	}
	
private void slideTimer(){
		
		if(intDuration == 0){
			intDuration = 10;
		}
		
		myHandler = new Handler();
		myRunner = new Runnable() {
	            @Override
	            public void run() {
	          	 
	            	nextShow();
	          	 
	            }
	        };
		try {
	            myHandler.postDelayed(myRunner, intDuration * 1000);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		

	}

		protected void nextShow() {
			// TODO Auto-generated method stub
			
			
			if(currentSize == imageSize - 1){
				loadImageForLocation();
			}else{
				sizeController(currentSize);
				Log.i("Current Size", "Current Size = " + currentSize);
				editLImage.setImageResource(imageLocationA.get(currentSize));
				slideTimer();
				
			}
			
			
			
		}


	


	private void sizeController(int lastSize) {
		lastSize++;
		currentSize = lastSize;
		
		}





	/**protected void generateDialog(String name, String description) {
		
		//loadData(location);
		//loadLocation();
		
		View dialogView = _activity.getLayoutInflater()
				.inflate(R.layout.activity_dialog_location, null, false);
		
		TextView labelLocationName = (TextView) dialogView.findViewById(R.id.labelLocationName);
		TextView labelLocationDescription = (TextView) dialogView.findViewById(R.id.labelLocationDescription);
		ImageView labelLocationImage = (ImageView) dialogView.findViewById(R.id.labelLocationImage);
		//progress = (ProgressBar) dialogView.findViewById(R.id.progressBar1);
		
		labelLocationName.setText(name);
		labelLocationDescription.setText(description);
		labelLocationImage.setBackgroundResource(R.drawable.images_3);
		
		new AlertDialog.Builder(_activity)
		.setTitle("Info of " + finalLocation)
		.setView(dialogView)
		.setPositiveButton("Ok",new DialogInterface.OnClickListener() {

		public void onClick(DialogInterface dialog, int which) {
			
					
					
		}

		
		})
		.show();

	}*/
	
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
	            params.add(new BasicNameValuePair("location_name", location_name));
	            // getting JSON string from URL
	            JSONObject json = jparser.makeHttpRequest(locationURL, "POST", params);
	 
	            // Check your log cat for JSON reponse
	            Log.d("All locations: ", json.toString());
	 
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
	                        
	                        Log.i("tester", "Testing~~~~");
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
	                	editLName.setText(name);
	                    editLDesc.setText(description);
	                }
	            });
	 
	        }
	 
	 }
	
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
  
    }
}