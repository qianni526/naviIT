package com.example.naviIT;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.naviIT.FragmentEvent.connectDB;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ToggleButton;


public class FragmentFriendsMap_Timeline extends Fragment implements OnItemClickListener{
	
	ListView listView1;
	ArrayList<Post> posts;
	MyPostCustomAdapter adapter;
	JSONArray jArray;
	JSONObject postItem;
	ProgressDialog pd;
	EditText inputSearch;
	Button btCheckIn;
	ImageButton clearText;
	ToggleButton btSearch;
	FrameLayout searchLayout;
	LinearLayout bottomButtonLayout, listViewLayout;
	
     @Override
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
          Bundle savedInstanceState) {
          
    	 setHasOptionsMenu(true);
    	 
	  	  View view = inflater.inflate(R.layout.fragfriendsmap_timeline, container, false);
	  	  
		  posts = new ArrayList<Post>();
	  	  listView1 = (ListView) view.findViewById(R.id.listView1);
	  	  inputSearch = (EditText) view.findViewById(R.id.inputSearch);
	  	  clearText = (ImageButton) view.findViewById(R.id.clear_txt);
	  	  btSearch = (ToggleButton) view.findViewById(R.id.btSearch);
	  	  btCheckIn = (Button) view.findViewById(R.id.btCheckin);
	  	  searchLayout = (FrameLayout) view.findViewById(R.id.searchLayout);
	  	  bottomButtonLayout = (LinearLayout) view.findViewById(R.id.bottomButtonLayout);
	  	  listViewLayout = (LinearLayout) view.findViewById(R.id.listViewLayout);
	  	  
		  	clearText.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	               inputSearch.setText("");
	            }
	        });  
	  	  
		  	btCheckIn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent= new Intent(getActivity(), SearchPlacesInCheckIn.class);
					//startActivity(intent);
					startActivityForResult(intent, 0);
				}
			});
		  	
		  	btSearch.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					 if(btSearch.isChecked()){
			                //Button is ON
						 searchLayout.setVisibility(View.VISIBLE);
							
							 LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bottomButtonLayout.getLayoutParams();
							 params.weight = 0.8f;
							 bottomButtonLayout.setLayoutParams(params);
							 
							 LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) listViewLayout.getLayoutParams();
							 params.weight = 0.47f;
							 listViewLayout.setLayoutParams(params2); 
			            }
			            else{
			            //Button is OFF
			            	inputSearch.setText("");
			            	
			            	searchLayout.setVisibility(View.GONE);
							
							LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) bottomButtonLayout.getLayoutParams();
							 params.weight = 0.9f;
							 bottomButtonLayout.setLayoutParams(params);
							 
							 LinearLayout.LayoutParams params2 = (LinearLayout.LayoutParams) listViewLayout.getLayoutParams();
							 params.weight = 0.9f;
							 listViewLayout.setLayoutParams(params2); 
			            	
			            	  
			        }
				}
			});
		  	
		  	 listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub

					}
		        	
		        });   
		  	 
		  	connectDB connect = new connectDB();
			connect.execute();
			Log.d("Message", "connect db");
			
          return view;
  	  }
     
     @Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
    	 
         menu.findItem(R.id.action_login).setVisible(false);
         menu.findItem(R.id.action_refresh).setVisible(true);
         
         super.onCreateOptionsMenu(menu, inflater);
 		 
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
    	 
    	 switch (item.getItemId()) {
         case R.id.action_login:
         	//newLoginIntent();
         	openLoginPage();
         	return false;
         case R.id.action_refresh:
        	 refresh();
        	 return true;
         default:
             return super.onOptionsItemSelected(item);
     }
	}

     public void openLoginPage(){
 		getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new FragmentLogin(), "Login").commit();
 	} 
     
	public void onActivityResult(int requestCode, int resultCode, Intent data){
   		super.onActivityResult(requestCode, resultCode, data);
   		if(requestCode == 0 || requestCode == 1){
   			if(resultCode == Activity.RESULT_OK){
   				refresh();
   			}
   		}
   		
   	}
     
     public void refresh(){
 		posts.clear();
 		
 		connectDB connect = new connectDB();
 		connect.execute();
 		
 	}

     
     class connectDB extends AsyncTask<Void, Void, Void> {

 		@Override
 		protected void onPreExecute() {
 			// TODO Auto-generated method stub
 			super.onPreExecute();
 			pd = ProgressDialog.show(getActivity(), "", "Loading...");
 		}

 		@Override
 		protected Void doInBackground(Void... params) {
 			// TODO Auto-generated method stub
 			JSONParser jsonparser = new JSONParser();
 			List<NameValuePair> list = new ArrayList<NameValuePair>();
 			
 			//JSONObject jObject = jsonparser.makeHttpRequest("http://10.0.2.2/login/event.php", "GET", list);
 			JSONObject jObject = jsonparser.makeHttpRequest("http://naviit.webuda.com/post.php", "GET", list);
 			Log.d("JSON respond", jObject.toString());
 			
 			try {
 				if (jObject.getString("status").equals("success")) {
 					jArray = jObject.getJSONArray("posts");
 					for(int i=0; i<jArray.length(); i++){
 						postItem = jArray.getJSONObject(i);
 						posts.add(new Post(postItem.getString("postId"),postItem.getString("userId"),postItem.getString("username"),postItem.getString("timestamp"),postItem.getString("room"),postItem.getString("content"),postItem.getString("regionId")));					
 					}
 				}
 			} catch (JSONException e) {
 				// TODO Auto-generated catch block
 				Log.d("Message","status is fail");
 				e.printStackTrace();
 			}

 			return null;
 		}

 		@Override
 		protected void onPostExecute(Void result) {

 			 if ((pd != null) && pd.isShowing()) { 
 				pd.dismiss();
 		   }
 			
 			adapter = new MyPostCustomAdapter(getActivity(), posts);
 			listView1.setAdapter(adapter);
 			
 			 inputSearch.addTextChangedListener(new TextWatcher() {
		            
		            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
		                // When user changed the Text
		            	adapter.getFilter().filter(cs);   
		            }
		             
		            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
		                // TODO Auto-generated method stub                
		            }
		             
		            public void afterTextChanged(Editable arg0) {
		                // TODO Auto-generated method stub                          
		            }
		        });
 			
 		}
 	}
     
	  @Override
	  public void onCreate(Bundle savedInstanceState)
	  {
	      super.onCreate(savedInstanceState);
	      setHasOptionsMenu(true);
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
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if ((pd != null) && pd.isShowing())
			pd.dismiss();
		pd = null;
	}

	@Override
	  public void onResume()
	  {
	      super.onResume();
	  }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}