package com.example.naviIT;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFriendListAdapter extends BaseAdapter implements Filterable {
	
	private Activity activity;
	ArrayList<User> data;
	ArrayList<User> ori; 
	static ArrayList<User> FilteredArrList;
  
    public MyFriendListAdapter(Activity a, ArrayList<User> d) {
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
        ImageView profile_pic;
    	TextView tvUsername;
    }
    
    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 ViewHolder holder = null;

	        if (convertView == null) {
	        LayoutInflater vi = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        	   convertView = vi.inflate(R.layout.friend_list_item, null);

	        	   holder = new ViewHolder();
	        	   holder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
	        	   holder.profile_pic = (ImageView) convertView.findViewById(R.id.profile_pic);
	        	   convertView.setTag(holder);    
	        	   
	        		   } 
	        		   else {
	        		    holder = (ViewHolder) convertView.getTag();
	        		   }
        		   
	        		   User user = data.get(position);    	        		
	        		   holder.tvUsername.setText(user.getUserName());
	        		   holder.profile_pic.setImageResource(R.drawable.ic_action_person);
	        		   

	        return convertView;
	    
	}

    @Override
    public Filter getFilter() {
    	Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                data = (ArrayList<User>) results.values; // has the filtered values
                if (data==null){
                	
                	data=new ArrayList<User>();
                }
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                FilteredArrList = new ArrayList<User>();

                if (ori == null) {
                    ori = new ArrayList<User>(data); // saves the original data in mOriginalValues
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
                    
                } else {
                    String searchText = constraint.toString().toLowerCase();
                    for (int i = 0; i < ori.size(); i++) {
                    	User data1 = ori.get(i);
                    	//Log.v("fdfd",String.valueOf(constraint));
                    	//Log.v("value",String.valueOf(data1.get("ATT_NAME")));

                        if (data1.getUserName().toLowerCase().indexOf(searchText) > -1 ) {
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
