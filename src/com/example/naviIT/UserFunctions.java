package com.example.naviIT;

import java.util.ArrayList;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

public class UserFunctions {
	
private LoginParser jsonParser;
	
	private static String loginURL = "http://navifsktm.comule.com/admin_login.php";
	
	 private static String login_tag = "login";
	
	// constructor
	public UserFunctions(){
		jsonParser = new LoginParser();
	}
	
	//function for user to login
	//the tag is important to identify whether the user has login successfully
	public JSONObject loginUser(String username, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        // Log.e("JSON", json.toString());
        return json;
    }
     
	
	
	
	
	//check if there are row in sqlite database
	//if not user will be redirect to login page
	//else user can proceed to the portal
	public boolean isUserLoggedIn(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user logged in
			return true;
		}
		return false;
	}
	
	//this is logout user function
	//call the resetTables function from DatabaseHandler to delete the data of user
	public boolean logoutUser(Context context){
		DatabaseHandler db = new DatabaseHandler(context);
		db.resetTables();
		return true;
	}
	
}
