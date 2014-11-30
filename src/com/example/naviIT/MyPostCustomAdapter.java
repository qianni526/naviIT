package com.example.naviIT;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.Toast;

public class MyPostCustomAdapter extends BaseAdapter implements Filterable {
    
	private Activity activity;
	ArrayList<Post> data;
	ArrayList<Post> ori; 
	static ArrayList<Post> FilteredArrList;
	SessionManager session;
    
    public MyPostCustomAdapter(Activity a, ArrayList<Post> d) {
        activity = a;
        data=d;
        ori=d;
    } 

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }
    
    public long getItemId(int position) {
        return position;
    }
    
    private class ViewHolder {
        ImageView profilePic;
        TextView username;
        TextView timestamp;
        TextView content;
        Button btVenue;
        Button btNavigate;
        ImageButton btEdit;
    }
    
    @Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		 ViewHolder holder = null;

	        if (convertView == null) {
	        LayoutInflater vi = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        	   convertView = vi.inflate(R.layout.timeline_list_item, null);

	        	   holder = new ViewHolder();
	        	   holder.profilePic = (ImageView) convertView.findViewById(R.id.profile_pic);
	        	   holder.username = (TextView) convertView.findViewById(R.id.tvUsername);
	        	   holder.timestamp = (TextView) convertView.findViewById(R.id.tvTimestamp);
	        	   holder.content = (TextView) convertView.findViewById(R.id.tvContent);
	        	   holder.btVenue = (Button) convertView.findViewById(R.id.btVenue);
	        	   holder.btNavigate = (Button) convertView.findViewById(R.id.btNavigate);
	        	   holder.btEdit = (ImageButton) convertView.findViewById(R.id.btEdit);
	        	   
	        	   convertView.setTag(holder);    
	        	   
	        		   } 
	        		   else {
	        		    holder = (ViewHolder) convertView.getTag();
	        		   }
        		   
	        		   final Post post = data.get(position);    	        		
	        		   
	        		   holder.profilePic.setImageResource(R.drawable.ic_action_person);
	        		   holder.username.setText(post.getUsername());
	        		   holder.timestamp.setText(post.getTimeStamp());
	        		   
	        		   holder.content.setText(post.getContent());
	        		   holder.btVenue.setText(post.getroom());
	        		   
	        		   holder.btVenue.setOnClickListener(new View.OnClickListener() {
	   					
							@Override
							public void onClick(View v) {
								// on click, show user in map with location
								Toast.makeText(activity, "btVenue for "+position+" is clicked.", Toast.LENGTH_LONG).show();
							}
		        	   });
		        	   
		        	   holder.btNavigate.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// on click, set as destination and go to navigation page 
								Toast.makeText(activity, "btNavigate "+position+" is clicked.", Toast.LENGTH_LONG).show();
						
							}
		        	   });	    
	        		   
		        	   
		        	  session = new SessionManager(activity.getApplicationContext());
		        	  String userId = Integer.toString(session.getUserId());
		        	  
		             if(post.getUserId().equals(userId)){
		            	  Log.d("edit visibility", "post's userId: "+post.getUserId()+", current userId = "+userId);
		            	  holder.btEdit.setVisibility(View.VISIBLE);
		              }else{
		            	  Log.d("edit visibility", "post's userId: "+post.getUserId()+", current userId = "+userId);
		            	  holder.btEdit.setVisibility(View.INVISIBLE);
		              }
		        	 
		              holder.btEdit.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								EditIntent(post);
							}
						});
		        	   
	        return convertView;
	}

    public void EditIntent(Post e){
		Intent editIntent = new Intent(activity, EditCheckInActivity.class);
		//editIntent.putExtra("data", new DataWrapper(events));
		Bundle b = new Bundle();
		b.putString("postId", e.getPostId());
		b.putString("etContent", e.getContent());
		b.putString("btRoom", e.getroom());
		b.putString("regionId", e.getRegionId());
		editIntent.putExtras(b);
		activity.startActivityForResult(editIntent, 1);
	}
    
    @Override
    public Filter getFilter() {
    	Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                data = (ArrayList<Post>) results.values; // has the filtered values
                if (data==null){
                	
                	data=new ArrayList<Post>();
                }
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                FilteredArrList = new ArrayList<Post>();

                if (ori == null) {
                    ori = new ArrayList<Post>(data); // saves the original data in mOriginalValues
                }

                /********
                 * 
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)  
                 *
                 ********/
               if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return  
                    results.count = ori.size();
                    results.values = ori;
                    
                }else {
                    String searchText = constraint.toString().toLowerCase();
                    for (int i = 0; i < ori.size(); i++) {
                    	Post data1 = ori.get(i);
                    	//Log.v("fdfd",String.valueOf(constraint));
                    	//Log.v("value",String.valueOf(data1.get("ATT_NAME")));

                        if (data1.getroom().toLowerCase().indexOf(searchText) > -1 || data1.getContent().toLowerCase().indexOf(searchText) > -1 || 
                        		data1.getUsername().toLowerCase().indexOf(searchText)>-1) {
                            FilteredArrList.add(data1);
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
	
	}
